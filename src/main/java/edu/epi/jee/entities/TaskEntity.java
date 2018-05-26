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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "task")
@NamedQueries({
    @NamedQuery(name = "TaskEntity.findAll", query = "SELECT t FROM TaskEntity t")})
public class TaskEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTask")
    private Integer idTask;
    @Size(max = 50)
    @Column(name = "taskName")
    private String taskName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Lob
    @Size(max = 65535)
    @Column(name = "descriptionTest")
    private String descriptionTest;
    @Column(name = "beginDate")
    @Temporal(TemporalType.DATE)
    private Date beginDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "number")
    private Integer number;
    @Column(name = "estimatePoint")
    private Integer estimatePoint;
    @Column(name = "noteTask")
    private Integer noteTask;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costTask")
    private Double costTask;
    
   
    
    @ManyToMany
    @JoinTable(name = "JOIN_workers_tasks", joinColumns = { @JoinColumn(name = "idTask", nullable = false, updatable = false) },
          inverseJoinColumns = { @JoinColumn(name = "idWorker", nullable = false, updatable = false) })
    
    private List<WorkerEntity>workers;

    @JoinColumn(name = "idProject", referencedColumnName = "idProject")
    @ManyToOne
    private ProjectEntity project;
    
    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public List<WorkerEntity> getWorkers() {
        return workers;
    }

    public void setWorkers(List<WorkerEntity> workers) {
        this.workers = workers;
    }
    public TaskEntity() {
    }

    public TaskEntity(Integer idTask) {
        this.idTask = idTask;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionTest() {
        return descriptionTest;
    }

    public void setDescriptionTest(String descriptionTest) {
        this.descriptionTest = descriptionTest;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getEstimatePoint() {
        return estimatePoint;
    }

    public void setEstimatePoint(Integer estimatePoint) {
        this.estimatePoint = estimatePoint;
    }

    public Integer getNoteTask() {
        return noteTask;
    }

    public void setNoteTask(Integer noteTask) {
        this.noteTask = noteTask;
    }

    public Double getCostTask() {
        return costTask;
    }

    public void setCostTask(Double costTask) {
        this.costTask = costTask;
    }
    public TaskEntity(String description, int number, String taskName ,int estimatePoint) {
		super();
		this.description = description;
		this.number = number;
		this.taskName = taskName;
		this.estimatePoint = estimatePoint;
	}



	
	public TaskEntity(String description, int number, String taskName) {
		super();
		this.description = description;
		this.number = number;
		this.taskName = taskName;
	}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTask != null ? idTask.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaskEntity)) {
            return false;
        }
        TaskEntity other = (TaskEntity) object;
        if ((this.idTask == null && other.idTask != null) || (this.idTask != null && !this.idTask.equals(other.idTask))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.epi.jee.entities.TaskEntity[ idTask=" + idTask + " ]";
    }
    
}
