package ProjectGUI;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity(name = "project")
public class Project implements Serializable{
	@Column(name = "project_id")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int project_id;
	
	@Column
	private String project_name;
	@Column
	private String project_description;
	@Column
	private String project_desired_outcome;


	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_description() {
		return project_description;
	}
	public void setProject_description(String project_description) {
		this.project_description = project_description;
	}
	public String getProject_desired_outcome() {
		return project_desired_outcome;
	}
	public void setProject_desired_outcome(String project_desired_outcome) {
		this.project_desired_outcome = project_desired_outcome;
	}

	
	public int getNumberOfColumns() {
		return 4;
	}
	
	public String getColumnData(int i) throws Exception {
		if (i == 0)
			return Integer.toString(getProject_id());
		else if (i == 1)
			return getProject_name();
		else if (i == 2)
			return getProject_description();
		else if (i == 3)
			return getProject_desired_outcome();
		else
			throw new Exception(
					"Error: invalid column index in courselist table");
	}
	public String getColumnName(int i) throws Exception {
		String colName = null;
		if (i == 0)
			colName = "Project ID";
		else if (i == 1)
			colName = "Project Name";
		else if (i == 2)
			colName = "Project Description";
		else if (i == 3)
			colName = "Project Desired Outcome";
		else
			throw new Exception(
					"Access to invalid column number in courselist table");
		return colName;
	}
	public void setColumnData(int i, Object value) throws Exception {
		if (i == 0)
			project_id = (int) value;
		else if (i == 1)
			project_name = (String) value;
		else if (i == 2)
			project_description = (String) value;
		else if (i == 3)
			project_desired_outcome = (String) value;
		else
			throw new Exception(
					"Error: invalid column index in courselist table");
	}
	@Override
	public String toString() {
		return "Project [project_id=" + project_id + ", project_name="
				+ project_name + ", project_description=" + project_description
				+ ", project_desired_outcome=" + project_desired_outcome + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((project_description == null) ? 0 : project_description
						.hashCode());
		result = prime
				* result
				+ ((project_desired_outcome == null) ? 0
						: project_desired_outcome.hashCode());
		result = prime * result + project_id;
		result = prime * result
				+ ((project_name == null) ? 0 : project_name.hashCode());
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
		Project other = (Project) obj;
		if (project_description == null) {
			if (other.project_description != null)
				return false;
		} else if (!project_description.equals(other.project_description))
			return false;
		if (project_desired_outcome == null) {
			if (other.project_desired_outcome != null)
				return false;
		} else if (!project_desired_outcome
				.equals(other.project_desired_outcome))
			return false;
		if (project_id != other.project_id)
			return false;
		if (project_name == null) {
			if (other.project_name != null)
				return false;
		} else if (!project_name.equals(other.project_name))
			return false;
		return true;
	}
	
}
