
package edu.epi.jee.controllers;

import edu.epi.jee.dao.AdminDao;
import edu.epi.jee.entities.AdminEntity;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AdminController implements Serializable{

    @EJB 
     AdminDao adminDao;

   
   private  AdminEntity adminEntity ;

    public AdminEntity getAdminEntity() {
        return adminEntity;
    }

    public void setAdminEntity(AdminEntity adminEntity) {
        this.adminEntity = adminEntity;
    }

    
  public String newUser()
{       
           AdminEntity a =adminDao.create(adminEntity);
	
	
	return "login.jsf?faces-redirect=true";
}
    
}
