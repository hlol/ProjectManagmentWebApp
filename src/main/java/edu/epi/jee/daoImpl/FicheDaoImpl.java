/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.daoImpl;

import edu.epi.jee.dao.FicheDao;
import edu.epi.jee.dao.GenericDaoImpl;
import edu.epi.jee.entities.FicheEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author HLOL
 */
@Stateless
public class FicheDaoImpl extends GenericDaoImpl<FicheEntity> implements FicheDao{

    @Override
    public List<FicheEntity> findAllFiches() {
                Query query = em.createQuery("select f FROM FicheEntity f"); 
		
		return query.getResultList();    }
	
}
