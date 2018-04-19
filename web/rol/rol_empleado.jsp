<%@ page import="utilidad.*" %>
<%@ page import="java.util.List" %>
<%@ page import="models.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<RolCliente> roles = (List<RolCliente>) request.getAttribute(Const.ROLES_CLIENTE);
    RolIndividual rolIndividual = (RolIndividual) request.getAttribute(Const.ROL_INDIVIDUAL);
    PagoMes pagoMes = (PagoMes) request.getAttribute(Const.PAGO_MES);
%>
<html>
    <head>
        <title>Rol Cliente</title>
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
            </div>

            <section>
                <h2 class="content-title">Rol Empleado</h2>
            </section>

            <div class="content-table">
                <table class="table table-striped table-bordered table-hover" style="max-width: 1000px; position: relative;left:0;right:0;margin: auto;">
                    <thead>
                        <tr class="table-info">
                            <th style="background: white"><small>Fecha: del <%=Fecha.toTextRangeNormalized(rolIndividual.getInicio())%></small></th>
                        </tr>
                    </thead>
                </table>
                <br>
                <br>
                <table class="table table-striped table-bordered table-hover" style="max-width: 800px; position: relative;left:0;right:0;margin: auto;">
                    <thead>
                        <%@include file="../WEB-INF/partials-static/header_info_empleado_detail.html" %>
                    </thead>
                    <tbody>
                        <tr>
                            <td style="background: white"><%=rolIndividual.getUsuario().getNombre()+" "+rolIndividual.getUsuario().getApellido()%></td>
                            <td style="background: white"><%=rolIndividual.getCedula()%></td>
                            <td style="background: white"><%=rolIndividual.getUsuario().getDetallesEmpleado().getCargo().getNombre()%></td>
                        </tr>
                        <tr>
                            <td colspan="3" style="background: white">Empresa: <%=rolIndividual.getUsuario().getDetallesEmpleado().getEmpresa().getNombre()%></td>
                        </tr>
                    </tbody>
                </table>
                <br>
            </div>
            <!-- Empleado Table --------------------------------------------------------------------------------------->
            <%
                Double decimo3 = null;
                Double decimo4 = null;
                Double ingreso = 0d;
                Double iess = 0d;
                Double quincena = 0d;
                Double deudas = 0d;
                Double deducciones = 0d;
                Double recibido = 0d;
                Double totalFinalRetenido = 0d;
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
            <div class="table-responsive" style="padding-top: 30px">
                <%
                    for (RolCliente rol : roles) {
                        Double totalRecibido = 0d;
                        Double totalRetenido = 0d;
                        String clienteName = "Otro cliente";
                        Boolean showClienteName = false;
                        totalRecibido += rol.getSalario()
                                + rol.getMontoHorasSobreTiempo()
                                + rol.getMontoHorasSuplementarias()
                                + rol.getBono()
                                + rol.getTransporte();
                        totalRetenido += (rol.getVacaciones()
                                + rol.getDecimoTercero()
                                + rol.getAportePatronal()
                                + rol.getJubilacionPatronal()
                                + rol.getSeguros()
                                + rol.getUniformes());
                        if (SessionUtility.getUser(request, response).getType() != UserType.CLIENTE
                                || rol.getClienteId() == ((Cliente) request.getSession()
                                .getAttribute(Const.DATA_USER)).getId()) {
                            clienteName = "Cliente: "+rol.getClienteNombre();
                            showClienteName = true;
                        }
                %>
                <table class="table table-striped table-bordered table-hover">
                    <tr class="table-info">
                        <th colspan="21" style="background: white"><%=clienteName%></th>
                    </tr>
                    <tr class="table-info">
                        <%
                            if (showClienteName) {
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
                                totalRecibido += rol.getDecimoTercero();
                        %>
                        <th class="th-size-4">Decimo 3ro</th>
                        <%
                            } else {
                                totalRetenido += rol.getDecimoTercero();
                        %>
                        <th style="background: rosybrown"  class="th-size-4">Decimo 3ro</th>
                        <%
                            }
                            if (decimo4 != null) {
                                totalRecibido += rol.getDecimoCuarto();
                        %>
                        <th class="th-size-4">Decimo 4to</th>
                        <%
                            } else {
                                totalRetenido += rol.getDecimoCuarto();
                        %>
                        <th style="background: rosybrown"  class="th-size-4">Decimo 4to</th>
                        <%
                            }
                        %>
                        <th style="background: rosybrown" class="th-size-1">Vacaciones</th>
                        <th style="background: rosybrown"  class="th-size-4">F. Reserva</th>
                        <th style="background: rosybrown"  class="th-size-4">Jub. Patronal</th>
                        <th style="background: rosybrown"  class="th-size-4">Ap. Patronal</th>
                        <th style="background: rosybrown"  class="th-size-1">Seguros</th>
                        <th style="background: rosybrown"  class="th-size-1">Uniformes</th>
                        <th style="background: lightgreen"  class="th-size-4">Total Recibido</th>
                        <th style="background: rosybrown"  class="th-size-5">Total Retencion</th>
                        <th style="background: sandybrown"  class="th-size-4">Total Ingresos</th>
                    </tr>
                    <tr>
                        <%
                            if (showClienteName) {
                        %>
                        <td>
                            <form>
                                <button class="searchButton" name="rol_cliente_id" value="<%=rol.getId()%>">
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
                       <td><small>$</small><%=rol.getDecimoTercero()%></td>
                       <td><small>$</small><%=rol.getDecimoCuarto()%></td>
                       <td><small>$</small><%=rol.getVacaciones()%></td>
                       <td><small>$</small><%=rol.getDecimoTercero()%></td>
                       <td><small>$</small><%=rol.getJubilacionPatronal()%></td>
                       <td><small>$</small><%=rol.getAportePatronal()%></td>
                       <td><small>$</small><%=rol.getSeguros()%></td>
                       <td><small>$</small><%=rol.getUniformes()%></td>
                        <td><small>$</small><%=Numeros.round(totalRecibido)%></td>
                        <td><small>$</small><%=Numeros.round(totalRetenido)%></td>
                       <td><small>$</small><%=rol.getTotalIngreso()%></td>
                    </tr>
                </table>
                <%
                        totalFinalRetenido += totalRetenido;
                    }
                    if (rolIndividual != null) {
                %>
            </div>
            <br>
            <div class="content-table">
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
                        <%
                            if (SessionUtility.getUser(request, response).getType() != UserType.EMPLEADO) {
                        %>
                        <%
                            }
                        %>
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
                        <th style="background: lightgreen" class="th-size-4">Total Recibido</th>
                        <th style="background: indianred" class="th-size-4">Total Agente de Retencion</th>
                    </tr>
                    <tr>
                        <td><small>$</small><%=ingreso%></td>
                        <td><small>$</small><%=Numeros.round(totalFinalRetenido)%></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
            </div>
        </div>
    </body>
</html>
