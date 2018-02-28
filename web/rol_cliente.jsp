<%@ page import="java.util.List" %>
<%@ page import="models.User" %>

<%
    List<User> listaUsuarios = (List<User>) request.getAttribute("dameLista");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rol Cliente</title>
    <link rel="stylesheet" href="estilo.css">
</head>
<body>

<header>
    <div class="ancho">
        <div class="logo">
            <p><a href="index.jsp">Control Guardias</a></p>
        </div>
        <nav>
            <ul>
                <!--<li><a href="#">Inicio</a></li>
                <li><a href="#">Contacto</a></li>
                <li><a href="#">Servicio</a></li>-->
                <li><a href="#">Salir</a></li>
            </ul>
        </nav>
    </div>
</header>

<table>
    <thead>
    <%@include file="WEB-INF/partials-static/header_list.html" %>
    </thead>
    <%
        for (User user :
                listaUsuarios) {
    %>

    <tr>
        <td><%=user.getNombre()%></td>
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
</body>
</html>

