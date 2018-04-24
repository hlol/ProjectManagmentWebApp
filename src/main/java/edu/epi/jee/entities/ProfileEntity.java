/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author HLOL
 */
@Entity
@Table(name = "profile")
@NamedQueries({
    @NamedQuery(name = "ProfileEntity.findAll", query = "SELECT p FROM ProfileEntity p")
    , @NamedQuery(name = "ProfileEntity.findByIdProfile", query = "SELECT p FROM ProfileEntity p WHERE p.idProfile = :idProfile")
    , @NamedQuery(name = "ProfileEntity.findByName", query = "SELECT p FROM ProfileEntity p WHERE p.name = :name")})
public class ProfileEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idProfile")
    private Integer idProfile;
    @Size(max = 35)
    @Column(name = "name")
    private String name;

    public ProfileEntity() {
    }

    public ProfileEntity(Integer idProfile) {
        this.idProfile = idProfile;
    }

    public Integer getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Integer idProfile) {
        this.idProfile = idProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfile != null ? idProfile.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfileEntity)) {
            return false;
        }
        ProfileEntity other = (ProfileEntity) object;
        if ((this.idProfile == null && other.idProfile != null) || (this.idProfile != null && !this.idProfile.equals(other.idProfile))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.epi.jee.entities.ProfileEntity[ idProfile=" + idProfile + " ]";
    }
    
}
