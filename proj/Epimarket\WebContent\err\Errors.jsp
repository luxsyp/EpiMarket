<%-- 
    Document   : Errors
    Created on : 6 janv. 2012, 17:08:07
    Author     : Marjorie
--%>


<%
if (request.getParameter("err") != null)  
    {
        response.sendRedirect("/Epimarket/err/ErrorsJSF.faces?err=" + request.getParameter("err")); 
    }
    else
    {   
        response.sendRedirect("transition.faces"); 
    }
%>
