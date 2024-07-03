package com.artistryhub.dao;

import com.db4o.ObjectContainer;

public abstract class DAO<T> implements DAOInterface<T> {
	protected static ObjectContainer manager;

	public static void open() {
		manager = Utility.connectDataBase();

	}

	public static void close() {
		Utility.disconnect();
	}

}
