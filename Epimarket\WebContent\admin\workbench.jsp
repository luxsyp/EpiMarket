<%-- 
    Document   : newjsp
    Created on : 14 déc. 2011, 10:17:00
    Author     : locque_d
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
                    <h:form id="prodTITLE">
                        <x:htmlTag value="legend"><x:outputText value="#{message.user}"/></x:htmlTag>
                    </h:form>
                    <jsp:include page="productList.jsp" />
                </x:htmlTag>
                    
                <x:htmlTag value="br" />
                <x:htmlTag value="br" />
                    
                <x:htmlTag value="fieldset">
                    <h:form id="usrTITLE">
                        <x:htmlTag value="legend"><x:outputText value="#{message.user}"/></x:htmlTag>
                    </h:form>
                    <jsp:include page="userList.jsp" />                    
                </x:htmlTag>
                
            </x:div>            
            <jsp:include page="/template/footer.jsp" />
            
        </body>
    </html>
</f:view>
            