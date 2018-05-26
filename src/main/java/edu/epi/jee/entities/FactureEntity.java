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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author HLOL
 */
@Entity
@Table(name = "facture")
@NamedQueries({
    @NamedQuery(name = "FactureEntity.findAll", query = "SELECT f FROM FactureEntity f")})
public class FactureEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFacture")
    private Integer idFacture;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "brutHt")
    private Float brutHt;
    @Column(name = "remise")
    private Float remise;
    @Column(name = "escompte")
    private Float escompte;
    @Column(name = "netFinancierHt")
    private Float netFinancierHt;
    @Column(name = "forfaitaire")
    private Float forfaitaire;
    @Column(name = "netHt")
    private Float netHt;
    @Column(name = "tVA")
    private Float tVA;
    @Column(name = "netTTC")
    private Float netTTC;
    @Column(name = "acompteVerse")
    private Float acompteVerse;
    @Column(name = "netPayerTTC")
    private Float netPayerTTC;
    @Column(name = "payementDate")
    @Temporal(TemporalType.DATE)
    private Date payementDate;

    @JoinColumn(name = "idProject", referencedColumnName = "idProject")
    @ManyToOne
    private ProjectEntity project;

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }
    public FactureEntity() {
    }

    public FactureEntity(Integer idFacture) {
        this.idFacture = idFacture;
    }

    public Integer getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(Integer idFacture) {
        this.idFacture = idFacture;
    }

    public Float getBrutHt() {
        return brutHt;
    }

    public void setBrutHt(Float brutHt) {
        this.brutHt = brutHt;
    }
    

    public Float getNetFinancierHt() {
        return netFinancierHt;
    }

    public void setNetFinancierHt(Float netFinancierHt) {
        this.netFinancierHt = netFinancierHt;
    }
    public Float getRemise() {
        return remise;
    }

    public void setRemise(Float remise) {
        this.remise = remise;
    }

    public Float getEscompte() {
        return escompte;
    }

    public void setEscompte(Float escompte) {
        this.escompte = escompte;
    }

    public Float getForfaitaire() {
        return forfaitaire;
    }

    public void setForfaitaire(Float forfaitaire) {
        this.forfaitaire = forfaitaire;
    }

    public Float getNetHt() {
        return netHt;
    }

    public void setNetHt(Float netHt) {
        this.netHt = netHt;
    }

    public Float getTVA() {
        return tVA;
    }

    public void setTVA(Float tVA) {
        this.tVA = tVA;
    }

    public Float getNetTTC() {
        return netTTC;
    }

    public void setNetTTC(Float netTTC) {
        this.netTTC = netTTC;
    }

    public Float getAcompteVerse() {
        return acompteVerse;
    }

    public void setAcompteVerse(Float acompteVerse) {
        this.acompteVerse = acompteVerse;
    }

    public Float getNetPayerTTC() {
        return netPayerTTC;
    }

    public void setNetPayerTTC(Float netPayerTTC) {
        this.netPayerTTC = netPayerTTC;
    }

    public Date getPayementDate() {
        return payementDate;
    }

    public void setPayementDate(Date payementDate) {
        this.payementDate = payementDate;
    }
    public FactureEntity(float brutHt, float remise, float escompte, float netFinancierHt, float forfaitaire,
		float netHt, float tVA, float netTTC, float acompteVerse, float netPayerTTC, Date payementDate) {
	super();
	this.brutHt = brutHt;
	this.remise = remise;
	this.escompte = escompte;
	this.netFinancierHt = netFinancierHt;
	this.forfaitaire = forfaitaire;
	this.netHt = netHt;
	this.tVA = tVA;
	this.netTTC = netTTC;
	this.acompteVerse = acompteVerse;
	this.netPayerTTC = netPayerTTC;
	this.payementDate = payementDate;
}
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFacture != null ? idFacture.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FactureEntity)) {
            return false;
        }
        FactureEntity other = (FactureEntity) object;
        if ((this.idFacture == null && other.idFacture != null) || (this.idFacture != null && !this.idFacture.equals(other.idFacture))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.epi.jee.entities.FactureEntity[ idFacture=" + idFacture + " ]";
    }
    
}
