package com.artistryhub.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public abstract class DAO<T> implements DAOInterface<T> {
	protected static ObjectContainer manager;
	protected static int currentMaxId = 1;
	@SuppressWarnings("unused")
	private Class<?> type;

	protected Class<?> getType() {
		Type genericSuperclass = getClass().getGenericSuperclass();
		if (genericSuperclass instanceof ParameterizedType) {
			Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
			if (actualTypeArguments.length > 0 && actualTypeArguments[0] instanceof Class) {
				return (Class<?>) actualTypeArguments[0];
			}
		}
		throw new IllegalArgumentException("Unable to determine type argument.");
	}

	public static void open() {
		manager = Utility.connectDataBase();
	}

	public static void close() {
		Utility.disconnect();
	}

	public void create(T obj) {
		manager.store(obj);
	}

	public T update(T obj) {
		manager.store(obj);
		return obj;
	}

	public abstract T read(Object key);

	public void delete(T obj) {
		manager.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<T> readAll() {
		manager.ext().purge();
		Class<T> type = (Class<T>) getType();
		Query query = manager.query();
		query.constrain(type);
		return (List<T>) query.execute();
	}

	@SuppressWarnings("unchecked")
	public List<T> readAll(int pageNumber, int pageSize) {
		manager.ext().purge();
		Class<T> type = (Class<T>) getType();
		Query query = manager.query();
		query.constrain(type);

		ObjectSet<T> result = query.execute();

		int offset = (pageNumber - 1) * pageSize;

		if (offset >= result.size()) {
			return new ArrayList<>();
		}

		int end = Math.min(offset + pageSize, result.size());
		return result.subList(offset, end);
	}

	@SuppressWarnings("unchecked")
	public void clear() {
		Class<T> type = (Class<T>) getType();
		Query q = manager.query();
		q.constrain(type);
		for (Object t : q.execute()) {
			manager.delete(t);
		}
	}

	public static void begin() {
	}

	public static void commit() {
		manager.commit();
	}

	public static void rollback() {
		manager.rollback();
	}

	public int generatObsoleteId() {
		@SuppressWarnings("unchecked")
		Class<T> type = (Class<T>) getType(); // getting actual type class

		if (manager.query(type).size() == 0) {
			return 1; // if the database is empty return the first generated id.
		} else {
			Query query = manager.query();
			query.constrain(type);
			query.descend("id").orderDescending();
			List<T> results = query.execute();
			if (results.isEmpty())
				return 1; // if the database is empty return the first generated id again.
			else
				try {
					T last_object = results.get(0);
					Field attribute = type.getDeclaredField("id");
					attribute.setAccessible(true);
					int idMax = (Integer) attribute.get(last_object);
					return idMax + 1; // take the object with the highest id in the database and add +1 to the new
										// object

				} catch (NoSuchFieldException e) {
					throw new RuntimeException("Class " + type + " does not have id attribute");
				} catch (IllegalAccessException e) {
					throw new RuntimeException("Unable to access id attribute of class " + type);
				}
		}
	}

	public int generateId() {
		return ++currentMaxId;
	}

	@SuppressWarnings("unchecked")
	public void initializeCurrentMaxId() {
		Class<T> type = (Class<T>) getType();
		Query query = manager.query();
		query.constrain(type);
		query.descend("id").orderDescending();
		List<T> results = query.execute();
		if (!results.isEmpty()) {
			try {
				T lastObject = results.get(0);
				Field attribute = type.getDeclaredField("id");
				attribute.setAccessible(true);
				currentMaxId = (Integer) attribute.get(lastObject);
			} catch (NoSuchFieldException | IllegalAccessException e) {
				throw new RuntimeException("Unable to access id attribute of class " + type, e);
			}
		}
	}
}
