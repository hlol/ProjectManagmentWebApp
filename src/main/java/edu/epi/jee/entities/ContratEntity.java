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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "contrat")
@NamedQueries({
    @NamedQuery(name = "ContratEntity.findAll", query = "SELECT c FROM ContratEntity c")})
public class ContratEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idContrat")
    private Integer idContrat;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "avance")
    private Double avance;
    @Column(name = "creationDate")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Size(max = 255)
    @Column(name = "documentType")
    private String documentType;
    @Size(max = 255)
    @Column(name = "documentUrl")
    private String documentUrl;
    @Size(max = 50)
    @Column(name = "typeContrat")
    private String typeContrat;

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }
    @OneToOne
    private ProjectEntity project;

    public ContratEntity() {
    }
    public ContratEntity(String typeContrat, String description,Date creationDate,String documentType, String documentUrl,  double avance ) {
		super();
                this.typeContrat = typeContrat ;
		this.documentType = documentType;
		this.description = description;
		this.documentUrl = documentUrl;
		this.creationDate = creationDate;
		this.avance = avance ;
		
	
	}
    public ContratEntity(Integer idContrat) {
        this.idContrat = idContrat;
    }

    public Integer getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(Integer idContrat) {
        this.idContrat = idContrat;
    }

    public Double getAvance() {
        return avance;
    }

    public void setAvance(Double avance) {
        this.avance = avance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContrat != null ? idContrat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratEntity)) {
            return false;
        }
        ContratEntity other = (ContratEntity) object;
        if ((this.idContrat == null && other.idContrat != null) || (this.idContrat != null && !this.idContrat.equals(other.idContrat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.epi.jee.entities.ContratEntity[ idContrat=" + idContrat + " ]";
    }
    
}
