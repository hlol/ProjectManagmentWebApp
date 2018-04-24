/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.controllers;

import edu.epi.jee.dao.CustomerDao;
import edu.epi.jee.dao.ProductDao;
import edu.epi.jee.dao.ProjectDao;
import edu.epi.jee.dao.UserDao;
import edu.epi.jee.dao.WorkerDao;
import edu.epi.jee.entities.CustomerEntity;
import edu.epi.jee.entities.ProductEntity;
import edu.epi.jee.entities.ProjectEntity;
import edu.epi.jee.entities.UserEntity;
import edu.epi.jee.entities.WorkerEntity;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static javassist.CtClass.version;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author HLOL
 */
@ManagedBean
@ViewScoped
public class ProjectController implements Serializable {
@EJB
private ProjectDao projectDao;
@EJB
private UserDao userDao ;
@EJB
private WorkerDao workerDao ;

@EJB
private CustomerDao customerDao;

@EJB
private ProductDao productDao ; 


private String nameProject;
private String description;
private Date debut_date;
private Date fin_date;
private boolean skip;

private List<UserEntity> listUsers;
private List<ProjectEntity> listWorks;
private List<WorkerEntity> listWorker;
CustomerEntity customerSelected;
CustomerEntity customerworkSelected ;
private List<UserEntity> droppedworker;
private List<ProductEntity> productfilter;
private List<CustomerEntity> customerfilter ;

    public List<CustomerEntity> getCustomerfilter() {
        return customerfilter;
    }

    public void setCustomerfilter(List<CustomerEntity> customerfilter) {
        this.customerfilter = customerfilter;
    }
private ProjectEntity projectEntity = new ProjectEntity();
  UserEntity userSelected;
  UserEntity userworkSelected;
  

private WorkerEntity workerSelected ;
private List<UserEntity> userfilter ;
private List<WorkerEntity> workerFilter;
private List<UserEntity> listUsersWork;
private List<ProductEntity> listProducts ;
private List<ProductEntity> listProductWork ;
private ProductEntity productSelected ; 
private ProductEntity productworkSelected ; 
private List<CustomerEntity>listCustomers;
private List<CustomerEntity> listCustomerWork ;
@PostConstruct
public void init() {
	listUsers=userDao.findAllUsers();
	listWorks= projectDao.findAllProjects();
	listUsersWork = new ArrayList<UserEntity>() ;	
	listCustomers = customerDao.findAllCustomers();
	listCustomerWork = new ArrayList<CustomerEntity>() ;
	 
}
public void addUserToWork(){
	
	System.out.println("***************************************");
	System.out.println("---"+userSelected.getIdUser());
	
	listUsersWork.add(userSelected);
	if(listUsers!=null)
	listUsers.remove(userSelected);
       
      // projectEntity.setUsers(listUsersWork);
     
}
    public void deleteUserFromWork()
{
	
	System.out.println("***************************************");
	System.out.println("***"+userworkSelected.getIdUser());
	listUsers.add(userworkSelected);
	if (listUsersWork!=null)
	listUsersWork.remove(userworkSelected);
	
	
}
    public void addCustomerToWork(){
	
	System.out.println("***************************************");
	System.out.println("---"+customerSelected.getIdCustomer());
	
	listCustomerWork.add(customerSelected);
	if(listCustomers!=null)
	listCustomers.remove(customerSelected);
        projectEntity.setCustomer(customerSelected);
}
public void deleteCustomerFromWork()
{
	
	System.out.println("***************************************");
	System.out.println("***"+customerworkSelected.getIdCustomer());
	
	
	listCustomers.add(customerworkSelected);
	if (listCustomerWork!=null)
		listCustomerWork.remove(customerworkSelected);
	
	
}
public void addProductToWork(){
	
	System.out.println("***************************************");
	System.out.println("---"+productSelected.getIdProduct());
	
	listProductWork.add(productSelected);
	if(listProducts!=null)
	listProducts.remove(productSelected);
        
	
}
public void deleteProductFromWork()
{
	
	System.out.println("***************************************");
	System.out.println("***"+productworkSelected.getIdProduct());
	
	
	listProducts.add(productworkSelected);
	if (listProductWork!=null)
	listProductWork.remove(productworkSelected);
	
	
}

    public ProjectDao getProjectDao() {
        return projectDao;
    }

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public WorkerDao getWorkerDao() {
        return workerDao;
    }

    public void setWorkerDao(WorkerDao workerDao) {
        this.workerDao = workerDao;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

  
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDebut_date() {
        return debut_date;
    }

    public void setDebut_date(Date debut_date) {
        this.debut_date = debut_date;
    }

    public Date getFin_date() {
        return fin_date;
    }

    public void setFin_date(Date fin_date) {
        this.fin_date = fin_date;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public List<UserEntity> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<UserEntity> listUsers) {
        this.listUsers = listUsers;
    }

    public List<ProjectEntity> getListWorks() {
        return listWorks;
    }

    public void setListWorks(List<ProjectEntity> listWorks) {
        this.listWorks = listWorks;
    }

    public List<WorkerEntity> getListWorker() {
        return listWorker;
    }

    public void setListWorker(List<WorkerEntity> listWorker) {
        this.listWorker = listWorker;
    }

    public CustomerEntity getCustomerSelected() {
        return customerSelected;
    }

    public void setCustomerSelected(CustomerEntity customerSelected) {
        this.customerSelected = customerSelected;
    }

    public CustomerEntity getCustomerworkSelected() {
        return customerworkSelected;
    }

    public void setCustomerworkSelected(CustomerEntity customerworkSelected) {
        this.customerworkSelected = customerworkSelected;
    }

    public List<UserEntity> getDroppedworker() {
        return droppedworker;
    }

    public void setDroppedworker(List<UserEntity> droppedworker) {
        this.droppedworker = droppedworker;
    }

    public List<ProductEntity> getProductfilter() {
        return productfilter;
    }

    public void setProductfilter(List<ProductEntity> productfilter) {
        this.productfilter = productfilter;
    }

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    public UserEntity getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(UserEntity userSelected) {
        this.userSelected = userSelected;
    }

    public UserEntity getUserworkSelected() {
        return userworkSelected;
    }

    public void setUserworkSelected(UserEntity userworkSelected) {
        this.userworkSelected = userworkSelected;
    }

    public WorkerEntity getWorkerSelected() {
        return workerSelected;
    }

    public void setWorkerSelected(WorkerEntity workerSelected) {
        this.workerSelected = workerSelected;
    }

    public List<UserEntity> getUserfilter() {
        return userfilter;
    }

    public void setUserfilter(List<UserEntity> userfilter) {
        this.userfilter = userfilter;
    }

    public List<WorkerEntity> getWorkerFilter() {
        return workerFilter;
    }

    public void setWorkerFilter(List<WorkerEntity> workerFilter) {
        this.workerFilter = workerFilter;
    }

    public List<UserEntity> getListUsersWork() {
        return listUsersWork;
    }

    public void setListUsersWork(List<UserEntity> listUsersWork) {
        this.listUsersWork = listUsersWork;
    }

    public List<ProductEntity> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<ProductEntity> listProducts) {
        this.listProducts = listProducts;
    }

    public List<ProductEntity> getListProductWork() {
        return listProductWork;
    }

    public void setListProductWork(List<ProductEntity> listProductWork) {
        this.listProductWork = listProductWork;
    }

    public ProductEntity getProductSelected() {
        return productSelected;
    }

    public void setProductSelected(ProductEntity productSelected) {
        this.productSelected = productSelected;
    }

    public ProductEntity getProductworkSelected() {
        return productworkSelected;
    }

    public void setProductworkSelected(ProductEntity productworkSelected) {
        this.productworkSelected = productworkSelected;
    }

    public List<CustomerEntity> getListCustomers() {
        return listCustomers;
    }

    public void setListCustomers(List<CustomerEntity> listCustomers) {
        this.listCustomers = listCustomers;
    }

    public List<CustomerEntity> getListCustomerWork() {
        return listCustomerWork;
    }

    public void setListCustomerWork(List<CustomerEntity> listCustomerWork) {
        this.listCustomerWork = listCustomerWork;
    }
    public void addProject() throws IOException
{   //System.out.println("---"+projectEntity.getIdProject());
    //projectEntity = new ProjectEntity();
         ProjectEntity projectEntity1= projectDao.create(projectEntity);
 ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
context.redirect("listProjects.xhtml");
	//return "listProjects.xhtml?faces-redirect=true";
        
}
public String onFlowProcess(FlowEvent event) {
    if(skip) {
        skip = false;   //reset in case user goes back
        return "confirm";
    }
    else {
        return event.getNewStep();
    }
}
}
