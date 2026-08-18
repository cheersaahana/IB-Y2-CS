
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String FILENAME = "tasks.db";
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + FILENAME);
            DatabaseManager.createTables(connection);
        } catch (SQLException oops) {
            System.out.println("Failed to connect to database");
            oops.printStackTrace();
            return;
        }

        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInput(scanner);

            if (choice == 1) {
                System.out.println("-> Add task selected");
            } else if (choice == 2) {
                System.out.println("-> View All Tasks selected");
            } else if (choice == 3) {
                System.out.println("View Tasks by Subject selected");
            } else if (choice == 4) {
                System.out.println("-> Edit Task selected");
            } else if (choice == 5) {
                System.out.println("-> Delete Task selected");
            } else if (choice == 6) {
                System.out.println("-> Mark Task as Complete selected");
            } else if (choice == 7) {
                System.out.println("Byeee!");
                running = false;
            }
        }

        scanner.close();

        try {
            connection.close();
        } catch (SQLException oops) {
            oops.printStackTrace();
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("=== Task Prioritizer ===");
        System.out.println("1. Add Task");
        System.out.println("2. View All Tasks (Priority-Based Ranking)");
        System.out.println("3. View Tasks by Subject");
        System.out.println("4. Edit Task");
        System.out.println("5. Delete Task");
        System.out.println("6. Mark Task as Complete");
        System.out.println("7. Exit");
        System.out.println("Enter choice:");
    }

    private static int readInput(Scanner scanner) {
        while (true) { 
            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input.trim());
                if (choice >= 1 && choice <= 7) {
                    return choice;
                } else {
                    System.out.println("Please enter a number (1-7): ");
                }
            } catch (NumberFormatException oops) {
                System.out.println("Invalid input - please enter a number (1-7): ");
            }
        }
    }

    private static void handleAddTask(Scanner scanner, Connection connection) {
        System.out.println();
        System.out.println("--- ADD TASK ---");

        System.out.print("Title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Subject: ");
        String subject = scanner.nextLine().trim();

        LocalDate deadline = readDeadline(scanner);

        System.out.print("Number of subtasks (0 if none): ");
        int subtaskCount = readNonNegativeInt(scanner);

        double manualWorkload = 0.0;
        if (subtaskCount == 0) {
            System.out.print("Estimated workload (hours): ");
            manualWorkload = readPositiveDouble(scanner);
        }

        Task task = new Task(title, subject, deadline, manualWorkload);

        for (int i = 1; i<= subtaskCount; i++) {
            System.out.print("Subtask " + i + " title: ");
            String subtaskTitle = scanner.nextLine().trim();

            System.out.print("Subtask " + i + " workload (hours): ");
            double subtaskWorkload = readPositiveDouble(scanner);

            task.addSubtask(new Subtask(subtaskTitle, subtaskWorkload));
        }

        try {
            DatabaseManager.addTask(connection, task);
            System.out.println("Task saved successfully.");
        } catch (SQLException oops) {
            System.out.println("Failed to save tasks");
            oops.printStackTrace();
        }
    }

    private static LocalDate readDeadline (Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (true) {
            System.out.println("Deadline (DD-MM-YYYY): ");
            String input = scanner.nextLine().trim();

            if (PriorityManager.validateDeadline(input)) {
                return LocalDate.parse(input, formatter);
            } else {
                System.out.println("Invalid date - please use DD-MM-YYYY format.");
            }
        }
    }

    private static int readNonNegativeInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);
                if (value >= 0) {
                    return value;
                } else {
                    System.out.println("Please enter a number 0 or greater: ");
                }
            } catch (NumberFormatException oops) {
                System.out.print("Invalid input - please enter a whole number greater than 0");
            }
        }
    }

    private static double readPositiveDouble(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();

            try {
                double value = Double.parseDouble(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Please enter a number greater than 0: ");
                }
            } catch (NumberFormatException oops) {
                System.out.println("Invalid input - please enter a number greater than 0: ");
            }
        }
    }
}