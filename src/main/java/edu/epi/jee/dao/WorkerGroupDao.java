/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.dao;

import edu.epi.jee.entities.WorkerGroupEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author HLOL
 */
@Local

public interface WorkerGroupDao extends GenericDao<WorkerGroupEntity>{
public List<WorkerGroupEntity> findAllWorkerGroups();
}