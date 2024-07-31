package com.artistryhub.service;

import java.util.List;

public interface FacadeInterface<T> {
	T create(T params);

	T search(Object key);

	T update(T params);

	T delete(Object key);

	List<T> readAll();

	void clear();
}
