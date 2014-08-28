<%-- 
    Document   : header
    Created on : 10 déc. 2011, 01:38:08
    Author     : Marjorie
--%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>
<%@ taglib uri="http://myfaces.apache.org/sandbox" prefix="s"%>

<f:loadBundle basename="message" var="message"/>

<f:subview id="header">
    
    <x:div styleClass="header">
        <h:panelGrid columns="7">
            <h:form id="HomeLogo">
                <x:div styleClass="epiTitle">  
                    <h:commandLink action="transition" >
                        <h:outputText value="EpiMarket" />
                    </h:commandLink>   
                </x:div>
            </h:form>

            <h:form id="helloAuthHeader" rendered="#{userBean.userConnect}"><h:outputText value="#{message.hello} #{userBean.user.firstName} #{userBean.user.lastName}"/></h:form>
            <h:form id="DisconnectHeader" rendered="#{userBean.userConnect}"><h:commandButton value="#{message.disconnect}" action="#{userBean.disconnect}"/></h:form>

            <h:form id="LoginFormHeader" rendered="#{!userBean.userConnect}">
                <h:panelGrid columns="5" style="width: auto;">
                    <h:outputText value="#{message.login}"/>
                    <h:inputText value="#{userBean.user.login}" id="login" required="true"/>
                    <h:outputText value="#{message.pass}"/>
                    <h:inputSecret id="pwd" value="#{userBean.user.password}" required="true"/>
                    <h:commandButton value="#{message.connect}" action="#{userBean.connect}"/>
                </h:panelGrid>
            </h:form>

            <h:form id="IncBtnHeader" rendered="#{!userBean.userConnect}">
                <h:commandLink action="inscription">           
                    <h:outputText value="#{message.create}" />
                </h:commandLink>
            </h:form>

            <h:form id="frlang">
                <h:commandLink action="#{LangApp.activerFR}" immediate="true">
                    <h:graphicImage value="/includes/images/flag_fr.png" style="border: none;" width="32" height="32" />                    
                </h:commandLink>
            </h:form>

            <h:form id="enlang">
                <h:commandLink action="#{LangApp.activerEN}" immediate="true">
                    <h:graphicImage value="/includes/images/flag_uk.png" style="border: none;" width="32" height="32" />
                </h:commandLink>
            </h:form>
        </h:panelGrid>

        <h:panelGrid columns="2" style="float: right; width: 500px;margin-right: 175px;" >
            <h:form style="width: 250px;" id="caddieHeader">                
                <x:commandLink action="caddie">                    
                    <h:panelGrid columns="2" style="width: 250px;vertical-align:middle">
                        <x:graphicImage value="/includes/images/parallax/market.png" height="32" width="32" alt="market" />
                        <h:outputText value="#{caddieBean.caddie.quantity} #{message.caddieItem} #{caddieBean.caddie.total}e" />                        
                    </h:panelGrid>
                </x:commandLink>
            </h:form>
            <h:form id="searchHeader">
               <h:panelGrid columns="2" style="width: 250px;">
                   <s:inputSuggestAjax suggestedItemsMethod="#{productSearchBean.getSuggest}" value="#{productSearchBean.search}" />
                   <h:commandButton value="#{message['search.search']}" action="#{productSearchBean.search}"/>
                </h:panelGrid>
            </h:form>
        </h:panelGrid>   
            
    </x:div>
  
</f:subview>

