<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="models.RolCliente" %>
<%@ page import="utilidad.Const" %>
<%@ page import="java.util.List" %>
<%
    String infoMsg = (String) request.getAttribute("info_msg");
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

            <form action="rol_cliente" method="post" target="_blank" >
                <button name="print">Print report</button>
            </form>

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

        <!--MODAL-->
        <div id="modal_no_date_pdf" class="modal fade"  >
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Eliminar</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>¿Esta seguro que desea eliminar el usuario?.</p>
                    </div>

                    <div class="modal-footer">
                        <form method="post" >
                            <button name=""  class="btn btn-primary" id="delete_modal_button" value="">Aceptar</button>
                        </form>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                    </div>

                </div>
            </div>
        </div>

        <span id="type_info" hidden><%=infoMsg%></span>

    </body>
</html>

