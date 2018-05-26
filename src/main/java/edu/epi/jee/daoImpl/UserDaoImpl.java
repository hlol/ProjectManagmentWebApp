package edu.epi.jee.daoImpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import edu.epi.jee.dao.GenericDaoImpl;
import edu.epi.jee.dao.UserDao;
import edu.epi.jee.entities.UserEntity;
//import edu.epi.jee.entities.UserGroupEntity;
@Stateless
public class UserDaoImpl extends GenericDaoImpl<UserEntity> implements UserDao{

	@Override
	public List<UserEntity> findAllUsers() {
Query query = em.createQuery("select f FROM UserEntity f"); 
		
		return query.getResultList();
	}

	@Override
	public UserEntity findUserByName(String name) {
Query query = em.createQuery("select f FROM UserEntity f WHERE concat(f.firstName,' ',f.lastName) LIKE :name")
    .setParameter("name", name);
	return 	 (UserEntity)query.getResultList().get(0);
	}

    @Override
    public UserEntity findUserByFirstName(String name) {
        Query query = em.createQuery("select f FROM UserEntity f WHEREf.firstName LIKE :name")
    .setParameter("name", name);
	return 	 (UserEntity)query.getResultList().get(0);
    }

	}
	

