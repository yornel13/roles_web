<%@ page import="models.RolCliente" %>
<%@ page import="java.util.List" %>
<%@ page import="models.RolIndividual" %>
<%@ page import="utilidad.Const" %>
<%@ page import="models.PagoMes" %>
<%@ page import="models.PagoMesItem" %>
<%@ page import="utilidad.Numeros" %>
<%@ page import="utilidad.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<RolCliente> roles = (List<RolCliente>) request.getAttribute(Const.ROLES_CLIENTE);
    RolIndividual rolIndividual = (RolIndividual) request.getAttribute(Const.ROL_INDIVIDUAL);
    PagoMes pagoMes = (PagoMes) request.getAttribute(Const.PAGO_MES);
    String vacaciones = (String) request.getAttribute(Const.VACATION_DATE);

    request.getSession().setAttribute(Const.PRINT_RL, roles);
    request.getSession().setAttribute(Const.PRINT_RI, rolIndividual);
%>
<html>
    <head>
        <title>Roles Empleado</title>
        <link rel="icon" href="../images/security_icon.png" />
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
                <button onclick="printPage()" class="print">
                    <img src="../images/bt_reportes.png">
                </button>
                <script>
                    function printPage() {
                        window.print();
                    }
                </script>
            </div>

            <section>
                <h2 class="content-title">Roles Empleado</h2>
            </section>

            <div class="container-filter">

                <%@include file="../WEB-INF/partials-dynamic/date_search.jsp" %>

            </div>
            <%
                if (roles != null && !roles.isEmpty()) {
            %>
            <%
                if (/*SessionUtility.getUser(request, response).getType() != UserType.EMPLEADO*/false) {
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
                        <th class="th-size-0"></th>
                        <th class="th-size-1">Dias</th>
                        <th class="th-size-3">Horas(N)</th>
                        <th class="th-size-3">Horas(ST)</th>
                        <th class="th-size-3">Horas(RC)</th>
                        <th class="th-size-4">Horas(ST+RC)</th>
                        <th class="th-size-2">Salario</th>
                        <th class="th-size-3">Sobre Tiempo</th>
                        <th class="th-size-2">Recargo</th>
                        <th class="th-size-2">Bono</th>
                        <th class="th-size-2">Transporte</th>
                        <th class="th-size-2">Total Bonos</th>
                        <th class="th-size-2">Subtotal</th>
                        <th class="th-size-2">Vacaciones</th>
                        <th class="th-size-2">Decimo 3ro</th>
                        <th class="th-size-2">Decimo 4to</th>
                        <th class="th-size-2">F. Reserva</th>
                        <th class="th-size-2">Jub. Patronal</th>
                        <th class="th-size-2">Ap. Patronal</th>
                        <th class="th-size-2">Seguros</th>
                        <th class="th-size-2">Uniformes</th>
                        <th class="th-size-2">Total Ingresos</th>
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
                        <th class="th-size-0"></th>
                        <th class="th-size-1">Dias</th>
                        <th class="th-size-3">Horas(N)</th>
                        <th class="th-size-3">Horas(ST)</th>
                        <th class="th-size-3">Horas(RC)</th>
                        <th class="th-size-3">Horas(ST+RC)</th>
                        <th class="th-size-2">Salario</th>
                        <th class="th-size-4">Sobre Tiempo</th>
                        <th class="th-size-2">Recargo</th>
                        <th class="th-size-2">Bono</th>
                        <th class="th-size-2">Transporte</th>
                        <th class="th-size-4">Total Bonos</th>
                        <th class="th-size-2">Subtotal</th>
                        <th class="th-size-3">Vacaciones</th>
                        <th class="th-size-4">Decimo 3ro</th>
                        <th class="th-size-4">Decimo 4to</th>
                        <th class="th-size-3">F. Reserva</th>
                        <th class="th-size-4">Jub. Patronal</th>
                        <th class="th-size-4">Ap. Patronal</th>
                        <th class="th-size-2">Seguros</th>
                        <th class="th-size-2">Uniformes</th>
                        <th class="th-size-4">Total Ingresos</th>
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
                }
            %>


            <!-- Empleado Table --------------------------------------------------------------------------------------->
            <div class="content-table">

                <%
                    Double decimo3 = null;
                    Double decimo4 = null;
                    Double ingreso = 0d;
                    Double iess = 0d;
                    Double quincena = 0d;
                    Double deudas = 0d;
                    Double deducciones = 0d;
                    Double recibido = 0d;
                    String iessTitle = "IESS";
                    for (PagoMesItem item: pagoMes.getPagosItems()) {
                        if (item.getIngreso() != null) {
                            ingreso += item.getIngreso();
                            if (item.getClave().equals("decimo_tercero")) {
                                decimo3 = item.getIngreso();
                            }
                            if (item.getClave().equals("decimo_cuarto")) {
                                decimo4 = item.getIngreso();
                            }
                        }
                        if (item.getDeduccion() != null) {
                            if (item.getClave().equals("iess")) {
                                iess += item.getDeduccion();
                                iessTitle = item.getDescripcion();
                            }
                            if (item.getClave().equals("adelanto_quincenal"))
                                quincena += item.getDeduccion();
                            if (item.getClave().equals("deuda"))
                                deudas += item.getDeduccion();
                            deducciones += item.getDeduccion();
                        }
                    }
                    recibido = ingreso - deducciones;

                    ingreso = Numeros.round(ingreso);
                    iess = Numeros.round(iess);
                    quincena = Numeros.round(quincena);
                    deudas = Numeros.round(deudas);
                    deducciones = Numeros.round(deducciones);
                    recibido = Numeros.round(recibido);
                %>

                <table class="table table-striped table-bordered table-hover" style="max-width: 800px; position: relative;left:0;right:0;margin: auto;">
                    <%
                        for (RolCliente rol :
                                roles) {
                    %>
                    <tr class="table-info">
                        <th colspan="22" style="background: white">Cliente: <%=rol.getClienteNombre()%></th>
                    </tr>
                    <tr class="table-info">
                        <%
                            if (SessionUtility.getUser(request, response).getType() != UserType.EMPLEADO) {
                        %>
                        <th class="th-size-0"></th>
                        <%
                            }
                        %>
                        <th class="th-size-1">Dias</th>
                        <th class="th-size-3">Horas(N)</th>
                        <th class="th-size-3">Horas(ST)</th>
                        <th class="th-size-3">Horas(RC)</th>
                        <th class="th-size-2">Salario</th>
                        <th class="th-size-4">Sobre Tiempo</th>
                        <th class="th-size-2">Recargo</th>
                        <th class="th-size-2">Bonos</th>
                        <th class="th-size-2">Subtotal</th>
                        <%
                            if (decimo3 != null) {
                        %>
                            <th class="th-size-4">Decimo 3ro</th>
                        <%
                            }
                        %>
                        <%
                            if (decimo3 != null) {
                        %>
                        <th class="th-size-4">Decimo 4to</th>
                        <%
                            }
                        %>
                    </tr>
                    <tr>
                        <%
                            if (SessionUtility.getUser(request, response).getType() != UserType.EMPLEADO) {
                        %>
                        <td>
                            <form action="cliente">
                                <button class="searchButton" name="id" value="<%=rol.getId()%>">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                            </form>
                        </td>
                        <%
                            }
                        %>
                        <td><%=rol.getDias()%></td>
                        <td><%=rol.getHorasNormales()%></td>
                        <td><%=rol.getHorasSobreTiempo()%></td>
                        <td><%=rol.getHorasSuplementarias()%></td>
                        <td><small>$</small><%=rol.getSalario()%></td>
                        <td><small>$</small><%=rol.getMontoHorasSobreTiempo()%></td>
                        <td><small>$</small><%=rol.getMontoHorasSuplementarias()%></td>
                        <td><small>$</small><%=rol.getTotalBonos()%></td>
                        <td><small>$</small><%=rol.getSubtotal()%></td>
                        <%
                            if (decimo3 != null) {
                        %>
                        <td><small>$</small><%=rol.getDecimoTercero()%></td>
                        <%
                            }
                        %>
                        <%
                            if (decimo4 != null) {
                        %>
                        <td><small>$</small><%=rol.getDecimoCuarto()%></td>
                        <%
                            }
                        %>
                    </tr>
                    <%
                        if (roles.indexOf(rol) != roles.size()-1) {
                    %>
                    <tr>
                        <td colspan="22"></td>
                    </tr>
                    <%
                        }
                    %>
                    <%
                        }
                        if (rolIndividual != null) {
                    %>
                </table>
                <br>
                <br>

                <!-- Vacaciones Table --------------------------------------------------------------------------------------->
                <%
                    if(vacaciones != null){
                %>
                <table class="table table-striped table-bordered table-hover" style="max-width: 800px; position: relative;left:0;right:0;margin: auto;">

                    <tr class="table-info" >
                        <th class="th-size-1" style="background: #fdff80">Vacaciones</th>
                    </tr>
                    <tbody>
                    <tr>
                        <td style="background: white"><%=vacaciones%></td>
                    </tr>
                    </tbody>
                </table>
                <%
                      }
                %>
                <br>
                <form action="/rol/cliente" method="post" STYLE="text-align: center">
                    <button style="background-color: #77c9ee; color:white; padding: 5px; border-radius: 5px; border: none; overflow: hidden; " name="schedule">
                        <img src="../images/schedule.png">
                        Ver Horario
                    </button>

                </form>

                <br>
                <br>
                <table class="table table-striped table-bordered table-hover" style="max-width: 900px; position: relative;left:0;right:0;margin: auto;">
                    <tr class="table-info">
                        <th colspan="22" style="background: white">Rol de Pago Mensual</th>
                    </tr>
                    <tr style="background: sandybrown">
                        <th class="th-size-1">Dias</th>
                        <th class="th-size-1">Horas(N)</th>
                        <th class="th-size-1">Horas(ST)</th>
                        <th class="th-size-1">Horas(RC)</th>
                        <th class="th-size-2">Salario</th>
                        <th class="th-size-4">Horas Extras</th>
                        <th class="th-size-1">Bonos</th>
                        <th class="th-size-1">Subtotal</th>
                        <%
                            if (decimo3 != null) {
                        %>
                        <th class="th-size-3">Decimo 3ro</th>
                        <%
                            }
                        %>
                        <%
                            if (decimo4 != null) {
                        %>
                        <th class="th-size-3">Decimo 4to</th>
                        <%
                            }
                        %>
                    </tr>
                    <tr>
                        <td><%=rolIndividual.getDias()%></td>
                        <td><%=rolIndividual.getHorasNormales()%></td>
                        <td><%=rolIndividual.getHorasSobreTiempo()%></td>
                        <td><%=rolIndividual.getHorasSuplementarias()%></td>
                        <td><small>$</small><%=rolIndividual.getSalario()%></td>
                        <td><small>$</small><%=rolIndividual.getMontoHorasExtras()%></td>
                        <td><small>$</small><%=rolIndividual.getTotalBonos()%></td>
                        <td><small>$</small><%=rolIndividual.getSubtotal()%></td>
                        <%
                            if (decimo3 != null) {
                        %>
                        <td><small>$</small><%=decimo3%></td>
                        <%
                            }
                        %>
                        <%
                            if (decimo4 != null) {
                        %>
                        <td><small>$</small><%=decimo4%></td>
                        <%
                            }
                        %>
                    </tr>
                </table>




                <table class="table table-striped table-bordered table-hover" style="max-width: 750px; position: relative;left:0;right:0;margin: auto;">
                    <tr style="background: sandybrown">
                        <th style="background: yellowgreen" class="th-size-4">Total Ingresos</th>
                        <th class="th-size-4"><%=iessTitle.toUpperCase()%></th>
                        <th class="th-size-1">Quinena</th>
                        <th class="th-size-1">Deudas</th>
                        <th style="background: indianred" class="th-size-4">Total Deducciones</th>
                        <th style="background: lightgreen" class="th-size-4">Total Recibido</th>
                    </tr>
                    <tr>
                        <td><small>$</small><%=ingreso%></td>
                        <td>-<small>$</small><%=iess%></td>
                        <td>-<small>$</small><%=quincena%></td>
                        <td>-<small>$</small><%=deudas%></td>
                        <td>-<small>$</small><%=deducciones%></td>
                        <td><strong><small><strong>$</strong></small><%=recibido%></strong></td>
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
