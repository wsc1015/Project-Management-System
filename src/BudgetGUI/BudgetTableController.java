package BudgetGUI;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Kevin
 */
public class BudgetTableController implements ListSelectionListener,
		TableModelListener {
	private BudgetTableModel tableModel;
	private BudgetEdtFrame gui;

	public BudgetTableController(BudgetEdtFrame gui) {
		this.gui = gui;
		// create the tableModel using the data in the cachedRowSet
		tableModel = new BudgetTableModel();
		tableModel.addTableModelListener(this);
	}

	public TableModel getTableModel() {
		return tableModel;
	}

	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
		int firstIndex = selectModel.getMinSelectionIndex();

		// read the data in each column using getValueAt and display it on
		// corresponding textfield
		gui.setBudgetIdTextField((String) tableModel.getValueAt(firstIndex, 0));
		gui.setProjectIdTextField((String) tableModel.getValueAt(firstIndex, 1));
		gui.setBudgetDetailTextField((String) tableModel.getValueAt(firstIndex, 2));
		gui.setBalanceTextField((String) tableModel.getValueAt(firstIndex, 3));
		gui.setTotalTextField((String) tableModel.getValueAt(firstIndex, 4));

	}

	public void tableChanged(TableModelEvent e) {
		try {
		} catch (Exception exp) {
			exp.getMessage();
			exp.printStackTrace();
		}
	}

	public void addRow(String[] array) {
		tableModel.addRow(array);
	}

	public void deleteRow(String row) {
		tableModel.deleteRow(row);
	}

	public void updateRow(String row, String[] array) {
		tableModel.updateRow(row, array);
	}

}