/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.daoImpl;

import edu.epi.jee.dao.AdminDao;
import edu.epi.jee.dao.GenericDaoImpl;
import edu.epi.jee.entities.AdminEntity;
import edu.epi.jee.entities.UserEntity;
import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class AdminDaoImpl extends GenericDaoImpl<AdminEntity> implements AdminDao{

    @Override
    public AdminEntity findUserByName(String name) {
Query query = em.createQuery("select f FROM AdminEntity f WHERE f.login LIKE :name")
    .setParameter("name", name);
	return 	 (AdminEntity)query.getResultList().get(0);
	   }
    
    
}
