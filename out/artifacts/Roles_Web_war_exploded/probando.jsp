<%@ page import="java.util.List" %>
<%@ page import="models.User" %>

<%
    List<User> listaUsuarios = (List<User>) request.getAttribute("dameLista");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Probando</title>
    <%@include file="WEB-INF/partials-static/meta-bootstrap.html"%>
</head>
<body>
    <h1>Listado de usuarios</h1>
    <div class="container">
        <div class="table-responsive">
            <table class="table table-striped table-bordered table-hover" >
                <tr class="table-info">
                    <%@include file="WEB-INF/partials-static/header_list.html" %>
                </tr>
                <%
                    for (User user :
                            listaUsuarios) {
                %>
                <tr>
                    <td><%=user.getUsername()%></td>
                    <td><%=user.getApellido()%></td>
                    <td><%=user.getCedula()%></td>
                    <%
                        if (user.getTipo().equals("A")) {
                    %>
                        <td>Administrador</td>
                    <%
                        } else if (user.getTipo().equals("E")) {
                    %>
                        <td>Empleado</td>
                    <%
                        } else if (user.getTipo().equals("C")) {
                    %>
                        <td>Cliente</td>
                    <%
                        }
                    %>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </div>
    <%@include file="WEB-INF/partials-static/scripts-bootstrap.html"%>
</body>
</html>
