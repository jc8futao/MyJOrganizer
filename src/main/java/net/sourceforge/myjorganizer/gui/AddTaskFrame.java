package net.sourceforge.myjorganizer.gui;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddTaskFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8074892116502385747L;

	private JLabel newTaskLabel;
	private JLabel taskTitleLabel;
	private JLabel taskDescriptionLabel;
	private JLabel taskUrgentLabel;
	private JLabel taskImportantLabel;
	private JLabel taskStartDateLabel;
	private JLabel taskDueDateLabel;
	private JLabel taskStatusLabel;

	private final JTextField taskTitleText = new JTextField();
	private final JTextArea taskDescriptionText = new JTextArea();
	private final JCheckBox taskUrgentCheck = new JCheckBox(_("YES"));
	private final JCheckBox taskImportantCheck = new JCheckBox(_("YES"));
	private final JTextField taskStartDateText = new JTextField();
	private final JTextField taskDueDateText = new JTextField();
	private final JComboBox taskStatusCombo = new JComboBox();

	private AddTaskPanel contentPane;

	public AddTaskFrame() {
		initComponents();
	}

	private void initComponents() {
		this.setTitle(_("NEW_TASK"));

		// instantiating title label
		newTaskLabel = new JLabel(_("NEW_TASK"));
		Font newFont = newTaskLabel.getFont().deriveFont(20.0F);
		newTaskLabel.setFont(newFont);

		// instantiating other labels
		taskTitleLabel = new JLabel(_("TASK_TITLE"));
		taskDescriptionLabel = new JLabel(_("TASK_DESCRIPTION"));
		taskUrgentLabel = new JLabel(_("TASK_URGENT"));
		taskImportantLabel = new JLabel(_("TASK_IMPORTANT"));
		taskStartDateLabel = new JLabel(_("TASK_START_DATE"));
		taskDueDateLabel = new JLabel(_("TASK_DUE_DATE"));
		taskStatusLabel = new JLabel(_("TASK_STATUS"));

		setContentPane(contentPane = new AddTaskPanel());
		
		contentPane.addLabeledComponent(taskTitleLabel, taskTitleText);
		contentPane.addLabeledComponent(taskDescriptionLabel, taskDescriptionText);
		contentPane.addLabeledComponent(taskUrgentLabel, taskUrgentCheck);
		contentPane.addLabeledComponent(taskImportantLabel, taskImportantCheck);
		contentPane.addLabeledComponent(taskStartDateLabel, taskStartDateText);
		contentPane.addLabeledComponent(taskDueDateLabel, taskDueDateText);
		contentPane.addLabeledComponent(taskStatusLabel, taskStatusCombo);

		setSize(400, 350);
	}
}
