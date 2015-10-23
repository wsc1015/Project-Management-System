package BudgetSearch;

public class BudgetSearchResult {
	public String projectName;
	public String budgetdetail;
	public double balance;
	public double total;
	public int getNumberOfColumns() {
		return 4;
	}
	public String getColumnData(int i) throws Exception {
		if (i == 0)
			return projectName;
		else if (i == 1)
			return budgetdetail;
		else if (i == 2)
			return Double.toString(balance);
		else if (i == 3)
			return Double.toString(total);
		else
			throw new Exception(
					"Error: invalid column index in courselist table");
	}
	public String getColumnName(int i) throws Exception {
		String colName = null;
		if (i == 0)
			colName = "Project Name";
		else if (i == 1)
			colName = "Budget Detail";
		else if (i == 2)
			colName = "Balance";
		else if (i == 3)
			colName = "Total";
		else
			throw new Exception(
					"Access to invalid column number in userlist table");
		return colName;
	}
}
