package Model;

import java.util.Date;

public final class Task {
    private final long id;
    private final String description;
    private boolean done;
    private Date deadline;

    public Task(long id, String description, boolean done, Date pfDeadline) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.deadline = pfDeadline;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    
    public Date getDeadLine() {
    	return this.deadline;
    }
    
    public void setDeadLine(Date pfDeadline) {
    	this.deadline = pfDeadline;
    }
}
