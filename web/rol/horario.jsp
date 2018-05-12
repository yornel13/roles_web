<%@ page import="utilidad.Const" %>
<%@ page import="models.RolCliente" %>
<%@ page import="models.RolIndividual" %>
<%@ page import="java.util.List" %>
<%@ page import="models.ControlExtras" %>
<%--
  Created by IntelliJ IDEA.
  User: Joshuan Marval
  Date: 10/05/2018
  Time: 12:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    RolIndividual rolIndividual = (RolIndividual) request.getAttribute(Const.ROL_INDIVIDUAL);
    List<RolCliente> roles = (List) request.getAttribute(Const.ROL_CLIENTE);
    List <ControlExtras> controlExtrasList = (List<ControlExtras>) request.getAttribute(Const.CONTROL_EXTRA);

    String nombreCliente="";
    for(RolCliente rol : roles){
        nombreCliente = rol.getClienteNombre();
    }



%>
<html>
<head>
    <title>Horario</title>
    <link rel="icon" href="../images/security_icon.png"/>
    <link rel="stylesheet" href="../css/table-style.css">
    <link rel="stylesheet" href="../css/general-style.css">
    <link rel="stylesheet" href="../css/header-style.css">
    <link rel="stylesheet" href="../css/date-search-style.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%@include file="../WEB-INF/partials-static/meta-bootstrap.html"%>
</head>
<body>

<%@include file="../WEB-INF/partials-dynamic/header_principal.jsp" %>

<div class="container">

    <div class="container-buttons-top">
        <a href="javascript:history.back()" name="return" class="return">
            ❮ Volver
        </a>
        <form action="rol_empleado" method="post" class="container-print">
            <button name="print" class="print" onclick="window.print()">
                <img src="../images/bt_reportes.png">
                <span class="tooltiptext">Imprimir Horario</span>
            </button>
        </form>
    </div>

    <section>
        <h2 class="content-title">Rol Empleado</h2>
    </section>

    <br>
    <br>
    <br>
    <br>

    <table class="table table-striped table-bordered table-hover" style="max-width: 800px; position: relative;left:0;right:0;margin: auto;">
        <thead>
        <tr class="table-info">
            <th class="th-size-1" style="background: lightsteelblue">Empleado</th>
            <th class="th-size-1" style="background: lightsteelblue">Cedula</th>
            <th class="th-size-1" style="background: lightsteelblue">Cargo</th>
        </tr>
        </thead>
        <tr>
            <td style="background: white"><%=rolIndividual.getUsuario().getNombre()+" "+rolIndividual.getUsuario().getApellido()%></td>
            <td style="background: white"><%=rolIndividual.getCedula()%></td>
            <td style="background: white"><%=rolIndividual.getUsuario().getDetallesEmpleado().getCargo().getNombre()%></td>
        </tr>
        <tr>
            <td colspan="3" style="background: white">Empresa: <%=rolIndividual.getUsuario().getDetallesEmpleado().getEmpresa().getNombre()%></td>
        </tr>
    </table>

    <br>
    <br>
    <br>
    <br>

    <!--Horario-->
<table class="table table-striped table-bordered table-hover" style="max-width: 800px; position: relative;left:0;right:0;margin: auto;">
    <thead>
    <tr class="table-info" style="background: lightsteelblue">
        <th class="th-size-1">Fecha</th>
        <th class="th-size-1">Hora Entrada</th>
        <th class="th-size-1">Hora Salida</th>
        <th class="th-size-1">Horas (ST)</th>
        <th class="th-size-1">Horas (RC)</th>
        <th class="th-size-1">Cliente</th>
        <th class="th-size-1">Observacion</th>
    </tr>
    </thead>
    <tbody>
    <%




        String caso="";
        for(ControlExtras controlExtras : controlExtrasList){

            if (controlExtras.getCaso().equals("L")) {
                caso =  "Libre";
            } else if (controlExtras.getCaso().equals("F")) {
                caso = "Falta";
            } else if (controlExtras.getCaso().equals("V")) {
                caso = "Vacaciones";
            } else if (controlExtras.getCaso().equals("P")) {
                caso = "Permiso";
            } else if (controlExtras.getCaso().equals("D")) {
                caso = "D. Medico";
            } else if (controlExtras.getCaso().equals("C")) {
                caso = "C. Medica";
            }
    %>
    <tr>
        <td style="background: white"><%=controlExtras.getFecha()%></td>
        <td style="background: white"><%=controlExtras.getEntrada()%></td>
        <td style="background: white"><%=controlExtras.getSalida()%></td>
        <td style="background: white"><%=controlExtras.getSobreTiempo()%></td>
        <td style="background: white"><%=controlExtras.getRecargo()%></td>
        <td style="background: white"><%=nombreCliente%></td>
        <td style="background: white"><%=caso%></td>
        </tr>
    <%

        }
    %>
    </tbody>
</table>

</div>
</body>
</html>
