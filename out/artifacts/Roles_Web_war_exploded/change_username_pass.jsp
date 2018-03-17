<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Control - Admin</title>
    <link rel="icon" href="images/security_icon.png" />
    <%@include file="WEB-INF/partials-static/meta-bootstrap.html"%>
    <link rel="stylesheet" href="css/admin.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">


</head>
<body>

<%
    String infoTitle = "Guardado!";
    User userLogged = (User) request.getAttribute("user_logged_id");


    String infoMsg = (String) request.getAttribute("info_msg");
    String usernameReturned = (String) request.getAttribute("username");

   // System.out.println("login user: "+userLogged.getNombre()+" infoMsg: "+infoMsg+" username "+usernameReturned);
%>



<div class="container">

    <div class="alert-container container" style=" width: 2000px;   padding-top:30px; height: 100px; ">
        <div id="saved_info_top" class="alert alert-success align-content-center invisible"  >
            <strong><%=infoTitle%></strong> <p id="msg_success_info_top">Su contraseña a sido modificada con exito</p>
        </div>
    </div>

    <div class="">

        <div class="form">



            <h1 id="form-title" class="register-text" >Perfil</h1>
            <form  class="well" method="post" action="admin">

                <br>
                <div class="panel panel-default">
                    <button type="button" id="edit_button_username" class="text-center btn btn-default offset-sm-11 col-sm-1 bg-transparent">
                        <span id="glyphicon-span_username" class="glyphicon glyphicon-pencil" style="color: #5d7ec2;"></span>
                    </button>
                    <div class="panel-heading">Cambiar nombre de usuario
                        <br>
                        <br>

                        <div class="">
                            <label class="sr-only" >Nombre usuario</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <div class="input-group-text"><span class="glyphicon glyphicon-user" ></span></div>
                                </div>
                                <input id="username_field" name="username" class="form-control" placeholder="Nombre usuario" tabindex="1" disabled value="<%=userLogged.getUsername()%>" required>
                            </div>
                        </div>

                        <br>
                        <br>
                        <div class="">
                            <label class="sr-only" >Contraseña</label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text"><i class="glyphicon glyphicon-lock"></i></div>
                                    </div>
                                    <input id="password_field" type="password" name="password" class="form-control" tabindex="2" placeholder="Contraseña" required disabled>
                                </div>
                            <h6 class="pull-right"  style="color: #5d7ec2;" hidden>Requiere contraseña actual  </h6>
                            <br>
                        </div>
                    </div>
                </div>

                <br>

                <div class="panel panel-default">
                    <button type="button" id="edit_button_password" class="text-center btn btn-default offset-sm-11 col-sm-1 bg-transparent">
                        <span id="glyphicon-span_password" class="glyphicon glyphicon-pencil" style="color: #5d7ec2;"></span>
                    </button>
                    <div class="panel-heading">Cambiar contraseña
                        <br>
                        <br>
                        <div class="">
                            <label class="sr-only" >Contraseña actual</label>
                            <div class="input-group mb-4">
                                <div class="input-group-prepend">
                                    <div class="input-group-text"><i class="glyphicon glyphicon-lock"></i></div>
                                </div>
                                <input id="current_password" type="password" name="current_password" class="form-control" placeholder="Contraseña" disabled>
                            </div>
                        </div>

                        <br>

                        <div class="">
                            <label class="sr-only" >Nueva Contraseña</label>
                            <div class="input-group mb-4">
                                <div class="input-group-prepend">
                                    <div class="input-group-text"><i class="glyphicon glyphicon-lock"></i></div>
                                </div>
                                <input id="new_password" type="password" name="newpassword" class="form-control" placeholder="Nueva contraseña" disabled>
                            </div>
                        </div>

                        <br>

                        <div class="">
                            <label class="sr-only" >Confirmar Contraseña</label>
                            <div class="input-group mb-4">
                                <div class="input-group-prepend">
                                    <div class="input-group-text"><i class="glyphicon glyphicon-lock"></i></div>
                                </div>
                                <input id="confirm_password" type="password" name="confirm_password" class="form-control" placeholder="Confirmar contraseña" disabled>
                            </div>
                        </div>
                    </div>
                </div>

                <br>
                <br>

                <button id="save_profile_button" name="save_profile" style="background: #6f9cc2;" class="btn btn-lg btn-primary" disabled>Guardar</button>

                <br>
                <br>

                <div id="wrong_info" class="alert alert-danger" hidden>
                    <strong>Campos obligatorios!</strong> <p id="msg_wrong_info"></p>
                </div>
                <div id="wrong_info_password" class="alert alert-danger" hidden>
                    <strong>Contraseña invalida!</strong> <p id="msg_wrong_pass"></p>
                </div>

                <div id="saved_info" class="alert alert-success" hidden>
                    <strong>Guardado!</strong> <p id="msg_success_info"></p>
                </div>

                <div id="wrong_info_username" class="alert alert-danger" hidden>
                    <strong>Usuario existente!</strong> <p id="msg_wrong_info_username">El nombre de usuario <strong><%=usernameReturned%></strong> se encuentra en uso</p>
                </div>

            </form>
        </div>
    </div>


    <span id="type_info" hidden><%=infoMsg%></span>

<script>



    var typeInfo = $("#type_info").text();
    console.log(typeInfo);

    if (!typeInfo.localeCompare("save_profile")) {
        $("#saved_info_top").removeClass("alert alert-success invisible")
            .addClass("alert alert-success");
        $("#msg_success_info_top").text("Su usuario ha sido actualizado con exito");
        setTimeout(function() {
            $("#saved_info_top").fadeOut(1500);
        },4000);
    }

    if (!typeInfo.localeCompare("save_pass")) {
        $("#saved_info_top").removeClass("alert alert-success invisible")
            .addClass("alert alert-success");
        $("#msg_success_info_top").text("Su contraseña ha sido actualizada con exito");
        setTimeout(function() {
            $("#saved_info_top").fadeOut(1500);
        },4000);
    }





    $(document).ready(function(){

        /**Username**/
        if(!typeInfo.localeCompare("empty_1")){
            $("#wrong_info").prop('hidden', false);
            $("#msg_wrong_info").text("El campo de usuario esta vacio");
        }
        if(!typeInfo.localeCompare("username_exists")){
            $("#wrong_info_username").prop('hidden', false);
        }
        if(!typeInfo.localeCompare("empty_2")){
            $("#wrong_info").prop('hidden', false);
            $("#msg_wrong_info").text("La contraseña es requerida para el cambio de usuaro");
        }
        if(!typeInfo.localeCompare("empty_3")){
            $("#wrong_info_password").prop('hidden', false);
            $("#msg_wrong_pass").text("Contraseña invalida!");
        }



        /**Password**/
        if(!typeInfo.localeCompare("empty_pass1")){
            $("#wrong_info").prop('hidden', false);
            $("#msg_wrong_info").text("El campo de contraseña es requerido para el cambio de clave");
        }
        if(!typeInfo.localeCompare("empty_pass2")){
            $("#wrong_info").prop('hidden', false);
            $("#msg_wrong_info").text("Debe especificar la nueva contraseña");
        }
        if(!typeInfo.localeCompare("empty_pass3")){
            $("#wrong_info").prop('hidden', false);
            $("#msg_wrong_info").text("EL campo de confirmacion es requerido");
        }
        if(!typeInfo.localeCompare("wrong_pass1")){
            $("#wrong_info").prop('hidden', false);
            $("#msg_wrong_info").text("La nueva contraseña no coincide con la confirmada");
        }
        if(!typeInfo.localeCompare("wrong_pass2")){
            $("#wrong_info").prop('hidden', false);
            $("#msg_wrong_info").text("La contraseña actual es invalida!");

        }




        $('body').on('click', '#edit_button_username', function(){

            var formTitle = $("#form-title").text();

            if(!formTitle.localeCompare("Perfil")){
                $("#form-title").text('Editar Nombre de usuario');
                $('#edit_button_username').blur();
                $('#edit_button_password').prop('disabled', true);

                $("#glyphicon-span_username").addClass('glyphicon glyphicon-remove');


                $("#username_field").prop('disabled', false);
                $("#password_field").prop('disabled', false);
                $("h6").prop('hidden', false);
                $("#save_profile_button").prop('disabled', false);
            } else {
                $('#edit_button_password').prop('disabled', false);
                $("#form-title").text('Perfil');
                $('#edit_button_username').blur();

                $("#glyphicon-span_username").removeClass('glyphicon glyphicon-remove')
                    .addClass('glyphicon glyphicon-pencil');

                $("#display_control").css("visibility", "hidden");

                $("#username_field").prop('disabled', true);
                $("#password_field").prop('disabled', true);
                $("h6").prop('hidden', true);
                $("#save_profile_button").prop('disabled', true);
            }

        });

        $('body').on('click', '#edit_button_password', function(){
            var formTitle = $("#form-title").text();
            if(!formTitle.localeCompare("Perfil")){

                $("#form-title").text('Cambiar contraseña');
                $('#edit_button_password').blur();
                $('#edit_button_username').prop('disabled', true);
                $("#glyphicon-span_password").addClass('glyphicon glyphicon-remove');

                $("#current_password").prop('disabled', false);
                $("#new_password").prop('disabled', false);
                $("#confirm_password").prop('disabled', false);
                $("#save_profile_button").prop('disabled', false);
            } else {
                $("#form-title").text('Perfil');
                $('#edit_button_password').blur();
                $('#edit_button_username').prop('disabled', false);

                $("#glyphicon-span_password").removeClass('glyphicon glyphicon-remove')
                    .addClass('glyphicon glyphicon-pencil');

                $("#display_control").css("visibility", "hidden");

                $("#current_password").prop('disabled', true);
                $("#new_password").prop('disabled', true);
                $("#confirm_password").prop('disabled', true);
                $("#save_profile_button").prop('disabled', true);

        }


        });





    })

</script>
<%@include file="WEB-INF/partials-static/scripts-bootstrap.html"%>
</body>
</html>
