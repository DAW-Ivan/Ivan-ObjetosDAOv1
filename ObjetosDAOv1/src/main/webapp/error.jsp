<%-- 
    Document   : salida
    Created on : 07-nov-2017, 10:49:06
    Author     : Jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
        
        <head>
            <title>Error</title>
        </head>
        <body>
            
            <div>
                <p><%=request.getAttribute("error")%></p>
                        
            </div>
                <p><a href="<%= request.getContextPath() %>">Volver</a></p>
        </body>
    </html>
