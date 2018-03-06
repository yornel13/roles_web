<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Roles Empresa</title>
        <link rel="icon" href="images/security_icon.png" />
        <link rel="stylesheet" href="css/general-style.css">
        <link rel="stylesheet" href="css/header-style.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/material-button.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <%@include file="WEB-INF/partials-static/meta-bootstrap.html"%>
    </head>
    <body>
        <%@include file="WEB-INF/partials-static/header_principal.html" %>

        <div class="container-buttons">
            <form action="empresa" method="post">
                <button name="goEmpleados" class="btn orange" ><span>Empleados</span></button>
                <button name="goClientes" class="btn red"><span>Clientes</span></button>
            </form>
        </div>
    </body>
</html>
