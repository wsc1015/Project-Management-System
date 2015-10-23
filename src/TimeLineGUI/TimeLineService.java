package TimeLineGUI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import UserGUI.UserList;

public class TimeLineService {
	private EntityManager manager;

	public TimeLineService(EntityManager manager) {
		this.manager = manager;
	}
	public TimeLine createTimeLine(int timeline_id, int project_id, String start_date,
			String end_date, String task) {
		TimeLine timeline = new TimeLine();
		timeline.setTimeline_id(timeline_id);
		timeline.setProject_id(project_id);
		timeline.setStart_date(start_date);
		timeline.setEnd_date(end_date);
		timeline.setTask(task);
		manager.persist(timeline);
		return timeline;
	}
	public TimeLine readTimeLine(int timeline_id) {
		TimeLine timeline = manager.find(TimeLine.class, timeline_id);
		return timeline;
	}

	// method to read all records
	public List<TimeLine> readAll() {
		TypedQuery<TimeLine> query = manager.createQuery(
				"select e from timeline e", TimeLine.class);
		List<TimeLine> result = query.getResultList();
		return result;
	}

	// method to update a record
	public TimeLine updateTimeLine(int timeline_id, int project_id, String start_date,
			String end_date, String task) {
		TimeLine timeline = manager.find(TimeLine.class, timeline_id);
		if (timeline != null) {
			timeline.setTimeline_id(timeline_id);
			timeline.setProject_id(project_id);
			timeline.setStart_date(start_date);
			timeline.setEnd_date(end_date);
			timeline.setTask(task);
		}
		return timeline;
	}

	// method to delete a record
	public void deleteTimeLine(int timeline_id) {
		TimeLine timeline = manager.find(TimeLine.class, timeline_id);
		if (timeline != null) {
			manager.remove(timeline);
		}
	}
}
