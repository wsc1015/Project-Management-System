package TaskSearch;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
public class TaskSearchTableController implements TableModelListener{
	public TaskSearchTableModel tableModel;
	public UserTaskSearchFrame gui;
	public String aaa;
	public int bbb;

	public TaskSearchTableController(UserTaskSearchFrame gui, String a_,
			int b_) {
		this.gui = gui;
		aaa = a_;
		bbb = b_;
		// create the tableModel using the data in the cachedRowSet
		tableModel = new TaskSearchTableModel(aaa, bbb);
		tableModel.addTableModelListener(this);

	}

	public TableModel getTableModel() {
		return tableModel;
	}

	public void tableChanged(TableModelEvent e) {
		try {
		} catch (Exception exp) {
			exp.getMessage();
			exp.printStackTrace();
		}
	}
}
