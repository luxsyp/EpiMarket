<%-- 
    Document   : UserEdit
    Created on : 7 déc. 2011, 17:36:05
    Author     : locque_d
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:view>
<f:loadBundle basename="message" var="message"/>

    <html>
        <head>
            <title>User : Edit</title>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link href="../includes/css/Common.css" rel="stylesheet" type="text/css" /> 
        </head>
        <body>
            
            <jsp:include page="/template/header.jsp" />
            
            <x:div styleClass="content" id="Users" >
                <x:htmlTag value="fieldset">
                    <x:htmlTag value="legend"><x:outputText value="Edition d'utilisateur" /></x:htmlTag>
                    <jsp:include page="../includes/messagesAll.jsp" />
                    <x:htmlTag value="br" />
                    
                    <h:form id="linkForm" >
                        <h:commandLink id="backLink" action="admin" rendered="#{userEditBean.stateDisplayValueOnly}" ><h:outputText value="Retour a la liste des Users" /></h:commandLink>
                        <h:commandLink id="stopLink" action="admin" rendered="#{!userEditBean.stateDisplayValueOnly}">
                            <h:outputText value="Annuler et retour a la liste des Users" rendered="#{!userEditBean.stateDisplayValueOnly}" />
                        </h:commandLink>
                    </h:form>
                    <x:htmlTag value="br"/>
                    <h:form id="userEditForm" >
                        <h:panelGrid columns="2">

                            <h:outputText value='#{message.id}' />
                            <x:inputText value="#{userEditBean.currentUser.id}" required="true" id="id" displayValueOnly="true" />

                            <h:outputText value='#{message.login}' />        
                            <x:inputText value="#{userEditBean.currentUser.login}" required="true" id="login" displayValueOnly="#{userEditBean.stateDisplayValueOnly}"  validator="#{userAddBean.validateLogin}" />

                            <h:outputText value='#{message.name}' />       
                           <x:inputText value="#{userEditBean.currentUser.firstName}" required="true" id="name" displayValueOnly="#{userEditBean.stateDisplayValueOnly}" validator="#{userAddBean.validateName}" />
                          
                            <h:outputText value='#{message.lastname}' />         
                            <x:inputText value="#{userEditBean.currentUser.lastName}" required="true" id="lastname" displayValueOnly="#{userEditBean.stateDisplayValueOnly}" validator="#{userAddBean.validateLastname}" />

                            <h:outputText value='#{message.mail}' />      
                            <x:inputText value="#{userEditBean.currentUser.mail}" required="true" id="mail" displayValueOnly="#{userEditBean.stateDisplayValueOnly}" validator="#{userAddBean.validateMail}" />

                            <h:outputText value='#{message.pass}' />      
                            <x:inputText value="#{userEditBean.currentUser.password}" required="true" id="pwd" displayValueOnly="#{userEditBean.stateDisplayValueOnly}" validator="#{userAddBean.validatePwd}" />
                            
                            <h:commandButton id="EditUserBtn" action="#{userEditBean.editUser}" value="Edit" rendered="#{userEditBean.stateDisplayValueOnly}" />
                            <h:commandButton id="CancelLink" action="UserEdit" value="Cancel" rendered="#{!userEditBean.stateDisplayValueOnly}" />
                            <h:commandButton id="SaveUserBtn" action="#{userEditBean.saveUser}" value="Save" rendered="#{!userEditBean.stateDisplayValueOnly}" />

                        </h:panelGrid>
                    </h:form>
                    </x:htmlTag>
            </x:div>

            <jsp:include page="/template/footer.jsp" />
        </body>
    </html>

</f:view>