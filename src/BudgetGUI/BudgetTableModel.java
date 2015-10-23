package BudgetGUI;

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

public class BudgetTableModel extends AbstractTableModel {

	List<Budget> budgetResultList; // stores the model data in a List
									// collection of type CourseList
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit"; // Used
																			// in
																			// persistence.xml

	private static EntityManagerFactory factory; // JPA
	public EntityManager manager; // JPA

	private Budget budget;// represents the entity courselist

	// This field contains additional information about the results
	private BudgetService budgetService;

	private int numcols, numrows; // number of rows and columns

	public BudgetTableModel() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		manager = factory.createEntityManager();

		budget = new Budget();

		// read all the Budgets from courselist
		budgetService = new BudgetService(manager);

		// update the number of rows and columns in the model
		budgetResultList = budgetService.readAll();
		numrows = budgetResultList.size();
		numcols = budget.getNumberOfColumns();
	}

	public BudgetTableModel(List<Budget> list, EntityManager em) {
		budgetResultList = list;
		numrows = budgetResultList.size();
		budget = new Budget();
		numcols = budget.getNumberOfColumns();
		manager = em;
		budgetService = new BudgetService(manager);
	}

	public int getRowCount() {
		return numrows;
	}

	public int getColumnCount() {
		return numcols;
	}

	public List<Budget> getList() {
		return budgetResultList;
	}

	public EntityManager getEntityManager() {
		return manager;
	}

	public Object getValueAt(int row, int col) {
		try {
			return budgetResultList.get(row).getColumnData(col);
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
			return budget.getColumnName(col);
		} catch (Exception e) {
			return e.toString();
		}
	}

	// update the data in the given row and column to aValue
	public void setValueAt(Object aValue, int row, int col) {
		// data[rowIndex][columnIndex] = (String) aValue;
		try {
			Budget element = budgetResultList.get(row);
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
			Budget newBudget = budgetService.createBudget(
					Integer.parseInt((String) array[0]),
					Integer.parseInt((String) array[1]), (String) array[2],
					Double.parseDouble((String) array[3]),
					Double.parseDouble((String) array[4]));
			userTransaction.commit();
			// set the current row to rowIndex
			budgetResultList.add(newBudget);
			int row = budgetResultList.size() - 1;
			fireTableRowsInserted(row, row);
			numrows++;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteRow(Object obj) {
		try {
			int row = Integer.parseInt((String) obj);
			budget = budgetResultList.get(row);

			EntityTransaction userTransaction = manager.getTransaction();
			userTransaction.begin();
			budgetService.deleteBudget(budget.getBudget_id());
			userTransaction.commit();
			budgetResultList.remove(budget);
			fireTableRowsDeleted(row, row);
			numrows--;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateRow(Object obj, Object[] array) {
		try {
			int row = Integer.parseInt((String) obj);
			budget = budgetResultList.get(row);

			EntityTransaction userTransaction = manager.getTransaction();
			userTransaction.begin();
			Budget newBudget = budgetService.updateBudget(
					Integer.parseInt((String) array[0]),
					Integer.parseInt((String) array[1]), (String) array[2],
					Double.parseDouble((String) array[3]),
					Double.parseDouble((String) array[4]));
			userTransaction.commit();

			budget = newBudget;
			fireTableRowsUpdated(row, row);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
