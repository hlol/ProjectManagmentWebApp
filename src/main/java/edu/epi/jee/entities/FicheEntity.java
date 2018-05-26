/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author HLOL
 */
@Entity
@Table(name = "fiche")
@NamedQueries({
    @NamedQuery(name = "FicheEntity.findAll", query = "SELECT f FROM FicheEntity f")})
public class FicheEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFiche")
    private Integer idFiche;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 50)
    @Column(name = "typeDoc")
    private String typeDoc;
    @Size(max = 50)
    @Column(name = "documentUrl")
    private String documentUrl;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Size(max = 50)
    @Column(name = "content")
    private String content;
   
   
    @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "idProject", referencedColumnName = "idProject")
    private ProjectEntity project ;

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    
     
     
    public FicheEntity() {
    }

    public FicheEntity(Integer idFiche) {
        this.idFiche = idFiche;
    }

    public Integer getIdFiche() {
        return idFiche;
    }

    public void setIdFiche(Integer idFiche) {
        this.idFiche = idFiche;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFiche != null ? idFiche.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FicheEntity)) {
            return false;
        }
        FicheEntity other = (FicheEntity) object;
        if ((this.idFiche == null && other.idFiche != null) || (this.idFiche != null && !this.idFiche.equals(other.idFiche))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.epi.jee.entities.FicheEntity[ idFiche=" + idFiche + " ]";
    }
    
}
