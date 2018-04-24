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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author HLOL
 */
@Entity
@Table(name = "notification")
@NamedQueries({
    @NamedQuery(name = "NotificationEntity.findAll", query = "SELECT n FROM NotificationEntity n")})
public class NotificationEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNotification")
    private Integer idNotification;
    @Size(max = 20)
    @Column(name = "title")
    private String title;
    @Size(max = 20)
    @Column(name = "subject")
    private String subject;
    @Size(max = 20)
    @Column(name = "type")
    private String type;
    @Column(name = "display")
    private Boolean display;
    @OneToOne
    private UserEntity sender;
    @OneToMany
    private List<UserEntity> receiver;

    public UserEntity getSender() {
        return sender;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
    }

    public List<UserEntity> getReceiver() {
        return receiver;
    }

    public void setReceiver(List<UserEntity> receiver) {
        this.receiver = receiver;
    }
    public NotificationEntity() {
    }

    public NotificationEntity(Integer idNotification) {
        this.idNotification = idNotification;
    }
    public NotificationEntity(String title, String subject, String type, boolean display) {
		super();
		this.title = title;
		this.subject = subject;
		this.type = type;
		this.display = display;
	}
    public Integer getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Integer idNotification) {
        this.idNotification = idNotification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotification != null ? idNotification.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotificationEntity)) {
            return false;
        }
        NotificationEntity other = (NotificationEntity) object;
        if ((this.idNotification == null && other.idNotification != null) || (this.idNotification != null && !this.idNotification.equals(other.idNotification))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.epi.jee.entities.NotificationEntity[ idNotification=" + idNotification + " ]";
    }
    
}
