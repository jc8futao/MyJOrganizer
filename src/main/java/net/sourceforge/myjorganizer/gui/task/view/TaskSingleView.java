package net.sourceforge.myjorganizer.gui.task.view;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import java.awt.Component;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import net.sourceforge.myjorganizer.gui.task.model.TaskStatusModel;
import net.sourceforge.myjorganizer.jpa.entities.Priority;
import net.sourceforge.myjorganizer.jpa.entities.Task;
import net.sourceforge.myjorganizer.jpa.entities.TaskStatus;

public class TaskSingleView extends AbstractTaskView {

    private JButton saveButton;
    private JButton cancelButton;
    private JComboBox statusCombo;
    private final DefaultComboBoxModel statusComboModel = new DefaultComboBoxModel();
    private JCheckBox importantCheck;
    private JCheckBox urgentCheck;
    private JTextField idText;
    private JTextArea description;
    private JTextField titleText;
    private JTextField completionText;
    private JTextField startDateText;
    private JTextField dueDateText;
    private JLabel idLabel;
    private JLabel titleLabel;
    private JLabel descriptionLabel;
    private JLabel startDateLabel;
    private JLabel dueDateLabel;
    private JLabel completionLabel;
    private JLabel statusLabel;

    private final GroupLayout layout = new GroupLayout(this);
    private final ParallelGroup leftGroup = layout.createParallelGroup();
    private final ParallelGroup rightGroup = layout.createParallelGroup();
    private final SequentialGroup vGroup = layout.createSequentialGroup();
    private final SequentialGroup hGroup = layout.createSequentialGroup();

    /**
     * 
     */
    private static final long serialVersionUID = 5343663442729045215L;

    public TaskSingleView() {
        initComponents();
    }

    @Override
    public Observer getTaskSetModelObserver() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Observer getTaskStatusModelObserver() {
        return new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                TaskStatusModel model = (TaskStatusModel) o;

                statusComboModel.removeAllElements();

                for (TaskStatus status : model.getList()) {
                    statusComboModel.addElement(status);
                }
            }
        };
    }

    public void addSaveActionListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public void addCancelActionListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    private void initComponents() {
        layout.setHorizontalGroup(hGroup);
        layout.setVerticalGroup(vGroup);

        // Turn on automatically adding gaps between components
        layout.setAutoCreateGaps(true);

        // Turn on automatically creating gaps between components that touch
        // the edge of the container and the container.
        layout.setAutoCreateContainerGaps(true);

        hGroup.addGroup(leftGroup);
        hGroup.addGroup(rightGroup);

        setLayout(layout);

        idLabel = new JLabel(_("TASK_ID"));
        idText = new JTextField();

        addLeftRight(idLabel, idText);

        titleLabel = new JLabel(_("TASK_TITLE"));
        titleText = new JTextField();

        addLeftRight(titleLabel, titleText);

        descriptionLabel = new JLabel(_("TASK_DESCRIPTION"));
        description = new JTextArea();
        addLeftRight(descriptionLabel, new JScrollPane(description));

        urgentCheck = new JCheckBox(_("TASK_URGENT"));
        importantCheck = new JCheckBox(_("TASK_IMPORTANT"));
        addLeftRight(urgentCheck, importantCheck);

        startDateLabel = new JLabel(_("TASK_START_DATE"));
        startDateText = new JTextField();
        addLeftRight(startDateLabel, startDateText);

        dueDateLabel = new JLabel(_("TASK_DUE_DATE"));
        dueDateText = new JTextField();
        addLeftRight(dueDateLabel, dueDateText);

        completionLabel = new JLabel(_("TASK_COMPLETION_PERCENT"));
        completionText = new JTextField();
        addLeftRight(completionLabel, completionText);

        statusLabel = new JLabel(_("TASK_STATUS"));
        statusCombo = new JComboBox(statusComboModel);
        addLeftRight(statusLabel, statusCombo);

        saveButton = new JButton(_("TASK_SAVE"));
        cancelButton = new JButton(_("TASK_CANCEL"));
        addLeftRight(saveButton, cancelButton);
    }

    public void reset() {
        JTextComponent[] toReset = { idText, titleText, description,
                completionText, startDateText, dueDateText };

        for (JTextComponent c : toReset) {
            c.setText("");
        }

        JCheckBox[] checks = { urgentCheck, importantCheck };
        for (JCheckBox check : checks) {
            check.setSelected(false);
        }

        statusCombo.setSelectedIndex(-1);
    }

    /**
     * <p>
     * addLabeledComponent
     * </p>
     * 
     * @param left
     *            a {@link javax.swing.JLabel} object.
     * @param right
     *            a {@link javax.swing.JComponent} object.
     */
    public void addLeftRight(JComponent left, JComponent right) {
        leftGroup.addComponent(left);
        rightGroup.addComponent(right);

        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(left).addComponent(right));
    }

    public Task getTask() {
        Task task = new Task()
                .setId(idText.getText())
                .setTitle(titleText.getText())
                .setDescription(description.getText())
                .setPriority(
                        Priority.factory(urgentCheck.isSelected(),
                                importantCheck.isSelected()))
                .setCompletion(Integer.parseInt(completionText.getText()));

        Object selected = statusCombo.getSelectedItem();
        if (selected != null) {
            task.setStatus((TaskStatus) selected);
        }

        return task;
    }

}
