/*
 * This file is part of MyJOrganizer.
 *
 * MyJOrganizer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MyJOrganizer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MyJOrganizer.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.sourceforge.myjorganizer.gui.task.view;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import java.awt.GridLayout;
import java.util.Observable;

import javax.swing.JPanel;

import net.sourceforge.myjorganizer.data.Task;
import net.sourceforge.myjorganizer.gui.task.model.TaskSetModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

// TODO refactoring
public class TaskStatPriorityView extends AbstractTaskSubView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9168880772692119068L;
	private DefaultPieDataset priorityDataSet = new DefaultPieDataset();

	public TaskStatPriorityView() {
		super(new GridLayout(1, 1));

		this.add(createPriorityPanel());
	}

	@Override
	public void update(Observable o, Object arg) {
		TaskSetModel model = (TaskSetModel) o;

		int urgentImportant = 0;
		int notUrgentImportant = 0;
		int urgentNotImportant = 0;
		int notUrgentNotImportant = 0;

		for (Task task : model.getList()) {
			if (task.isUrgent()) {
				if (task.isImportant()) {
					urgentImportant++;
				} else {
					urgentNotImportant++;
				}
			} else {
				if (task.isImportant()) {
					notUrgentImportant++;
				} else {
					notUrgentNotImportant++;
				}
			}

		}

		priorityDataSet.setValue(_("URGENT_IMPORTANT"), urgentImportant);
		priorityDataSet.setValue(_("NOT_URGENT_IMPORTANT"), notUrgentImportant);
		priorityDataSet.setValue(_("URGENT_NOT_IMPORTANT"), urgentNotImportant);
		priorityDataSet.setValue(_("NOT_URGENT_NOT_IMPORTANT"),
				notUrgentNotImportant);
	}

	/**
	 * Creates a panel for the demo (used by SuperDemo.java).
	 * 
	 * @return A panel.
	 */
	protected JPanel createPriorityPanel() {
		JFreeChart chart = createChart(priorityDataSet, _("TASK_PRIORITY"));
		return new ChartPanel(chart);
	}
}
