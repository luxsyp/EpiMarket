<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:loadBundle basename="message" var="message"/>

<f:subview id="userList">
    <h:form id="userForm">  
        <x:dataTable    id="userList" 
                        value='#{userBean.allUser}' 
                        var='user'
                        binding="#{userBean.dataTable}"
                        sortColumn="#{userBean.sortColumn}" 
                        sortAscending="#{userBean.sortAscending}" 
                        preserveSort="true" 
                        rows="#{userBean.rowCount}"
                        styleClass="genTable">

            <h:column>
                <f:facet name="header">
                    <x:commandSortHeader columnName="id" arrow="#{true}" >
                        <h:outputText value='#{message.id}' style="font-weight: bold" /> 
                    </x:commandSortHeader>
                </f:facet>
                <h:outputText value='#{user.id}' />                          
            </h:column>

           <h:column>
                <f:facet name="header">
                    <x:commandSortHeader columnName="login" arrow="#{true}" >
                        <h:outputText value='#{message.login}' style="font-weight: bold" /> 
                    </x:commandSortHeader>
                </f:facet>
                <h:commandLink action="#{userBean.searchUser}">
                    <h:outputText value='#{user.login}' />                          
                </h:commandLink>
            </h:column>

            <h:column>
                <f:facet name="header">
                    <x:commandSortHeader columnName="firstName" arrow="#{true}" >
                        <h:outputText value='#{message.name}' style="font-weight: bold" /> 
                    </x:commandSortHeader>
                </f:facet>
                <h:outputText value='#{user.firstName}' />
            </h:column>

            <h:column>
                <f:facet name="header">
                    <x:commandSortHeader columnName="lastName" arrow="#{true}" >
                        <h:outputText value='#{message.lastname}' style="font-weight: bold" /> 
                    </x:commandSortHeader>      
                </f:facet>                          
                <h:outputText value='#{user.lastName}' />
            </h:column>

            <h:column>
                <f:facet name="header">
                    <x:commandSortHeader columnName="mail" arrow="#{true}" >
                        <h:outputText value='#{message.mail}' style="font-weight: bold" /> 
                    </x:commandSortHeader>      
                </f:facet>                          
                <h:outputText value='#{user.mail}' />
            </h:column>

            <h:column>
                <f:facet name="header">
                    <x:commandSortHeader columnName="pwd" arrow="#{true}" >
                        <h:outputText value='#{message.pass}' style="font-weight: bold" /> 
                    </x:commandSortHeader>      
                </f:facet>                          
                <h:outputText value='#{user.password}' />
            </h:column>

            <h:column>
                <x:commandLink id="deleteUser" action="#{userBean.deleteUser}">
                    <x:graphicImage value="/includes/images/trash_full.png" width="32" height="32" />
                </x:commandLink>
            </h:column>
            
        </x:dataTable>          
        <x:dataScroller id="scroll_1" for="userList" fastStep="10" pageCountVar="pageCount" 
                        pageIndexVar="pageIndex" styleClass="scroller" paginator="true" paginatorMaxPages="9" 
                        paginatorTableClass="paginator" paginatorActiveColumnStyle="font-weight:bold;" immediate="true" >
            <f:facet name="first" >     <x:graphicImage url="/includes/images/arrow-first.png" border="1" />    </f:facet>
            <f:facet name="last">       <x:graphicImage url="/includes/images/arrow-last.png" border="1" />     </f:facet>
            <f:facet name="previous">   <x:graphicImage url="/includes/images/arrow-previous.png" border="1" /> </f:facet>
            <f:facet name="next">       <x:graphicImage url="/includes/images/arrow-next.png" border="1" />     </f:facet>
            <f:facet name="fastforward"><x:graphicImage url="/includes/images/arrow-ff.png" border="1" />       </f:facet>
            <f:facet name="fastrewind"> <x:graphicImage url="/includes/images/arrow-fr.png" border="1" />       </f:facet>
        </x:dataScroller>
        <x:div styleClass="scroller" >
            <x:dataScroller id="scroll_2" for="userList" rowsCountVar="rowsCount" displayedRowsCountVar="displayedRowsCountVar" firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex" pageCountVar="pageCount" immediate="true" pageIndexVar="pageIndex" >
                <h:outputFormat value="#{message['info.scroller.main']}" styleClass="standard" > 
                    <f:param value="#{rowsCount}" />
                    <f:param value="#{displayedRowsCountVar}" />
                    <f:param value="#{firstRowIndex}" />
                    <f:param value="#{lastRowIndex}" />
                    <f:param value="#{pageIndex}" />
                    <f:param value="#{pageCount}" />
                </h:outputFormat>
            </x:dataScroller>
        </x:div>
        <x:div styleClass="scroller">
            <h:commandLink id="lienChart" action="#{userBean.getPdf}" ><h:outputText value="#{message.pdfExport}" /></h:commandLink>
        </x:div>  

    </h:form>               
</f:subview>