package RecordGUI;

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
public class RecordTableController implements ListSelectionListener,
		TableModelListener {
	private RecordTableModel tableModel;
	private RecordEdtFrame gui;

	public RecordTableController(RecordEdtFrame gui) {
		this.gui = gui;
		// create the tableModel using the data in the cachedRowSet
		tableModel = new RecordTableModel();
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
		gui.setRecordIdTextField((String) tableModel.getValueAt(firstIndex, 0));
		gui.setUserIdTextField((String) tableModel.getValueAt(firstIndex, 1));
		gui.setProjectIdTextField((String) tableModel.getValueAt(firstIndex, 2));
		gui.setTimeTextField((String) tableModel.getValueAt(firstIndex, 3));
		gui.setContentTextField((String) tableModel.getValueAt(firstIndex, 4));

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
