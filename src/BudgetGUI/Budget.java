package BudgetGUI;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "budget")
public class Budget implements Serializable {
	@Column(name = "budget_id")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int budget_id;
	@Column
	private int project_id;
	@Column
	private String budget_detail;
	@Column
	private double balance;
	@Column
	private double total;

	public int getBudget_id() {
		return budget_id;
	}

	public void setBudget_id(int budget_id) {
		this.budget_id = budget_id;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getBudget_detail() {
		return budget_detail;
	}

	public void setBudget_detail(String budget_detail) {
		this.budget_detail = budget_detail;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getNumberOfColumns() {
		return 5;
	}

	public String getColumnData(int i) throws Exception {
		if (i == 0)
			return Integer.toString(getBudget_id());
		else if (i == 1)
			return Integer.toString(getProject_id());
		else if (i == 2)
			return getBudget_detail();
		else if (i == 3)
			return Double.toString(getBalance());
		else if (i == 4)
			return Double.toString(getTotal());
		else
			throw new Exception(
					"Error: invalid column index in courselist table");
	}

	public String getColumnName(int i) throws Exception {
		String colName = null;
		if (i == 0)
			colName = "Budget ID";
		else if (i == 1)
			colName = "Project ID";
		else if (i == 2)
			colName = "Budget Detail";
		else if (i == 3)
			colName = "Balance";
		else if (i == 4)
			colName = "Total";
		else
			throw new Exception(
					"Access to invalid column number in userlist table");
		return colName;
	}

	public void setColumnData(int i, Object value) throws Exception {
		if (i == 0)
			budget_id = (int) value;
		else if (i == 1)
			project_id = (int) value;
		else if (i == 2)
			budget_detail = (String) value;
		else if (i == 3)
			balance = (double) value;
		else if (i == 4)
			total = (double) value;
		else
			throw new Exception("Error: invalid column index in userlist table");
	}

	@Override
	public String toString() {
		return "Budget [budget_id=" + budget_id + ", project_id=" + project_id
				+ ", budget_detail=" + budget_detail + ", balance=" + balance
				+ ", total=" + total + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((budget_detail == null) ? 0 : budget_detail.hashCode());
		result = prime * result + budget_id;
		result = prime * result + project_id;
		temp = Double.doubleToLongBits(total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Budget other = (Budget) obj;
		if (Double.doubleToLongBits(balance) != Double
				.doubleToLongBits(other.balance))
			return false;
		if (budget_detail == null) {
			if (other.budget_detail != null)
				return false;
		} else if (!budget_detail.equals(other.budget_detail))
			return false;
		if (budget_id != other.budget_id)
			return false;
		if (project_id != other.project_id)
			return false;
		if (Double.doubleToLongBits(total) != Double
				.doubleToLongBits(other.total))
			return false;
		return true;
	}

}
