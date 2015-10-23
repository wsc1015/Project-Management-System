package TeamGUI;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;

import UserGUI.UserList;
import UserGUI.UserListService;

public class TeamTableModel extends AbstractTableModel {
	List<Team> teamResultList; // stores the model data in a List
	// collection of type CourseList
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit"; // Used
	// in
	// persistence.xml

	private static EntityManagerFactory factory; // JPA
	public EntityManager manager; // JPA

	private Team team;// represents the entity courselist

	// This field contains additional information about the results
	private TeamService teamService;

	private int numcols, numrows; // number of rows and columns

	public TeamTableModel() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		manager = factory.createEntityManager();

		team = new Team();

		// read all the records from courselist
		teamService = new TeamService(manager);

		// update the number of rows and columns in the model
		teamResultList = teamService.readAll();
		numrows = teamResultList.size();
		numcols = team.getNumberOfColumns();
	}

	public TeamTableModel(List<Team> list, EntityManager em) {
		teamResultList = list;
		numrows = teamResultList.size();
		team = new Team();
		numcols = team.getNumberOfColumns();
		manager = em;
		teamService = new TeamService(manager);
	}

	public int getRowCount() {
		return numrows;
	}

	public int getColumnCount() {
		return numcols;
	}

	public List<Team> getList() {
		return teamResultList;
	}

	public EntityManager getEntityManager() {
		return manager;
	}

	public Object getValueAt(int row, int col) {
		try {
			return teamResultList.get(row).getColumnData(col);
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
			return team.getColumnName(col);
		} catch (Exception e) {
			return e.toString();
		}
	}

	// update the data in the given row and column to aValue
	public void setValueAt(Object aValue, int row, int col) {
		// data[rowIndex][columnIndex] = (String) aValue;
		try {
			Team element = teamResultList.get(row);
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
			Team newRecord = teamService.createTeam(
					Integer.parseInt((String) array[0]), Integer.parseInt((String) array[1]),
					Integer.parseInt((String) array[2]));
			userTransaction.commit();
			// set the current row to rowIndex
			teamResultList.add(newRecord);
			int row = teamResultList.size() - 1;
			fireTableRowsInserted(row, row);
			numrows++;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteRow(Object obj) {
		try {
			int row = Integer.parseInt((String) obj);
			team = teamResultList.get(row);

			EntityTransaction userTransaction = manager.getTransaction();
			userTransaction.begin();
			teamService.deleteTeam(team.getTeam_id());
			userTransaction.commit();

			teamResultList.remove(team);
			fireTableRowsDeleted(row, row);
			numrows--;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateRow(Object obj, Object[] array) {
		try {
			int row = Integer.parseInt((String) obj);
			team = teamResultList.get(row);

			EntityTransaction userTransaction = manager.getTransaction();
			userTransaction.begin();
			Team newRecord = teamService.updateTeam(
					Integer.parseInt((String) array[0]), Integer.parseInt((String) array[1]),
					Integer.parseInt((String) array[2]));
			userTransaction.commit();

			team = newRecord;
			fireTableRowsUpdated(row, row);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
