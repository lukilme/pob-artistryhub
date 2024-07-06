package com.artistryhub.dao;

import com.artistryhub.model.Artist;
import com.artistryhub.model.City;
import com.artistryhub.model.Presentation;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;



public class Utility {
	private static ObjectContainer manager=null;
	
	public static ObjectContainer connectDataBase(){
		if (manager != null)
			return manager;
		EmbeddedConfiguration setting =  Db4oEmbedded.newConfiguration(); 

		setting.common().objectClass(Artist.class).cascadeOnDelete(false);
		setting.common().objectClass(Artist.class).cascadeOnUpdate(true);;
		setting.common().objectClass(Artist.class).cascadeOnActivate(true);
		setting.common().objectClass(City.class).cascadeOnDelete(false);
		setting.common().objectClass(City.class).cascadeOnUpdate(true);;
		setting.common().objectClass(City.class).cascadeOnActivate(true);
		setting.common().objectClass(Presentation.class).cascadeOnDelete(false);
		setting.common().objectClass(Presentation.class).cascadeOnUpdate(true);;
		setting.common().objectClass(Presentation.class).cascadeOnActivate(true);
		
		setting.common().objectClass(City.class).objectField("id").indexed(true);
		setting.common().objectClass(Artist.class).objectField("id").indexed(true);
		setting.common().objectClass(Artist.class).objectField("name").indexed(true);
		
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
