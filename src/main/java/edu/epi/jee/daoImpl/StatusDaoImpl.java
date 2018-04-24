/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.daoImpl;

import edu.epi.jee.dao.GenericDaoImpl;
import edu.epi.jee.dao.StatusDao;
import edu.epi.jee.entities.StatusEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author HLOL
 */

  @Stateless
public class StatusDaoImpl extends GenericDaoImpl<StatusEntity> implements StatusDao{

    @Override
    public List<StatusEntity> findAllEntities() {
                Query query = em.createQuery("select f FROM StatusEntity f"); 
		
		return query.getResultList();    }
  
}
