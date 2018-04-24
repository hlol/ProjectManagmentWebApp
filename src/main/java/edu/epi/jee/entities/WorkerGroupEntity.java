/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author HLOL
 */
@Entity
@Table(name = "workergroup")
@NamedQueries({
    @NamedQuery(name = "WorkerGroupEntity.findAll", query = "SELECT w FROM WorkerGroupEntity w")})
public class WorkerGroupEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idworkerGroup")
    private Integer idworkerGroup;
    @Size(max = 55)
    @Column(name = "groupName")
    private String groupName;
    @OneToMany
    private List<WorkerEntity> workers;

    public List<WorkerEntity> getWorkers() {
        return workers;
    }

    public void setWorkers(List<WorkerEntity> workers) {
        this.workers = workers;
    }
    public WorkerGroupEntity() {
    }

    public WorkerGroupEntity(Integer idworkerGroup) {
        this.idworkerGroup = idworkerGroup;
    }

    public Integer getIdworkerGroup() {
        return idworkerGroup;
    }

    public void setIdworkerGroup(Integer idworkerGroup) {
        this.idworkerGroup = idworkerGroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idworkerGroup != null ? idworkerGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorkerGroupEntity)) {
            return false;
        }
        WorkerGroupEntity other = (WorkerGroupEntity) object;
        if ((this.idworkerGroup == null && other.idworkerGroup != null) || (this.idworkerGroup != null && !this.idworkerGroup.equals(other.idworkerGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.epi.jee.entities.WorkerGroupEntity[ idworkerGroup=" + idworkerGroup + " ]";
    }
    public WorkerGroupEntity(String groupName) {
		super();
		this.groupName = groupName;
	}
}
