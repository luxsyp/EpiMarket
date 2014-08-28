<%-- 
    Document   : affItems
    Created on : 13 déc. 2011, 17:48:36
    Author     : locque_d
--%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:loadBundle basename="message" var="message"/>

<h:form id="affItemCaddie">
    <x:dataTable    id="caddieList" 
                    value='#{caddieBean.caddie.lines}'
                    binding="#{caddieBean.dataTable}"
                    var='lines'
                    styleClass="genTable"
                    cellpadding="0" cellspacing="0">

        <h:column>
            <f:facet name="header"><h:outputText value="#{message.title}" /></f:facet>  
            <h:commandLink styleClass="linkProduct" id="lienProduct" action="#{productBuyBean.viewProduct}" >
                <f:param name="id" value="#{lines.product.idabstract}"/>
                <h:outputText value="#{lines.product.name}" />
            </h:commandLink>
            <f:facet name="footer"><h:outputText value="#{message.total}"/></f:facet>
        </h:column>

        <h:column>
            <f:facet name="header"><h:outputText value="#{message.design}" /></f:facet>            
            <h:outputText value="#{lines.product.designation}" />
        </h:column>

        <h:column>
            <f:facet name="header"><h:outputText value="#{message.quantity}" /></f:facet>            
            <x:inputText value="#{lines.quantity}" style="width: 50px;" />
        </h:column>

        <h:column>
            <f:facet name="header"><h:outputText value="#{message.price}" /></f:facet>            
            <h:outputText value="#{lines.product.price}" />
            <f:facet name="footer"><h:outputText value="#{caddieBean.caddie.total}" /></f:facet>
        </h:column>

        <h:column>
            <x:commandLink action="#{caddieBean.deleteProduct}">
                <x:graphicImage value="/includes/images/trash_full.png" width="32" height="32" />
            </x:commandLink>
        </h:column>
    </x:dataTable>
    <h:commandButton value="#{message.doCommand}" action="#{userBean.doCommand}" styleClass="PasserCommandeBtn" />
</h:form> 