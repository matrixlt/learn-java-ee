/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linyinfeng.projects.jpastudentmanager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yinfeng
 */
@Stateless
public class StudentsFacade extends AbstractFacade<Students> {

    @PersistenceContext(unitName = "com.linyinfeng.projects_jpa-student-manager_war_0.1-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StudentsFacade() {
        super(Students.class);
    }
    
}
