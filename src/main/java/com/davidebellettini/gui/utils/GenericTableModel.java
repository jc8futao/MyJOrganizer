package com.davidebellettini.gui.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.objectweb.asm.Type;

public class GenericTableModel<T> implements TableModel {
	@SuppressWarnings("unused")
	private Class<? extends T> type;
	private HashMap<Integer, Method> getters = new HashMap<Integer, Method>();

	public GenericTableModel(Class<? extends T> type) {
		this.type = type;

		for (Method method : type.getMethods()) {
			String methodName = method.getName();
			DisplayInTable displayAnnotation;

			if (methodName.startsWith("get")
					&& (displayAnnotation = method
							.getAnnotation(DisplayInTable.class)) != null
					&& !method.getGenericReturnType().equals(Type.VOID)
					&& method.getParameterTypes().length == 0) {

				getters.put(displayAnnotation.position(), method);

				System.err.println(methodName);
			}
		}
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return getters.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return getters.get(columnIndex).getName().substring(3);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

}
