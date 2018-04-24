/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.epi.jee.dao;

import edu.epi.jee.entities.ProjectEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ProjectDao extends GenericDao<ProjectEntity>{
public List<ProjectEntity> findAllProjects();
}

