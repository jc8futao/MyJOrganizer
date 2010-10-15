package net.sourceforge.myjorganizer.gui;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddTaskPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5294764363832679648L;

	private final GroupLayout layout = new GroupLayout(this);
	private final ParallelGroup labelGroup = layout.createParallelGroup();
	private final ParallelGroup componentGroup = layout.createParallelGroup();
	private final SequentialGroup vGroup = layout.createSequentialGroup();
	private final SequentialGroup hGroup = layout.createSequentialGroup();

	public AddTaskPanel() {
		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);
		
		// Turn on automatically adding gaps between components
		layout.setAutoCreateGaps(true);

		// Turn on automatically creating gaps between components that touch
		// the edge of the container and the container.
		layout.setAutoCreateContainerGaps(true);
		
		hGroup.addGroup(labelGroup);
		hGroup.addGroup(componentGroup);
		
		setLayout(layout);
	}

	public void addLabeledComponent(JLabel label, JComponent component) {
		labelGroup.addComponent(label);
		componentGroup.addComponent(component);

		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(label).addComponent(component));
	}
}
