<%@ page import="models.RolIndividual" %>
<%@ page import="utilidad.Const" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<RolIndividual> roles = (List<RolIndividual>) request.getAttribute(Const.ROLES_INDIVIDUAL);
%>
<html>
    <head>
        <title>Roles Individual</title>
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
                <h2 class="content-title">Roles Individual</h2>
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
                            <th class="th-size-p1"></th>
                            <th class="th-size-0">Cedula</th>
                            <th class="th-size-4">Empleado</th>
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
                    </thead>
                    <tbody>
                        <%
                            for (RolIndividual rolIndividual :
                                    roles) {
                        %>
                        <tr>
                            <td>
                                <form>
                                    <button class="searchButton" name="id" value="<%=rolIndividual.getUsuarioId()%>">
                                        <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </form>
                            </td>
                            <td><%=rolIndividual.getCedula()%></td>
                            <td><%=rolIndividual.getEmpleado()%></td>
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
                            <td><small>$</small><%=rolIndividual.getTotalIngreso()%></td>
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
