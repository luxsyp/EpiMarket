<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>
<%@ taglib uri="http://sourceforge.net/projects/jsf-comp" prefix="c" %>

<f:loadBundle basename="message" var="message"/>

<f:view locale="#{LangApp.locale}">

<html>
<head>           
    <head>           
        <title>Product: Chart</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">     
        <link href="../includes/css/Common.css" rel="stylesheet" type="text/css" />    
    </head>    
  <body>
      <jsp:include page="/template/header.jsp" />
      <x:div styleClass="content" >
          <x:htmlTag value="fieldset">
              <x:htmlTag value="legend"><x:outputText value="#{message.stat}" /></x:htmlTag>          
              <h:form id="chartForm" styleClass="scroller"> 

                <c:chart id="chart1" datasource="#{productChart.pieDataset}" type="pie" title="#{message.typeprod}" is3d="true" styleClass="scroller"
                    alpha="75" startAngle="90" legend="true" height="500" width="600"></c:chart>
                <x:htmlTag value="br" />

                <c:chart id="chart8" datasource="#{productChart.firstXYDataset}" type="xyline" background="#FFFFFF" title="#{message.dvd}"></c:chart>
                <c:chart id="chart9" datasource="#{productChart.secondXYDataset}" type="xyline" background="#FFFFFF" title="#{message.game}"></c:chart>

              </h:form>
          </x:htmlTag>
    </x:div>            
    <jsp:include page="/template/footer.jsp" />
            
  </body>
</html>
</f:view>