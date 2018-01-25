package Controler;

import java.util.Date;
import java.util.List;

import Model.Project;
import Model.Task;

/**
 * La classe ControlerProject qui hérite de Controler.
 * Cette classe permet de gérer tout les projets.
 * @author Dimitri Calmels & Xavier Fernandez
 *
 */
public class ControlerProject extends Controler {
	private Project project;
	
	/**
	 * Le constructeur qui prend en parametre un Projet.
	 * Permet de créer le controleur d'un projet.
	 * @param pfProject
	 */
	public ControlerProject(Project pfProject) {
		this.project = pfProject;
	}
	
	/**
	 * Permet de récupérer toutes les tâches qui ce finissent aujourd'hui.
	 * @return Tâches
	 */
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
	/**
	 * Permet de récupérer le nom du projet.
	 * @return Nom
	 */
	public String getNom() {
		return this.project.getNom();
	}
	
	/**
	 * Permet de récupérer toutes les tâches du projet.
	 * @return Tâches
	 */
	public List<Task> getTasks() {
		return this.project.getTasks();
	}
	
	/**
	 * Permet de récupérer la tâche dont l'id est pfId
	 * @param pfId
	 * @return Tâche
	 */
	public Task getTask(long pfId) {
		return this.project.getTask(pfId);
	}
	
	/**
	 * Përmet d'ajouter au projet la tâche entrée en paramètre.
	 * @param pfTask
	 */
	public void addTask(Task pfTask) {
		this.project.addTask(pfTask);
	}
	
	/**
	 * Permet de supprimer au projet la tâche entrée en paramètre.
	 * @param pfId
	 */
	public void rmTask(long pfId) {
		this.project.rmTask(pfId);
	}
	
	/**
	 * Permet de récupérer le nombre de tâches qie contient le projet.
	 * @return Taille
	 */
	public int size() {
		return this.project.size();
	}
	
	/**
	 * Permet de récupérer le projet entier.
	 * @return this.projet
	 */
	public Project getProject() {
		return this.project;
	}
}