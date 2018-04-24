/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.daoImpl;

import edu.epi.jee.dao.GenericDaoImpl;
import edu.epi.jee.dao.TaskDao;
import edu.epi.jee.entities.TaskEntity;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author HLOL
 */
public class TaskDaoImpl extends GenericDaoImpl<TaskEntity> implements TaskDao{

    @Override
    public List<TaskEntity> findAllTasks() {
                Query query = em.createQuery("select f FROM TaskEntity f"); 
		
		return query.getResultList();    }

   

    @Override
    public TaskEntity findTaskByNumber(int nbr) {
        TaskEntity taskEntity = (TaskEntity) em.createQuery("from TaskEntity as p where p.number ="+nbr).getSingleResult();
		return taskEntity ;    }

    @Override
    public TaskEntity findTaskByName(String taskName) {
            TaskEntity taskEntity = (TaskEntity) em.createQuery("from TaskEntity as p where p.taskName = :x").setParameter("x",
				taskName).getSingleResult();
		return taskEntity ;    }
    
}
