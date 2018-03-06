<%@ page import="models.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Joshuan Marval
  Date: 5/3/2018
  Time: 8:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Control - Admin</title>
    <%@include file="WEB-INF/partials-static/meta-bootstrap.html"%>
</head>
<body>
    <div class="container">

        <div class="table-responsive">

            <table class="table table-striped table-bordered table-hover">
                <thead>
                    <tr class="table-info">
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Cedula</th>
                        <th>Nombre usuario</th>
                        <th>Tipo</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <%
                            List<User> listaUsuario = (List<User>) request.getAttribute("listaUsuario");

                            for (User user :
                                    listaUsuario) {
                        %>
                        <td><%=user.getNombre()%></td>
                        <td><%=user.getApellido()%></td>
                        <td><%=user.getCedula()%></td>
                        <td><%=user.getUsername()%></td>
                        <td><%=user.getTipo()%></td>
                    </tr>
                <%
                    }
                %>
                </tbody>

            </table>

        </div>


    </div>




<%@include file="WEB-INF/partials-static/scripts-bootstrap.html"%>
</body>
</html>
