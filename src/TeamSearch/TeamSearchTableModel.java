package TeamSearch;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TeamSearchTableModel extends AbstractTableModel {
	public List<TeamSearchResult> teamSearchResult;
	public TeamSearchResult tsr;// represents the entity courselist

	// This field contains additional information about the results
	public TeamSearchService teamSearchService;
	public String a;
	public int b;
	public String c;
	public int d;

	public int numcols, numrows;

	public TeamSearchTableModel(String aa, int bb, String cc, int dd) {
		a = aa;
		b = bb;
		c = cc;
		d = dd;
		tsr = new TeamSearchResult();
		teamSearchService = new TeamSearchService();
		System.out.println(d);
		teamSearchResult = teamSearchService.searchTeam(a, b, c, d);
		System.out.println(teamSearchResult.get(0).memberPhone);
		numrows = teamSearchResult.size();
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
			return teamSearchResult.get(row).getColumnData(col);
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
