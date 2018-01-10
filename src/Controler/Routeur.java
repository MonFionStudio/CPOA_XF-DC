package Controler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.Project;
import Model.Task;
import View.TaskOtron3000;

public class Routeur {
	private List<ControlerProject> lProject = new ArrayList<ControlerProject>();
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
			for (ControlerProject p : this.lProject) {
				msg += "Project " + p.getNom() + " :\n";
				for (Task task : p.getTasks()) {
					if(task.isDone())
						msg += "    [x] " + task + "\n";
					else
						msg += "    [ ] " + task + "\n";
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
								p.addTask(new Task(lastID, commandRest[3], false, new Date(Integer.parseInt(date[2]), Integer.parseInt(date[1])-1, Integer.parseInt(date[0]))));
								lastID++;
							}
						}
					} catch (Exception e) {
						notifyView("add <task> <Project Name> <Task Description> <dd/mm/YYYY>");
					}
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
						p.getTask(Integer.parseInt(commandRest[1])).setDone(true);
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
						p.getTask(Integer.parseInt(commandRest[1])).setDone(false);
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
			view.help();
			break;
		case "today":
			for (ControlerProject p : lProject) {
				notifyView(p.viewTodayDeadLine());
			}
			break;
		default:
			view.error(command);
			break;
		}
	}
	
	public void help() {
		String msg = "";
        msg += "Commands:\n";
        msg += "  show\n";
        msg += "  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }

    public void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
	
	private void notifyView(String msg) {
		view.actualiser(msg);
	}
}
