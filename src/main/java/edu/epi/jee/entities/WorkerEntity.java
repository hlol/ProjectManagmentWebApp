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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "worker")
@NamedQueries({
    @NamedQuery(name = "WorkerEntity.findAll", query = "SELECT w FROM WorkerEntity w")})
public class WorkerEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWorker")
    private Integer idWorker;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "firstName")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "lastName")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "city")
    private String city;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "phone")
    private int phone;
    @ManyToOne
    private WorkerGroupEntity workerGroup;
    
    @ManyToMany(mappedBy="workers")
    private List<TaskEntity>taskEntities;

    public List<TaskEntity> getTaskEntities() {
        return taskEntities;
    }

    public void setTaskEntities(List<TaskEntity> taskEntities) {
        this.taskEntities = taskEntities;
    }
    
    @ManyToMany
    @JoinTable(name = "JOIN_projects_workers", joinColumns = { @JoinColumn(name = "idWorker", nullable = false, updatable = false) },
	            inverseJoinColumns = { @JoinColumn(name = "idProject", nullable = false, updatable = false) })
    private List<ProjectEntity> projects;

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
    }
    public WorkerGroupEntity getWorkerGroup() {
        return workerGroup;
    }

    public void setWorkerGroup(WorkerGroupEntity workerGroup) {
        this.workerGroup = workerGroup;
    }
	     
    public WorkerEntity() {
    }

    public WorkerEntity(Integer idWorker) {
        this.idWorker = idWorker;
    }

    public WorkerEntity(Integer idWorker, String firstName, String lastName, Date birthday, String city, String email, int phone) {
        this.idWorker = idWorker;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.city = city;
        this.email = email;
        this.phone = phone;
    }

    public Integer getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(Integer idWorker) {
        this.idWorker = idWorker;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWorker != null ? idWorker.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorkerEntity)) {
            return false;
        }
        WorkerEntity other = (WorkerEntity) object;
        if ((this.idWorker == null && other.idWorker != null) || (this.idWorker != null && !this.idWorker.equals(other.idWorker))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.epi.jee.entities.WorkerEntity[ idWorker=" + idWorker + " ]";
    }
    
}
