package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * La classe Task qui est une tâche.
 * @author Dimitri Calmels & Xavier Fernandez
 *
 */
public final class Task {
    private final long id;
    private final String description;
    private boolean done;
    private Date deadline;

    /**
     * Le constructeur de la tâche.
     * Permet de créer une tâche avec un ID, une description, si elle est fini ou non, et sa date de fin.
     * @param id
     * @param description
     * @param done
     * @param pfDeadline
     */
    public Task(long id, String description, boolean done, Date pfDeadline) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.deadline = pfDeadline;
    }

    /**
     * Permet de récupérer l'Id de la tâche.
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Permet de connaitre la description de la tâche.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Permet de savoir si la tâche est fini ou non.
     * @return boolean
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Permet de définir si la tâche est fini ou non.
     * @param done
     */
    public void setDone(boolean done) {
        this.done = done;
    }
    
    /**
     * Permet de récupérer la date de fin de la tâche.
     * @return date
     */
    public Date getDeadLine() {
    	return this.deadline;
    }
    
    /**
     * Permet de définir la date de fin de la tâche.
     * @param pfDeadline
     */
    public void setDeadLine(Date pfDeadline) {
    	this.deadline = pfDeadline;
    }
    
    /**
     * Permet de récupérer la tâche de façon lisible.
     * @return tâche
     */
    public String toString() {
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	return this.getId() + " : " + this.getDescription() + " " + df.format(this.deadline);
    }
}
