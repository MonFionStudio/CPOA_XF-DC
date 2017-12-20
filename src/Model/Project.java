package Model;

import java.util.ArrayList;
import java.util.List;


public class Project {
	private final List<Task> tasks = new ArrayList<Task>();
	private final String nom;
	
	public Project(String pfNom) {
		this.nom = pfNom;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public List<Task> getTasks() {
		return this.tasks;
	}
	
	public Task getTask(long pfId) {
		for (Task teuch : this.tasks) {
			if(teuch.getId() == pfId) {
				return teuch;
			}
		}
		return null;
	}
	
	public void addTask(Task pfTask) {
		this.tasks.add(pfTask);
	}
	
	public void rmTask(Task pfTask) {
		this.tasks.remove(pfTask);
	}

}
