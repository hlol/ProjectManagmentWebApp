/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.daoImpl;

import edu.epi.jee.dao.GenericDaoImpl;
import edu.epi.jee.dao.WorkerDao;
import edu.epi.jee.entities.WorkerEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author HLOL
 */
@Stateless
public class WorkerDaoImpl extends GenericDaoImpl<WorkerEntity> implements WorkerDao {

    @Override
    public List<WorkerEntity> findAllWorkers() {
                Query query = em.createQuery("select w FROM WorkerEntity w"); 
		
		return query.getResultList();    }

}
