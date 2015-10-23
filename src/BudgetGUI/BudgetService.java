package BudgetGUI;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;



public class BudgetService {
	private EntityManager manager;

	public BudgetService(EntityManager manager) {
		this.manager = manager;
	}
	public Budget createBudget(int budget_id, int project_id, String budget_detail,double balance,
			double total) {
		Budget budget = new Budget();
		budget.setBudget_id(budget_id);
		budget.setProject_id(project_id);
		budget.setBudget_detail(budget_detail);
		budget.setBalance(balance);
		budget.setTotal(total);		
		manager.persist(budget);
		return budget;
	}

	// method to read a Budget
	public Budget readBudget(int budget_id) {
		Budget budget = manager.find(Budget.class, budget_id);
		return budget;
	}

	// method to read all Budgets
	public List<Budget> readAll() {
		TypedQuery<Budget> query = manager.createQuery(
				"select e from budget e", Budget.class);
		List<Budget> result = query.getResultList();
		return result;
	}
	public Budget updateBudget(int budget_id, int project_id, String budget_detail,double balance,
	double total){
		Budget budget = manager.find(Budget.class, budget_id);
		if (budget != null) {
			budget.setProject_id(project_id);
			budget.setBudget_detail(budget_detail);
			budget.setBalance(balance);
			budget.setTotal(total);		
	
		}
		return budget;
	}

	// method to delete a Budget
	public void deleteBudget(int budget_id) {
		Budget budget = manager.find(Budget.class, budget_id);
		if (budget != null) {
			manager.remove(budget);
		}
	}
}

