<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Students</title>
            <link rel="stylesheet" type="text/css" href="/jpa-student-manager/jsfcrud.xhtml" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Students</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{students.students.id}" title="Id" />
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{students.students.name}" title="Name" required="true" requiredMessage="The name field is required." />
                    <h:outputText value="Age:"/>
                    <h:inputText id="age" value="#{students.students.age}" title="Age" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{students.edit}" value="Save">
                    <f:param name="jsfcrud.currentStudents" value="#{jsfcrud_class['com.linyinfeng.projects.jpastudentmanager.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][students.students][students.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{students.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentStudents" value="#{jsfcrud_class['com.linyinfeng.projects.jpastudentmanager.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][students.students][students.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{students.listSetup}" value="Show All Students Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
