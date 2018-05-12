<%@ page import="models.Cliente" %>
<%@ page import="utilidad.Const" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<Cliente> clientes = (List<Cliente>) request.getAttribute(Const.CLIENTES);
%>
<html>
    <head>
        <title>Clientes</title>
        <link rel="icon" href="images/security_icon.png" />
        <link rel="stylesheet" href="css/table-style.css">
        <link rel="stylesheet" href="css/general-style.css">
        <link rel="stylesheet" href="css/header-style.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/date-search-style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <%@include file="WEB-INF/partials-static/meta-bootstrap.html"%>
    </head>
    <body>
        <%@include file="WEB-INF/partials-dynamic/header_principal.jsp" %>

        <div class="container">

            <div class="container-buttons-top">
                <a href="/empresa" name="return" class="return">
                    ❮ Volver
                </a>
            </div>

            <section>
                <h2 class="content-title">Clientes</h2>
            </section>

            <div class="content-table">

                <table class="table table-striped table-bordered table-hover" style="max-width: 1000px; position: relative;left:0;right:0;margin: auto;">
                    <thead>
                        <tr class="table-info">
                            <th class="th-size-p1"></th>
                            <th class="th-size-1">Ruc</th>
                            <th colspan="4" class="th-size-1">Nombre</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Cliente client :
                                    clientes) {
                        %>
                        <tr>
                            <td>
                                <form method="post">
                                    <button class="searchButton" name="<%=Const.ID%>" value="<%=client.getId()%>">
                                        <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </form>
                            </td>
                            <td><%=client.getRuc()%></td>
                            <td><%=client.getNombre()%></td>
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
