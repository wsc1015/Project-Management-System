package TeamSearch;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class TeamSearchTableController implements TableModelListener{
	public TeamSearchTableModel tableModel;
	public UserTeamSearchFrame gui;
	public String aaa;
	public int bbb;
	public String ccc;
	public int ddd;
	public TeamSearchTableController(UserTeamSearchFrame gui, String a_,int b_, String c_,int d_) {
		this.gui = gui;
		aaa = a_;
		bbb = b_;
		ccc = c_;
		ddd = d_;
		// create the tableModel using the data in the cachedRowSet
		tableModel = new TeamSearchTableModel(aaa, bbb ,ccc ,ddd);
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
