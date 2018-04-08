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
    <link rel="icon" href="images/security_icon.png" />
    <%@include file="WEB-INF/partials-static/meta-bootstrap.html"%>
    <link rel="stylesheet" type="text/css" href="css/bootstrap-msg.css" />
    <link rel="stylesheet" href="css/admin.css">
    <link rel="stylesheet" href="css/admin-form.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script type="text/javascript" src="js/bootstrap-msg.js"></script>

</head>
<body>

<%
    String infoMsg = (String) request.getAttribute("info_msg");

    String tipoReturned = (String) request.getAttribute("tipo");
    String nameReturned = (String) request.getAttribute("nombre");
    String lastnameReturned = (String) request.getAttribute("apellido");
    String cedulaReturned = (String) request.getAttribute("cedula");
    String usernameReturned = (String) request.getAttribute("username");
%>

<div class="container">
    <div class="register-page">

        <div class="form">


            <h1 id="form-title" class="register-text" >Crear usuario</h1>
            <form  class="well" method="post" action="admin">

                <%
                    String level1 = "Administrador";
                    String level2 = "Empresa";
                    String level3 = "Cliente";
                    String level4 = "Empleado";

                    String adminVal = "A";
                    String empVal = "M";
                    String cliVal = "C";
                    String eVal = "E";

                    if(tipoReturned != null){
                        if(tipoReturned.equals("M")){
                            level1 = "Empresa";
                            level2 = "Administrador";
                            level3 = "Cliente";
                            level4 = "Empleado";

                            adminVal = "M";
                            empVal = "A";
                            cliVal = "C";
                            eVal = "E";
                        }
                        if(tipoReturned.equals("C")){
                            level1 = "Cliente";
                            level2 = "Administrador";
                            level3 = "Empresa";
                            level4 = "Empleado";

                            adminVal = "C";
                            empVal = "A";
                            cliVal = "M";
                            eVal = "E";
                        }
                        if(tipoReturned.equals("E")){
                            level1 = "Empleado";
                            level2 = "Administrador";
                            level3 = "Empresa";
                            level4 = "Cliente";

                            adminVal = "E";
                            empVal = "A";
                            cliVal = "M";
                            eVal = "C";
                        }
                    }
                %>

                <div class="form-group" >
                    <label class="col-md-3 control-label">Nivel de usuario</label>
                    <div class="input-group">
                        <div class="col-md-12 input-group-prepend">
                            <div id="glyphicon_control" class="input-group-text "><i class="glyphicon glyphicon-th-list"></i></div>
                            <select name="tipo" id="select" class="form-control selectpicker" >
                                <option value="<%=adminVal%>"><%=level1%></option>
                                <option value="<%=empVal%>"><%=level2%></option>
                                <option value="<%=cliVal%>"><%=level3%></option>
                                <option value="<%=eVal%>"><%=level4%></option>
                            </select>
                        </div>
                    </div>
                </div>
                <br>

                <input id="name" class="form-control" name="nombre" value="" placeholder="Nombre" />
                <br>
                <input id="lastname" class="form-control" name="apellido" value="" placeholder="Apellido" />
                <br>
                <input id="cedula" class="form-control" type="number" name="cedula" value="" placeholder="cedula" />

                <br>

                <div class="form-row">
                    <label class="sr-only" >Nombre usuario</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><span class="glyphicon glyphicon-user"></span></div>
                        </div>
                        <input id="username" name="username" class="form-control" placeholder="Nombre usuario" >
                    </div>
                </div>

                <br>
                <br>

                <div class="form-row">
                    <label class="sr-only" >Contraseña</label>
                    <div class="input-group mb-4">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><i class="glyphicon glyphicon-lock"></i></div>
                        </div>
                        <input type="password" name="password" class="form-control" placeholder="Contraseña" >
                    </div>
                </div>

                <br>

                <div class="form-row">
                    <label class="sr-only" >Confirmar contraseña</label>
                    <div class="input-group mb-4">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><i class="glyphicon glyphicon-lock"></i></div>
                        </div>
                        <input type="password" name="confirm_password" class="form-control" placeholder="Confirmar contraseña" >
                    </div>
                </div>

                <br>
                <br>

                <button id="save_button" name="save" class="btn btn-lg btn-primary" >Guardar</button>

                <br>
                <br>


                <div id="wrong_info" class="alert alert-danger" hidden>
                    <strong>Campos obligatorios!</strong> <p id="msg_wrong_info"></p>
                </div>

                <div id="wrong_info_cedula" class="alert alert-danger" hidden>
                    <strong>Usuario existente!</strong> <p id="msg_wrong_info_cedula"></p>
                </div>

                <div id="wrong_info_username" class="alert alert-danger" hidden>
                    <strong>Usuario existente!</strong> <p id="msg_wrong_info_username">El nombre de usuario <strong><%=usernameReturned%></strong> se encuentra en uso</p>
                </div>

                <div id="saved_info" class="alert alert-success" hidden>
                    <strong>Guardado!</strong> <p id="msg_success_info"></p>
                </div>

            </form>
        </div>
    </div>

    <span id="type_info" hidden><%=infoMsg%></span>
    <span id="name-returned" hidden><%=nameReturned%></span>
    <span id="lastname-returned" hidden><%=lastnameReturned%></span>
    <span id="cedula-returned" hidden><%=cedulaReturned%></span>
    <span id="username-returned" hidden><%=usernameReturned%></span>


    <script>

        $(document).ready(function(){

            var typeInfo = $("#type_info").text();
            var nameReturned = $("#name-returned").text();
            var lastnameReturned = $("#lastname-returned").text();
            var cedulaReturned = $("#cedula-returned").text();
            var usernameReturned = $("#username-returned").text();

            console.log(typeInfo);

            if(!typeInfo.localeCompare("saved")){
                $("#saved_info").prop('hidden', false);
                $("#msg_success_info").text("Usuario creado exitosamente");
            }
            if(!typeInfo.localeCompare("empty_1")){
                $("#wrong_info").prop('hidden', false);
                $("#msg_wrong_info").text("Los campos de nombre, apellido y cedula son requeridos");
                $("#name").val(nameReturned);
                $("#lastname").val(lastnameReturned);
                $("#cedula").val(cedulaReturned);
                $("#username").val(usernameReturned);
            }
            if(!typeInfo.localeCompare("empty_2")){
                $("#wrong_info").prop('hidden', false);
                $("#msg_wrong_info").text("EL campo Nombre usuario es requerido");
                $("#name").val(nameReturned);
                $("#lastname").val(lastnameReturned);
                $("#cedula").val(cedulaReturned);

            }
            if(!typeInfo.localeCompare("empty_3")){
                $("#wrong_info").prop('hidden', false);
                $("#msg_wrong_info").text("Los campos de contraseña y confirmacion no coinciden");
                $("#name").val(nameReturned);
                $("#lastname").val(lastnameReturned);
                $("#cedula").val(cedulaReturned);
                $("#username").val(usernameReturned);
            }
            if(!typeInfo.localeCompare("empty_4")){
                $("#wrong_info").prop('hidden', false);
                $("#msg_wrong_info").text("Los campos de contraseña y confirmacion son requeridos");
                $("#name").val(nameReturned);
                $("#lastname").val(lastnameReturned);
                $("#cedula").val(cedulaReturned);
                $("#username").val(usernameReturned);
            }
            if(!typeInfo.localeCompare("same_dni")){
                $("#wrong_info_cedula").prop('hidden', false);
                $("#msg_wrong_info_cedula").text("Existe un usuario registrado con esta cedula");
                $("#name").val(nameReturned);
                $("#lastname").val(lastnameReturned);
                $("#username").val(usernameReturned);

            }
            if(!typeInfo.localeCompare("username_exists")){
                $("#wrong_info_username").prop('hidden', false);
                $("#name").val(nameReturned);
                $("#lastname").val(lastnameReturned);
                $("#cedula").val(cedulaReturned);
            }
            if(!typeInfo.localeCompare("empty_confirm")){
                $("#wrong_info").prop('hidden', false);
                $("#msg_wrong_info").text("Debe confirmar la contraseña");
                $("#name").val(nameReturned);
                $("#lastname").val(lastnameReturned);
                $("#cedula").val(cedulaReturned);
                $("#username").val(usernameReturned);
            }
        })

    </script>


    <%@include file="WEB-INF/partials-static/scripts-bootstrap.html"%>

</body>
</html>