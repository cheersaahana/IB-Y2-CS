import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

public class PriorityManager {
    private static final double TAU = 10.0; //urgency decay rate in days
    private static final double MAX_WORKLOAD = 15.0; //in hours
    private static final double MAX_SUBTASKS = 15.0; //no research to back up, just based on personal experience
    private static final double WORKLOAD_WEIGHT = 0.35;
    private static final double URGENCY_WEIGHT = 0.35;
    private static final double SUBTASK_WEIGHT = 0.30;
    private static final double FEASIBILITY_THRESHOLD = 4.0; //in hours per day

    
    public static double calculatePriority(Task task) {
        if (task.isCompleted()) {
            task.setPriorityScore(0.0);
            return 0.0;
        }

        long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), task.getDeadline());
        task.setOverdue(daysRemaining < 0);

        double effectiveDays = Math.max(daysRemaining, 0);
        double urgencyNorm = Math.exp(-effectiveDays / TAU);

        double workloadNorm = Math.min(task.getWorkload() / MAX_WORKLOAD, 1.0);
        double subtaskNorm = Math.min(task.getSubtaskCount() / MAX_SUBTASKS, 1.0);

        double score = 100 * (
            WORKLOAD_WEIGHT * workloadNorm +
            URGENCY_WEIGHT * urgencyNorm +
            SUBTASK_WEIGHT * subtaskNorm
        );

        task.setPriorityScore(score);
        return score;
    }

    public static String checkFeasibility(Task task) {
        long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), task.getDeadline());
        if (daysRemaining < 0) {
            task.setInfeasible(true);
            return "OVERDUE";
        }

        double effectiveDays;
        if (daysRemaining == 0) {
            effectiveDays = 1;
        } else {
            effectiveDays = daysRemaining;
        }

        double requiredHoursPerDay = task.getWorkload() / effectiveDays;

        if (requiredHoursPerDay > FEASIBILITY_THRESHOLD) {
            task.setInfeasible(true);
            return "INFEASIBLE";
        }

        task.setInfeasible(false);
        return "FEASIBLE";
    }

    public static boolean validateDeadline(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeParseException oops) {
            return false;
        }
    }

    public static List<Task> rankTasks(List<Task> tasks) {
        for(int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            calculatePriority(task);
            checkFeasibility(task);
        }

        tasks.sort(Comparator.comparingDouble(Task::getPriorityScore).reversed());
        return tasks;
    }
}