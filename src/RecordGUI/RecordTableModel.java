package RecordGUI;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;

/**
 * Updated model Model for the courselist table. Contains the database-specific
 * code to read the data from the database into the table model.
 * 
 * @author Kevin
 * 
 * 
 */

public class RecordTableModel extends AbstractTableModel {

	List<Record> recordResultList; // stores the model data in a List
									// collection of type CourseList
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit"; // Used
																			// in
																			// persistence.xml

	private static EntityManagerFactory factory; // JPA
	private EntityManager manager; // JPA

	private Record record;// represents the entity courselist

	// This field contains additional information about the results
	private RecordService recordService;

	private int numcols, numrows; // number of rows and columns

	public RecordTableModel() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		manager = factory.createEntityManager();

		record = new Record();

		// read all the records from courselist
		recordService = new RecordService(manager);

		// update the number of rows and columns in the model
		recordResultList = recordService.readAll();
		numrows = recordResultList.size();
		numcols = record.getNumberOfColumns();
	}

	public RecordTableModel(List<Record> list, EntityManager em) {
		recordResultList = list;
		numrows = recordResultList.size();
		record = new Record();
		numcols = record.getNumberOfColumns();
		manager = em;
		recordService = new RecordService(manager);
	}

	public int getRowCount() {
		return numrows;
	}

	public int getColumnCount() {
		return numcols;
	}

	public List<Record> getList() {
		return recordResultList;
	}

	public EntityManager getEntityManager() {
		return manager;
	}

	public Object getValueAt(int row, int col) {
		try {
			return recordResultList.get(row).getColumnData(col);
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
			return record.getColumnName(col);
		} catch (Exception e) {
			return e.toString();
		}
	}

	// update the data in the given row and column to aValue
	public void setValueAt(Object aValue, int row, int col) {
		// data[rowIndex][columnIndex] = (String) aValue;
		try {
			Record element = recordResultList.get(row);
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
			Record newRecord = recordService.createRecord(
					Integer.parseInt((String) array[0]),
					Integer.parseInt((String) array[1]),
					Integer.parseInt((String) array[2]), (String) array[3],
					(String) array[4]);
			userTransaction.commit();
			// set the current row to rowIndex
			recordResultList.add(newRecord);
			int row = recordResultList.size() - 1;
			fireTableRowsInserted(row, row);
			numrows++;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteRow(Object obj) {
		try {
			int row = Integer.parseInt((String) obj);
			record = recordResultList.get(row);

			EntityTransaction userTransaction = manager.getTransaction();
			userTransaction.begin();
			recordService.deleteRecord(record.getRecord_id());
			userTransaction.commit();

			recordResultList.remove(record);
			fireTableRowsDeleted(row, row);
			numrows--;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateRow(Object obj, Object[] array) {
		try {
			int row = Integer.parseInt((String) obj);
			record = recordResultList.get(row);

			EntityTransaction userTransaction = manager.getTransaction();
			userTransaction.begin();
			Record newRecord = recordService.updateRecord(
					Integer.parseInt((String) array[0]),
					Integer.parseInt((String) array[1]),
					Integer.parseInt((String) array[2]), (String) array[3],
					(String) array[4]);
			userTransaction.commit();

			record = newRecord;
			fireTableRowsUpdated(row, row);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
