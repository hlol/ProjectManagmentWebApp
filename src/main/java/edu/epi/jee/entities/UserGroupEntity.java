/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author HLOL
 */
@Entity
@Table(name = "usergroup")
@NamedQueries({
    @NamedQuery(name = "UserGroupEntity.findAll", query = "SELECT u FROM UserGroupEntity u")})
public class UserGroupEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idGroup")
    @SequenceGenerator(name = "user_grp_id_seq", sequenceName = "public.user_grp_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_grp_id_seq")
    private Integer idGroup;
    @Size(max = 55)
    @Column(name = "groupName")
    private String groupName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usergroupEntity")
    private List<UserEntity> userEntityList;

    public UserGroupEntity() {
    }

    public UserGroupEntity(Integer idGroup) {
        this.idGroup = idGroup;
    }
     public UserGroupEntity(String groupName) {
        this.groupName = groupName;
    }
    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<UserEntity> getUserEntityList() {
        return userEntityList;
    }

    public void setUserEntityList(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGroup != null ? idGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGroupEntity)) {
            return false;
        }
        UserGroupEntity other = (UserGroupEntity) object;
        if ((this.idGroup == null && other.idGroup != null) || (this.idGroup != null && !this.idGroup.equals(other.idGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.epi.jee.entities.UsergroupEntity[ idGroup=" + idGroup + " ]";
    }
    
}
