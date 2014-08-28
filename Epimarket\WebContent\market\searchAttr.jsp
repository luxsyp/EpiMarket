<%-- 
    Document   : searchAttr
    Created on : 7 janv. 2012, 15:06:01
    Author     : Marjorie
--%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>
<%@ taglib uri="http://myfaces.apache.org/sandbox" prefix="s"%>

<f:loadBundle basename="message" var="message"/>

<h:form id="searchAttributes">
   <h:panelGrid columns="5" style="width: 600px;margin-left: auto;margin-right: auto;">

        <x:outputText value="#{message['search.genre']}" />
        <h:selectOneMenu id="gender" value="#{productSearchBean.idCategory}">
            <x:selectItems value="#{categoryBean.allCategory}" var="category" itemLabel="#{category.name}" itemValue="#{category.id}"/>
        </h:selectOneMenu>
       
        <x:outputText value="#{message['search.andOr']}" />
        <h:selectBooleanCheckbox title="ANDor"  value="#{productSearchBean.andOr}" style="width: auto;" />
        
       <h:panelGroup style="width: 250px;" >
            <s:inputSuggestAjax suggestedItemsMethod="#{productSearchBean.getSuggest}" value="#{productSearchBean.search}" styleClass="autoComplete" />
            <h:commandButton value="#{message['search.search']}" action=""/>           
       </h:panelGroup>

   </h:panelGrid>
</h:form>
