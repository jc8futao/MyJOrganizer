package net.sourceforge.myjorganizer.parser.visitor;

import static net.sourceforge.myjorganizer.parser.StringUtils.unescape;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import net.sourceforge.myjorganizer.gui.task.model.TaskDependencyModel;
import net.sourceforge.myjorganizer.gui.task.model.TaskModels;
import net.sourceforge.myjorganizer.gui.task.model.TaskSetModel;
import net.sourceforge.myjorganizer.gui.task.model.TaskStatusModel;
import net.sourceforge.myjorganizer.jpa.entities.Task;
import net.sourceforge.myjorganizer.jpa.entities.TaskDependency;
import net.sourceforge.myjorganizer.parser.syntaxtree.DependencyAdd;
import net.sourceforge.myjorganizer.parser.syntaxtree.DependencyDefinition;
import net.sourceforge.myjorganizer.parser.syntaxtree.DependencyDelete;
import net.sourceforge.myjorganizer.parser.syntaxtree.DependencyList;
import net.sourceforge.myjorganizer.parser.syntaxtree.EditableField;
import net.sourceforge.myjorganizer.parser.syntaxtree.NodeToken;
import net.sourceforge.myjorganizer.parser.syntaxtree.OptionalField;
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
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskUpdateCommand;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskUrgency;
import net.sourceforge.myjorganizer.parser.syntaxtree.UpdateField;

/**
 * 
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * 
 */
public class ExecutingVisitor extends AbstractDepthFirstVisitor {

    private TaskSetModel taskModel;
    private TaskStatusModel statusModel;
    private Task currentTask;
    private TaskDependencyModel depModel;
    private TokenParser tokenParser;
    private boolean before;
    private ArrayList<TaskDependency> dependenciesToAdd = new ArrayList<TaskDependency>();
    private DependencyMode depMode = DependencyMode.ADD;
    private HashMap<DependencyMode, DependencyParser> parserModes = new HashMap<ExecutingVisitor.DependencyMode, ExecutingVisitor.DependencyParser>();
    
    public ExecutingVisitor(TaskModels models) {
        this.taskModel = models.getTaskModel();
        this.statusModel = models.getStatusModel();
        this.depModel = models.getDependencyModel();
        
        parserModes.put(DependencyMode.ADD, new DependencyParser() {
            @Override
            public void parse(boolean before, String taskId) {
                Task otherTask = taskModel.find(taskId);
                TaskDependency dependency;

                if (before) {
                    dependency = TaskDependency.before(currentTask, otherTask);
                } else {
                    dependency = TaskDependency.after(currentTask, otherTask);
                }

                dependency.getLeft().getLeftDependencies().add(dependency);
                dependenciesToAdd.add(dependency);
            }
        });
        
        parserModes.put(DependencyMode.DELETE, new DependencyParser() {

            @Override
            public void parse(boolean before, String taskId) {
                String left = before ? currentTask.getId() : taskId;
                String right = before ? taskId : currentTask.getId();
                
                depModel.deleteFromId(left, right);            
            }
        });
    }

    @Override
    public void visit(NodeToken n) {
        if (this.tokenParser != null) {
            this.tokenParser.parseToken(n.tokenImage);
        }
    }

    /**
     * Grammar production: f0 -> <INSERT> f1 -> TaskDefinition()
     */
    @Override
    public void visit(TaskInsertCommand n) {
        this.currentTask = new Task();
        n.f1.accept(this);

        taskModel.add(currentTask);
        depModel.addMany(dependenciesToAdd);
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
    /**
     * Grammar production:
     * f0 -> <DONE>
     * f1 -> <TASK>
     * f2 -> <IDENTIFIER>
     */
    public void visit(TaskDoneCommand n) {
        taskModel.markAsDone(n.f2.tokenImage);
    }

    @Override
    /**
     * Grammar production:
     * f0 -> <TASK>
     * f1 -> <IDENTIFIER>
     * f2 -> <COLON>
     * f3 -> TaskTitle()
     * f4 -> ( OptionalField() )*
     * f5 -> <END>
     * f6 -> <TASK>
     */
    public void visit(TaskDefinition n) {
        currentTask.setId(n.f1.tokenImage);

        n.f3.accept(this);
        n.f4.accept(this);
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
    /**
     * Grammar production:
     * <PRE>
     * f0 -> &lt;DESCRIPTION&gt;
     * f1 -> &lt;COLON&gt;
     * f2 -> ( &lt;STRING_LITERAL&gt; | &lt;NULL&gt; )
     * </PRE>
     */
    public void visit(TaskDescription n) {
        tokenParser = new TokenParser() {

            @Override
            public void parseToken(String node) {
                if ("null".equals(node)) {
                    currentTask.setDescription(null);
                } else {
                    currentTask.setDescription(unescape(node));
                }
            }
        };
        
        n.f2.accept(this);
        tokenParser = null;
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
        tokenParser = new TokenParser() {

            @Override
            public void parseToken(String node) {
                if ("null".equals(node)) {
                    currentTask.setStartDate(null);
                } else {
                    try {
                        currentTask.setDueDate(formatter.parse(node));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        n.f2.accept(this);

        tokenParser = null;
    }

    @Override
    public void visit(TaskDueDate n) {
        tokenParser = new TokenParser() {

            @Override
            public void parseToken(String node) {
                if ("null".equals(node)) {
                    currentTask.setDueDate(null);
                } else {
                    try {
                        currentTask.setDueDate(formatter.parse(node));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        n.f2.accept(this);

        tokenParser = null;
    }

    @Override
    public void visit(TaskStatus n) {
        net.sourceforge.myjorganizer.jpa.entities.TaskStatus status = statusModel
                .getById(unescape(n.f2.tokenImage));

        currentTask.setStatus(status);

    }

    @Override
    /**
     * Grammar production:
     * f0 -> ( <BEFORE> | <AFTER> )
     * f1 -> <IDENTIFIER>
     */
    public void visit(DependencyDefinition n) {
        this.tokenParser = new TokenParser() {
            @Override
            public void parseToken(String node) {
                before = "before".equals(node);
            }
        };

        n.f0.accept(this);
        
        this.tokenParser = null;
        
        parserModes.get(depMode).parse(before, n.f1.tokenImage);
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

    @Override
    public void visit(OptionalField n) {
        n.f0.accept(this);
    }

    @Override
    /**
     * Grammar production:
     * <PRE>
     * f0 -> &lt;UPDATE&gt;
     * f1 -> &lt;TASK&gt;
     * f2 -> &lt;IDENTIFIER&gt;
     * f3 -> &lt;COLON&gt;
     * f4 -> ( UpdateField() )+
     * f5 -> &lt;END&gt;
     * f6 -> &lt;UPDATE&gt;
     * </PRE>
     */
    public void visit(TaskUpdateCommand n) {
        currentTask = taskModel.find(n.f2.tokenImage);
        
        n.f4.accept(this);
        
        taskModel.update(currentTask);
    }

    @Override
    /**
     * Grammar production:
     * <PRE>
     * f0 -> TaskTitle()
     *       | TaskCompletion()
     *       | TaskUrgency()
     *       | TaskImportance()
     *       | TaskStatus()
     *       | TaskStartDate()
     *       | TaskDueDate()
     * </PRE>
     */
    public void visit(EditableField n) {
        n.f0.accept(this);
    }

    @Override
    /**
     * Grammar production:
     * <PRE>
     * f0 -> &lt;SET&gt; EditableField()
     *       | DependencyAdd()
     *       | DependencyDelete()
     * </PRE>
     */
    public void visit(UpdateField n) {
        n.f0.accept(this);
    }

    @Override
    /**
     * Grammar production:
     * <PRE>
     * f0 -> &lt;ADD&gt;
     * f1 -> &lt;DEPENDENCY&gt;
     * f2 -> DependencyDefinition()
     * </PRE>
     */
    public void visit(DependencyAdd n) {
        depMode=DependencyMode.ADD;
        n.f2.accept(this);
    }

    @Override
    public void visit(DependencyDelete n) {
        depMode=DependencyMode.DELETE;
        n.f2.accept(this);
    }

    private enum DependencyMode { ADD, DELETE }

    private interface TokenParser {
        public void parseToken(String node);
    }
    
    private interface DependencyParser {
        public void parse(boolean before, String taskId);
    }
}