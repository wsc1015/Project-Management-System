package TaskSearch;

import java.util.ArrayList;
import java.util.List;

import BudgetSearch.BudgetSearchResult;
import BudgetSearch.BudgetSearchService;
import ProjectGUI.Project;
import ProjectGUI.ProjectService;
import ProjectGUI.ProjectTableModel;
import TimeLineGUI.*;

public class TaskSearchService {
	public static ProjectTableModel projectModel = new ProjectTableModel();
	public static ProjectService projectService = new ProjectService(
			projectModel.manager);
	public static List<Project> project = projectService.readAll();
	public static TimeLineTableModel taskModel = new TimeLineTableModel();
	public static TimeLineService taskService = new TimeLineService(
			taskModel.manager);
	public static List<TimeLine> task = taskService.readAll();

	public List<TaskSearchResult> searchTask(String p_name, int p_id) {
		ArrayList<TaskSearchResult> result = new ArrayList<TaskSearchResult>();
		Project tempP = new Project();
		for (int i = 0; i < project.size(); i++) {
			if (project.get(i).getProject_name().equals(p_name)
					|| project.get(i).getProject_id() == p_id) {
				tempP = project.get(i);
				break;
			}
		}
		ArrayList<TimeLine> tempT = new ArrayList<TimeLine>();
		for (int i = 0; i < task.size(); i++) {
			if (task.get(i).getProject_id() == tempP.getProject_id()) {
				tempT.add(task.get(i));
			}
		}
		System.out.println(tempT.size());
		for (int i = 0; i < tempT.size(); i++) {
			TaskSearchResult newResult = new TaskSearchResult();
			newResult.projectName = tempP.getProject_name();
			newResult.startdate = tempT.get(i).getStart_date();
			newResult.enddate = tempT.get(i).getEnd_date();
			newResult.task = tempT.get(i).getTask();
			result.add(newResult);
		}
		return result;
	}

	public static void main(String args[]) {
		TaskSearchService bss = new TaskSearchService();
		List<TaskSearchResult> a = bss.searchTask("Dota solo match", -1);
		System.out.println(a.get(0).projectName + a.get(0).enddate);
		System.out.println(a.get(0).projectName + a.get(1).enddate);
	}
}
