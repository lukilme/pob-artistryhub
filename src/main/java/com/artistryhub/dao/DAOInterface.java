package com.artistryhub.dao;

import java.util.List;

public interface DAOInterface<T> {
	public void create(T params);
	public T read(Object chave);
	public T update(T params);
	public void delete(T params) ;
	public List<T> readAll();
	public void clear();
}
