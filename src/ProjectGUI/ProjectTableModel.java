package ProjectGUI;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;

import UserGUI.UserList;
import UserGUI.UserListService;

public class ProjectTableModel extends AbstractTableModel {

	List<Project> projectResultList; // stores the model data in a List
	// collection of type CourseList
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit"; // Used
	// in
	// persistence.xml

	private static EntityManagerFactory factory; // JPA
	public EntityManager manager; // JPA

	private Project project;// represents the entity courselist

	// This field contains additional information about the results
	private ProjectService projectService;

	private int numcols, numrows; // number of rows and columns

	public ProjectTableModel() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		manager = factory.createEntityManager();

		project = new Project();

		// read all the records from courselist
		projectService = new ProjectService(manager);

		// update the number of rows and columns in the model
		projectResultList = projectService.readAll();
		numrows = projectResultList.size();
		numcols = project.getNumberOfColumns();
	}

	public ProjectTableModel(List<Project> list, EntityManager em) {
		projectResultList = list;
		numrows = projectResultList.size();
		project = new Project();
		numcols = project.getNumberOfColumns();
		manager = em;
		projectService = new ProjectService(manager);
	}

	public int getRowCount() {
		return numrows;
	}

	public int getColumnCount() {
		return numcols;
	}

	public List<Project> getList() {
		return projectResultList;
	}

	public EntityManager getEntityManager() {
		return manager;
	}

	public Object getValueAt(int row, int col) {
		try {
			return projectResultList.get(row).getColumnData(col);
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
			return project.getColumnName(col);
		} catch (Exception e) {
			return e.toString();
		}
	}

	// update the data in the given row and column to aValue
	public void setValueAt(Object aValue, int row, int col) {
		// data[rowIndex][columnIndex] = (String) aValue;
		try {
			Project element = projectResultList.get(row);
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
			Project newRecord = projectService.createProject(
					Integer.parseInt((String) array[0]), (String) array[1],
					(String) array[2], (String) array[3]
					);
			userTransaction.commit();
			// set the current row to rowIndex
			projectResultList.add(newRecord);
			int row = projectResultList.size() - 1;
			fireTableRowsInserted(row, row);
			numrows++;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteRow(Object obj) {
		try {
			int row = Integer.parseInt((String) obj);
			project = projectResultList.get(row);

			EntityTransaction userTransaction = manager.getTransaction();
			userTransaction.begin();
			projectService.deleteProject(project.getProject_id());
			userTransaction.commit();

			projectResultList.remove(project);
			fireTableRowsDeleted(row, row);
			numrows--;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateRow(Object obj, Object[] array) {
		try {
			int row = Integer.parseInt((String) obj);
			project = projectResultList.get(row);

			EntityTransaction userTransaction = manager.getTransaction();
			userTransaction.begin();
			Project newRecord = projectService.updateProject(
					Integer.parseInt((String) array[0]), (String) array[1],
					(String) array[2], (String) array[3]);
			userTransaction.commit();

			project = newRecord;
			fireTableRowsUpdated(row, row);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
