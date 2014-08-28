<%-- 
    Document   : Products
    Created on : 11 déc. 2011, 12:39:26
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

            <script type="text/javascript" src="../includes/scripts/jquery-1.7.1.min.js"></script>
            <script type="text/javascript" src="../includes/scripts/coin-slider.min.js"></script>
        </head> 
         <body>
            <jsp:include page="../template/header.jsp" />
 
            <x:div styleClass="content">
                <jsp:include page="slider.jsp" />
                <jsp:include page="searchAttr.jsp" />
                <jsp:include page="affProducts.jsp" />
            </x:div>
                       
            <jsp:include page="../template/footer.jsp" />
         </body>
    </html>
</f:view>
             
  
