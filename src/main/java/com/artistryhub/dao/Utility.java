package com.artistryhub.dao;

import java.util.Properties;

import org.apache.log4j.Logger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Utility {

    private static EntityManager manager;
	private static EntityManagerFactory factory;
    
    private static final Logger logger = Logger.getLogger(Utility.class);
    
    public static EntityManager connectDataBase() {
    	if(manager == null) {
    		try {
				logger.info("----conectar banco - lendo arquivo de propriedades...");

				String sgbd = "postgresql";
				String banco = "artistryhub";
				String ip = "localhost";
				logger.info("sgbd => " + sgbd);
				logger.info("banco => " + banco);
				logger.info("ip => " + ip);

				Properties configuracoes = new Properties();
				if (sgbd.equals("postgresql")) {
					logger.info("----configurando postgresql");
					configuracoes.setProperty("jakarta.persistence.jdbc.driver", "org.postgresql.Driver");
					configuracoes.setProperty("jakarta.persistence.jdbc.url", "jdbc:postgresql://postgres:5432/" + banco); // Altere aqui
					configuracoes.setProperty("jakarta.persistence.jdbc.user", "postgres");
					configuracoes.setProperty("jakarta.persistence.jdbc.password", "password");
				}
				if (sgbd.equals("mysql")) {
					logger.info("----configurando mysql");
					configuracoes.setProperty("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
					configuracoes.setProperty("jakarta.persistence.jdbc.url",
							"jdbc:mysql://" + ip + ":3306/" + banco + "?createDatabaseIfNotExist=true");
					configuracoes.setProperty("jakarta.persistence.jdbc.user", "root");
					configuracoes.setProperty("jakarta.persistence.jdbc.password", "");
				}

				// -----------------------------------------------------------------------------------
				String unit_name = "hibernate" + "-" + sgbd;
				factory = Persistence.createEntityManagerFactory(unit_name, configuracoes);
				manager = factory.createEntityManager();
    		}catch(Exception e) {
    			logger.info("Something wrong happness: " + e.getMessage());
    			System.out.println(e);
				System.exit(0);
    		}
    	}
    	return manager;
//        EmbeddedConfiguration setting = Db4oEmbedded.newConfiguration();
//
//        setting.common().objectClass(Artist.class).cascadeOnDelete(false);
//        setting.common().objectClass(Artist.class).cascadeOnUpdate(true);
//        setting.common().objectClass(Artist.class).cascadeOnActivate(true);
//        setting.common().objectClass(City.class).cascadeOnDelete(false);
//        setting.common().objectClass(City.class).cascadeOnUpdate(true);
//        setting.common().objectClass(City.class).cascadeOnActivate(true);
//        setting.common().objectClass(Presentation.class).cascadeOnDelete(false);
//        setting.common().objectClass(Presentation.class).cascadeOnUpdate(true);
//        setting.common().objectClass(Presentation.class).cascadeOnActivate(true);
//        
//        setting.common().objectClass(City.class).objectField("name").indexed(true);
//        setting.common().objectClass(Artist.class).objectField("name").indexed(true);
//        setting.common().objectClass(Presentation.class).objectField("id").indexed(true);
//        setting.common().objectClass(Presentation.class).objectField("date").indexed(true);
//        setting.common().objectClass(Presentation.class).objectField("artist").indexed(true);
//        setting.common().objectClass(Presentation.class).objectField("city").indexed(true);
//
//        Db4oEmbedded.openFile(setting, "database.db4o");
    }

    public static void disconnect() {
    	logger.info("----desconectar banco");
		if (manager != null && manager.isOpen()) {
			manager.close();
			factory.close();
			manager = null;
		}
    }
}

