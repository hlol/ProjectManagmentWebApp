/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author HLOL
 */
@Entity
@Table(name = "project")
@NamedQueries({
    @NamedQuery(name = "ProjectEntity.findAll", query = "SELECT p FROM ProjectEntity p")})
public class ProjectEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idProject")
    private Integer idProject;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nameProject")
    private String nameProject;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codeProcess")
    private String codeProcess;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numTask")
    private int numTask;
    @JoinColumn(name = "idCustomer", referencedColumnName = "idCustomer")
    @ManyToOne
    private CustomerEntity customer;

    //@ManyToMany(mappedBy="projects",cascade = CascadeType.ALL)
    //private List<UserEntity>users;

     @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
     @JoinTable(name = "projects_users", joinColumns = { @JoinColumn(name = "idProject", referencedColumnName = "idProject") },
         inverseJoinColumns = { @JoinColumn(name = "idUser", referencedColumnName = "idUser" ) })
     private List<UserEntity>users;
     
    @ManyToMany(mappedBy="projects")
    private List<WorkerEntity>workers;

    @ManyToMany(mappedBy="projects")
    private List<ProductEntity> products;
    
    @JoinColumn(name = "idFiche", referencedColumnName = "idFiche")
    @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL,orphanRemoval = true)
    private FicheEntity fiche ;

    @OneToMany(mappedBy="project")
    private List<TaskEntity> tasks; 
    
    @JoinColumn(name = "idStatus", referencedColumnName = "idStatus")
    @ManyToOne
    private StatusEntity status;

   
    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }
    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }
    
    public FicheEntity getFiche() {
        return fiche;
    }

    public void setFiche(FicheEntity fiche) {
        this.fiche = fiche;
    }
    
    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<WorkerEntity> getWorkers() {
        return workers;
    }

    public void setWorkers(List<WorkerEntity> workers) {
        this.workers = workers;
    }

   
    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public ProjectEntity() {
    }

    public ProjectEntity(Integer idProject) {
        this.idProject = idProject;
    }

    public ProjectEntity(Integer idProject, String nameProject, Date startDate, String description, String codeProcess, int numTask) {
        this.idProject = idProject;
        this.nameProject = nameProject;
        this.startDate = startDate;
        this.description = description;
        this.codeProcess = codeProcess;
        this.numTask = numTask;
    }

    public Integer getIdProject() {
        return idProject;
    }

    public void setIdProject(Integer idProject) {
        this.idProject = idProject;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCodeProcess() {
        return codeProcess;
    }

    public void setCodeProcess(String codeProcess) {
        this.codeProcess = codeProcess;
    }

    public int getNumTask() {
        return numTask;
    }

    public void setNumTask(int numTask) {
        this.numTask = numTask;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProject != null ? idProject.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectEntity)) {
            return false;
        }
        ProjectEntity other = (ProjectEntity) object;
        if ((this.idProject == null && other.idProject != null) || (this.idProject != null && !this.idProject.equals(other.idProject))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.epi.jee.entities.ProjectEntity[ idProject=" + idProject + " ]";
    }
    public ProjectEntity(String nameProject, Date startDate, Date endDate, String description,int numTask) {
	super();
	this.nameProject = nameProject;
	this.startDate = startDate;
	this.endDate = endDate;
	this.description = description;
	this.numTask =numTask ;
}
}
