/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.daoImpl;

import edu.epi.jee.dao.GenericDaoImpl;
import edu.epi.jee.dao.ProductDao;
import edu.epi.jee.entities.ProductEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author HLOL
 */
@Stateless
public class ProductDaoImpl  extends GenericDaoImpl<ProductEntity> implements ProductDao{

    @Override
    public List<ProductEntity> findAllProducts() {
                Query query = em.createQuery("select f FROM ProductEntity f"); 
		
		return query.getResultList();    }

}