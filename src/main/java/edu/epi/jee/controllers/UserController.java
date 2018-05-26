package edu.epi.jee.controllers;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import org.primefaces.event.FlowEvent;


import edu.epi.jee.dao.RoleDao;
import edu.epi.jee.dao.UserDao;
import edu.epi.jee.entities.RoleEntity;
import edu.epi.jee.entities.UserEntity;
import edu.epi.jee.entities.UserGroupEntity;
import edu.epi.jee.dao.UserGroupDao;
import edu.epi.jee.entities.AdminEntity;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserController implements Serializable {
@EJB
private UserDao userDao ;
@EJB
private RoleDao roleDao ;
@EJB
private UserGroupDao userGroupDao ;


private AdminEntity adminEntity = new AdminEntity();

    public AdminEntity getAdminEntity() {
        return adminEntity;
    }

    public void setAdminEntity(AdminEntity adminEntity) {
        this.adminEntity = adminEntity;
    }
private String roleSelected;

private UserEntity userEntity = new UserEntity();
private UserEntity userEntity1 = new UserEntity();

    public UserEntity getUserEntity1() {
        return userEntity1;
    }

    public void setUserEntity1(UserEntity userEntity1) {
        this.userEntity1 = userEntity1;
    }

private List<UserEntity> listUsers;
private List<RoleEntity> listRoles;

private boolean skip;
private List<SelectItem> allGroupsName;    
private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
public UserDao getUserDao() {
	return userDao;
}

public void setUserDao(UserDao userDao) {
	this.userDao = userDao;
} 
@PostConstruct
public void init() {
	/*RoleEntity r1 = new RoleEntity();
	r1.setName("gerant");
	RoleEntity r2 = new RoleEntity();
	r2.setName("responsable technique");
	RoleEntity r3 = new RoleEntity();
	r3.setName("testeur");
	RoleEntity r4 = new RoleEntity();
	r4.setName("responsable financier");
	roleDao.create(r1);
	roleDao.create(r2);
	roleDao.create(r3);
	roleDao.create(r4);
   UserEntity u1 = new UserEntity();
   u1.setLastName("ali");
   u1.setFirstName("ahmed");
   UserEntity u2 = new UserEntity();
   u2.setLastName("nouha");
   u2.setFirstName("talbi");
   UserEntity u3 = new UserEntity();
   u3.setLastName("sonia");
   u3.setFirstName("melki");
   UserEntity u4 = new UserEntity();
   u4.setLastName("saif");
   u4.setFirstName("manaa");
   UserEntity u5 = new UserEntity();
   u5.setLastName("hend");
   u5.setFirstName("selmi");
   
UserGroupEntity g1 = new UserGroupEntity("groupe1");
UserGroupEntity g2 = new UserGroupEntity("groupe2");
UserGroupEntity g3 = new UserGroupEntity("groupe3");
 userGroupDao.create(g1);
 userGroupDao.create(g2);
 userGroupDao.create(g3);
 u1.setUsergroupEntity(g1);
 u2.setUsergroupEntity(g2);
 u3.setUsergroupEntity(g3);
 u4.setUsergroupEntity(g1);
 u5.setUsergroupEntity(g2);
 userDao.create(u1);
 userDao.create(u2);
 userDao.create(u3);
 userDao.create(u4);
 userDao.create(u5);*/
 listRoles = roleDao.findAllRoles();
  listUsers=userDao.findAllUsers();
    List<UserGroupEntity> listGroupUser = userGroupDao.findAllUserGroup();
 allGroupsName = new ArrayList<SelectItem>();
 ListIterator<UserGroupEntity> it = listGroupUser.listIterator();
 while(it.hasNext()){
	 UserGroupEntity group = it.next();
	 SelectItemGroup groupitem = new SelectItemGroup(group.getGroupName());
		 ListIterator<UserEntity> its = listUsers.listIterator();
		SelectItem[] listItems = new SelectItem[group.getUserEntityList().size()];
		int i=0;
		 for(UserEntity us : group.getUserEntityList()){
		 SelectItem userPerGroupitem = new SelectItem(us.getFirstName()+" "+us.getLastName(),us.getFirstName()+" "+us.getLastName());
		listItems[i]=userPerGroupitem;
		
		i++;
		 }
		 groupitem.setSelectItems(listItems);//.setSelectItems(new SelectItem[]{userPerGroupitem});
		 
	 allGroupsName.add(groupitem);
		 
 }
}
public UserEntity getUserEntity() {
	return userEntity;
}

public void setUserEntity(UserEntity userEntity) {
	this.userEntity = userEntity;
}

public String saveNewUser() {
    try {
      
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("User Saved "));
        context.getExternalContext().getFlash().setKeepMessages(true);
        return "/dashboard?faces-redirect=true";
    } catch (Exception e) {
        return "ERROR";
    }
}
public static String getMD5(String input) {
    try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
}

public boolean isSkip() {
	return skip;
}

public void setSkip(boolean skip) {
	this.skip = skip;
}
public String onFlowProcess(FlowEvent event) {
    if(skip) {
        skip = false;   //reset in case user goes back
        return "confirm";
    }
    else {
        return event.getNewStep();
    }
}

public List<SelectItem> getAllGroupsName() {
	return allGroupsName;
}

public void setAllGroupsName(List<SelectItem> allGroupsName) {
	this.allGroupsName = allGroupsName;
}



public String addUserToGroup(){
	UserGroupEntity userGroupEntity = new UserGroupEntity();
        System.out.println("--*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-***-*-*-*-*-*-*"+roleSelected);
	System.out.println(groupName);
	System.out.println("--*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-***-*-*-*-*-*-*"+roleSelected);
	UserEntity user = userDao.findUserByName(groupName);
	RoleEntity role = roleDao.findRoleByName(roleSelected);
	userEntity.setUsergroupEntity(user.getUsergroupEntity());
	userEntity.setRoleEntity(role);
	return "dashboard.jsf?faces-redirect=true";
}
public String newUser()
{
	UserEntity u1 = userDao.create(userEntity);
	listUsers= userDao.findAllUsers();
	userEntity = new UserEntity();
	return "listUsers.jsf?faces-redirect=true";
}

public String getRoleSelected() {
	return roleSelected;
}

public void setRoleSelected(String roleSelected) {
	this.roleSelected = roleSelected;
}

public List<RoleEntity> getListRoles() {
	return listRoles;
}

public void setListRoles(List<RoleEntity> listRoles) {
	this.listRoles = listRoles;
}
 

}
