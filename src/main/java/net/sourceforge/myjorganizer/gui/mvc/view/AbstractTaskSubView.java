package net.sourceforge.myjorganizer.gui.mvc.view;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import java.awt.Font;
import java.awt.LayoutManager;
import java.util.Observer;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;

public abstract class AbstractTaskSubView extends JPanel implements Observer{

	/**
	 * Creates a chart.
	 * 
	 * @param dataset
	 *            the dataset.
	 * 
	 * @return A chart.
	 */
	protected static JFreeChart createChart(PieDataset dataset, String title) {
	
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

	public AbstractTaskSubView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AbstractTaskSubView(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public AbstractTaskSubView(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public AbstractTaskSubView(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7883840848791383229L;

}
