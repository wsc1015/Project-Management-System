
package UserGUI;

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
public class UserListTableController implements ListSelectionListener,
		TableModelListener {
	private UserListTableModel tableModel;
	private UserListGUI gui;

	public UserListTableController(UserListGUI gui) {
		this.gui = gui;
		// create the tableModel using the data in the cachedRowSet
		tableModel = new UserListTableModel();
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
		gui.setUserIdTextField((String) tableModel.getValueAt(firstIndex, 0));
		gui.setPasswordTextField((String) tableModel.getValueAt(firstIndex, 1));
		gui.setUserNameTextField((String) tableModel.getValueAt(firstIndex, 2));
		//gui.setUserTypeTextField((String) tableModel.getValueAt(firstIndex, 3));
		gui.setAddressTextField((String) tableModel.getValueAt(firstIndex, 4));
		gui.setEmailTextField((String) tableModel.getValueAt(firstIndex, 5));
		gui.setPhoneTextField((String) tableModel.getValueAt(firstIndex, 6));
		
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
