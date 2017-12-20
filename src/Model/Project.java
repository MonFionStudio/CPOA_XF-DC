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
	
	public void setTask(Task pfTask) {
		this.tasks.add(pfTask);
	}

}
