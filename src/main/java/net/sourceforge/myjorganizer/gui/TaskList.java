package net.sourceforge.myjorganizer.gui;

import java.awt.GridLayout;
import java.awt.dnd.DropTarget;

import javax.swing.DropMode;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.TransferHandler;

import net.sourceforge.myjorganizer.gui.model.TaskListModel;

public class TaskList extends JPanel {

	private static final long serialVersionUID = -7578060745143955602L;

	private final JList list;
	private final JScrollPane jScrollPane;

	public TaskList(String title, TaskListModel model, TransferHandler transferHandler) {
		super(new GridLayout(0, 1));

		list = new JList();
		jScrollPane = new JScrollPane(list);

		add(new JLabel(title));
		add(jScrollPane);

		list.setTransferHandler(transferHandler);

		list.setDragEnabled(true);
		list.setDropMode(DropMode.ON_OR_INSERT);
		
		list.setModel(model);
	}

	@Override
	public void setDropTarget(DropTarget dt) {
		list.setDropTarget(dt);
	}
}
