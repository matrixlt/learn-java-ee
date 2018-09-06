<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Students Items</title>
            <link rel="stylesheet" type="text/css" href="/jpa-student-manager/jsfcrud.xhtml" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Students Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Students Items Found)<br />" rendered="#{students.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{students.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{students.pagingInfo.firstItem + 1}..#{students.pagingInfo.lastItem} of #{students.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{students.prev}" value="Previous #{students.pagingInfo.batchSize}" rendered="#{students.pagingInfo.firstItem >= students.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{students.next}" value="Next #{students.pagingInfo.batchSize}" rendered="#{students.pagingInfo.lastItem + students.pagingInfo.batchSize <= students.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{students.next}" value="Remaining #{students.pagingInfo.itemCount - students.pagingInfo.lastItem}"
                                   rendered="#{students.pagingInfo.lastItem < students.pagingInfo.itemCount && students.pagingInfo.lastItem + students.pagingInfo.batchSize > students.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{students.studentsItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id"/>
                            </f:facet>
                            <h:outputText value="#{item.id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Name"/>
                            </f:facet>
                            <h:outputText value="#{item.name}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Age"/>
                            </f:facet>
                            <h:outputText value="#{item.age}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{students.detailSetup}">
                                <f:param name="jsfcrud.currentStudents" value="#{jsfcrud_class['com.linyinfeng.projects.jpastudentmanager.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][students.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{students.editSetup}">
                                <f:param name="jsfcrud.currentStudents" value="#{jsfcrud_class['com.linyinfeng.projects.jpastudentmanager.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][students.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{students.remove}">
                                <f:param name="jsfcrud.currentStudents" value="#{jsfcrud_class['com.linyinfeng.projects.jpastudentmanager.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][students.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{students.createSetup}" value="New Students"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
