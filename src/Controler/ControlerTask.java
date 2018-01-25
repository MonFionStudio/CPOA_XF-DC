package Controler;

import java.util.Date;

import Model.Task;

/**
 * La classe ControlerTask qui hérite de Controler.
 * Cette classe permet de gérer une tâche.
 * @author Dimitri Calmels & Xavier Fernandez
 *
 */
public class ControlerTask extends Controler {
	private Task task;
	
	/**
	 * Le constructeur qui prend en paramètre une tâche.
	 * Permet de créer le controleur d'une tâche.
	 * @param pfTask
	 */
	public ControlerTask(Task pfTask) {
		this.task = pfTask;
	}
	
	/**
	 * Permet de savoir si la date de fin de la tâche est la date entrée en paramètre.
	 * @param pfDeadline
	 * @return boolean
	 */
	public boolean isDeadLine(Date pfDeadline) {
		if(this.task.getDeadLine().equals(pfDeadline)) {
			return true;
		}
		return false;
	}
	
	/*
	 * Override methodes classe
	 */
	/**
	 * Permet de connaitre l'id de la tâche.
	 * @return id
	 */
	public long getId() {
        return this.task.getId();
    }

	/**
	 * Permet de connaitre la description de la tâche.
	 * @return description
	 */
    public String getDescription() {
        return this.task.getDescription();
    }

    /**
     * Permet de savoir qi la tâche est fini ou non.
     * @return boolean
     */
    public boolean isDone() {
        return this.task.isDone();
    }

    /**
     * Permet de définir si la tâche est finie ou non.
     * @param done
     */
    public void setDone(boolean done) {
        this.task.setDone(done);
    }
    
    /**
     * Permet de connaitre la date de fin de la tâche.
     * @return date
     */
    public Date getDeadLine() {
    	return this.task.getDeadLine();
    }
    
    /**
     * Permet de définir la date de fin de la tâche.
     * @param pfDeadline
     */
    public void setDeadLine(Date pfDeadline) {
    	this.task.setDeadLine(pfDeadline);
    }
}