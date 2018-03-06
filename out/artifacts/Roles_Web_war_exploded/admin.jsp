<%--
  Created by IntelliJ IDEA.
  User: Joshuan Marval
  Date: 5/3/2018
  Time: 8:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Control - Admin</title>
    <link rel="stylesheet" href="css/admin.css">
    <%@include file="WEB-INF/partials-static/meta-bootstrap.html"%>

</head>
<body>

<div class="container">


    <div class="register-page">
        <div class="form">
            <h1 class="register-text">Registrar Usuario</h1>
            <form  class="well form-horizontal" method="post" action="register">
                <input class="form-control" name="nombre" placeholder="Nombre"/>
                <input class="form-control" name="apellido" placeholder="Apellido"/>
                <input class="form-control" name="cedula" placeholder="cedula"/>

                <div class="form-group">
                    <label class="sr-only" >Nombre usuario</label>
                    <div class="input-group mb-2">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><i class="glyphicon glyphicon-user"></i></div>
                        </div>
                        <input name="username" class="form-control" placeholder="Nombre usuario">
                    </div>
                </div>

                <div class="form-group">
                    <label class="sr-only" >Password</label>
                    <div class="input-group mb-2">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><i class="glyphicon glyphicon-lock"></i></div>
                        </div>
                        <input type="password" name="password" class="form-control" placeholder="Password">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Nivel de usuario</label>
                    <div class="input-group ">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><i class="glyphicon glyphicon-th-list"></i></div>
                            <select name="tipo" class="form-control selectpicker" >
                                <option value="A">Administrador</option>
                                <option value="C">Cliente</option>
                                <option value="E">Empleado</option>
                                <option value="M">Empresa</option>
                            </select>
                        </div>
                    </div>
                    <label class="col-md-3 control-label">Estado</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><i class="glyphicon glyphicon-list"></i></div>
                            <select name="activo" class="form-control selectpicker" >
                                <option value="1">Activo</option>
                                <option value="0">Inactivo</option>
                            </select>
                        </div>
                    </div>
                </div>

                <button>Registrar</button>
            </form>
        </div>
    </div>


</div>
    <%@include file="WEB-INF/partials-static/scripts-bootstrap.html"%>
</body>
</html>
