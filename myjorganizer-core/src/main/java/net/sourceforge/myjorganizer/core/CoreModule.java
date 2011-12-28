package net.sourceforge.myjorganizer.core;

import net.sourceforge.myjorganizer.dao.DAOModule;

import com.google.inject.AbstractModule;

public class CoreModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new DAOModule());
	}

}
