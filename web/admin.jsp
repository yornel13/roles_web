<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Control - Admin</title>
    <link rel="icon" href="images/security_icon.png" />
    <%@include file="WEB-INF/partials-static/meta-bootstrap.html"%>
    <link rel="stylesheet" href="css/admin.css">
    <link rel="stylesheet" href="css/admin-form.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">

</head>
<body>

<%
    User user = (User) request.getAttribute("user");
    String infoMsg = (String) request.getAttribute("info_msg");
    String username = (String) request.getAttribute("username");

    System.out.println(infoMsg+" <----- "+"Nombre: " + user.getNombre()+" ---> admin.jsp");

%>

<div class="container">
    <div class="register-page">

        <div class="form">

            <button type="button" id="edit_button" class="text-center btn btn-default offset-sm-11 col-sm-1 ">
                <span id="glyphicon-span" class="glyphicon glyphicon-pencil"></span>
            </button>

            <h1 id="form-title" class="register-text" >Usuario</h1>
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

                    if(user.getTipo().equals("M")){
                        level1 = "Empresa";
                        level2 = "Administrador";
                        level3 = "Cliente";
                        level4 = "Empleado";

                        adminVal = "M";
                        empVal = "A";
                        cliVal = "C";
                        eVal = "E";
                    }
                    if(user.getTipo().equals("C")){
                        level1 = "Cliente";
                        level2 = "Administrador";
                        level3 = "Empresa";
                        level4 = "Empleado";

                        adminVal = "C";
                        empVal = "A";
                        cliVal = "M";
                        eVal = "E";
                    }
                    if(user.getTipo().equals("E")){
                        level1 = "Empleado";
                        level2 = "Administrador";
                        level3 = "Empresa";
                        level4 = "Cliente";

                        adminVal = "E";
                        empVal = "A";
                        cliVal = "M";
                        eVal = "C";
                    }
                %>

                <br>
                <div class="form-group" >
                    <label class="col-md-3 control-label">Nivel de usuario</label>
                    <div class="input-group">
                        <div class="col-md-12 input-group-prepend">
                            <div id="glyphicon_control" class="input-group-text "><i class="glyphicon glyphicon-th-list"></i></div>
                            <select name="tipo" id="select" class="form-control selectpicker" disabled >
                                <option value="<%=adminVal%>"><%=level1%></option>
                                <option value="<%=empVal%>"><%=level2%></option>
                                <option value="<%=cliVal%>"><%=level3%></option>
                                <option value="<%=eVal%>"><%=level4%></option>
                            </select>
                        </div>
                    </div>
                </div>
                <br>

                <input class="form-control" name="nombre" value="<%=user.getNombre()%>" placeholder="Nombre" disabled/>
                <br>
                <input class="form-control" name="apellido" value="<%=user.getApellido()%>" placeholder="Apellido" disabled/>
                <br>
                <input class="form-control" type="number" name="cedula" value="<%=user.getCedula()%>" placeholder="cedula" disabled/>

                <br>

                <div class="form-row">
                    <label class="sr-only" >Nombre usuario</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><span class="glyphicon glyphicon-user"></span></div>
                        </div>
                        <input name="username"  class="form-control" value="<%=user.getUsername()%>" placeholder="Nombre usuario" disabled>
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
                        <input type="password" name="password" class="form-control" placeholder="Contraseña" disabled>
                    </div>
                </div>

                <br>

                <div class="form-row">
                    <label class="sr-only" >Confirmar contraseña</label>
                    <div class="input-group mb-4">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><i class="glyphicon glyphicon-lock"></i></div>
                        </div>
                        <input type="password" name="confirm_password" class="form-control" placeholder="Confirmar contraseña" disabled >
                    </div>
                </div>

                <%
                    String active = "Activo";
                    String noActive = "Inactivo";
                    String activeVal = "1";
                    String noActiveVal = "0";

                    Boolean activeState = user.getActivo();
                    if(!activeState) {
                       active =  "Inactivo";
                       noActive = "Activo";
                       activeVal = "0";
                       noActiveVal = "1";
                    }
                %>

                <div class="form-group" id="display_control" hidden>
                    <label class="col-md-1 control-label visible">Estado</label>
                    <div class="input-group">
                        <div class="col-md-4 input-group-prepend visible">
                            <div id="estado" class="input-group-text"><i class="glyphicon glyphicon-list visible"></i></div>
                            <select id="active_select" name="activo"  class="form-control selectpicker visible" >
                                <option value="<%=activeVal%>"><%=active%></option>
                                <option value="<%=noActiveVal%>"><%=noActive%></option>
                            </select>
                        </div>
                    </div>
                </div>
                <br>

                <button id="save_update" name="update_user" value="<%=user.getId()%>" style="background-color: #5d7ec2" class="btn btn-lg btn-primary disabled" >Guardar</button>

                <br>
                <br>

                <div id="wrong_info" class="alert alert-danger" hidden>
                    <strong>Campos obligatorios!</strong> <p id="msg_wrong_info"></p>
                </div>

                <div id="wrong_info_cedula" class="alert alert-danger" hidden>
                    <strong>Usuario existente!</strong> <p id="msg_wrong_info_cedula">El nombre de usuario <strong><%=username%></strong> se encuentra en uso</p>
                </div>

                <div id="saved_info" class="alert alert-success" hidden>
                    <strong>Actualizado!</strong> <p id="msg_success_info"></p>
                </div>

            </form>
        </div>
</div>

    <span id="type_info" hidden><%=infoMsg%></span>

    <script>

        $(document).ready(function(){

            var typeInfo = $("#type_info").text();

            if(!typeInfo.localeCompare("saved")){
                $("#saved_info").prop('hidden', false);
                $("#msg_success_info").text("Usuario actualizado con exito");
            }
            if(!typeInfo.localeCompare("empty_1")){
                $("#wrong_info").prop('hidden', false);
                $("#msg_wrong_info").text("Los campos de nombre, apellido y cedula son requeridos");
            }
            if(!typeInfo.localeCompare("empty_2")){
                $("#wrong_info").prop('hidden', false);
                $("#msg_wrong_info").text("Los campos de nombre usuario y contraseña son requeridos");
            }
            if(!typeInfo.localeCompare("empty_3")){
                $("#wrong_info").prop('hidden', false);
                $("#msg_wrong_info").text("Los campos de contraseña y confirmacion no coinciden");
            }
            if(!typeInfo.localeCompare("same_dni")){
                $("#wrong_info_cedula").prop('hidden', false);
                $("#msg_wrong_info_cedula").text("Existe un usuario registrado con esta cedula");
            }
            if(!typeInfo.localeCompare("username_exists")){
                $("#wrong_info_cedula").prop('hidden', false);
            }
            if(!typeInfo.localeCompare("empty_confirm")){
                $("#wrong_info").prop('hidden', false);
                $("#msg_wrong_info").text("Debe confirmar la contraseña");
            }

            $('body').on('click', '#edit_button', function(){
                var formTitle = $("#form-title").text();

                if(!formTitle.localeCompare("Usuario")){
                    $("#form-title").text('Editar usuario');
                    $('#edit_button').blur();
                    $("#glyphicon-span").addClass('glyphicon glyphicon-remove');


                    $("#display_control").prop('hidden', false);
                    $("#active_select").prop('hidden', false);
                    $("select").prop('disabled', false);

                    $("input").prop('disabled', false);
                    $("#save_update").removeClass('btn btn-lg btn-primary disabled')
                        .addClass('btn btn-lg btn-primary');
                } else {

                    $("#form-title").text('Usuario');
                    $('#edit_button').blur();

                    $("#glyphicon-span").removeClass('glyphicon glyphicon-remove')
                        .addClass('glyphicon glyphicon-pencil');

                    $("select").prop('disabled', true);
                    $("#display_control").css("visibility", "hidden");
                    $("input").prop('disabled', true);
                    $("#save_update").removeClass('btn btn-lg btn-primary ')
                        .addClass('btn btn-lg btn-primary disabled');
                }

            })
        })

    </script>

    <%@include file="WEB-INF/partials-static/scripts-bootstrap.html"%>

</body>
</html>
