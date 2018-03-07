<%@ page import="models.User" %><%--
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


    <%@include file="WEB-INF/partials-static/meta-bootstrap.html"%>
    <link rel="stylesheet" href="css/admin.css">
    <link rel="stylesheet" href="css/admin-form.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">

</head>
<body>

<%

    String formName = "Usuario";

    User user = (User) request.getAttribute("user");
%>

<div class="container">



    <div class="register-page">

        <div class="form">

            <button type="button" id="edit_button" class="text-center btn btn-default offset-sm-11 col-sm-1 ">
                <span class="glyphicon glyphicon-pencil "></span>
            </button>

            <h1 id="form-title" class="register-text" ><%=formName%></h1>
            <form  class="well form-horizontal" method="post" action="admin">

                <div class="form-group" >
                    <label class="col-md-3 control-label">Nivel de usuario</label>
                    <div class="input-group">
                        <div class="col-md-12 input-group-prepend">
                            <div id="glyphicon_control" class="input-group-text "><i class="glyphicon glyphicon-th-list"></i></div>
                            <select name="tipo" id="select" class="form-control selectpicker" disabled >
                                <option value="A">Administrador</option>
                                <option value="M">Empresa</option>
                                <option value="C">Cliente</option>
                                <option value="E">Empleado</option>
                            </select>
                        </div>
                    </div>
                </div>
                <br>

                <input class="form-control" name="nombre" value="<%=user.getNombre()%>" placeholder="Nombre" disabled/>
                <br>
                <input class="form-control" name="apellido" value="<%=user.getApellido()%>" placeholder="Apellido" disabled/>
                <br>
                <input class="form-control" name="cedula" value="<%=user.getCedula()%>" placeholder="cedula" disabled/>
                <br>
                <div class="form-row">
                    <label class="sr-only" >Nombre usuario</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><span class="glyphicon glyphicon-user"></span></div>
                        </div>
                        <input name="username" class="form-control" placeholder="Nombre usuario" disabled>
                    </div>
                </div>

                <br>
                <br>

                <div class="form-row">
                    <label class="sr-only" >Password</label>
                    <div class="input-group mb-4">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><i class="glyphicon glyphicon-lock"></i></div>
                        </div>
                        <input type="password" name="password" class="form-control" placeholder="Password" disabled>
                    </div>
                </div>



                <div class="form-group" id="display_control">
                    <label class="col-md-2 control-label">Estado</label>
                    <div class="input-group">
                        <div class="col-md-4 input-group-prepend">
                            <div id="estado" class="input-group-text"><i class="glyphicon glyphicon-list"></i></div>
                            <select name="activo"  class="form-control selectpicker" >
                                <option value="1">Activo</option>
                                <option value="0">Inactivo</option>
                            </select>
                        </div>
                    </div>
                </div>
                <br>
                <br>

                <button>Guardar</button>
            </form>
        </div>
    </div>


</div>

    <script>
        $(document).ready(function(){
            $('body').on('click', '#edit_button', function(){
                $('#edit_button').blur()
                $("#form-title").text('Editar usuario');
                $("select").prop('disabled', false);
                $("#display_control").css("visibility", "visible");
                $("input").prop('disabled', false);
            })
        })
        
    </script>


    <%@include file="WEB-INF/partials-static/scripts-bootstrap.html"%>
</body>
</html>
