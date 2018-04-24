/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.daoImpl;

import edu.epi.jee.dao.ContratDao;
import edu.epi.jee.dao.GenericDaoImpl;
import edu.epi.jee.entities.ContratEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author HLOL
 */
@Stateless
public class ContratDaoImpl extends GenericDaoImpl<ContratEntity> implements ContratDao{

    @Override
    public List<ContratEntity> findAllContrats() {
                Query query = em.createQuery("select f FROM ContratEntity f"); 
		
		return query.getResultList();    }

    @Override
    public List<ContratEntity> findAllContratByProject() {
                return null;
    }

	
}