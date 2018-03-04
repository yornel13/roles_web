<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="models.RolCliente" %>

<%
    List<RolCliente> roles = (List<RolCliente>) request.getAttribute("roles");
    String mes = (String) request.getAttribute("mes");
    String searchDate = (String) request.getAttribute("searchDate");
    if (searchDate == null) {
        searchDate = "";
    }
%>

<html>
    <head>
        <title>Roles Cliente</title>
        <link rel="icon" href="imagenes/security_icon.png" />
        <link rel="stylesheet" href="css/estilo.css">
        <%@include file="WEB-INF/partials-static/meta-bootstrap.html"%>
    </head>
    <body>
        <%@include file="WEB-INF/partials-static/header_principal.html" %>

        <div class="container">

            <div class="container-filter">

                <form class="container-range" action="rol_cliente" method="post">
                    <button href="#" name="previous" class="previous">&#8249;</button>
                    <strong><%=mes%></strong>
                    <button href="#" name="next" class="next">&#8250;</button>
                </form>
                <%
                    if (!searchDate.equals("") || roles != null && !roles.isEmpty() ) {
                %>
                <div class="row">
                    <div class="col-sm-6 col-sm-offset-3">
                        <div id="imaginary_container">
                            <form class="input-group stylish-input-group input-append" action="rol_cliente" method="post">
                                <input value="<%=searchDate%>" name="text" class="form-control" placeholder="Buscar empleado" >
                                <span class="input-group-addon">
                                    <button class="searchButton" name="search">
                                        <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </span>
                            </form>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
            <%
                if (roles != null && !roles.isEmpty()) {
            %>
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover" >
                    <thead>
                        <tr class="table-info">
                            <%@include file="WEB-INF/partials-static/header_list.html" %>
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
            <div class="emptyTable">
                <p><a href="">No hubo resultados para la busqueda</a></p>
            </div>
            <%
                }
            %>
        </div>
    </body>
</html>

