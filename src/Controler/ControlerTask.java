package Controler;

import java.util.Date;

import Model.Task;

public class ControlerTask extends Controler {
	private Task task;
	
	public ControlerTask(Task pfTask) {
		this.task = pfTask;
	}
	
	public boolean isDeadLine(Date pfDeadline) {
		if(this.task.getDeadLine().equals(pfDeadline)) {
			return true;
		}
		return false;
	}
	
	/*
	 * Override methodes classe
	 */
	public long getId() {
        return this.task.getId();
    }

    public String getDescription() {
        return this.task.getDescription();
    }

    public boolean isDone() {
        return this.task.isDone();
    }

    public void setDone(boolean done) {
        this.task.setDone(done);
    }
    
    public Date getDeadLine() {
    	return this.task.getDeadLine();
    }
    
    public void setDeadLine(Date pfDeadline) {
    	this.task.setDeadLine(pfDeadline);
    }
}