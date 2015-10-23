package TimeLineGUI;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;


public class TimeLineTableModel extends AbstractTableModel {
	List<TimeLine> timelineResultList; // stores the model data in a List
	// collection of type CourseList
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit"; // Used
	// in
	// persistence.xml

	private static EntityManagerFactory factory; // JPA
	public EntityManager manager; // JPA

	private TimeLine timeline;// represents the entity courselist

	// This field contains additional information about the results
	private TimeLineService timelineService;

	private int numcols, numrows; // number of rows and columns

	public TimeLineTableModel() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		manager = factory.createEntityManager();

		timeline = new TimeLine();

		// read all the records from courselist
		timelineService = new TimeLineService(manager);

		// update the number of rows and columns in the model
		timelineResultList = timelineService.readAll();
		numrows = timelineResultList.size();
		numcols = timeline.getNumberOfColumns();
	}

	public TimeLineTableModel(List<TimeLine> list, EntityManager em) {
		timelineResultList = list;
		numrows = timelineResultList.size();
		timeline = new TimeLine();
		numcols = timeline.getNumberOfColumns();
		manager = em;
		timelineService = new TimeLineService(manager);
	}

	public int getRowCount() {
		return numrows;
	}

	public int getColumnCount() {
		return numcols;
	}

	public List<TimeLine> getList() {
		return timelineResultList;
	}

	public EntityManager getEntityManager() {
		return manager;
	}

	public Object getValueAt(int row, int col) {
		try {
			return timelineResultList.get(row).getColumnData(col);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	// table cells are not editable
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public Class<?> getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	}

	// returns the name of the column
	public String getColumnName(int col) {
		try {
			return timeline.getColumnName(col);
		} catch (Exception e) {
			return e.toString();
		}
	}

	// update the data in the given row and column to aValue
	public void setValueAt(Object aValue, int row, int col) {
		// data[rowIndex][columnIndex] = (String) aValue;
		try {
			TimeLine element = timelineResultList.get(row);
			element.setColumnData(col, aValue);
			fireTableCellUpdated(row, col);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addRow(Object[] array) {
		try {
			// code in the method addRow in CourseListTableModel
			EntityTransaction userTransaction = manager.getTransaction();
			userTransaction.begin();
			TimeLine newRecord = timelineService.createTimeLine(
					Integer.parseInt((String) array[0]), Integer.parseInt((String) array[1]),
					(String) array[2], (String) array[3], (String) array[4]);
			userTransaction.commit();
			// set the current row to rowIndex
			timelineResultList.add(newRecord);
			int row = timelineResultList.size() - 1;
			fireTableRowsInserted(row, row);
			numrows++;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteRow(Object obj) {
		try {
			int row = Integer.parseInt((String) obj);
			timeline = timelineResultList.get(row);

			EntityTransaction userTransaction = manager.getTransaction();
			userTransaction.begin();
			timelineService.deleteTimeLine(timeline.getTimeline_id());
			userTransaction.commit();

			timelineResultList.remove(timeline);
			fireTableRowsDeleted(row, row);
			numrows--;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateRow(Object obj, Object[] array) {
		try {
			int row = Integer.parseInt((String) obj);
			timeline = timelineResultList.get(row);

			EntityTransaction userTransaction = manager.getTransaction();
			userTransaction.begin();
			TimeLine newRecord = timelineService.updateTimeLine(
					Integer.parseInt((String) array[0]), Integer.parseInt((String) array[1]),
					(String) array[2], (String) array[3], (String) array[4]);
			userTransaction.commit();

			timeline = newRecord;
			fireTableRowsUpdated(row, row);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
