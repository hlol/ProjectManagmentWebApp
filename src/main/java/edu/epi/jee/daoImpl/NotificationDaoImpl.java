/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.daoImpl;

import edu.epi.jee.dao.GenericDaoImpl;
import edu.epi.jee.dao.NotificationDao;
import edu.epi.jee.entities.NotificationEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author HLOL
 */
@Stateless
public class NotificationDaoImpl extends GenericDaoImpl<NotificationEntity> implements NotificationDao{

    @Override
    public List<NotificationEntity> findAllNotifications() {
            Query query = em.createQuery("select f FROM NotificationEntity f"); 
		
		return query.getResultList();    }
}
