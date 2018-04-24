package edu.epi.jee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class GenericDaoImpl<T> implements GenericDao<T> {
    
	 @PersistenceContext(name = "MyPrjPU")
         protected EntityManager em;
 
 
	
         @Override
	public T create(T t) {
		em.persist(t);
		return t;
	}

         @Override
	public T update(T t) {
	em.merge(t);
	return t;
	}

         @Override
	public T delete(int id) {
		T t = (T) em.find(GenericDaoImpl.class, id);
	em.remove(t);
		return null;
	}

         @Override
	public T findById(int id) {
		T t= (T) em.find(GenericDaoImpl.class, id);
		return t;
	}

         @Override
	public List<T> findAll(T t) {
	
		return null;
	}
}
