<%-- 
    Document   : userInfo
    Created on : 7 janv. 2012, 18:35:52
    Author     : Marjorie
--%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:loadBundle basename="message" var="message"/>

<f:subview id="userFormMembre">
    
    <h:form id="formMembre">
        <x:htmlTag value="fieldset">
            <x:htmlTag value="legend"><x:outputText value="Mes informations" /></x:htmlTag>
            <h:panelGrid columns="2">

                <h:outputText value='#{message.login}' />        
                <h:outputText value="#{userBean.user.login}" />

                <h:outputText value='#{message.name}' />       
                <x:inputText value="#{userBean.user.firstName}" required="true" id="name" validator="#{userAddBean.validateName}" />

                <h:outputText value='#{message.lastname}' />         
                <x:inputText value="#{userBean.user.lastName}" required="true" id="lastname" validator="#{userAddBean.validateLastname}" />

                <h:outputText value='#{message.mail}' />      
                <x:inputText value="#{userBean.user.mail}" required="true" id="mail" validator="#{userAddBean.validateMail}" />

                <h:outputText value='#{message.pass}' />      
                <x:inputText value="#{userBean.user.password}" required="true" id="pwd" validator="#{userAddBean.validatePwd}" />

                <h:commandButton id="SaveUserBtn" action="#{userBean.saveUser}" value="#{message.save}" />

            </h:panelGrid>
        </x:htmlTag>
    </h:form>
    
    <x:htmlTag value="br" />
    
    <h:form id="formHistoric">
        <x:htmlTag value="fieldset">
            <x:htmlTag value="legend"><x:outputText value="#{message['historic.buy']}" /></x:htmlTag>
            
            <x:dataTable id="HistoricalData" binding="#{historicalBean.dataTable}" value="#{userBean.allOrder}" var="data" styleClass="genTable" >
                
            <h:column>
                <f:facet name="header"><x:outputText value="#{message.date}" /></f:facet>
                <x:commandLink action="#{historicalBean.searchData}" value="#{data.dateOrderStarted}" />
            </h:column>
                
            <h:column>
                 <f:facet name="header"><x:outputText value="#{message.total}" /></f:facet>
                 <h:outputText value="#{data.total}" />
            </h:column>
                
            </x:dataTable>

        </x:htmlTag>
    </h:form>

</f:subview>
