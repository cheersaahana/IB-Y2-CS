public class Subtask {

    private int id;
    private int parentId;
    private String title;
    private double workload; //estimated hours
    private boolean completed;

    public Subtask(String title, double workload) {
        this.title = title;
        this.workload = workload;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    } 
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getWorkload() {
        return workload;
    }

    public void setWorkload(double workload) {
        this.workload = workload;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completionStatus) {
        this.completed = completionStatus;
    }
}