<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:view locale="#{LangApp.locale}" >

<f:loadBundle basename="message" var="message"/>

<html>
    <head>
        <title>Product: Edit</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">     
        <link href="../includes/css/Common.css" rel="stylesheet" type="text/css" /> 
    </head>   
    <body>
        <jsp:include page="../template/header.jsp" />
      
        <x:div styleClass="content">
            <x:htmlTag value="fieldset">  
                <x:htmlTag value="legend"><x:outputText value="Edition de Produit" /></x:htmlTag>

                <jsp:include page="../includes/messagesAll.jsp" />
                <x:htmlTag value="br"/>                
                <h:form id="linkForm">  
                    <h:commandLink id="lienWelcome" action="admin" rendered="#{productEditBean.stateDisplayValueOnly}" >
                        <h:outputText value="#{message.backprodlist}" />
                    </h:commandLink>
                    <h:commandLink id="lienAnnuler2" action="admin" rendered="#{!productEditBean.stateDisplayValueOnly}">
                        <h:outputText  value="#{message.stopAndBack}" rendered="#{!productEditBean.stateDisplayValueOnly}"/>
                    </h:commandLink>
                </h:form>

                <h:form id="prodEditForm" >
                    <h:panelGrid columns="2">
                        <h:outputText value="#{message.editProd}" style="font-weight: bold" />
                        <h:outputText value="#{productEditBean.currentProduct.name}" />        
                        
                        <h:outputText value='#{message.id}' style="font-weight: bold" />                       
                        <x:inputText value="#{productEditBean.currentProduct.id}" required="true" id="id" displayValueOnly="true" />
 
                        <h:outputText value='#{message.lastname}' style="font-weight: bold"/>
                        <x:inputText value="#{productEditBean.currentProduct.name}" required="true" id="Prodname" displayValueOnly="#{productEditBean.stateDisplayValueOnly}" validator="#{productAddBean.validateName}" />
      
                        <h:outputText value='#{message.design}' style="font-weight: bold" />
                        <x:inputText value="#{productEditBean.currentProduct.designation}" id="designation" displayValueOnly="#{productEditBean.stateDisplayValueOnly}" validator="#{productAddBean.validateDesignation}"/> 
                        
                        <h:outputText value='#{message.price}' style="font-weight: bold" /> 
                        <x:inputText value="#{productEditBean.currentProduct.price}" required="true" id="price" displayValueOnly="#{productEditBean.stateDisplayValueOnly}" validator="#{productAddBean.validateEditPrice}" /> 
                       
                        <h:outputText value='#{message.categ}' style="font-weight: bold" />
                        <h:selectOneMenu id="gender" value="#{productEditBean.currentProduct.category.id}" rendered="#{!productEditBean.stateDisplayValueOnly}">
                            <x:selectItems value="#{productSearchBean.allCategory}" var="category" itemLabel="#{category.name}" itemValue="#{category.id}"/>
                        </h:selectOneMenu>
                        <x:outputText value="#{productEditBean.currentProduct.category.name}" id="category" rendered="#{productEditBean.stateDisplayValueOnly}" /> 
                                                  
                        <h:commandButton id="butEditProduct" action="#{productEditBean.editProduct}" value="#{message.edit}" rendered="#{productEditBean.stateDisplayValueOnly}" />
                        <h:commandButton id="lienAnnuler" action="productEdit" value="#{message.cancel}" rendered="#{!productEditBean.stateDisplayValueOnly}" />          
                        <h:commandButton id="butSaveProduct" action="#{productEditBean.saveProduct}" value="#{message.save}" rendered="#{!productEditBean.stateDisplayValueOnly}" />
                            
                    </h:panelGrid>   
                </h:form>
            </x:htmlTag>
        </x:div>
    
      <jsp:include page="../template/footer.jsp" />
  </body>
</html>
</f:view>
    
        