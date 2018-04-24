package edu.epi.jee.dao;

import java.util.List;

public interface GenericDao<T> {
public T create(T t);
public T update(T t);
public T delete(int id);
public T findById(int id);
public List<T>findAll(T t);
}
