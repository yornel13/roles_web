<%@ page import="models.User" %>
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
    User user = (User) request.getAttribute("user_logged_id");

    String infoMsg = (String) request.getAttribute("info_msg");

%>

<div class="container">
    <div class="register-page">

        <div class="form">

            <button type="button" id="edit_button" class="text-center btn btn-default offset-sm-11 col-sm-1 ">
                <span id="glyphicon-span" class="glyphicon glyphicon-pencil"></span>
            </button>

            <h1 id="form-title" class="register-text" >Perfil</h1>
            <form  class="well" method="post" action="admin">

                <br>
                <div class="form-row">
                    <label class="sr-only" >Nombre usuario</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><span class="glyphicon glyphicon-user"></span></div>
                        </div>
                        <input name="username" class="form-control" placeholder="Nombre usuario" disabled value="<%=user.getUsername()%>">
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
                    <label class="sr-only" >Contraseña</label>
                    <div class="input-group mb-4">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><i class="glyphicon glyphicon-lock"></i></div>
                        </div>
                        <input type="password" name="newpassword" class="form-control" placeholder="Nueva contraseña" disabled>
                    </div>
                </div>

                <br>

                <div class="form-row">
                    <label class="sr-only" >Contraseña</label>
                    <div class="input-group mb-4">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><i class="glyphicon glyphicon-lock"></i></div>
                        </div>
                        <input type="password" name="password" class="form-control" placeholder="Confirmar contraseña" disabled>
                    </div>
                </div>

                <br>
                <br>

                <button id="save_profile_button" name="save_profile" style="background: #6f9cc2;" class="btn btn-lg btn-primary disabled" >Guardar</button>

                <br>
                <br>

                <div id="wrong_info" class="alert alert-danger" hidden>
                    <strong>Campos obligatorios!</strong> <p id="msg_wrong_info"></p>
                </div>

                <div id="saved_info" class="alert alert-success" hidden>
                    <strong>Guardado!</strong> <p id="msg_success_info"></p>
                </div>

            </form>
        </div>
    </div>


    <span id="type_info" hidden><%=infoMsg%></span>

<script>

    var typeInfo = $("#type_info").text();
    console.log(typeInfo);


    $(document).ready(function(){

        if(!typeInfo.localeCompare("empty_1")){
            $("#wrong_info").prop('hidden', false);
            $("#msg_wrong_info").text("Los campos de nombre usuario y contraseña son requeridos");
        }
        if(!typeInfo.localeCompare("empty_2")){
            $("#wrong_info").prop('hidden', false);
            $("#msg_wrong_info").text("Los campos de contraseña y confirmacion no coinciden");
        }


        $('body').on('click', '#edit_button', function(){
            var formTitle = $("#form-title").text();

            if(!formTitle.localeCompare("Perfil")){
                $("#form-title").text('Editar perfil');
                $('#edit_button').blur();
                $("#glyphicon-span").addClass('glyphicon glyphicon-remove');


                $("#display_control").prop('hidden', false);
                $("#active_select").prop('hidden', false);
                $("select").prop('disabled', false);

                $("input").prop('disabled', false);
                $("#save_profile_button").removeClass('btn btn-lg btn-primary disabled')
                    .addClass('btn btn-lg btn-primary');
            } else {

                $("#form-title").text('Perfil');
                $('#edit_button').blur();

                $("#glyphicon-span").removeClass('glyphicon glyphicon-remove')
                    .addClass('glyphicon glyphicon-pencil');

                $("select").prop('disabled', true);
                $("#display_control").css("visibility", "hidden");
                $("input").prop('disabled', true);
                $("#save_profile_button").removeClass('btn btn-lg btn-primary ')
                    .addClass('btn btn-lg btn-primary disabled');
            }

        })







    })

</script>
<%@include file="WEB-INF/partials-static/scripts-bootstrap.html"%>
</body>
</html>
