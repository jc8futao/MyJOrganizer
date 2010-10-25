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

package net.sourceforge.myjorganizer.gui.mvc.view;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import net.sourceforge.myjorganizer.data.Task;
import net.sourceforge.myjorganizer.gui.mvc.model.TaskSetModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

// TODO refactoring
public class TaskStatView extends AbstractTaskView implements TaskView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2541380650705637543L;

	private JTabbedPane tabbedPane = new JTabbedPane();
	private DefaultPieDataset completionDataSet = new DefaultPieDataset();
	private DefaultPieDataset priorityDataSet = new DefaultPieDataset();

	public TaskStatView() {
		super(new GridLayout(1, 1));

		tabbedPane.add(createCompletionPanel());
		tabbedPane.add(createPriorityPanel());

		tabbedPane.setTitleAt(0, _("TASK_COMPLETION"));
		tabbedPane.setTitleAt(1, _("TASK_PRIORITY"));

		this.add(tabbedPane);
	}

	@Override
	public void update(Observable o, Object arg) {
		TaskSetModel model = (TaskSetModel) o;

		int complete = 0;
		int open = 0;

		int urgentImportant = 0;
		int notUrgentImportant = 0;
		int urgentNotImportant = 0;
		int notUrgentNotImportant = 0;

		for (Task task : model.getList()) {
			if (task.getCompletion() == 100) {
				complete++;
			} else {
				open++;
			}

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

		completionDataSet.setValue(_("TASK_COMPLETED"), complete);
		completionDataSet.setValue(_("TASK_NOTCOMPLETED"), open);
		
		priorityDataSet.setValue(_("URGENT_IMPORTANT"), urgentImportant);
		priorityDataSet.setValue(_("NOT_URGENT_IMPORTANT"), notUrgentImportant);
		priorityDataSet.setValue(_("URGENT_NOT_IMPORTANT"), urgentNotImportant);
		priorityDataSet.setValue(_("NOT_URGENT_NOT_IMPORTANT"), notUrgentNotImportant);
	}

	/**
	 * Creates a chart.
	 * 
	 * @param dataset
	 *            the dataset.
	 * 
	 * @return A chart.
	 */
	private static JFreeChart createChart(PieDataset dataset, String title) {

		JFreeChart chart = ChartFactory.createPieChart(title, // chart
																// title
				dataset, // data
				true, // include legend
				true, false);

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage(_("CHART_NODATA"));
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		return chart;
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
