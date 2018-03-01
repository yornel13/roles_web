<%@ page import="java.util.List" %>
<%@ page import="models.RolCliente" %>

<%
    List<RolCliente> listaRoles = (List<RolCliente>) request.getAttribute("dameLista");
%>
<%
    String a = request.getParameter("next");
    if(a != null){
        System.out.println("click next");
    }
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Rol Cliente</title>
    <link rel="stylesheet" href="css/estilo.css">
    <%@include file="WEB-INF/partials-static/meta-bootstrap.html"%>
</head>
<body>
    <%@include file="WEB-INF/partials-static/header_principal.html" %>

    <div class="container">

        <div class="container-range">
            <a href="#" class="previous">&#8249;</a>
            <strong>Enero 2017</strong>
            <a href="#" type="submit" class="next" name="next">&#8250;</a>
        </div>

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
                                listaRoles) {
                    %>
                    <tr>
                        <td><%=rolCliente.getCedula()%></td>
                        <td><%=rolCliente.getEmpleado()%></td>
                        <td><%=rolCliente.getEmpresa()%></td>
                        <td><%=rolCliente.getDias()%></td>
                        <td><%=rolCliente.getHorasNormales()%></td>
                        <td><%=rolCliente.getHorasSobreTiempo()%></td>
                        <td><%=rolCliente.getHorasSuplementarias()%></td>
                        <td><%=rolCliente.getTotalHorasExtras()%></td>
                        <td><%=rolCliente.getSalario()%></td>
                        <td><%=rolCliente.getMontoHorasSobreTiempo()%></td>
                        <td><%=rolCliente.getMontoHorasSuplementarias()%></td>
                        <td><%=rolCliente.getBono()%></td>
                        <td><%=rolCliente.getTransporte()%></td>
                        <td><%=rolCliente.getTotalBonos()%></td>
                        <td><%=rolCliente.getSubtotal()%></td>
                        <td><%=rolCliente.getVacaciones()%></td>
                        <td><%=rolCliente.getDecimoTercero()%></td>
                        <td><%=rolCliente.getDecimoCuarto()%></td>
                        <td><%=rolCliente.getDecimoTercero()%></td>
                        <td><%=rolCliente.getJubilacionPatronal()%></td>
                        <td><%=rolCliente.getAportePatronal()%></td>
                        <td><%=rolCliente.getSeguros()%></td>
                        <td><%=rolCliente.getUniformes()%></td>
                        <td><%=rolCliente.getTotalIngreso()%></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody
            </table>
        </div>
    </div>
</body>
</html>

