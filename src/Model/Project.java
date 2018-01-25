package Model;

import java.util.ArrayList;
import java.util.List;


/**
 * La classe Projet qui est un projet.
 * @author Dimitri Calmels & Xavier Fernandez
 *
 */
public class Project {
	private final List<Task> tasks = new ArrayList<Task>();
	private final String nom;
	
	/**
	 * Le constructeur d'un projet appelé avec le nom de celui-ci.
	 * @param pfNom
	 */
	public Project(String pfNom) {
		this.nom = pfNom;
	}
	
	/**
	 * Permet de connaitre le nom du projet.
	 * @return nom
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * Permet de récupérer toutes les tâches du projet.
	 * @return tâches
	 */
	public List<Task> getTasks() {
		return this.tasks;
	}
	
	/**
	 * Permet de récupérer une tâche en fonction de sont id entré en paramètre.
	 * @param pfId
	 * @return tâche
	 */
	public Task getTask(long pfId) {
		for (Task teuch : this.tasks) {
			if(teuch.getId() == pfId) {
				return teuch;
			}
		}
		return null;
	}
	
	/**
	 * Permet d'ajouter une tâche au projet.
	 * @param pfTask
	 */
	public void addTask(Task pfTask) {
		this.tasks.add(pfTask);
	}
	
	/**
	 * Permet de supprimer une tâche au projet en fonction de sont id.
	 * @param pfId
	 */
	public void rmTask(long pfId) {
		for (Task teuch : this.tasks) {
			if(teuch.getId() == pfId) {
				this.tasks.remove(teuch);
			}
		}
	}
	
	/**
	 * Permet de connaitre le nombre de tâches que le projet contient.
	 * @return nombre
	 */
	public int size() {
		return this.tasks.size();
	}
	
	/**
	 * Permet d'afficher le projet de façon lisible.
	 * @return projet
	 */
	public String toString() {
		String msg = this.getNom() + "\n";
		for (Task teuch : this.tasks) {
			msg += "       " + teuch.toString() + "\n";
		}
		return msg;
	}

}
