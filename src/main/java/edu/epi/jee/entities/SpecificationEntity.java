/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "specification")
@NamedQueries({
    @NamedQuery(name = "SpecificationEntity.findAll", query = "SELECT s FROM SpecificationEntity s")
    , @NamedQuery(name = "SpecificationEntity.findByIdSpecification", query = "SELECT s FROM SpecificationEntity s WHERE s.idSpecification = :idSpecification")
    , @NamedQuery(name = "SpecificationEntity.findByDocumentType", query = "SELECT s FROM SpecificationEntity s WHERE s.documentType = :documentType")
    , @NamedQuery(name = "SpecificationEntity.findByDocumentUrl", query = "SELECT s FROM SpecificationEntity s WHERE s.documentUrl = :documentUrl")
    , @NamedQuery(name = "SpecificationEntity.findByCreationDate", query = "SELECT s FROM SpecificationEntity s WHERE s.creationDate = :creationDate")})
public class SpecificationEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSpecification")
    private Integer idSpecification;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "documentType")
    private String documentType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "documentUrl")
    private String documentUrl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creationDate")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public SpecificationEntity() {
    }

    public SpecificationEntity(Integer idSpecification) {
        this.idSpecification = idSpecification;
    }

    public SpecificationEntity(Integer idSpecification, String documentType, String documentUrl, Date creationDate) {
        this.idSpecification = idSpecification;
        this.documentType = documentType;
        this.documentUrl = documentUrl;
        this.creationDate = creationDate;
    }

    public Integer getIdSpecification() {
        return idSpecification;
    }

    public void setIdSpecification(Integer idSpecification) {
        this.idSpecification = idSpecification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSpecification != null ? idSpecification.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpecificationEntity)) {
            return false;
        }
        SpecificationEntity other = (SpecificationEntity) object;
        if ((this.idSpecification == null && other.idSpecification != null) || (this.idSpecification != null && !this.idSpecification.equals(other.idSpecification))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.epi.jee.entities.SpecificationEntity[ idSpecification=" + idSpecification + " ]";
    }
    
}
