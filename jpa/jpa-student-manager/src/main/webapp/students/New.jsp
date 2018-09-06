<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Students</title>
            <link rel="stylesheet" type="text/css" href="/jpa-student-manager/jsfcrud.xhtml" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Students</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{students.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:inputText id="id" value="#{students.students.id}" title="Id" required="true" requiredMessage="The id field is required." />
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{students.students.name}" title="Name" required="true" requiredMessage="The name field is required." />
                    <h:outputText value="Age:"/>
                    <h:inputText id="age" value="#{students.students.age}" title="Age" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{students.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{students.listSetup}" value="Show All Students Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
