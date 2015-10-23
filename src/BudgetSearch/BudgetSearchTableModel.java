package BudgetSearch;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class BudgetSearchTableModel extends AbstractTableModel {
	public List<BudgetSearchResult> budgetSearchResult;
	public BudgetSearchResult bsr;// represents the entity courselist
	// This field contains additional information about the results
	public BudgetSearchService budgetSearchService;
	public String a;
	public int b;
	public int numcols, numrows;

	public BudgetSearchTableModel(String aa, int bb) {
		a = aa;
		b = bb;
		bsr = new BudgetSearchResult();
		budgetSearchService = new BudgetSearchService();
		budgetSearchResult = budgetSearchService.searchBudget(a, b);
		numrows = budgetSearchResult.size();
		System.out.println(numrows);
		numcols = bsr.getNumberOfColumns();
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
			return budgetSearchResult.get(row).getColumnData(col);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
	public String getColumnName(int col) {
		try {
			return bsr.getColumnName(col);
		} catch (Exception e) {
			return e.toString();
		}
	}
}
