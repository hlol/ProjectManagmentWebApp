package edu.epi.jee.dao;

import java.util.List;

import javax.ejb.Local;

import edu.epi.jee.entities.UserEntity;
@Local
public interface UserDao extends GenericDao<UserEntity>{
public List<UserEntity> findAllUsers();
public UserEntity findUserByName(String name);
public UserEntity findUserByFirstName(String name);

}
