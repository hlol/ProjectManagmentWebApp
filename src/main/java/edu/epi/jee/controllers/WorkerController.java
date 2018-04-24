/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.controllers;

import edu.epi.jee.dao.WorkerDao;
import edu.epi.jee.dao.WorkerGroupDao;
import edu.epi.jee.entities.WorkerEntity;
import edu.epi.jee.entities.WorkerGroupEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author HLOL
 */
@ManagedBean
@SessionScoped
public class WorkerController  implements Serializable{

@EJB
private WorkerDao workerDoa;
@EJB
private WorkerGroupDao workerGroupDao ;
private List<WorkerEntity> listWorkers;
private List<WorkerEntity> filterWorker;
private  WorkerEntity workerEntity  = new WorkerEntity() ;
WorkerEntity workerSelected ;
    @PostConstruct
    public void init() {
    	listWorkers = workerDoa.findAllWorkers();
    	workerSelected = new WorkerEntity();

    }
    public String newWorker()
    {
    	WorkerGroupEntity workerGroupEntity = new WorkerGroupEntity("group1");
    	WorkerGroupEntity workerGroupEntity1 = workerGroupDao.create(workerGroupEntity);
    	WorkerEntity workerEntity1=workerDoa.create(workerEntity);
    	workerEntity = new WorkerEntity();
    	listWorkers = workerDoa.findAllWorkers();
    	return null;
    }
    public String deleteWorker()
    {
    
    	
    //	
    	System.out.println("*****************************");
    	System.out.println("*************************"+workerSelected.getIdWorker());
    	workerSelected = null;
    	WorkerEntity w1 =workerDoa.delete(workerSelected.getIdWorker());
    	listWorkers = workerDoa.findAllWorkers();
    	return null;
    }
  
    public String updateWorker(){
    	workerDoa.update(workerSelected);
    	listWorkers = workerDoa.findAllWorkers();
    	return null;
    }
	public WorkerDao getWorkerDoa() {
		return workerDoa;
	}
	public void setWorkerDoa(WorkerDao workerDoa) {
		this.workerDoa = workerDoa;
	}
	public List<WorkerEntity> getListWorkers() {
		return listWorkers;
	}
	public void setListWorkers(List<WorkerEntity> listWorkers) {
		this.listWorkers = listWorkers;
	}
	public WorkerEntity getWorkerEntity() {
		return workerEntity;
	}
	public void setWorkerEntity(WorkerEntity workerEntity) {
		this.workerEntity = workerEntity;
	}
	public WorkerEntity getWorkerSelected() {
		return workerSelected;
	}
	public void setWorkerSelected(WorkerEntity workerSelected) {
		this.workerSelected = workerSelected;
	}
	public List<WorkerEntity> getFilterWorker() {
		return filterWorker;
	}
	public void setFilterWorker(List<WorkerEntity> filterWorker) {
		this.filterWorker = filterWorker;
	}
    
    
}
