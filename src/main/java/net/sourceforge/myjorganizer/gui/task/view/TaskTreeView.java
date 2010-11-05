package net.sourceforge.myjorganizer.gui.task.view;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import net.sourceforge.myjorganizer.gui.task.model.TaskSetModel;
import net.sourceforge.myjorganizer.jpa.entities.Task;
import net.sourceforge.myjorganizer.jpa.entities.TaskDependency;

public class TaskTreeView extends AbstractTaskView implements Observer {

    private JTree tree;
    private DefaultMutableTreeNode root = new DefaultMutableTreeNode(_("TASKS"));
    private DefaultTreeModel treeModel = new DefaultTreeModel(root);
    /**
     * 
     */
    private static final long serialVersionUID = -6727909398022969171L;

    public TaskTreeView() {
        this.tree = new JTree(treeModel);
        setLayout(new GridLayout(1, 1));
        add(tree);
    }

    @Override
    public Observer getTaskSetModelObserver() {
        return this;
    }

    @Override
    public Observer getTaskStatusModelObserver() {
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        TaskSetModel model = (TaskSetModel) o;

        root.removeAllChildren();

        for (Task t : model.getList()) {
            if (t.getParent() == null) {
                addWithChildren(root, t);
            }
        }
        
        treeModel.reload();
    }

    private void addWithChildren(DefaultMutableTreeNode node, Task t) {
        DefaultMutableTreeNode currentNode = new DefaultMutableTreeNode(t);

        if (t.getChildren().size() > 0) {
            DefaultMutableTreeNode childrenNode = new DefaultMutableTreeNode(
                    _("TASK_CHILDREN"));

            for (Task child : t.getChildren()) {
                addWithChildren(childrenNode, child);
            }

            currentNode.add(childrenNode);
        }

        if (t.getDependencies().size() > 0) {
            DefaultMutableTreeNode depNode = new DefaultMutableTreeNode(
                    _("TASK_DEPENDENCIES"));

            for (TaskDependency dep : t.getDependencies()) {
                depNode.add(new DefaultMutableTreeNode(dep));
            }
            
            currentNode.add(depNode);
        }

        node.add(currentNode);
    }
}
