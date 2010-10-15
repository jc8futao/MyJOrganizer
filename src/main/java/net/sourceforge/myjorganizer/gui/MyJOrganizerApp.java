package net.sourceforge.myjorganizer.gui;

import static net.sourceforge.myjorganizer.i18n.Translator._;

import java.util.Arrays;
import java.util.EventObject;

import javax.swing.JOptionPane;

import net.sourceforge.myjorganizer.data.HibernateUtil;

import org.hibernate.SessionFactory;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

public class MyJOrganizerApp extends SingleFrameApplication {

	public MyJOrganizerApp() {
		getContext().getResourceManager().setApplicationBundleNames(Arrays.asList("net.sourceforge.myjorganizer.gui.resources.MyJOrganizerApp"));
	}
	public static void main(String args[]) {
		launch(MyJOrganizerApp.class, args);
	}

	public static MyJOrganizerApp getApplication() {
		return Application.getInstance(MyJOrganizerApp.class);
	}

	private String language;
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	protected void startup() {
		registerExitListener();
		
		this.sessionFactory = HibernateUtil.getSessionFactory();

		show(new MainView(this));
	}

	private void registerExitListener() {
		ExitListener maybeExit = new ExitListener() {
			@Override
			public boolean canExit(EventObject e) {
				int option = JOptionPane.showConfirmDialog(null,
						_("REALLY_EXIT"), _("EXIT"),
						JOptionPane.YES_NO_OPTION);
				return option == JOptionPane.YES_OPTION;
			}

			@Override
			public void willExit(EventObject e) {
			}
		};
		addExitListener(maybeExit);
	}


	public String getLanguage() {
		return this.language;
	}
}
