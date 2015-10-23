package RecordGUI;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;



public class RecordService {
	private EntityManager manager;

	public RecordService(EntityManager manager) {
		this.manager = manager;
	}
	public Record createRecord(int record_id, int user_id, int project_id,String time,
			String content) {
		Record record = new Record();
		record.setRecord_id(record_id);
		record.setUser_id(user_id);
		record.setProject_id(project_id);
		record.setTime(time);
		record.setContent(content);		
		manager.persist(record);
		return record;
	}

	// method to read a record
	public Record readRecord(int record_id) {
		Record record = manager.find(Record.class, record_id);
		return record;
	}

	// method to read all records
	public List<Record> readAll() {
		TypedQuery<Record> query = manager.createQuery(
				"select e from record e", Record.class);
		List<Record> result = query.getResultList();
		return result;
	}
	public Record updateRecord(int record_id, int user_id, int project_id,String time,
			String content) {
		Record record = manager.find(Record.class, record_id);
		if (record != null) {
			record.setUser_id(user_id);
			record.setProject_id(project_id);
			record.setTime(time);
			record.setContent(content);	
		}
		return record;
	}

	// method to delete a record
	public void deleteRecord(int record_id) {
		Record record = manager.find(Record.class, record_id);
		if (record != null) {
			manager.remove(record);
		}
	}
}
