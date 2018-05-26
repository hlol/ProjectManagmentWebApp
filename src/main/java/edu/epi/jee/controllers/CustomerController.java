/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.controllers;

import edu.epi.jee.dao.CustomerDao;
import edu.epi.jee.entities.CustomerEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CustomerController implements Serializable{
	
@EJB
private CustomerDao customerDAO;
private CustomerEntity customerEntity;
CustomerEntity selectedCustomer;
private List<CustomerEntity> listCustomers;
private List<CustomerEntity> filterCustomer ;

    public CustomerController() {
        this.customerEntity = new CustomerEntity();
    }
    
    public List<CustomerEntity>allCustomers(){
        listCustomers = customerDAO.findAllCustomers();
        return listCustomers;
    }


public String newCustomer(){
	CustomerEntity p1 = customerDAO.create(customerEntity);
	listCustomers  = customerDAO.findAllCustomers();
	return null;
}
public String deleteCustomer(){
	return null;
}
public String updateCustomer(){
	CustomerEntity p2 = customerDAO.update(selectedCustomer);
	listCustomers = customerDAO.findAllCustomers();
	return null;
}

    public CustomerDao getCustomerDAO() {
        return customerDAO;
    }

    public void setCustomerDAO(CustomerDao customerDAO) {
        this.customerDAO = customerDAO;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public CustomerEntity getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(CustomerEntity selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public List<CustomerEntity> getListCustomers() {
        return listCustomers;
    }

    public void setListCustomers(List<CustomerEntity> listCustomers) {
        this.listCustomers = listCustomers;
    }

    public List<CustomerEntity> getFilterCustomer() {
        return filterCustomer;
    }

    public void setFilterCustomer(List<CustomerEntity> filterCustomer) {
        this.filterCustomer = filterCustomer;
    }

}
