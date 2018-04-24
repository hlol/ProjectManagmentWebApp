/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author HLOL
 */
@Entity
@Table(name = "customer")
@NamedQueries({
    @NamedQuery(name = "CustomerEntity.findAll", query = "SELECT c FROM CustomerEntity c")})
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCustomer")
    private Integer idCustomer;
    @Size(max = 55)
    @Column(name = "firstName")
    private String firstName;
    @Size(max = 55)
    @Column(name = "lastName")
    private String lastName;
    @Size(max = 35)
    @Column(name = "cin")
    private String cin;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Lob
    @Size(max = 2147483647)
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Size(max = 30)
    @Column(name = "zipCode")
    private String zipCode;
    @Size(max = 55)
    @Column(name = "siteweb")
    private String siteweb;
    @Size(max = 30)
    @Column(name = "city")
    private String city;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<ProjectEntity> projects;

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
    }
    public CustomerEntity() {
    }

    public CustomerEntity(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
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

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getSiteweb() {
        return siteweb;
    }

    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCustomer != null ? idCustomer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerEntity)) {
            return false;
        }
        CustomerEntity other = (CustomerEntity) object;
        if ((this.idCustomer == null && other.idCustomer != null) || (this.idCustomer != null && !this.idCustomer.equals(other.idCustomer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.epi.jee.entities.CustomerEntity[ idCustomer=" + idCustomer + " ]";
    }
    public CustomerEntity(String firstName, String lastName, String cin,String email,String phone,
			String zipCode, String siteweb, String city) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.cin = cin;
		this.email = email;
		this.phone = phone;
		this.zipCode = zipCode;
                this.siteweb = siteweb;
		this.city = city;
		
		
	}

	public CustomerEntity(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
