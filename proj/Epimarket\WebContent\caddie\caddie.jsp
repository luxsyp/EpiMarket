<%-- 
    Document   : caddie
    Created on : 13 déc. 2011, 17:47:53
    Author     : locque_d
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
        </head> 
         <body>
            <jsp:include page="../template/header.jsp" />
 
            <x:div styleClass="content">
                <jsp:include page="affItems.jsp" />
            </x:div>
                       
            <jsp:include page="../template/footer.jsp" />
         </body>
    </html>
</f:view>