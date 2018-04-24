package edu.epi.jee.daoImpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import edu.epi.jee.dao.GenericDaoImpl;
import edu.epi.jee.entities.UserGroupEntity;
import edu.epi.jee.dao.UserGroupDao;

@Stateless
public class UserGroupDaoImpl extends GenericDaoImpl<UserGroupEntity> implements UserGroupDao{

    @Override
    public List<UserGroupEntity> findAllUserGroup() {
                Query query = em.createQuery("select f FROM UserGroupEntity f"); 
		return query.getResultList();    }

    @Override
    public UserGroupEntity findGroupByName(String groupName) {
            Query query = em.createQuery("select f FROM UserGroupEntity f WHERE f.groupName LIKE :groupName").setParameter("groupName", groupName);
            return (UserGroupEntity) query.getResultList().get(0);    }

	

}
