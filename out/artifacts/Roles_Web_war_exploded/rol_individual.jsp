<%@ page import="models.RolIndividual" %>
<%@ page import="utilidad.Fecha" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    RolIndividual rolIndividual = (RolIndividual) request.getAttribute("rol");
%>
<html>
<head>
    <title>Rol Individual</title>
    <link rel="icon" href="imagenes/security_icon.png" />
    <link rel="stylesheet" href="css/estilo-table.css">
    <link rel="stylesheet" href="css/estilo.css">
    <%@include file="WEB-INF/partials-static/meta-bootstrap.html"%>
</head>
<body>
<%@include file="WEB-INF/partials-static/header_principal.html" %>

<div class="container">

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
                <td style="background: white"><%=rolIndividual.getUsuario().getApellido()+" "+rolIndividual.getUsuario().getNombre()%></td>
                <td style="background: white"><%=rolIndividual.getCedula()%></td>
                <td style="background: white"><%=rolIndividual.getUsuario().getDetallesEmpleado().getCargo().getNombre()%></td>
            </tr>
            <tr>
                <td colspan="3" style="background: white">Empresa: <%=rolIndividual.getUsuario()
                        .getDetallesEmpleado().getEmpresa().getNombre()%></td>
            </tr>
        </table>
        <br>
        <br>
        <table class="table table-striped table-bordered table-hover" style="max-width: 1000px; position: relative;left:0;right:0;margin: auto;">
            <thead>
            <tr class="table-info">
                <th style="background: white"><small>Fecha: del <%=Fecha.toTextRangeNormalized(rolIndividual.getInicio())%></small></th>
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
                <td><%=rolIndividual.getDias()%></td>
                <td><%=rolIndividual.getHorasNormales()%></td>
                <td><%=rolIndividual.getHorasSobreTiempo()%></td>
                <td><%=rolIndividual.getHorasSuplementarias()%></td>
                <td><%=rolIndividual.getTotalHorasExtras()%></td>
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
                <td>$<%=rolIndividual.getSalario()%></td>
                <td>$<%=rolIndividual.getMontoHorasSobreTiempo()%></td>
                <td>$<%=rolIndividual.getMontoHorasSuplementarias()%></td>
                <td>$<%=rolIndividual.getBono()%></td>
                <td>$<%=rolIndividual.getTransporte()%></td>
                <td>$<%=rolIndividual.getTotalBonos()%></td>
                <td>$<%=rolIndividual.getSubtotal()%></td>
                <td>$<%=rolIndividual.getVacaciones()%></td>
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
                <td>$<%=rolIndividual.getDecimoTercero()%></td>
                <td>$<%=rolIndividual.getDecimoCuarto()%></td>
                <td>$<%=rolIndividual.getDecimoTercero()%></td>
                <td>$<%=rolIndividual.getJubilacionPatronal()%></td>
                <td>$<%=rolIndividual.getAportePatronal()%></td>
                <td>$<%=rolIndividual.getSeguros()%></td>
                <td>$<%=rolIndividual.getUniformes()%></td>
                <td><strong>$<%=rolIndividual.getTotalIngreso()%></strong></td>
            </tr>
        </table>

    </div>
</div>
</body>
</html>
