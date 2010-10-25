package net.sourceforge.myjorganizer.gui.mvc.model;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class TaskTableModelProxy extends AbstractTableModel {

	private static final long serialVersionUID = -7085928223934964783L;
	
	private TableModel implementation;

	public TaskTableModelProxy(TableModel implementation) {
		super();
		this.implementation = implementation;
	}

	@Override
	public int getRowCount() {
		return implementation.getRowCount();
	}

	@Override
	public int getColumnCount() {
		return implementation.getColumnCount();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return implementation.getColumnName(columnIndex);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return implementation.getColumnClass(columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return implementation.isCellEditable(rowIndex, columnIndex);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return implementation.getValueAt(rowIndex, columnIndex);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		implementation.setValueAt(aValue, rowIndex, columnIndex);

		fireTableCellUpdated(rowIndex, columnIndex);
	}
}
