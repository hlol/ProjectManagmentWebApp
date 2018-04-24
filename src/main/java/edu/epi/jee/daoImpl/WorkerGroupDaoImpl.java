/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.daoImpl;

import edu.epi.jee.dao.GenericDaoImpl;
import edu.epi.jee.dao.WorkerGroupDao;
import edu.epi.jee.entities.WorkerGroupEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author HLOL
 */
@Stateless
public class WorkerGroupDaoImpl extends GenericDaoImpl<WorkerGroupEntity> implements WorkerGroupDao{

    @Override
    public List<WorkerGroupEntity> findAllWorkerGroups() {
                Query query = em.createQuery("select g FROM WorkerGroupEntity g"); 
		
		return query.getResultList();
	    }

}