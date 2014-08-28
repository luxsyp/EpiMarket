<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:loadBundle basename="message" var="message"/>

<f:subview id="productList">
    
        <h:form id="productForm">  
            <x:dataTable    id="productList" 
                            value='#{productBean.allProduct}' 
                            var='product' 
                            binding="#{productBean.dataTable}"
                            sortColumn="#{productBean.sortColumn}" 
                            sortAscending="#{productBean.sortAscending}" 
                            preserveSort="true" 
                            rows="#{productBean.rowCount}"
                            styleClass="genTable">
                
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="id" arrow="#{true}" >
                            <h:outputText value='#{message.id}' style="font-weight: bold" /> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:outputText value='#{product.id}' />                          
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="name" arrow="#{true}" >
                            <h:outputText value='#{message.lastname}' style="font-weight: bold" /> 
                        </x:commandSortHeader>
                    </f:facet>
                    <h:commandLink id="lienProduct" action="#{productBean.searchProduct}" >
                        <h:outputText value='#{product.name}' />
                    </h:commandLink>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="designation" arrow="#{true}" >
                            <h:outputText value='#{message.design}' style="font-weight: bold" /> 
                        </x:commandSortHeader>      
                    </f:facet>                          
                    <h:outputText value='#{product.designation}' />
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <x:commandSortHeader columnName="#{message.price}" arrow="#{true}" >
                            <h:outputText value='price' style="font-weight: bold" /> 
                        </x:commandSortHeader>      
                    </f:facet>                          
                    <h:outputText value='#{product.price}' />
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value='#{message.price}' style="font-weight: bold" />     
                    </f:facet>                          
                    <h:outputText value='#{product.category.name}' />
                </h:column>
                <h:column>
                    <x:commandLink id="deleteProduct" action="#{productBean.deleteProduct}">
                        <x:graphicImage value="/includes/images/trash_full.png" width="32" height="32" />
                    </x:commandLink>
                </h:column>
                
            </x:dataTable>          
            <x:dataScroller id="scroll_1" for="productList" fastStep="10" pageCountVar="pageCount" pageIndexVar="pageIndex" styleClass="scroller" paginator="true" paginatorMaxPages="9" paginatorTableClass="paginator" paginatorActiveColumnStyle="font-weight:bold;" immediate="true" >
                <f:facet name="first" >     <x:graphicImage url="/includes/images/arrow-first.png" border="1" />    </f:facet>
                <f:facet name="last">       <x:graphicImage url="/includes/images/arrow-last.png" border="1" />     </f:facet>
                <f:facet name="previous">   <x:graphicImage url="/includes/images/arrow-previous.png" border="1" /> </f:facet>
                <f:facet name="next">       <x:graphicImage url="/includes/images/arrow-next.png" border="1" />     </f:facet>
                <f:facet name="fastforward"><x:graphicImage url="/includes/images/arrow-ff.png" border="1" />       </f:facet>
                <f:facet name="fastrewind"> <x:graphicImage url="/includes/images/arrow-fr.png" border="1" />       </f:facet>
            </x:dataScroller>
            <x:div styleClass="scroller">
                <x:dataScroller id="scroll_2" for="productList" rowsCountVar="rowsCount" displayedRowsCountVar="displayedRowsCountVar" firstRowIndexVar="firstRowIndex" lastRowIndexVar="lastRowIndex" pageCountVar="pageCount" immediate="true" pageIndexVar="pageIndex" >
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
            <x:htmlTag value="br" />
            <x:div styleClass="scroller">
                <x:commandLink value="#{message.addprod}" action="#{productAddBean.goAddProduct}" />
                <x:htmlTag value="br" />
                <x:commandLink value="#{message.categ}" action="#{categoryBean.goAddCategory}" />
            </x:div>
            <x:htmlTag value="br" />
            <x:div styleClass="scroller">
                <h:commandLink id="lienPDF" action="#{productBean.getPdf}" ><h:outputText value="#{message.pdfExport}" /></h:commandLink>
            </x:div>              
            <x:div styleClass="scroller">
                <x:commandLink id="lienChart" action="chartProd" value="#{message.visustats}"></x:commandLink>
            </x:div>              
            <x:div styleClass="scroller">
                <x:commandLink id="lienMarketing" action="#{userBean.sendMail}" value="#{message.marketing}"></x:commandLink>
            </x:div>              
        </h:form>               
    
</f:subview>