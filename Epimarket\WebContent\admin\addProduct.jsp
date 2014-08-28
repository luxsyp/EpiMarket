<%-- 

    Document   : addProduct

    Created on : 29 déc. 2011, 21:48:49

    Author     : Marjorie

--%>

 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<f:loadBundle basename="message" var="message"/>
 
<f:view locale="#{LangApp.locale}">
    <html>
        <head>           
            <title>EpiMarket</title>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">     
            <link href="../includes/css/Common.css" rel="stylesheet" type="text/css" />
        </head>       
        <body> 
            <jsp:include page="/template/header.jsp" />
            <x:div styleClass="content" >
                <x:htmlTag value="fieldset">
                    <x:htmlTag value="legend"><x:outputText value="Ajout de Produit" /></x:htmlTag>
                    
                    <h:form id="goBack">
                        <h:commandLink id="lienWelcome" action="admin"><h:outputText value="Retour à la liste des Produits" /></h:commandLink>
                    </h:form>

                    <h:form id="typeAdd">
                        <h:commandButton value="Creer un dvd" action="#{productAddBean.addDvd}" />
                        <h:commandButton value="Creer un jeu" action="#{productAddBean.addGame}" />
                    </h:form>
                    
                    <h:form id="AddProd" >
                        <h:panelGrid columns="2">
                                <h:outputText value="#{message.addtype}" />
                                <h:outputText value="#{productAddBean.type}" />
                                <h:outputText value="#{message.lastname}" />
                                <h:panelGroup>
                                    <h:inputText id="name" value="#{productAddBean.product.name}" required="true" validator="#{productAddBean.validateName}" />
                                    <h:message for="name" />
                                </h:panelGroup>
                                <h:outputText value="#{message.design}" />
                                <h:panelGroup>
                                    <h:inputText id="designation" value="#{productAddBean.product.designation}" required="true" validator="#{productAddBean.validateDesignation}" />
                                    <h:message for="designation" />
                                </h:panelGroup>
                                <h:outputText value="#{message.price}" />
                                <h:panelGroup>
                                    <h:inputText id="price" value="#{productAddBean.product.price}" required="true" validator="#{productAddBean.validatePrice}" />
                                    <h:message for="price" />
                                </h:panelGroup>
                                <h:outputText value="#{message.categ}" />
                                <h:panelGroup>
                                    <h:selectOneMenu id="gender" value="#{productAddBean.product.category.id}">
                                        <x:selectItems value="#{categoryBean.allCategory}" var="category" itemLabel="#{category.name}" itemValue="#{category.id}"/>
                                    </h:selectOneMenu>
                                </h:panelGroup>
                                <h:commandButton value="#{message.cree}" action="#{productAddBean.addProduct}" />
                        </h:panelGrid>
                    </h:form>
                
                </x:htmlTag>                               
            </x:div>            
            <jsp:include page="/template/footer.jsp" />               
        </body>
    </html>
</f:view>