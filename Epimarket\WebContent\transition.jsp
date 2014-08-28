
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="x"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<f:loadBundle basename="message" var="message"/>

<f:view locale="#{LangApp.locale}">
    <html>
        <head>           
            <title>EpiMarket</title>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">     
            <link href="includes/css/Common.css" rel="stylesheet" type="text/css" /> 
             
            <script type="text/javascript" src="includes/scripts/jquery-1.2.6.min.js"></script>
            <script type="text/javascript" src="includes/scripts/jquery.jparallax.js"></script>
             
        </head>       
        <body> 
  
            <jsp:include page="/template/header.jsp" />

            <h:panelGrid columns="3" styleClass="content" >
            
                <x:div styleClass="parallaxBorder parallaxBorder1" style="margin-left: auto;margin-right: auto;" >
                    <x:div styleClass="parallaxImg">
                        <x:div styleClass="parallax" id="parallax1">
                            <h:graphicImage value="includes/images/parallax/parallax-clouds.png" alt="" style="width:350px; height:450px;"/>
                            <h:graphicImage value="includes/images/parallax/parallax-member.png" alt="" style="width:250px; height:450px;"/>
                        </x:div>
                    </x:div>
                </x:div>

                <x:div styleClass="parallaxBorder parallaxBorder3" style="margin-left: auto;margin-right: auto;" >
                    <x:div styleClass="parallaxImg">
                        <x:div styleClass="parallax" id="parallax2">
                            <h:graphicImage value="includes/images/parallax/parallax-clouds.png" alt="" style="width:350px; height:450px;"/>
                            <h:graphicImage value="includes/images/parallax/parallax-admin.png" alt="" style="width:250px; height:450px;"/>
                        </x:div>                                    
                    </x:div>
                </x:div>

                <x:div styleClass="parallaxBorder parallaxBorder1" style="margin-left: auto;margin-right: auto;" >
                    <x:div styleClass="parallaxImg">
                        <x:div styleClass="parallax" id="parallax3">
                            <h:graphicImage value="includes/images/parallax/parallax-clouds.png" alt="" style="width:350px; height:450px;"/>
                            <h:graphicImage value="includes/images/parallax/parallax-mini-market.png" alt="" style="width:330px; height:450px;"/>
                            <h:graphicImage value="includes/images/parallax/parallax-market.png" alt="" style="width:250px; height:450px;"/>
                        </x:div>                                    
                    </x:div>
                </x:div>
                
                <x:div styleClass="transitionCategorie" >
                    <h:form>
                        <h:commandLink action="userPage">
                            <h:graphicImage value="includes/images/parallax/member.png" width="96" height="96" alt="" />
                            <x:htmlTag value="br" />
                            <h:outputText value="#{message.membre}" />
                        </h:commandLink>
                    </h:form>                    
                </x:div>
                <x:div styleClass="transitionCategorie" >
                    <h:form>
                        <h:commandLink action="admin">
                            <h:graphicImage value="includes/images/parallax/admin.png" width="96" height="96" alt="" />                    
                            <x:htmlTag value="br" />
                            <h:outputText value="#{message.admin}" />
                        </h:commandLink>
                    </h:form>
                </x:div>
                <x:div styleClass="transitionCategorie" >
                    <h:form>
                        <h:commandLink action="market">
                            <h:graphicImage value="includes/images/parallax/market.png" width="96" height="96" alt="" />                    
                            <x:htmlTag value="br" />
                            <h:outputText value="#{message.market}" />
                        </h:commandLink>
                    </h:form>
                </x:div>
            </h:panelGrid>
         
            <% for (int i = 0 ; i < 15 ; ++i) {%><x:htmlTag value="br" /><%}%>
            
            <jsp:include page="/template/footer.jsp" />
            
            <script type="text/javascript">
                $(document).ready(function(){
                    $('#parallax1').jparallax();
                    $('#parallax2').jparallax();
                    $('#parallax3').jparallax();
                });
            </script>
        
        </body>
    </html>
</f:view>