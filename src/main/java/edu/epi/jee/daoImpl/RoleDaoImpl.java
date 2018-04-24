package edu.epi.jee.daoImpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import edu.epi.jee.dao.GenericDaoImpl;
import edu.epi.jee.dao.RoleDao;
import edu.epi.jee.entities.RoleEntity;
//import edu.epi.jee.entities.UserGroupEntity;
@Stateless
public class RoleDaoImpl extends GenericDaoImpl<RoleEntity> implements RoleDao{

	@Override
	public List<RoleEntity> findAllRoles() {
Query query = em.createQuery("select f FROM RoleEntity f"); 
		
		return query.getResultList();
	}

	@Override
	public RoleEntity findRoleByName(String name) {
		Query query = em.createQuery("select f FROM RoleEntity f WHERE f.name LIKE :name")
			    .setParameter("name", name);
				return 	(RoleEntity) query.getResultList().get(0);
	}

}
