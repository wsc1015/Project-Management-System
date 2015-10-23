package TaskSearch;

public class TaskSearchResult {
	public String projectName;
	public String startdate;
	public String enddate;
	public String task;
	public int getNumberOfColumns() {
		return 4;
	}
	public String getColumnData(int i) throws Exception {
		if (i == 0)
			return projectName;
		else if (i == 1)
			return startdate;
		else if (i == 2)
			return enddate;
		else if (i == 3)
			return task;
		else
			throw new Exception(
					"Error: invalid column index in courselist table");
	}
	public String getColumnName(int i) throws Exception {
		String colName = null;
		if (i == 0)
			colName = "Project Name";
		else if (i == 1)
			colName = "Start Date";
		else if (i == 2)
			colName = "End Date";
		else if (i == 3)
			colName = "Task";
		else
			throw new Exception(
					"Access to invalid column number in userlist table");
		return colName;
	}
}
