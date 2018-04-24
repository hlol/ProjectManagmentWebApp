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

/**
 *
 * @author HLOL
 */
@Entity
@Table(name = "bill")
@NamedQueries({
    @NamedQuery(name = "BillEntity.findAll", query = "SELECT b FROM BillEntity b")
    , @NamedQuery(name = "BillEntity.findByIdBill", query = "SELECT b FROM BillEntity b WHERE b.idBill = :idBill")})
public class BillEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idBill")
    private Integer idBill;

    public BillEntity() {
    }

    public BillEntity(Integer idBill) {
        this.idBill = idBill;
    }

    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBill != null ? idBill.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BillEntity)) {
            return false;
        }
        BillEntity other = (BillEntity) object;
        if ((this.idBill == null && other.idBill != null) || (this.idBill != null && !this.idBill.equals(other.idBill))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.epi.jee.entities.BillEntity[ idBill=" + idBill + " ]";
    }
    
}
