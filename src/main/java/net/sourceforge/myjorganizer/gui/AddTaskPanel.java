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
