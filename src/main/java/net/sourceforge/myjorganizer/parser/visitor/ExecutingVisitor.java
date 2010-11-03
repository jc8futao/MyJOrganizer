package net.sourceforge.myjorganizer.parser.visitor;

import static net.sourceforge.myjorganizer.parser.StringUtils.unescape;
import net.sourceforge.myjorganizer.gui.task.model.TaskSetModel;
import net.sourceforge.myjorganizer.gui.task.model.TaskStatusModel;
import net.sourceforge.myjorganizer.jpa.entities.Task;
import net.sourceforge.myjorganizer.parser.syntaxtree.ChildOf;
import net.sourceforge.myjorganizer.parser.syntaxtree.DependencyDefinition;
import net.sourceforge.myjorganizer.parser.syntaxtree.DependencyList;
import net.sourceforge.myjorganizer.parser.syntaxtree.NodeToken;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskCommand;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskCommands;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskCompletion;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskDefinition;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskDeleteCommand;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskDescription;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskDoneCommand;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskDueDate;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskImportance;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskInsertCommand;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskStartDate;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskStatus;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskTitle;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskUrgency;

public class ExecutingVisitor extends AbstractDepthFirstVisitor {

    private TaskSetModel taskModel;
    private TaskStatusModel statusModel;
    private Task currentTask;

    public ExecutingVisitor(TaskSetModel taskModel, TaskStatusModel statusModel) {
        this.taskModel = taskModel;
        this.statusModel = statusModel;
    }

    @Override
    public void visit(NodeToken n) {
    }

    @Override
    /**
     * Grammar production:
     * f0 -> ( TaskCommand() )*
     */
    public void visit(TaskCommands n) {
        n.f0.accept(this);
    }

    @Override
    public void visit(TaskCommand n) {
        n.f0.accept(this);
    }

    /**
     * Grammar production: f0 -> <INSERT> f1 -> TaskDefinition()
     */
    @Override
    public void visit(TaskInsertCommand n) {
        this.currentTask = new Task();
        n.f1.accept(this);
        taskModel.add(currentTask);
    }

    @Override
    /**
     * Grammar production:
     * f0 -> <DELETE>
     * f1 -> <TASK>
     * f2 -> <IDENTIFIER>
     */
    public void visit(TaskDeleteCommand n) {
        taskModel.delete(n.f2.tokenImage);
    }

    @Override
    public void visit(TaskDoneCommand n) {
        taskModel.markAsDone(n.f2.tokenImage);
    }

    @Override
    /**
     * Grammar production:
     * f0 -> <TASK>
     * f1 -> <IDENTIFIER>
     * f2 -> [ ChildOf() ]
     * f3 -> <COLON>
     * f4 -> TaskTitle()
     * f5 -> [ TaskDescription() ]
     * f6 -> [ TaskCompletion() ]
     * f7 -> [ TaskUrgency() ]
     * f8 -> [ TaskImportance() ]
     * f9 -> [ TaskStatus() ]
     * f10 -> [ DependencyList() ]
     * f11 -> <END>
     * f12 -> <TASK>
     */
    public void visit(TaskDefinition n) {
        currentTask.setId(n.f1.tokenImage);

        n.f2.accept(this);
        n.f4.accept(this);
        n.f5.accept(this);
        n.f6.accept(this);
        n.f7.accept(this);
        n.f8.accept(this);
        n.f9.accept(this);
        n.f10.accept(this);
    }

    @Override
    /**
     * Grammar production:
     * f0 -> <CHILDOF>
     * f1 -> <COLON>
     * f2 -> <IDENTIFIER>
     */
    public void visit(ChildOf n) {
        n.f2.accept(this);
    }

    @Override
    /**
     * Grammar production:
     * f0 -> <TITLE>
     * f1 -> <COLON>
     * f2 -> <STRING_LITERAL>
     */
    public void visit(TaskTitle n) {
        currentTask.setTitle(unescape(n.f2.tokenImage));
    }

    @Override
    public void visit(TaskDescription n) {
        currentTask.setDescription(unescape(n.f2.tokenImage));
    }

    @Override
    public void visit(TaskCompletion n) {
        currentTask.setCompletion(Integer.parseInt(n.f2.tokenImage));
    }

    @Override
    public void visit(TaskUrgency n) {
        currentTask.setUrgent("true".equals(n.f2.tokenImage));
    }

    @Override
    public void visit(TaskImportance n) {
        currentTask.setImportant("true".equals(n.f2.tokenImage));
    }

    @Override
    public void visit(TaskStartDate n) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(TaskDueDate n) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(TaskStatus n) {
        net.sourceforge.myjorganizer.jpa.entities.TaskStatus status = statusModel.getById(unescape(n.f2.tokenImage));
        
        currentTask.setStatus(status);
        
        
    }

    @Override
    public void visit(DependencyDefinition n) {
        // TODO Auto-generated method stub

    }

    @Override
    /**
     * Grammar production:
     * f0 -> <DEPENDENCIES>
     * f1 -> <COLON>
     * f2 -> ( DependencyDefinition() )+
     * f3 -> <END>
     * f4 -> <DEPENDENCIES>
     */
    public void visit(DependencyList n) {
        n.f2.accept(this);
    }
}
