package Controler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.Project;
import Model.Task;
import View.TaskOtron3000;

/**
 * La classe routeur qui fait le lien entre les objets est les vues.
 * @author Dimitri Calmels & Xavier Fernandez
 *
 */
public class Routeur {
	private List<ControlerProject> lProject = new ArrayList<ControlerProject>();
	private TaskOtron3000 view;
	private int lastID = 0;

	/**
	 * Le constructeur du routeur qui prend la vue en paramètre.
	 * Permet de créer un routeur avec la vue.
	 * @param pfView
	 */
	public Routeur(TaskOtron3000 pfView) {
		this.view = pfView;
	}

	/**
	 * La méthode appelé par la vue afin de pouvoir exécuter toutes les commandes.
	 * @param commandLine
	 */
	public void execute(String commandLine) {
		String[] commandRest = commandLine.split(" ");
		String command = commandRest[0];
		switch (command) {
		case "show":
			String msg = "";
			for (ControlerProject p : this.lProject) {
				msg += "Project " + p.getNom() + " :\n";
				for (Task task : p.getTasks()) {
					ControlerTask CT = new ControlerTask(task);
					if(CT.isDone())
						msg += "    [x] " + CT.getId() + " " + CT.getDescription() + " " + CT.getDeadLine() + "\n";
					else
						msg += "    [ ] " + CT.getId() + " " + CT.getDescription() + " " + CT.getDeadLine() + "\n";
				}
				notifyView(msg);
			}
			break;
		case "add":
			try {
				if (commandRest[1].equals("project")) {
					try {
						lProject.add(new ControlerProject(new Project(commandRest[2])));
					}catch (Exception e) {
						notifyView("add <project> <Project Name>");
					}
				} else if (commandRest[1].equals("task")) {
					try {
						for (ControlerProject p : this.lProject) {
							if(p.getNom().equals(commandRest[2])) {
								String[] date = commandRest[4].split("/");
								p.addTask(new Task(lastID, commandRest[3], false, new Date(Integer.parseInt(date[2])-1900, Integer.parseInt(date[1])-1, Integer.parseInt(date[0]))));
								lastID++;
							}
						}
					} catch (Exception e) {
						notifyView("add <task> <Project Name> <Task Description> <dd/mm/YYYY>");
					}
				}else {
					notifyView("add <project|task> <Project Name> <Task Description>");
				}
			} catch (Exception e) {
				notifyView("add <project|task> <Project Name> <Task Description>");
			}
			break;
		case "check":
			try {
				boolean bonc = false;
				for(ControlerProject p : this.lProject) {
					if(p.getTask(Integer.parseInt(commandRest[1])) != null) {
						ControlerTask CT = new ControlerTask(p.getTask(Integer.parseInt(commandRest[1])));
						CT.setDone(true);
						bonc = true;
						break;
					}
				}
				if(!bonc)
					notifyView("Erreur la tache est inexistante !");
			}catch (Exception e) {
				notifyView("Erreur lors du check !");
			}
			break;
		case "uncheck":
			try {
				boolean bonuc = false;
				for(ControlerProject p : this.lProject) {
					if(p.getTask(Integer.parseInt(commandRest[1])) != null) {
						ControlerTask CT = new ControlerTask(p.getTask(Integer.parseInt(commandRest[1])));
						CT.setDone(false);
						bonuc = true;
						break;
					}
				}
				if(!bonuc)
					notifyView("Erreur la tache est inexistante !");
			} catch (Exception e) {
				notifyView("Erreur lors du uncheck !");
			}
			break;
		case "help":
			notifyView(help());
			break;
		case "today":
			for (ControlerProject p : lProject) {
				notifyView(p.viewTodayDeadLine());
			}
			break;
		case "byday":
			try {
				for (ControlerProject p : lProject) {
					String[] date = commandRest[2].split("/");
					notifyView(p.viewTasksDeadLine(new Date(Integer.parseInt(date[2])-1900, Integer.parseInt(date[1])-1, Integer.parseInt(date[0]))));
				}
			}catch (Exception e) {
				notifyView("byday <dd/mm/YYYY>");
			}
			break;
		default:
			notifyView(error(commandLine));
			break;
		}
	}

	/**
	 * Ce qui est retourné à la vue quand on entre la commande help
	 * @return aide
	 */
	private String help() {
		String msg = "";
		msg += "Commands:\n";
		msg += "  show\n";
		msg += "  add project <project name>\n";
		msg += "  add task <project name> <task description> <dd/mm/YYYY>\n";
		msg += "  check <task ID>\n";
		msg += "  uncheck <task ID>\n";
		msg += "  today\n";
		msg += "  byday <dd/mm/YYYY>";
		return msg;
	}

	/**
	 * Ce qui est retourné à la vue quand on entre une mauvaise commande.
	 * @param command
	 * @return erreur
	 */
	private String error(String command) {
		String msg = "";
		msg += "I don't know what the command " + command + " is.";
		return msg;
	}

	/**
	 * La commande qui indique à la vue qu'elle doit s'actualiser.
	 * @param msg
	 */
	public void notifyView(String msg) {
		view.actualiser(msg);
	}
}
