package ru.javawebinar.topjava.dao;

import java.util.List;

public interface MemoryDao<E,K> {

	E get(K k);

	List<E> getAll();

	E save(E e);

	boolean delete(K k);
}
