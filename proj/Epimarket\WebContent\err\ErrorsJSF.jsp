<%-- 
    Document   : ErrorsJSF
    Created on : 10 janv. 2012, 13:06:11
    Author     : locque_d
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>
<%@ taglib uri="http://sourceforge.net/projects/jsf-comp" prefix="c" %>

<f:loadBundle basename="message" var="message"/>

<f:view locale="#{LangApp.locale}" >

<html>
    <head>           
        <title>Product: Chart</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">     
        <link href="../includes/css/Common.css" rel="stylesheet" type="text/css" />    
    </head>    
    <body>
        
        <jsp:include page="/template/header.jsp" />
        
        <x:div styleClass="content" >
            <x:div style="margin-left: auto; margin-right: auto; width: 800px;margin-top: 100px;">
                <h:form>                    
<%          if (request.getParameter("err") != null) 
                      {%><x:graphicImage alt="" value="/includes/images/CatErr/#{param['err']}.jpg" width="" height="" /><%}%> 
                </h:form>
            </x:div>
        </x:div>       
        
        <jsp:include page="/template/footer.jsp" />
            
    </body>
</html>

</f:view>