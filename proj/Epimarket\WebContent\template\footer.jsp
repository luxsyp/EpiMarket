<%-- 
    Document   : footer
    Created on : 11 déc. 2011, 01:23:15
    Author     : Marjorie
--%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:loadBundle basename="message" var="message"/>
<f:subview id="footer">
    <x:div styleClass="footer">
        <h:panelGrid id="gridFooter" columns="9">
            <h:form id="formFooter">
                <x:div styleClass="epiTitle">
                    <h:commandLink id="home1" action="transition" >
                        <h:outputText value="#{message.epimarket}" />
                    </h:commandLink>   
                </x:div>
            </h:form>

            <h:form id="ContactBtn">
                <h:commandLink action="contact" >
                    <h:outputText value="#{message.contact}" />
                </h:commandLink>
            </h:form>

        </h:panelGrid>
    </x:div>
    
</f:subview>
