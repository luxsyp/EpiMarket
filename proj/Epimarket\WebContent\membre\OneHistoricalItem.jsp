<%-- 
    Document   : OneHistoricalItem
    Created on : 8 janv. 2012, 02:56:04
    Author     : Marjorie
--%>


<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:loadBundle basename="message" var="message"/>

<f:subview id="histoSubview">
    
    <h:form id="histoForm">
        <x:htmlTag value="fieldset">
            <x:htmlTag value="legend"><x:outputText value="#{historicalBean.currentOrder.dateOrderStarted}" /></x:htmlTag>
            
            <x:commandLink id="backLink" action="userPage" value="Retour a ma page" />
            <x:htmlTag value="br" />
            
            <x:dataTable id="OneHistoricalData" value="#{historicalBean.currentOrder.lines}" var="data" styleClass="genTable" >

                <h:column>
                    <f:facet name="header"><h:outputText value="#{message.title}" /></f:facet>  
                    
                    <h:outputText value="#{data.product.name}" />
                    <f:facet name="footer"><h:outputText value="#{message.total}"/></f:facet>
                </h:column>

                <h:column>
                    <f:facet name="header"><h:outputText value="#{message.design}" /></f:facet>            
                    <h:outputText value="#{data.product.designation}" />
                </h:column>

                <h:column>
                    <f:facet name="header"><h:outputText value="#{message.quantity}" /></f:facet>  
                    <h:outputText value="#{data.quantity}" />
                </h:column>


                <h:column>
                    <f:facet name="header"><h:outputText value="#{message.price}" /></f:facet>            
                    <h:outputText value="#{data.price}" />
                 <f:facet name="footer"><h:outputText value="#{historicalBean.currentOrder.total}" /></f:facet>
                </h:column>

            </x:dataTable>
        </x:htmlTag>
    </h:form>

</f:subview>