/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linyinfeng.projects.jpastudentmanager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import com.linyinfeng.projects.jpastudentmanager.util.JsfUtil;
import com.linyinfeng.projects.jpastudentmanager.util.PagingInfo;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author yinfeng
 */
public class StudentsController {

    public StudentsController() {
        pagingInfo = new PagingInfo();
        converter = new StudentsConverter();
    }
    private Students students = null;
    private List<Students> studentsItems = null;
    private StudentsFacade jpaController = null;
    private StudentsConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "com.linyinfeng.projects_jpa-student-manager_war_0.1-SNAPSHOTPU")
    private EntityManagerFactory emf = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public StudentsFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (StudentsFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "studentsJpa");
        }
        return jpaController;
    }

    public SelectItem[] getStudentsItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getStudentsItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Students getStudents() {
        if (students == null) {
            students = (Students) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentStudents", converter, null);
        }
        if (students == null) {
            students = new Students();
        }
        return students;
    }

    public String listSetup() {
        reset(true);
        return "students_list";
    }

    public String createSetup() {
        reset(false);
        students = new Students();
        return "students_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(students);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Students was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("students_detail");
    }

    public String editSetup() {
        return scalarSetup("students_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        students = (Students) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentStudents", converter, null);
        if (students == null) {
            String requestStudentsString = JsfUtil.getRequestParameter("jsfcrud.currentStudents");
            JsfUtil.addErrorMessage("The students with id " + requestStudentsString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String studentsString = converter.getAsString(FacesContext.getCurrentInstance(), null, students);
        String currentStudentsString = JsfUtil.getRequestParameter("jsfcrud.currentStudents");
        if (studentsString == null || studentsString.length() == 0 || !studentsString.equals(currentStudentsString)) {
            String outcome = editSetup();
            if ("students_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit students. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(students);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Students was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentStudents");
        Long id = new Long(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Students was successfully deleted.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
//        if ((ERROR)) {
//            return relatedControllerOutcome;
//        }
        return listSetup();
    }

    public List<Students> getStudentsItems() {
        if (studentsItems == null) {
            getPagingInfo();
            studentsItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return studentsItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "students_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "students_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        students = null;
        studentsItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Students newStudents = new Students();
        String newStudentsString = converter.getAsString(FacesContext.getCurrentInstance(), null, newStudents);
        String studentsString = converter.getAsString(FacesContext.getCurrentInstance(), null, students);
        if (!newStudentsString.equals(studentsString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
