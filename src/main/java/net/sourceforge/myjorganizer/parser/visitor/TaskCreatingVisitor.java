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

package net.sourceforge.myjorganizer.parser.visitor;

import java.util.ArrayList;

import net.sourceforge.myjorganizer.data.Task;
import net.sourceforge.myjorganizer.parser.syntaxtree.NodeToken;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskCompletion;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskDefinition;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskImportance;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskTitle;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskUrgency;

public class TaskCreatingVisitor extends DepthFirstVisitor {
	private Task currentTask;
	private ArrayList<Task> visitedTasks = new ArrayList<Task>();
	private PropertyParser propertyParser;

	@Override
	public void visit(TaskDefinition n) {
		this.currentTask = new Task();
		visitedTasks.add(this.currentTask);

		super.visit(n);
	}

	/**
	 * f0 -> "title" f1 -> <COLON> f2 -> <STRING_LITERAL>
	 */
	public void visit(TaskTitle n) {
		this.propertyParser = new PropertyParser() {

			@Override
			public void setValue(String value) {
				currentTask.setTitle(value);

				System.err.println("Title: " + value);
			}
		};

		n.f2.accept(this);

		this.propertyParser = null;
	}

	/**
	 * f0 -> "completion" f1 -> <COLON> f2 -> <INTEGER_LITERAL> f3 -> <PERCENT>
	 */
	public void visit(TaskCompletion n) {
		this.propertyParser = new PropertyParser() {
			@Override
			public void setValue(String value) {
				currentTask.setCompletion(Integer.parseInt(value));
			}
		};

		n.f2.accept(this);

		this.propertyParser = null;
	}

	/**
	 * f0 -> "urgent" f1 -> <COLON> f2 -> <BOOL_LITERAL>
	 */
	public void visit(TaskUrgency n) {
		this.propertyParser = new PropertyParser() {
			@Override
			public void setValue(String value) {
				currentTask.setUrgent(value.equals("true"));				
			}
		};
		
		n.f2.accept(this);
		
		this.propertyParser = null;
	}
	
	/**
	 * f0 -> "important" f1 -> <COLON> f2 -> <BOOL_LITERAL>
	 */
	public void visit(TaskImportance n) {
		this.propertyParser = new PropertyParser() {
			@Override
			public void setValue(String value) {
				currentTask.setImportant(value.equals("true"));				
			}
		};
		
		n.f2.accept(this);
		
		this.propertyParser = null;
	}

	@Override
	public void visit(NodeToken n) {
		if (propertyParser != null) {
			propertyParser.setValue(n.tokenImage);
		}
	}

	private interface PropertyParser {
		public void setValue(String value);
	}
}
