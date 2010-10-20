package net.sourceforge.myjorganizer.gui.mvc.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.sourceforge.myjorganizer.gui.mvc.model.TaskSetModel;

public class TaskJListView extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1394543402648078273L;
	private JList jlist = new JList();

	public TaskJListView() {
		this.add(new JScrollPane(this.jlist));
	}

	@Override
	public void update(Observable o, Object arg) {
		TaskSetModel model = (TaskSetModel)o;
		setListData(model.getList().toArray());
	}

	public void setListData(Object[] array) {
		this.jlist.setListData(array);
	}
}
