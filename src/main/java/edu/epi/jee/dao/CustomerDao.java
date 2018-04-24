package edu.epi.jee.dao;

import java.util.List;

import javax.ejb.Local;


import edu.epi.jee.entities.CustomerEntity;


@Local
public interface CustomerDao extends GenericDao<CustomerEntity> {
public List<CustomerEntity> findAllCustomers();
public CustomerEntity findCustomerByName(String lastName,String firstName);
}
