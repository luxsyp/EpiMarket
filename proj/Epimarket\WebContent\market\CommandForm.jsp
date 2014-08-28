<%-- 
    Document   : CommandForm
    Created on : 10 janv. 2012, 18:20:33
    Author     : locque_d
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:loadBundle basename="message" var="message"/>
<x:div styleClass="content">

    <x:htmlTag value="fieldset">
        <x:htmlTag value="legend"><x:outputText value="Passer Commande" /></x:htmlTag>
            <h:form id="validAddr">
                <x:panelGrid columns="2" >

                    <x:outputText value="#{message['command.total']}" />
                    <x:outputText value="#{caddieBean.caddie.total}" />

                    <x:outputText value="#{message['command.number']}" />
                    <h:panelGroup>
                        <x:inputText value="#{userBean.user.billingAddress.streetNumber}" id="num" validator="#{adressCommandBean.validateNum}" required="true" />
                        <h:message for="num" />
                    </h:panelGroup>

                    <x:outputText value="#{message['command.street']}" />
                    <h:panelGroup>
                        <x:inputText value="#{userBean.user.billingAddress.streetName}" id="rue" validator="#{adressCommandBean.validateRue}" required="true" />
                        <h:message for="rue" />
                    </h:panelGroup>

                    <x:outputText value="#{message['command.zip']}" />
                    <h:panelGroup>
                        <x:inputText value="#{userBean.user.billingAddress.zipCode}" id="zip" validator="#{adressCommandBean.validateZip}" required="true" />
                        <h:message for="zip" />
                    </h:panelGroup>

                    <x:outputText value="#{message['command.city']}" />
                    <h:panelGroup>
                        <x:inputText value="#{userBean.user.billingAddress.city}" id="city" validator="#{adressCommandBean.validateCity}" required="true" />
                        <h:message for="city" />
                    </h:panelGroup>

                    <x:outputText value="#{message['command.country']}" />
                    <h:panelGroup>
                        <x:inputText value="#{userBean.user.billingAddress.country}" id="country" validator="#{adressCommandBean.validateCountry}" required="true" />
                        <h:message for="country" />
                    </h:panelGroup>

                    <x:div></x:div>
                    <h:commandButton value="#{message['command.valid']}" action="#{userBean.finalizeCaddie}" />

                </x:panelGrid>               
            </h:form>
        </x:htmlTag>

</x:div>