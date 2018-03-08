<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Joshuan Marval
  Date: 5/3/2018
  Time: 8:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Control - Admin</title>
    <link rel="icon" href="images/security_icon.png" />
    <link rel="stylesheet" href="css/general-style.css">
    <link rel="stylesheet" href="css/header-style.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%@include file="WEB-INF/partials-static/meta-bootstrap.html"%>
</head>
<body>
    <%@include file="WEB-INF/partials-dynamic/header_principal.jsp" %>

    <div class="container">

        <div class="table-responsive">

            <table class="table table-striped table-bordered table-hover">
                <thead>
                    <tr class="table-info">
                        <th></th>
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

                            for (User userIn :
                                    listaUsuario) {
                        %>
                        <td>
                            <form>
                                <button class="searchButton" name="user_id" value="<%=userIn.getId()%>">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                            </form>
                        </td>
                        <td><%=userIn.getNombre()%></td>
                        <td><%=userIn.getApellido()%></td>
                        <td><%=userIn.getCedula()%></td>
                        <td><%=userIn.getUsername()%></td>
                        <td><%=userIn.getTipo()%></td>
                    </tr>
                <%
                    }
                %>
                </tbody>

            </table>

        </div>


    </div>

</body>
</html>
