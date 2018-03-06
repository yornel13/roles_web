<%@ page import="models.RolCliente" %>
<%@ page import="java.util.List" %>
<%@ page import="models.RolIndividual" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<RolCliente> roles = (List<RolCliente>) request.getAttribute("roles");
    RolIndividual rolIndividual = (RolIndividual) request.getAttribute("rolIndividual");
    String mes = (String) request.getAttribute("mes");
%>
<html>
    <head>
        <title>Roles Empleado</title>
        <link rel="icon" href="../images/security_icon.png" />
        <link rel="stylesheet" href="../css/general-style.css">
        <link rel="stylesheet" href="../css/header-style.css">
        <link rel="stylesheet" href="../css/date-search-style.css">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <%@include file="../WEB-INF/partials-static/meta-bootstrap.html"%>
    </head>
    <body>
        <%@include file="../WEB-INF/partials-static/header_principal.html" %>
        <div class="container">

            <div class="container-filter">

                <%@include file="../WEB-INF/partials-dynamic/date_search.html" %>

            </div>
            <%
                if (roles != null && !roles.isEmpty()) {
            %>
            <div class="table-responsive" style="padding-top: 30px">
                <table class="table table-striped table-bordered table-hover" >
                    <%
                        for (RolCliente rol :
                                roles) {
                    %>
                    <tr class="table-info">
                        <th colspan="22" style="background: white">Cliente: <%=rol.getClienteNombre()%></th>
                    </tr>
                    <tr class="table-info">
                        <th class="th-size-p1"></th>
                        <th class="th-size-1">Dias</th>
                        <th class="th-size-1">Horas (N)</th>
                        <th class="th-size-1">Horas (ST)</th>
                        <th class="th-size-1">Horas (RC)</th>
                        <th class="th-size-1-2">Horas (ST+RC)</th>
                        <th class="th-size-1">Salario</th>
                        <th class="th-size-1">Sobre Tiempo</th>
                        <th class="th-size-1">Recargo</th>
                        <th class="th-size-1">Bono</th>
                        <th class="th-size-1">Transporte</th>
                        <th class="th-size-1">Total Bonos</th>
                        <th class="th-size-1">Subtotal</th>
                        <th class="th-size-1">Vacaciones</th>
                        <th class="th-size-1">Decimo 3ro</th>
                        <th class="th-size-1">Decimo 4to</th>
                        <th class="th-size-1">F. Reserva</th>
                        <th class="th-size-1">Jub. Patronal</th>
                        <th class="th-size-1">Ap. Patronal</th>
                        <th class="th-size-1">Seguros</th>
                        <th class="th-size-1">Uniformes</th>
                        <th class="th-size-1">Total Ingresos</th>
                    </tr>
                    <tr>
                        <td>
                            <form action="cliente">
                                <button class="searchButton" name="id" value="<%=rol.getId()%>">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                            </form>
                        </td>
                        <td><%=rol.getDias()%></td>
                        <td><%=rol.getHorasNormales()%></td>
                        <td><%=rol.getHorasSobreTiempo()%></td>
                        <td><%=rol.getHorasSuplementarias()%></td>
                        <td><%=rol.getTotalHorasExtras()%></td>
                        <td><small>$</small><%=rol.getSalario()%></td>
                        <td><small>$</small><%=rol.getMontoHorasSobreTiempo()%></td>
                        <td><small>$</small><%=rol.getMontoHorasSuplementarias()%></td>
                        <td><small>$</small><%=rol.getBono()%></td>
                        <td><small>$</small><%=rol.getTransporte()%></td>
                        <td><small>$</small><%=rol.getTotalBonos()%></td>
                        <td><small>$</small><%=rol.getSubtotal()%></td>
                        <td><small>$</small><%=rol.getVacaciones()%></td>
                        <td><small>$</small><%=rol.getDecimoTercero()%></td>
                        <td><small>$</small><%=rol.getDecimoCuarto()%></td>
                        <td><small>$</small><%=rol.getDecimoTercero()%></td>
                        <td><small>$</small><%=rol.getJubilacionPatronal()%></td>
                        <td><small>$</small><%=rol.getAportePatronal()%></td>
                        <td><small>$</small><%=rol.getSeguros()%></td>
                        <td><small>$</small><%=rol.getUniformes()%></td>
                        <td><small>$</small><%=rol.getTotalIngreso()%></td>
                    </tr>
                    <tr>
                        <td colspan="22"></td>
                    </tr>
                    <%
                        }
                        if (rolIndividual != null) {
                    %>
                    <tr class="table-info">
                        <th colspan="22" style="background: white">Totales (Rol Empleado)</th>
                    </tr>
                    <tr style="background: sandybrown">
                        <th class="th-size-p1"></th>
                        <th class="th-size-1">Dias</th>
                        <th class="th-size-1">Horas (N)</th>
                        <th class="th-size-1">Horas (ST)</th>
                        <th class="th-size-1">Horas (RC)</th>
                        <th class="th-size-1-2">Horas (ST+RC)</th>
                        <th class="th-size-1">Salario</th>
                        <th class="th-size-1">Sobre Tiempo</th>
                        <th class="th-size-1">Recargo</th>
                        <th class="th-size-1">Bono</th>
                        <th class="th-size-1">Transporte</th>
                        <th class="th-size-1">Total Bonos</th>
                        <th class="th-size-1">Subtotal</th>
                        <th class="th-size-1">Vacaciones</th>
                        <th class="th-size-1">Decimo 3ro</th>
                        <th class="th-size-1">Decimo 4to</th>
                        <th class="th-size-1">F. Reserva</th>
                        <th class="th-size-1">Jub. Patronal</th>
                        <th class="th-size-1">Ap. Patronal</th>
                        <th class="th-size-1">Seguros</th>
                        <th class="th-size-1">Uniformes</th>
                        <th class="th-size-1">Total Ingresos</th>
                    </tr>
                    <tr>
                        <td>
                            <form>
                                <button class="searchButton" name="id" value="<%=rolIndividual.getId()%>">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                            </form>
                        </td>
                        <td><%=rolIndividual.getDias()%></td>
                        <td><%=rolIndividual.getHorasNormales()%></td>
                        <td><%=rolIndividual.getHorasSobreTiempo()%></td>
                        <td><%=rolIndividual.getHorasSuplementarias()%></td>
                        <td><%=rolIndividual.getTotalHorasExtras()%></td>
                        <td><small>$</small><%=rolIndividual.getSalario()%></td>
                        <td><small>$</small><%=rolIndividual.getMontoHorasSobreTiempo()%></td>
                        <td><small>$</small><%=rolIndividual.getMontoHorasSuplementarias()%></td>
                        <td><small>$</small><%=rolIndividual.getBono()%></td>
                        <td><small>$</small><%=rolIndividual.getTransporte()%></td>
                        <td><small>$</small><%=rolIndividual.getTotalBonos()%></td>
                        <td><small>$</small><%=rolIndividual.getSubtotal()%></td>
                        <td><small>$</small><%=rolIndividual.getVacaciones()%></td>
                        <td><small>$</small><%=rolIndividual.getDecimoTercero()%></td>
                        <td><small>$</small><%=rolIndividual.getDecimoCuarto()%></td>
                        <td><small>$</small><%=rolIndividual.getDecimoTercero()%></td>
                        <td><small>$</small><%=rolIndividual.getJubilacionPatronal()%></td>
                        <td><small>$</small><%=rolIndividual.getAportePatronal()%></td>
                        <td><small>$</small><%=rolIndividual.getSeguros()%></td>
                        <td><small>$</small><%=rolIndividual.getUniformes()%></td>
                        <td><strong><small><strong>$</strong></small><%=rolIndividual.getTotalIngreso()%></strong></td>
                    </tr>
                    <%
                        }
                    %>
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
