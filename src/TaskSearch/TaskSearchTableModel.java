package TaskSearch;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TaskSearchTableModel extends AbstractTableModel{
	public List<TaskSearchResult> taskSearchResult;
	public TaskSearchResult tsr;// represents the entity courselist
	// This field contains additional information about the results
	public TaskSearchService taskSearchService;
	public String a;
	public int b;
	public int numcols, numrows;

	public TaskSearchTableModel(String aa, int bb) {
		a = aa;
		b = bb;
		tsr = new TaskSearchResult();
		taskSearchService = new TaskSearchService();
		taskSearchResult = taskSearchService.searchTask(a, b);
		numrows = taskSearchResult.size();
		System.out.println(numrows);
		numcols = tsr.getNumberOfColumns();
		System.out.println(numcols);
	}
	public int getRowCount() {
		return numrows;
	}

	public int getColumnCount() {
		return numcols;
	}

	public Object getValueAt(int row, int col) {
		try {
			return taskSearchResult.get(row).getColumnData(col);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
	public String getColumnName(int col) {
		try {
			return tsr.getColumnName(col);
		} catch (Exception e) {
			return e.toString();
		}
	}
}
