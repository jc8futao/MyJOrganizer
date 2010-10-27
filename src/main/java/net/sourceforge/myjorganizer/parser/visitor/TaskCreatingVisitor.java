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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import net.sourceforge.myjorganizer.data.Task;
import net.sourceforge.myjorganizer.parser.syntaxtree.ChildOf;
import net.sourceforge.myjorganizer.parser.syntaxtree.DependencyDefinition;
import net.sourceforge.myjorganizer.parser.syntaxtree.NodeToken;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskCompletion;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskDefinition;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskDescription;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskDueDate;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskImportance;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskStartDate;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskStatus;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskTitle;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskUrgency;

public class TaskCreatingVisitor extends DepthFirstVisitor {
	private boolean after;
	private Task currentTask;
	private ArrayList<Task> visitedTasks = new ArrayList<Task>();
	private HashMap<String, Task> taskByIdentifier = new HashMap<String, Task>();
	private HashMap<String, ArrayList<Task>> childrenMap = new HashMap<String, ArrayList<Task>>();
	private HashMap<Task, String> statuses = new HashMap<Task, String>();
	private PropertyParser propertyParser;

	private HashMap<String, ArrayList<Task>> afterDepMap = new HashMap<String, ArrayList<Task>>();
	private HashMap<String, ArrayList<Task>> beforeDepMap = new HashMap<String, ArrayList<Task>>();

	/**
	 * f0 -> <TASK> f1 -> [ <IDENTIFIER> ] f2 -> [ ChildOf() ] f3 -> <COLON> f4
	 * -> TaskTitle() f5 -> [ TaskDescription() ] f6 -> [ TaskCompletion() ] f7
	 * -> [ TaskUrgency() ] f8 -> [ TaskImportance() ] f9 -> [ TaskStatus() ]
	 * f10 -> [ DependencyList() ] f11 -> <END> f12 -> <TASK>
	 */
	public void visit(TaskDefinition n) {
		visitedTasks.add(currentTask = new Task());

		this.propertyParser = new PropertyParser() {

			@Override
			public void setValue(String value) {
				currentTask.setIdentifier(value);
				taskByIdentifier.put(value, currentTask);
			}
		};

		n.f1.accept(this);
		this.propertyParser = null;

		n.f2.accept(this);

		n.f4.accept(this);
		n.f5.accept(this);
		n.f6.accept(this);
		n.f7.accept(this);
		n.f8.accept(this);
		n.f9.accept(this);
		n.f10.accept(this);
	}

	/**
	 * f0 -> "childof" f1 -> <COLON> f2 -> <IDENTIFIER>
	 */
	public void visit(ChildOf n) {
		this.propertyParser = new PropertyParser() {

			@Override
			public void setValue(String value) {
				addChild(value, currentTask);
			}
		};

		n.f2.accept(this);

		this.propertyParser = null;
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

	public void visit(TaskDescription n) {
		this.propertyParser = new PropertyParser() {

			@Override
			public void setValue(String value) {
				currentTask.setDescription(value);

				System.err.println("Description: " + value);
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

	/**
	 * f0 -> "startdate" f1 -> <COLON> f2 -> <DATE_LITERAL>
	 */
	public void visit(TaskStartDate n) {
		this.propertyParser = new PropertyParser() {
			@Override
			public void setValue(String value) {
				currentTask.setStartDate(convertDate(value));
			}
		};

		n.f2.accept(this);

		this.propertyParser = null;
	}

	/**
	 * f0 -> "duedate" f1 -> <COLON> f2 -> <DATE_LITERAL>
	 */
	public void visit(TaskDueDate n) {
		this.propertyParser = new PropertyParser() {
			@Override
			public void setValue(String value) {
				currentTask.setDueDate(convertDate(value));
			}
		};

		n.f2.accept(this);

		this.propertyParser = null;
	}

	public void visit(TaskStatus n) {
		this.propertyParser = new PropertyParser() {

			@Override
			public void setValue(String value) {
				statuses.put(currentTask, value);
			}
		};

		n.f2.accept(this);

		this.propertyParser = null;
	}

	/**
	 * f0 -> ( "before" | "after" ) f1 -> <IDENTIFIER> f2 -> ( <COMMA>
	 * <IDENTIFIER> )*
	 */
	public void visit(DependencyDefinition n) {
		this.propertyParser = new PropertyParser() {

			@Override
			public void setValue(String value) {
				after = value.equals("after");
			}
		};
		n.f0.accept(this);

		this.propertyParser = new PropertyParser() {

			@Override
			public void setValue(String value) {
				addToMap(after ? afterDepMap : beforeDepMap, value, currentTask);
			}
		};

		n.f1.accept(this);

		this.propertyParser = null;
	}

	@Override
	public void visit(NodeToken n) {
		if (propertyParser != null) {
			propertyParser.setValue(n.tokenImage);
		}
	}

	public ArrayList<Task> getVisitedTasks() {
		return visitedTasks;
	}

	private interface PropertyParser {
		public void setValue(String value);
	}

	private static Date convertDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	private void addChild(String parentIdentifier, Task child) {
		addToMap(childrenMap, parentIdentifier, child);
	}

	private void addToMap(HashMap<String, ArrayList<Task>> map,
			String identifier, Task task) {
		ArrayList<Task> list = map.get(identifier);

		if (list == null) {
			map.put(identifier, list = new ArrayList<Task>());
		}

		list.add(task);
	}

	private static String unescapeString(String escaped) {
		return escaped;
	}
}
