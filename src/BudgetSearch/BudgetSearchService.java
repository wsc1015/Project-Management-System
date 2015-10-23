package BudgetSearch;

import java.util.*;

import ProjectGUI.*;
import BudgetGUI.*;

public class BudgetSearchService {
	public static ProjectTableModel projectModel = new ProjectTableModel();
	public static ProjectService projectService = new ProjectService(
			projectModel.manager);
	public static List<Project> project = projectService.readAll();
	public static BudgetTableModel budgetModel = new BudgetTableModel();
	public static BudgetService budgetService = new BudgetService(
			budgetModel.manager);
	public static List<Budget> budget = budgetService.readAll();

	public List<BudgetSearchResult> searchBudget(String p_name, int p_id) {
		ArrayList<BudgetSearchResult> result = new ArrayList<BudgetSearchResult>();
		Project tempP = new Project();
		for (int i = 0; i < project.size(); i++) {
			if (project.get(i).getProject_name().equals(p_name)
					|| project.get(i).getProject_id() == p_id) {
				tempP = project.get(i);
				break;
			}
		}
		ArrayList<Budget> tempB = new ArrayList<Budget>();
		for (int i = 0; i < budget.size(); i++) {
			if (budget.get(i).getProject_id() == tempP.getProject_id()) {
				tempB.add(budget.get(i));
			}
		}
		System.out.println(tempB.size());
		for(int i = 0; i < tempB.size();i++){
			BudgetSearchResult newResult = new  BudgetSearchResult();
			newResult.projectName = tempP.getProject_name();
			newResult.balance = tempB.get(i).getBalance();
			newResult.total = tempB.get(i).getTotal();
			newResult.budgetdetail = tempB.get(i).getBudget_detail();
			result.add(newResult);
		}
		return result;
	}
	public static void main(String args[]){
		BudgetSearchService bss = new BudgetSearchService();
		List<BudgetSearchResult> a = bss.searchBudget("Dota solo match", -1);
		System.out.println(a.get(0).budgetdetail);
	}
}
