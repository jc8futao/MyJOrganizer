/**
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
public class TaskStatCompletionView extends AbstractTaskSubView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5013464742592880984L;
	/**
	 * 
	 */
	private DefaultPieDataset completionDataSet = new DefaultPieDataset();

	public TaskStatCompletionView() {
		super(new GridLayout(1, 1));

		add(createCompletionPanel());
	}

	@Override
	public void update(Observable o, Object arg) {
		TaskSetModel model = (TaskSetModel) o;

		int complete = 0;
		int open = 0;

		for (Task task : model.getList()) {
			if (task.getCompletion() == 100) {
				complete++;
			} else {
				open++;
			}
		}

		completionDataSet.setValue(_("TASK_COMPLETED"), complete);
		completionDataSet.setValue(_("TASK_NOTCOMPLETED"), open);
	}

	/**
	 * Creates a panel for the demo (used by SuperDemo.java).
	 * 
	 * @return A panel.
	 */
	protected JPanel createCompletionPanel() {
		JFreeChart chart = createChart(completionDataSet, _("TASK_COMPLETION"));
		return new ChartPanel(chart);
	}
}
