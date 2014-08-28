<%-- 
    Document   : inscriptionForm
    Created on : 7 janv. 2012, 18:32:23
    Author     : Marjorie
--%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>
<%@ taglib uri="http://myfaces.apache.org/sandbox" prefix="s"%>

<f:loadBundle basename="message" var="message"/>

<x:htmlTag value="fieldset">
    <x:htmlTag value="legend"><x:outputText value="#{message.connect}" /></x:htmlTag>
    <h:form id="loginIncPage">
        <h:panelGrid columns="2"> 
            <h:outputText value="#{message.login}" />&nbsp;:
            <h:panelGroup>
                <h:inputText value="#{userBean.user.login}" id="loginC" required="true" validator="" />
                <h:message for="loginC"/>
            </h:panelGroup>

            <h:outputText value="#{message.pass}" />&nbsp;:
            <h:panelGroup>
                <h:inputSecret id="pwdC" required="true"  value="#{userBean.user.password}" validator="" />
                <h:message for="pwdC"/>
            </h:panelGroup>
            <h:commandButton value="#{message.connect}" action="#{userBean.connect}" />
        </h:panelGrid>                            
    </h:form>                    
</x:htmlTag>                

<x:htmlTag value="br" />

<x:htmlTag value="fieldset">
    <x:htmlTag value="legend"><x:outputText value="#{message.create}" /></x:htmlTag>
    <h:form id="subscrite">
        <h:panelGrid columns="2">

            <h:outputText value="#{message.name}" />&nbsp;:
            <h:panelGroup>
                <h:inputText value="#{userAddBean.user.firstName}" id="name" required="true" validator="#{userAddBean.validateName}" />
                <h:message for="name"/>
            </h:panelGroup>

            <h:outputText value="#{message.lastname}" />&nbsp;:
            <h:panelGroup>
                <h:inputText value="#{userAddBean.user.lastName}" id="lastname" required="true" validator="#{userAddBean.validateLastname}" />
                <h:message for="lastname"/>
            </h:panelGroup>

            <h:outputText value="#{message.login}" />&nbsp;:
            <h:panelGroup>
                <h:inputText value="#{userAddBean.user.login}" id="login" required="true" validator="#{userAddBean.validateLogin}" />
                <h:message for="login"/>
            </h:panelGroup>

            <h:outputText value="#{message.mail}" />&nbsp;:
            <h:panelGroup>
                <h:inputText value="#{userAddBean.user.mail}" id="mail" required="true" validator="#{userAddBean.validateMail}" />
                <h:message for="mail"/>
            </h:panelGroup>

            <h:outputText value="#{message.pass}" />&nbsp;:
            <h:panelGroup>
                <s:passwordStrength id="pwd" required="true"
                                    preferredPasswordLength="6"
                                    value="#{userAddBean.user.password}"
                                    validator="#{userAddBean.validatePwd}"
                                    prefixText="Strength : "
                                    textStrengthDescriptions="Very Poor;Weak;Average;Strong;Excellent"
                                    strengthIndicatorType="bar"
                                    useCustomSecurity="true"
                                    customSecurityExpression="A3S2N3A2"
                                    penaltyRatio="50" /> 
                <h:message for="pwd"/>
            </h:panelGroup>

            <h:outputText value="#{message.pass2}" />&nbsp;:
            <h:panelGroup>
                <h:inputSecret id="pwd2" required="true"  value=""  validator="#{userAddBean.validatePwd2}" />
                <h:message for="pwd2"/>
            </h:panelGroup>

            <h:commandButton value="#{message.create}" action="#{userAddBean.addUser}" />
        </h:panelGrid>                       
    </h:form>
</x:htmlTag>