<%@ page import="models.RolCliente" %>
<%@ page import="utilidad.Const" %>
<%@ page import="utilidad.Fecha" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    RolCliente rolCliente = (RolCliente) request.getAttribute(Const.ROL_CLIENTE);
%>
<html>
    <head>
        <title>Rol Cliente</title>
        <link rel="icon" href="../images/security_icon.png" />
        <link rel="stylesheet" href="../css/table-style.css">
        <link rel="stylesheet" href="../css/general-style.css">
        <link rel="stylesheet" href="../css/header-style.css">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <%@include file="../WEB-INF/partials-static/meta-bootstrap.html"%>
    </head>
    <body>
        <%@include file="../WEB-INF/partials-dynamic/header_principal.jsp" %>

        <div class="container">

            <section>
                <h2 class="content-title">Rol Cliente</h2>
            </section>

            <div class="content-table">

                <table class="table table-striped table-bordered table-hover" style="max-width: 800px; position: relative;left:0;right:0;margin: auto;">
                    <thead>
                    <tr class="table-info">
                        <th class="th-size-1" style="background: lightsteelblue">Empleado</th>
                        <th class="th-size-1" style="background: lightsteelblue">Cedula</th>
                        <th class="th-size-1" style="background: lightsteelblue">Cargo</th>
                    </tr>
                    </thead>
                    <tr>
                        <td style="background: white"><%=rolCliente.getUsuario().getNombre()+" "+rolCliente.getUsuario().getApellido()%></td>
                        <td style="background: white"><%=rolCliente.getCedula()%></td>
                        <td style="background: white"><%=rolCliente.getUsuario().getDetallesEmpleado().getCargo().getNombre()%></td>
                    </tr>
                    <tr>
                        <td colspan="3" style="background: white">Empresa: <%=rolCliente.getEmpresa()%></td>
                    </tr>
                </table>
                <br>
                <table class="table table-striped table-bordered table-hover" style="max-width: 800px; position: relative;left:0;right:0;margin: auto;">
                    <thead>
                    <tr class="table-info">
                        <th class="th-size-1" style="background: lightsteelblue">Cliente</th>
                        <th class="th-size-1" style="background: lightsteelblue">Ruc</th>
                    </tr>
                    </thead>
                    <tr>
                        <td style="background: white"><%=rolCliente.getClienteNombre()%></td>
                        <td style="background: white"><%=rolCliente.getCliente().getRuc()%></td>
                    </tr>
                </table>
                <br>
                <br>
                <table class="table table-striped table-bordered table-hover" style="max-width: 1000px; position: relative;left:0;right:0;margin: auto;">
                    <thead>
                        <tr class="table-info">
                            <th style="background: white"><small>Fecha: del <%=Fecha.toTextRangeNormalized(rolCliente.getInicio())%></small></th>
                        </tr>
                    </thead>
                </table>
                <table class="table table-striped table-bordered table-hover" style="max-width: 1000px; position: relative;left:0;right:0;margin: auto;">
                    <thead>
                        <tr class="table-info">
                            <th class="th-size-1">Dias</th>
                            <th class="th-size-1">Horas (N)</th>
                            <th class="th-size-1">Horas (ST)</th>
                            <th class="th-size-1">Horas (RC)</th>
                            <th class="th-size-1-2">Horas (ST+RC)</th>
                        </tr>
                    </thead>
                    <tr>
                        <td><%=rolCliente.getDias()%></td>
                        <td><%=rolCliente.getHorasNormales()%></td>
                        <td><%=rolCliente.getHorasSobreTiempo()%></td>
                        <td><%=rolCliente.getHorasSuplementarias()%></td>
                        <td><%=rolCliente.getTotalHorasExtras()%></td>
                    </tr>
                </table>
                <table class="table table-striped table-bordered table-hover" style="max-width: 1000px; position: relative;left:0;right:0;margin: auto;">
                    <thead>
                        <tr class="table-info">
                            <th class="th-size-0">Salario</th>
                            <th class="th-size-0">Sobre Tiempo</th>
                            <th class="th-size-0">Recargo</th>
                            <th class="th-size-0">Bono</th>
                            <th class="th-size-0">Transporte</th>
                            <th class="th-size-0">Total Bonos</th>
                            <th class="th-size-0">Subtotal</th>
                            <th class="th-size-0">Vacaciones</th>
                        </tr>
                    </thead>
                    <tr>
                        <td>$<%=rolCliente.getSalario()%></td>
                        <td>$<%=rolCliente.getMontoHorasSobreTiempo()%></td>
                        <td>$<%=rolCliente.getMontoHorasSuplementarias()%></td>
                        <td>$<%=rolCliente.getBono()%></td>
                        <td>$<%=rolCliente.getTransporte()%></td>
                        <td>$<%=rolCliente.getTotalBonos()%></td>
                        <td>$<%=rolCliente.getSubtotal()%></td>
                        <td>$<%=rolCliente.getVacaciones()%></td>
                    </tr>
                </table>
                <table class="table table-striped table-bordered table-hover" style="max-width: 1000px; position: relative;left:0;right:0;margin: auto;">
                    <thead>
                        <tr class="table-info">
                            <th class="th-size-0">Decimo 3ro</th>
                            <th class="th-size-0">Decimo 4to</th>
                            <th class="th-size-0">F. Reserva</th>
                            <th class="th-size-0">Jub. Patronal</th>
                            <th class="th-size-0">Ap. Patronal</th>
                            <th class="th-size-0">Seguros</th>
                            <th class="th-size-0">Uniformes</th>
                            <th class="th-size-0" style="background: sandybrown">Total Ingresos</th>
                        </tr>
                    </thead>
                    <tr>
                        <td>$<%=rolCliente.getDecimoTercero()%></td>
                        <td>$<%=rolCliente.getDecimoCuarto()%></td>
                        <td>$<%=rolCliente.getDecimoTercero()%></td>
                        <td>$<%=rolCliente.getJubilacionPatronal()%></td>
                        <td>$<%=rolCliente.getAportePatronal()%></td>
                        <td>$<%=rolCliente.getSeguros()%></td>
                        <td>$<%=rolCliente.getUniformes()%></td>
                        <td><strong>$<%=rolCliente.getTotalIngreso()%></strong></td>
                    </tr>
                </table>

            </div>
        </div>

    </body>
</html>
