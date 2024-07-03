package com.artistryhub.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

public abstract class DAO<T> implements DAOInterface<T> {
	protected static ObjectContainer manager;

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

	public void delete(T obj) {
		manager.delete(obj);
	}

	public abstract T get(Object key);

	/**
	 * Get all elements of current type (type parameter 'T')
	 * from the generic superclass of this class.
	 *
	 * This method queries all objects of type 'T'
	 * and returns them using the manager.
	 *
	 * @return a list of class objects that represent the current type ('T').
	 */
	@SuppressWarnings("unchecked")
	public List<T> readAll() {
		manager.ext().purge();
		Class<T> type = (Class<T>) getType();
		Query q = manager.query();
		q.constrain(type);
		return (List<T>) q.execute();
	}
	/**
	 * Delete all elements of real type (type parameter 'T') 
	 * from the generic superclass of this class.
	 * 
	 * This method queries all objects of type 'T' 
	 * and removes them using the manager.
	 */
	@SuppressWarnings("unchecked")
	public void clear() {
		Class<T> type = (Class<T>) getType();
		Query q = manager.query();
		q.constrain(type);
		for (Object t : q.execute()) {
			manager.delete(t);
		}
	}

	/**
	 * Extracts the actual type (type parameter 'T') of the generic superclass of this class.
	 * 
	 * This method is used to reflect on the actual type used 
	 * when the current class extends from a generic class.
	 * 
	 * @return The class representing the actual type ('T').
	 * @throws IllegalArgumentException If the superclass is not generic 
	 * or the type parameter cannot be determined.
	 */
	private Class<?> getType() {
		Type genericSuperclass = getClass().getGenericSuperclass();
		if (genericSuperclass instanceof ParameterizedType) {
			Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
			if (actualTypeArguments.length > 0 && actualTypeArguments[0] instanceof Class) {
				return (Class<?>) actualTypeArguments[0];
			}
		}
		throw new IllegalArgumentException("Unable to determine type argument.");
	}
	
	public static void begin(){	
	}

	public static void commit(){
		manager.commit();
	}
	
	public static void rollback(){
		manager.rollback();
	}
	
	
	public int generateId() {
		@SuppressWarnings("unchecked")
		Class<T> type = (Class<T>) getType(); // getting  actual type class
		
		if(manager.query(type).size()==0) { 
			return 1; // if the database is empty return the first generated id.
		}
		else {
			Query query = manager.query();
			query.constrain(type);
			query.descend("id").orderDescending();
			List<T> results =  query.execute();
			if(results.isEmpty()) 
				return 1; // if the database is empty return the first generated id again.
			else 
				try {
					T last_object =  results.get(0);  
					Field attribute = type.getDeclaredField("id") ;
					attribute.setAccessible(true);
					int idMax = (Integer) attribute.get(last_object);
					return idMax+1; // take the object with the highest id in the database and add +1 to the new object

				} catch(NoSuchFieldException e) {
					throw new RuntimeException("Class "+type+" does not have id attribute");
				} catch (IllegalAccessException e) {
					throw new RuntimeException("Unable to access id attribute of class "+type);
				}
		}
	}

}
