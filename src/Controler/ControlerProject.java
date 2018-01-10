package Controler;

import java.util.Date;
import java.util.List;

import Model.Project;
import Model.Task;

public class ControlerProject extends Controler {
	private Project project;
	
	public ControlerProject(Project pfProject) {
		this.project = pfProject;
	}
	
	public String viewTodayDeadLine() {
		String msg = "";
		for (Task teuch : this.getTasks()) {
			if(teuch.getDeadLine().equals(new Date(new Date().getTime()))) {
				msg += this.project.getNom() + "\n" + teuch.toString() + "\n";
			}
		}
		return msg;
	}
	
	public String viewTasksDeadLine(Date pfDeadLine) {
		String msg = "";
		for (Task teuch : this.getTasks()) {
			if(teuch.getDeadLine().equals(pfDeadLine)) {
				msg += this.project.getNom() + "\n" + teuch.toString() + "\n";
			}
		}
		return msg;
	}
	
	/*
	 * Override methodes classe
	 */
	public String getNom() {
		return this.project.getNom();
	}
	
	public List<Task> getTasks() {
		return this.project.getTasks();
	}
	
	public Task getTask(long pfId) {
		return this.project.getTask(pfId);
	}
	
	public void addTask(Task pfTask) {
		this.project.addTask(pfTask);
	}
	
	public void rmTask(long pfId) {
		this.project.rmTask(pfId);
	}
	
	public int size() {
		return this.project.size();
	}
	
	public Project getProject() {
		return this.project;
	}
}