package TeamSearch;

import java.io.Serializable;


public class TeamSearchResult implements Serializable{
	public String projectName;
	public String memberName;
	public String memberType;
	public String memberPhone;
	public String memberEmail;
	public int getNumberOfColumns() {
		return 5;
	}
	public String getColumnData(int i) throws Exception {
		if (i == 0)
			return projectName;
		else if (i == 1)
			return memberName;
		else if (i == 2)
			return memberType;
		else if (i == 3)
			return memberPhone;
		else if (i == 4)
			return memberEmail;
		else
			throw new Exception(
					"Error: invalid column index in courselist table");
	}
	public String getColumnName(int i) throws Exception {
		String colName = null;
		if (i == 0)
			colName = "Project Name";
		else if (i == 1)
			colName = "Member Name";
		else if (i == 2)
			colName = "Member Type";
		else if (i == 3)
			colName = "Member Phone number";
		else if (i == 4)
			colName = "Member Email";
		else
			throw new Exception(
					"Access to invalid column number in userlist table");
		return colName;
	}
}
