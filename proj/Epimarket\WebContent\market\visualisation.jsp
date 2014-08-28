<%-- 
    Document   : visualisation
    Created on : 29 déc. 2011, 18:13:59
    Author     : Marjorie
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:loadBundle basename="message" var="message"/>

<f:view locale="#{LangApp.locale}">
    <html>
        <head>           
            <title>EpiMarket - Market</title>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">     
            <link href="../includes/css/Common.css" rel="stylesheet" type="text/css" /> 
            <link href="../includes/css/slider.css" rel="stylesheet" type="text/css" />             

        </head> 
         <body>
            <jsp:include page="../template/header.jsp" />
 
            <x:div styleClass="content">
                <jsp:include page="../includes/messagesAll.jsp" />
                <x:div styleClass="sliderBack" >
                    <x:div id="sliderPos">
                        <x:graphicImage value="http://media.screened.com/uploads/0/34/278312-breaking_bad_super.jpg" width="600" height="300" />
                    </x:div>
                </x:div>

                <h:form id="visuProduct">
                    <x:panelGrid columns="2" style="width: 650px; margin-left: auto;margin-right: auto;text-align: center;" >
                        <h:outputText value="Name" style="font-weight: bold;" />                        
                        <h:outputText value="#{productBuyBean.currentProduct.name}" />
                        
                        <h:outputText value="#{message.design}" style="font-weight: bold;" />                        
                        <h:outputText value="#{productBuyBean.currentProduct.designation}" />
                                                
                        <h:outputText value="#{message.categ}" style="font-weight: bold;" />                        
                        <h:outputText value="#{productBuyBean.currentProduct.category.name}" />

                        <h:outputText value="#{message.price}" style="font-weight: bold;" />                        
                        <h:outputText value="#{productBuyBean.currentProduct.price}" />
                        
                        <h:outputText value="#{message.type}" style="font-weight: bold;" />                        
                        <h:outputText value="#{productBuyBean.currentProduct.type}" />
                        
                        <h:outputText value=" " />
                        <h:commandButton action="#{productBuyBean.buyProduct}" value="#{message['caddie.add']}" />
                        
                    </x:panelGrid>                    
                </h:form>
                 
            </x:div>
            
                       
            <jsp:include page="../template/footer.jsp" />
         </body>
    </html>
</f:view>