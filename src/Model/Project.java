package Model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Project {
	private final Map<String, List<Task>> tasks = new LinkedHashMap<>();
	private final String nom;
	
	public Project(String pfNom) {
		this.nom = pfNom;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public Task getTask(long pfId) {
		return null;
	}

}
