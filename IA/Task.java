import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private int id;
    private String title;
    private String subject;
    private LocalDate deadline;
    private boolean completed;
    private double manualWorkload; //estimate used if no subtasks exist
    private List<Subtask> subtasks;
    //all of the below will be provided by PriorityManager.java, not computed here
    private double priorityScore;
    private boolean overdue;
    private boolean infeasible;

    public Task(String title, String subject, LocalDate deadline, double manualWorkload) {
        this.title = title;
        this.subject = subject;
        this.deadline = deadline;
        this.manualWorkload = manualWorkload;
        this.subtasks = new ArrayList<>();
        this.completed = false;
    }

    public void addSubtask (Subtask subtask) {
        subtasks.add(subtask);
    }

    public boolean removeSubtask(Subtask subtask) {
        return subtasks.remove(subtask);
    }

    public int getSubtaskCount() {
        return subtasks.size();
    }

    /*note to self: workload should be computed so  
    if there are subtasks take the sum of their estimates
    otherwise, use the manual estimate given when task created */ 

    public double getWorkload() {
        if (subtasks.isEmpty()) {
            return manualWorkload;
        }
        double total = 0.0;
        for (int i = 0; i < subtasks.size(); i++){
            total += subtasks.get(i).getWorkload();
        }
        return total;
    }

    public double getManualWorkload() {
        return manualWorkload;
    }

    public void setManualWorkload (double manualWorkload) {
        this.manualWorkload = manualWorkload;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline (LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean hasSubtasks() {
        return !subtasks.isEmpty();
    }

    public List<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks);
    }

	public int getCompletedSubtaskCount() {
		int completed = 0;
		for (int i = 0; i < subtasks.size(); i++) {
			if (subtasks.get(i).isCompleted()) {
				completed++;
			}
		}
		return completed;
	}

	public boolean areAllSubtasksCompleted() {
		for (int i = 0; i < subtasks.size(); i++) {
            if (!subtasks.get(i).isCompleted()) {
                return false;
            }
        }
        return true;
	}

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public double getPriorityScore() {
        return priorityScore;
    }

    public void setPriorityScore(double priorityScore) {
        this.priorityScore = priorityScore;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

    public boolean isInfeasible() {
        return infeasible;
    }

    public void setInfeasible(boolean infeasible) {
        this.infeasible = infeasible;
    }
}