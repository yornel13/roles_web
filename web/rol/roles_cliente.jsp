<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="models.RolCliente" %>
<%@ page import="utilidad.Const" %>
<%@ page import="utilidad.Numeros" %>
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
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <%@include file="../WEB-INF/partials-static/header_table_empleado.html" %>
                    </thead>
                    <tbody>
                        <%
                            for (RolCliente rolCliente : roles) {
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
            <br>
            <h1>Totales</h1>
            <%
                Double diasTextValor = 0d;
                Double normalesTextValor = 0d;
                Double suplementariasTextValor = 0d;
                Double sobreTiempoTextValor = 0d;
                Double extraTextValor = 0d;
                Double sueldoTotalTextValor = 0d;
                Double montoSuplementariasTextValor = 0d;
                Double montoSobreTiempoTextValor = 0d;
                Double montoBonoTextValor = 0d;
                Double montoTransporteTextValor = 0d;
                Double totalBonosTextValor = 0d;
                Double subTotalTextValor = 0d;
                Double vacacionesTextValor = 0d;
                Double decimosTotalTextValor = 0d;
                Double decimoTerceroTotalTextValor = 0d;
                Double decimoCuartoTotalTextValor = 0d;
                Double montoReservaTextValor = 0d;
                Double montoJubilacionTextValor = 0d;
                Double montoAportePatronalTextValor = 0d;
                Double montoSegurosTextValor = 0d;
                Double montoUniformasTextValor = 0d;
                Double montoTotalIngresos = 0d;

                for (RolCliente pago: roles) {

                    diasTextValor += pago.getDias();
                    normalesTextValor += pago.getHorasNormales();
                    suplementariasTextValor += pago.getHorasSuplementarias();
                    sobreTiempoTextValor += pago.getHorasSobreTiempo();
                    sueldoTotalTextValor += pago.getSalario();
                    extraTextValor += (pago.getMontoHorasSuplementarias()
                            + pago.getMontoHorasSobreTiempo());
                    totalBonosTextValor += pago.getTotalBonos();
                    vacacionesTextValor += pago.getVacaciones();
                    subTotalTextValor += pago.getSubtotal();
                    decimosTotalTextValor += (pago.getDecimoCuarto()
                            + pago.getDecimoTercero());
                    decimoTerceroTotalTextValor += pago.getDecimoTercero();
                    decimoCuartoTotalTextValor += pago.getDecimoCuarto();
                    montoReservaTextValor += pago.getDecimoTercero();
                    montoSuplementariasTextValor += pago.getMontoHorasSuplementarias();
                    montoSobreTiempoTextValor += pago.getMontoHorasSobreTiempo();
                    montoBonoTextValor += pago.getBono();
                    montoTransporteTextValor += pago.getTransporte();
                    montoJubilacionTextValor += pago.getJubilacionPatronal();
                    montoAportePatronalTextValor += pago.getAportePatronal();
                    montoSegurosTextValor += pago.getSeguros();
                    montoUniformasTextValor += pago.getUniformes();
                    montoTotalIngresos += pago.getTotalIngreso();
                }
            %>
            <table class="table table-striped table-bordered table-hover" style="max-width: 800px; position: relative;left:0;right:0;margin: auto;">
                <%@include file="../WEB-INF/partials-static/header_info_total_rol_cliente_1.html" %>
                <tr>
                    <td><%=Numeros.round(diasTextValor)%></td>
                    <td><%=Numeros.round(normalesTextValor)%></td>
                    <td><%=Numeros.round(suplementariasTextValor)%></td>
                    <td><%=Numeros.round(sobreTiempoTextValor)%></td>
                    <td><%=Numeros.round(extraTextValor)%></td>
                    <td><small>$</small><%=Numeros.round(sueldoTotalTextValor)%></td>
                    <td><small>$</small><%=Numeros.round(montoSuplementariasTextValor)%></td>
                    <td><small>$</small><%=Numeros.round(montoSobreTiempoTextValor)%></td>
                    <td><small>$</small><%=Numeros.round(montoBonoTextValor)%></td>
                    <td><small>$</small><%=Numeros.round(montoTransporteTextValor)%></td>
                    <td><small>$</small><%=Numeros.round(totalBonosTextValor)%></td>
                    <td><small>$</small><%=Numeros.round(subTotalTextValor)%></td>
                </tr>
            </table>
            <br>
            <table class="table table-striped table-bordered table-hover" style="max-width: 800px; position: relative;left:0;right:0;margin: auto">
                <%@include file="../WEB-INF/partials-static/header_info_total_rol_cliente_2.html" %>
                <tr>
                    <td><small>$</small><%=Numeros.round(vacacionesTextValor)%></td>
                    <td><small>$</small><%=Numeros.round(decimoTerceroTotalTextValor)%></td>
                    <td><small>$</small><%=Numeros.round(decimoCuartoTotalTextValor)%></td>
                    <td><small>$</small><%=Numeros.round(montoReservaTextValor)%></td>
                    <td><small>$</small><%=Numeros.round(montoJubilacionTextValor)%></td>
                    <td><small>$</small><%=Numeros.round(montoAportePatronalTextValor)%></td>
                    <td><small>$</small><%=Numeros.round(montoSegurosTextValor)%></td>
                    <td><small>$</small><%=Numeros.round(montoUniformasTextValor)%></td>
                    <td><small>$</small><%=Numeros.round(montoTotalIngresos)%></td>
                </tr>
            </table>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
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

