package ProjectGUI;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;



public class ProjectTableController implements ListSelectionListener,
TableModelListener{
	private ProjectTableModel tableModel;
	private ProjectEdtFrame gui;
	public ProjectTableController(ProjectEdtFrame gui) {
		this.gui = gui;
		// create the tableModel using the data in the cachedRowSet
		tableModel = new ProjectTableModel();
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
		gui.setProjectIdTextField((String) tableModel.getValueAt(firstIndex, 0));
		gui.setProjectNameTextField((String) tableModel.getValueAt(firstIndex, 1));
		gui.setProjectDescriptionTextField((String) tableModel.getValueAt(firstIndex, 2));
		gui.setProjectDesiredOutcomeTextField((String) tableModel.getValueAt(firstIndex, 3));
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
