/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserGUI;

/**
 *
 * @author Kevin
 */
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

public class UserListTableModel extends AbstractTableModel {

	List<UserList> userListResultList; // stores the model data in a List
										// collection of type CourseList
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit"; // Used
																			// in
																			// persistence.xml

	private static EntityManagerFactory factory; // JPA
	public EntityManager manager; // JPA

	private UserList userlist;// represents the entity courselist

	// This field contains additional information about the results
	private UserListService userlistService;

	private int numcols, numrows; // number of rows and columns

	public UserListTableModel() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		manager = factory.createEntityManager();

		userlist = new UserList();

		// read all the records from courselist
		userlistService = new UserListService(manager);

		// update the number of rows and columns in the model
		userListResultList = userlistService.readAll();
		numrows = userListResultList.size();
		numcols = userlist.getNumberOfColumns();
	}

	public UserListTableModel(List<UserList> list, EntityManager em) {
		userListResultList = list;
		numrows = userListResultList.size();
		userlist = new UserList();
		numcols = userlist.getNumberOfColumns();
		manager = em;
		userlistService = new UserListService(manager);
	}

	public int getRowCount() {
		return numrows;
	}

	public int getColumnCount() {
		return numcols;
	}

	public List<UserList> getList() {
		return userListResultList;
	}

	public EntityManager getEntityManager() {
		return manager;
	}

	public Object getValueAt(int row, int col) {
		try {
			return userListResultList.get(row).getColumnData(col);
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
			return userlist.getColumnName(col);
		} catch (Exception e) {
			return e.toString();
		}
	}

	// update the data in the given row and column to aValue
	public void setValueAt(Object aValue, int row, int col) {
		// data[rowIndex][columnIndex] = (String) aValue;
		try {
			UserList element = userListResultList.get(row);
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
			UserList newRecord = userlistService.createUser(
					Integer.parseInt((String) array[0]), (String) array[1],
					(String) array[2], (String) array[3], (String) array[4],
					(String) array[5],(String) array[6]);
			userTransaction.commit();
			// set the current row to rowIndex
			userListResultList.add(newRecord);
			int row = userListResultList.size() - 1;
			fireTableRowsInserted(row, row);
			numrows++;
			System.out.println("Hi");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteRow(Object obj) {
		try {
			int row = Integer.parseInt((String) obj);
			userlist = userListResultList.get(row);

			EntityTransaction userTransaction = manager.getTransaction();
			userTransaction.begin();
			userlistService.deleteUser(userlist.getUser_ID());
			userTransaction.commit();

			userListResultList.remove(userlist);
			fireTableRowsDeleted(row, row);
			numrows--;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateRow(Object obj, Object[] array) {
		try {
			int row = Integer.parseInt((String) obj);
			userlist = userListResultList.get(row);

			EntityTransaction userTransaction = manager.getTransaction();
			userTransaction.begin();
			UserList newRecord = userlistService.updateUser(
					Integer.parseInt((String) array[0]), (String) array[1],
					(String) array[2], (String) array[3], (String) array[4],
					(String) array[5],(String) array[6]);
			userTransaction.commit();

			userlist = newRecord;
			fireTableRowsUpdated(row, row);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
