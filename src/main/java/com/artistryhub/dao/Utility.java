package com.artistryhub.dao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

public class Utility {
	private static ObjectContainer manager=null;
	
	public static ObjectContainer connectDataBase(){
		if (manager != null)
			return manager;
		EmbeddedConfiguration setting =  Db4oEmbedded.newConfiguration(); 

		manager = Db4oEmbedded.openFile(setting, "database.db4o");
		return manager;
	}
	
	public static void disconnect() {
		if(manager!=null) {
			manager.close();
			manager=null;
		}
	}
}
