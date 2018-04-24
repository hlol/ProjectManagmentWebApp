package edu.epi.jee.dao;

import java.util.List;
import javax.ejb.Local;
import edu.epi.jee.entities.RoleEntity;


@Local
public interface RoleDao extends GenericDao<RoleEntity>{
public List<RoleEntity> findAllRoles();
public RoleEntity findRoleByName(String name);
}
