package edu.epi.jee.daoImpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import edu.epi.jee.dao.GenericDaoImpl;
import edu.epi.jee.dao.CustomerDao;
import edu.epi.jee.entities.CustomerEntity;
@Stateless
public class CustomerDaoImpl extends GenericDaoImpl<CustomerEntity> implements CustomerDao{

        @Override
	public CustomerEntity findCustomerByName(String lastName,String firstName) {
		 CustomerEntity customerEntity =
	                (CustomerEntity) em.createQuery("select c from CustomerEntity c where c.firstName = :x and c.lastName = :y").setParameter("x",
	                		firstName).setParameter("y", lastName).getSingleResult();
	    	if(customerEntity !=  null ){
	    	
	    		 return customerEntity;
	    	}
	        return null;
	    }

		
        @Override
		public List<CustomerEntity> findAllCustomers() {
			Query query = em.createQuery("select f FROM CustomerEntity f"); 
			
			return query.getResultList();
		
		}

}
