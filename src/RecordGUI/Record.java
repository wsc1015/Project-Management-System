package RecordGUI;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "record")
public class Record implements Serializable{
	@Column(name = "record_id")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)	
	private int record_id;
	@Column
	private int user_id;
	@Column
	private int project_id;
	@Column
	private String time;	
	@Column
	private String content;
	public int getRecord_id() {
		return record_id;
	}
	public void setRecord_id(int record_id) {
		this.record_id = record_id;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getNumberOfColumns() {
		return 5;
	}
	// return the data in column i
		public String getColumnData(int i) throws Exception {
			if (i == 0)
				return Integer.toString(getRecord_id());
			else if (i == 1)
				return Integer.toString(getUser_id());
			else if (i == 2)
				return Integer.toString(getProject_id());
			else if (i == 3)
				return getTime();
			else if (i == 4)
				return getContent();
			else
				throw new Exception(
						"Error: invalid column index in courselist table");
		}
		// return the name of column i
		public String getColumnName(int i) throws Exception {
			String colName = null;
			if (i == 0)
				colName = "Record ID";
			else if (i == 1)
				colName = "User ID";
			else if (i == 2)
				colName = "Project ID";
			else if (i == 3)
				colName = "Time";
			else if (i == 4)
				colName = "Content";
			else
				throw new Exception(
						"Access to invalid column number in userlist table");
			return colName;
		}
		// set data column i to value
		public void setColumnData(int i, Object value) throws Exception {
			if (i == 0)
				record_id = (int) value;
			else if (i == 1)
				user_id = (int) value;
			else if (i == 2)
				project_id = (int) value;
			else if (i == 3)
				time = (String) value;
			else if (i == 4)
				content = (String) value;
			else
				throw new Exception(
						"Error: invalid column index in userlist table");
		}
		@Override
		public String toString() {
			return "Record [record_id=" + record_id + ", user_id=" + user_id
					+ ", project_id=" + project_id + ", time=" + time
					+ ", content=" + content + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((content == null) ? 0 : content.hashCode());
			result = prime * result + project_id;
			result = prime * result + record_id;
			result = prime * result + ((time == null) ? 0 : time.hashCode());
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
			Record other = (Record) obj;
			if (content == null) {
				if (other.content != null)
					return false;
			} else if (!content.equals(other.content))
				return false;
			if (project_id != other.project_id)
				return false;
			if (record_id != other.record_id)
				return false;
			if (time == null) {
				if (other.time != null)
					return false;
			} else if (!time.equals(other.time))
				return false;
			if (user_id != other.user_id)
				return false;
			return true;
		}

}
