package edu.epi.jee.dao;

import java.util.List;

import javax.ejb.Local;

import edu.epi.jee.entities.UserGroupEntity;


@Local

public interface UserGroupDao extends GenericDao<UserGroupEntity>{
public List<UserGroupEntity> findAllUserGroup();
public UserGroupEntity findGroupByName(String groupName);
}
