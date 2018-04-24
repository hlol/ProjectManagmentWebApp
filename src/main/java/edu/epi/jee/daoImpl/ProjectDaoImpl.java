/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.daoImpl;

import edu.epi.jee.dao.GenericDaoImpl;
import edu.epi.jee.dao.ProjectDao;
import edu.epi.jee.entities.ProjectEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author HLOL
 */
@Stateless
public class ProjectDaoImpl extends GenericDaoImpl<ProjectEntity> implements ProjectDao {

    @Override
    public List<ProjectEntity> findAllProjects() {
                Query query = em.createQuery("select p FROM ProjectEntity p"); 
		
		return query.getResultList();
        }

	
}