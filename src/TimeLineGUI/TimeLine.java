package TimeLineGUI;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "timeline")
public class TimeLine implements Serializable{
	@Column(name = "timeline_id")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int timeline_id;
	
	@Column
	private int project_id;
	@Column
	private String start_date;
	@Column
	private String end_date;
	@Column
	private String task;
	public int getTimeline_id() {
		return timeline_id;
	}
	public void setTimeline_id(int timeline_id) {
		this.timeline_id = timeline_id;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	
	// return number of columns in the table
		public int getNumberOfColumns() {
			return 5;
		}

		// return the data in column i
		public String getColumnData(int i) throws Exception {
			if (i == 0)
				return Integer.toString(getTimeline_id());
			else if (i == 1)
				return Integer.toString(getProject_id());
			else if (i == 2)
				return getStart_date();
			else if (i == 3)
				return getEnd_date();
			else if (i == 4)
				return getTask();
			else
				throw new Exception(
						"Error: invalid column index in courselist table");
		}

		// return the name of column i
		public String getColumnName(int i) throws Exception {
			String colName = null;
			if (i == 0)
				colName = "Timeline ID";
			else if (i == 1)
				colName = "Project ID";
			else if (i == 2)
				colName = "Start Date";
			else if (i == 3)
				colName = "End Date";
			else if (i == 4)
				colName = "Task";
			else
				throw new Exception(
						"Access to invalid column number in courselist table");
			return colName;
		}

		// set data column i to value
		public void setColumnData(int i, Object value) throws Exception {
			if (i == 0)
				timeline_id = (int) value;
			else if (i == 1)
				project_id = (int) value;
			else if (i == 2)
				start_date = (String) value;
			else if (i == 3)
				end_date = (String) value;
			else if (i == 4)
				task = (String) value;
			else
				throw new Exception(
						"Error: invalid column index in courselist table");
		}

	@Override
	public String toString() {
		return "TimeLine [timeline_id=" + timeline_id + ", project_id="
				+ project_id + ", start_date=" + start_date + ", end_date="
				+ end_date + ", task=" + task + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((end_date == null) ? 0 : end_date.hashCode());
		result = prime * result + project_id;
		result = prime * result
				+ ((start_date == null) ? 0 : start_date.hashCode());
		result = prime * result + ((task == null) ? 0 : task.hashCode());
		result = prime * result + timeline_id;
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
		TimeLine other = (TimeLine) obj;
		if (end_date == null) {
			if (other.end_date != null)
				return false;
		} else if (!end_date.equals(other.end_date))
			return false;
		if (project_id != other.project_id)
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		if (task == null) {
			if (other.task != null)
				return false;
		} else if (!task.equals(other.task))
			return false;
		if (timeline_id != other.timeline_id)
			return false;
		return true;
	}


}
