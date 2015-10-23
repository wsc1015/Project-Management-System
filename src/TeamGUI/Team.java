package TeamGUI;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity(name = "team")
public class Team implements Serializable{
	@Column(name = "team_id")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	
	private int team_id;
	@Column
	private int user_id;
	@Column
	private int project_id;
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public int getNumberOfColumns() {
		return 3;
	}
	public String getColumnData(int i) throws Exception {
		if (i == 0)
			return Integer.toString(getTeam_id());
		else if (i == 1)
			return Integer.toString(getUser_id());
		else if (i == 2)
			return Integer.toString(getProject_id());
		else
			throw new Exception(
					"Error: invalid column index in courselist table");
	}
	public String getColumnName(int i) throws Exception {
		String colName = null;
		if (i == 0)
			colName = "Team ID";
		else if (i == 1)
			colName = "User ID";
		else if (i == 2)
			colName = "Project ID";
		else
			throw new Exception(
					"Access to invalid column number in userlist table");
		return colName;
	}
	public void setColumnData(int i, Object value) throws Exception {
		if (i == 0)
			team_id = (int) value;
		else if (i == 1)
			user_id = (int) value;
		else if (i == 2)
			project_id = (int) value;
		else
			throw new Exception(
					"Error: invalid column index in userlist table");
	}
	@Override
	public String toString() {
		return "Team [team_id=" + team_id + ", user_id=" + user_id
				+ ", project_id=" + project_id + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + project_id;
		result = prime * result + team_id;
		result = prime * result + user_id;
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
		Team other = (Team) obj;
		if (project_id != other.project_id)
			return false;
		if (team_id != other.team_id)
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}
}
