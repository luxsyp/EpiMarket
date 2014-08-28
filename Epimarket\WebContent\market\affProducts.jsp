<%-- 
    Document   : allProduct
    Created on : 11 déc. 2011, 13:49:10
    Author     : Marjorie
--%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>

<f:loadBundle basename="message" var="message"/>

<h:form id="productForm">  
    <x:div style="witdh: 100%;height: 800px;margin-left: auto;margin-right: auto;">
        <x:dataList    id="productList" 
                       value='#{productBean.allProduct}' 
                       var='product'
                       rows="#{productBean.rowCount}"
                       binding="#{productBean.productTable}">

                <x:div styleClass="productDiv">
                    <h:panelGrid columns="1">
                        <x:div styleClass="productPicCadre">
                            <x:graphicImage value="http://www.mobile2u.com.pk/Images/NoImage.jpg" width="150" height="130" /> 
                        </x:div>
                        <h:commandLink styleClass="linkProduct" id="lienProduct" action="#{productBean.viewProduct}" >
                            <h:outputText value='#{product.name}' />
                        </h:commandLink>
                    </h:panelGrid>
                                 
                </x:div>
        </x:dataList>        
    </x:div>

    <x:dataScroller id="scroll_1" for="productList" fastStep="10" pageCountVar="pageCount" pageIndexVar="pageIndex" 
                    styleClass="scroller" paginator="true" paginatorMaxPages="9" paginatorTableClass="paginator"
                    paginatorActiveColumnStyle="font-weight:bold;" immediate="true" style="float: clear;" >
        <f:facet name="first" >     <x:graphicImage url="/includes/images/arrow-first.png" border="1" />    </f:facet>
        <f:facet name="last">       <x:graphicImage url="/includes/images/arrow-last.png" border="1" />     </f:facet>
        <f:facet name="previous">   <x:graphicImage url="/includes/images/arrow-previous.png" border="1" /> </f:facet>
        <f:facet name="next">       <x:graphicImage url="/includes/images/arrow-next.png" border="1" />     </f:facet>
        <f:facet name="fastforward"><x:graphicImage url="/includes/images/arrow-ff.png" border="1" />       </f:facet>
        <f:facet name="fastrewind"> <x:graphicImage url="/includes/images/arrow-fr.png" border="1" />       </f:facet>
    </x:dataScroller>
    <x:div styleClass="scroller" >
        <x:dataScroller id="scroll_2" for="productList" rowsCountVar="rowsCount"
                        displayedRowsCountVar="displayedRowsCountVar" firstRowIndexVar="firstRowIndex"
                        lastRowIndexVar="lastRowIndex" pageCountVar="pageCount" immediate="true"
                        pageIndexVar="pageIndex" >
            <h:outputFormat value="#{message['info.scroller.main']}"  > 
                <f:param value="#{rowsCount}" />
                <f:param value="#{displayedRowsCountVar}" />
                <f:param value="#{firstRowIndex}" />
                <f:param value="#{lastRowIndex}" />
                <f:param value="#{pageIndex}" />
                <f:param value="#{pageCount}" />
            </h:outputFormat>
        </x:dataScroller>
    </x:div>    
</h:form> 