package Controler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.Project;
import Model.Task;
import View.TaskOtron3000;

public class Routeur {
	private Controler ctrl;
	private List<Project> lProject = new ArrayList<Project>();
	private TaskOtron3000 view;
	private int lastID = 0;

	public Routeur(TaskOtron3000 pfView) {
		this.view = pfView;
	}

	public void execute(String commandLine) {
		String[] commandRest = commandLine.split(" ");
		String command = commandRest[0];
		switch (command) {
		case "show":
			String msg = "";
			for (Project p : this.lProject) {
				msg += p.getNom();
				for (Task task : p.getTasks()) {
					if(task.isDone())
						msg += "    [x] " + task.getId() + ": " + task.getDescription() + "\n";
					else
						msg += "    [ ] " + task.getId() + ": " + task.getDescription() + "\n";
				}
				view.refresh(msg);
			}
			break;
		case "add":
			try {
				if (commandRest[1].equals("project")) {
					try {
						lProject.add(new Project(commandRest[2]));
					}catch (Exception e) {
						view.refresh("add <project> <Project Name>");
					}
				} else if (commandRest[1].equals("task")) {
					try {
						for (Project p : this.lProject) {
							if(p.getNom().equals(commandRest[2])) {
								String[] date = commandRest[4].split("-");
								p.addTask(new Task(lastID, commandRest[3], false, new Date(Integer.parseInt(date[3]), Integer.parseInt(date[2]), Integer.parseInt(date[1]))));
								lastID++;
							}
						}
					} catch (Exception e) {
						view.refresh("add <task> <Project Name> <Task Description> <dd/mm/YYYY>");
					}
				}
			} catch (Exception e) {
				view.refresh("add <project|task> <Project Name> <Task Description>");
			}
			break;
		case "check":
			try {
				boolean bonc = false;
				for(Project p : this.lProject) {
					if(p.getTask(Integer.parseInt(commandRest[1])) != null) {
						p.getTask(Integer.parseInt(commandRest[1])).setDone(true);
						bonc = true;
						break;
					}
				}
				if(!bonc)
					view.refresh("Erreur la tache est inexistante !");
			}catch (Exception e) {
				view.refresh("Erreur lors du check !");
			}
			break;
		case "uncheck":
			try {
				boolean bonuc = false;
				for(Project p : this.lProject) {
					if(p.getTask(Integer.parseInt(commandRest[1])) != null) {
						p.getTask(Integer.parseInt(commandRest[1])).setDone(false);
						bonuc = true;
						break;
					}
				}
				if(!bonuc)
					view.refresh("Erreur la tache est inexistante !");
			} catch (Exception e) {
				view.refresh("Erreur lors du uncheck !");
			}
			break;
		case "help":
			view.help();
			break;
		default:
			view.error(command);
			break;
		}
	}
}
