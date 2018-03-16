<%@ page import="utilidad.Const" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%

    String infoMsg = (String) request.getAttribute("info_msg");
    String message = (String) request.getAttribute(Const.MESSAGE);
    System.out.println("el infoMsg es: "+infoMsg);
    if (message == null) message = "";

    String username = (String) request.getAttribute(Const.USERNAME);
    if (username == null) username = "";

%>
<html>
    <head>
        <title>Login Control</title>
        <link rel="icon" href="images/security_icon.png" />
        <link rel="stylesheet" href="css/login.css" type="text/css">
        <%@include file="WEB-INF/partials-static/meta-bootstrap.html"%>

    </head>
    <body style="background: #6f9cc2">
        <div  class="login-page">
            <div style="height: 390px" class="form">
                <h1 class="login-text">Login</h1>
                <form method="post" action="login">
                    <input value="<%=username%>" class="form-control" name="username" placeholder="Nombre usuario"/>
                    <input type="password" class="form-control" name="password" placeholder="Contraseña"/>
                    <button>Entrar</button>
                    <br>
                    <br>
                    <div id="wrong_info" class="alert alert-danger" hidden>
                        <strong>Campos obligatorios!</strong> <p id="msg_wrong_info"></p>
                    </div>
                    <div id="wrong_pass_username" class="alert alert-danger" hidden>
                        <strong>Invalido!</strong> <p id="msg_wrong_pass_user"></p>
                    </div>

                    <div id="session-timeout" class="text" style="color: red"><%=message%></div>
                </form>
            </div>
        </div>

        <span id="type_info" hidden><%=infoMsg%></span>
        <script>

            var typeInfo = $("#type_info").text();
            if(!typeInfo.localeCompare("wrong_1")){
                $("#wrong_info").prop('hidden', false);
                $("#msg_wrong_info").text("Campo de usuario o contraseña vacio!");
            }
            if(!typeInfo.localeCompare("wrong_2")){
                $("#wrong_pass_username").prop('hidden', false);
                $("#msg_wrong_pass_user").text("Usuario o contraseña erroneo!");
            }
        </script>

        <%@include file="WEB-INF/partials-static/scripts-bootstrap.html"%>
    </body>
</html>

