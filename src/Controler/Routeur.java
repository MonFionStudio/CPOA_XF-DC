package Controler;

import java.util.Date;
import java.util.List;

import Model.Project;
import Model.Task;
import View.TaskOtron3000;

public class Routeur {
	private Controler ctrl;
	private List<Project> lProject;
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
            	if (commandRest[1].equals("project")) {
            		try {
            			lProject.add(new Project(commandRest[2]));
            		}catch (Exception e) {
						view.refresh("add <project> <Project Name>");
					}
                } else if (commandRest[1].equals("task")) {
                    try {
                    	boolean bon = false;
						for (Project p : this.lProject) {
							if(p.getNom().equals(commandRest[2])) {
								p.addTask(new Task(lastID, commandRest[3], false, new Date(commandRest[4])));
								lastID++;
							}
						}
						if(!bon)
							view.refresh("Projet inexistant !");
					} catch (Exception e) {
						view.refresh("add <task> <Project Name> <Task Description> <DeadLine>");
					}
                }
            	view.refresh("add <project|task> <Project Name> <Task Description>");
                break;
            case "check":
                for(Project p : this.lProject) {
                	p.getTask(Integer.parseInt(commandRest[1]));
                }
                break;
            case "uncheck":
                uncheck(commandRest[1]);
                break;
            case "help":
                help();
                break;
            default:
                error(command);
                break;
        }
    }
}
