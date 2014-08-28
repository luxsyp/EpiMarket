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
                    <x:htmlTag value="legend"><x:outputText value="#{message.addcateg}" /></x:htmlTag>
                    
                    <h:form id="goBack">
                        <h:commandLink id="lienWelcome" action="admin"><h:outputText value="#{message.backprodlist}" /></h:commandLink>
                    </h:form>
                    <h:form id="AddProd" >
                        <h:panelGrid columns="2">
                                <h:outputText value="Name" />
                                <h:panelGroup>
                                    <h:inputText id="category" value="#{categoryBean.category.name}" required="true" validator="#{categoryBean.validateCategory}" />
                                    <h:message for="category" />
                                </h:panelGroup>
                                <h:commandButton value="#{message.cree}" action="#{categoryBean.addCategory}" />

                        </h:panelGrid>
                    </h:form>
                </x:htmlTag>                               
            </x:div>            
            <jsp:include page="/template/footer.jsp" />               
        </body>
    </html>
</f:view>