
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    public static void createTables(Connection db) throws SQLException {
        Statement statement = db.createStatement();
        String taskTable = 
            "CREATE TABLE IF NOT EXISTS tasks(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "title TEXT NOT NULL, " +
            "subject TEXT NOT NULL, " +
            "deadline TEXT NOT NULL, " +
            "manualWorkload REAL NOT NULL, " +
            "completed INTEGER NOT NULL" +
            ");";
        statement.execute(taskTable);
        String subtaskTable = 
            "CREATE TABLE IF NOT EXISTS subtasks(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "parentId INTEGER NOT NULL, " +
            "title TEXT NOT NULL, " +
            "workload REAL NOT NULL, " +
            "completed INTEGER NOT NULL, " +
            "FOREIGN KEY(parentId) REFERENCES tasks(id)" +
            ");";
        statement.execute(subtaskTable);
        statement.close();
    }

    public static void addTask(Connection db, Task task) throws SQLException {
        String sql = 
            "INSERT INTO tasks(title, subject, deadline, manualWorkload, completed) " +
            "VALUES(?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet keys = null;

        try {
            statement = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, task.getTitle());
            statement.setString(2, task.getSubject());
            statement.setString(3, task.getDeadline().toString());
            statement.setDouble(4, task.getManualWorkload());
            statement.setBoolean(5, task.isCompleted());
            
            statement.executeUpdate();

            keys = statement.getGeneratedKeys();
            if (keys.next()) {
                task.setId(keys.getInt(1));
            }
        } finally {
            if (keys != null) {
                keys.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        addSubtasks(db, task);
    }

    public static List<Task> loadTasks(Connection db) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";

        try (PreparedStatement statement = db.prepareStatement(sql); ResultSet results = statement.executeQuery()) {
            while (results.next()) {
                String title = results.getString("title");
                String subject = results.getString("subject");
                LocalDate deadline = LocalDate.parse(results.getString("deadline"));
                double manualWorkload = results.getDouble("manualWorkload");
                boolean completed = results.getBoolean("completed");
                int id = results.getInt("id");

                Task task = new Task(title, subject, deadline, manualWorkload);
                task.setId(id);
                task.setCompleted(completed);

                List<Subtask> subtasks = loadSubtasks(db, id);
                for (int i = 0; i < subtasks.size(); i++) {
                    Subtask subtask = subtasks.get(i);
                    task.addSubtask(subtask);
                }

                tasks.add(task);
            }
        }
        return tasks;
    }

    private static void addSubtasks(Connection db, Task task) throws SQLException {
        String sql = "INSERT INTO subtasks(parentId, title, workload, completed) " +
            "VALUES(?, ?, ?, ?)";

        try (PreparedStatement statement = db.prepareStatement(sql)) {
            for (int i = 0; i < task.getSubtasks().size(); i++) {
                Subtask subtask = task.getSubtasks().get(i);
                statement.setInt(1, task.getId());
                statement.setString(2, subtask.getTitle());
                statement.setDouble(3, subtask.getWorkload());
                statement.setBoolean(4, subtask.isCompleted());
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    private static List<Subtask> loadSubtasks(Connection db, int parentId) throws SQLException {
        List<Subtask> subtasks = new ArrayList<>();
        String sql = "SELECT * FROM subtasks WHERE parentId = ?";

        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setInt(1, parentId);

            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    String title = results.getString("title");
                    double workload = results.getDouble("workload");
                    boolean completed = results.getBoolean("completed");
                    int id = results.getInt("id");

                    Subtask subtask = new Subtask(title, workload);
                    subtask.setId(id);
                    subtask.setParentId(parentId);
                    subtask.setCompleted(completed);

                    subtasks.add(subtask);
                }
            }
        }
        return subtasks;
    }

    public static void deleteTask(Connection db, int id) throws SQLException {
        deleteSubtasksByParentId(db, id);

        String sql = "DELETE FROM tasks WHERE id = ?";

        PreparedStatement statement = null;

        try {
            statement = db.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    private static void deleteSubtasksByParentId(Connection db, int parentId) throws SQLException {
        String sql = "DELETE FROM subtasks WHERE parentId = ?";

        PreparedStatement statement = null;

        try {
            statement = db.prepareStatement(sql);
            statement.setInt(1, parentId);
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static void updateTask(Connection db, Task task) throws SQLException {
        String sql = "UPDATE tasks SET title = ?, subject = ?, deadline = ?, manualWorkload = ?, completed = ?" +
            "WHERE id = ?";
        
        PreparedStatement statement = null;

        try {
            statement = db.prepareStatement(sql);
            statement.setString(1, task.getTitle());
            statement.setString(2, task.getSubject());
            statement.setString(3, task.getDeadline().toString());
            statement.setDouble(4, task.getManualWorkload());
            statement.setBoolean(5, task.isCompleted());
            statement.setInt(6, task.getId());
            
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }

        deleteSubtasksByParentId(db, task.getId());
        addSubtasks(db, task);
    }
}