<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="models.RolCliente" %>
<%@ page import="utilidad.Const" %>
<%
    List<RolCliente> roles = (List<RolCliente>) request.getAttribute(Const.ROLES_CLIENTE);
%>
<html>
    <head>
        <title>Roles Cliente</title>
        <link rel="icon" href="../images/security_icon.png" />
        <link rel="stylesheet" href="../css/general-style.css">
        <link rel="stylesheet" href="../css/header-style.css">
        <link rel="stylesheet" href="../css/bar-search-style.css">
        <link rel="stylesheet" href="../css/date-search-style.css">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <%@include file="../WEB-INF/partials-static/meta-bootstrap.html"%>
    </head>
    <body>
        <%@include file="../WEB-INF/partials-dynamic/header_principal.jsp" %>

        <div class="container">

            <section>
                <h2 class="content-title">Roles Cliente</h2>
            </section>

            <div class="container-filter">

                <%@include file="../WEB-INF/partials-dynamic/date_search.jsp" %>

                <%@include file="../WEB-INF/partials-dynamic/bar_search.jsp" %>

            </div>
            <%
                if (roles != null && !roles.isEmpty()) {
            %>
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover" >
                    <thead>
                        <tr class="table-info">
                            <%@include file="../WEB-INF/partials-static/header_list.html" %>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (RolCliente rolCliente :
                                    roles) {
                        %>
                        <tr>
                            <td>
                                <form>
                                    <button class="searchButton" name="id" value="<%=rolCliente.getId()%>">
                                        <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </form>
                            </td>
                            <td><%=rolCliente.getCedula()%></td>
                            <td><%=rolCliente.getEmpleado()%></td>
                            <td><%=rolCliente.getEmpresa()%></td>
                            <td><%=rolCliente.getDias()%></td>
                            <td><%=rolCliente.getHorasNormales()%></td>
                            <td><%=rolCliente.getHorasSobreTiempo()%></td>
                            <td><%=rolCliente.getHorasSuplementarias()%></td>
                            <td><%=rolCliente.getTotalHorasExtras()%></td>
                            <td><small>$</small><%=rolCliente.getSalario()%></td>
                            <td><small>$</small><%=rolCliente.getMontoHorasSobreTiempo()%></td>
                            <td><small>$</small><%=rolCliente.getMontoHorasSuplementarias()%></td>
                            <td><small>$</small><%=rolCliente.getBono()%></td>
                            <td><small>$</small><%=rolCliente.getTransporte()%></td>
                            <td><small>$</small><%=rolCliente.getTotalBonos()%></td>
                            <td><small>$</small><%=rolCliente.getSubtotal()%></td>
                            <td><small>$</small><%=rolCliente.getVacaciones()%></td>
                            <td><small>$</small><%=rolCliente.getDecimoTercero()%></td>
                            <td><small>$</small><%=rolCliente.getDecimoCuarto()%></td>
                            <td><small>$</small><%=rolCliente.getDecimoTercero()%></td>
                            <td><small>$</small><%=rolCliente.getJubilacionPatronal()%></td>
                            <td><small>$</small><%=rolCliente.getAportePatronal()%></td>
                            <td><small>$</small><%=rolCliente.getSeguros()%></td>
                            <td><small>$</small><%=rolCliente.getUniformes()%></td>
                            <td><small>$</small><%=rolCliente.getTotalIngreso()%></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <%
                } else {
            %>
            <%@include file="../WEB-INF/partials-static/empty_search.html" %>
            <%
                }
            %>
        </div>
    </body>
</html>

