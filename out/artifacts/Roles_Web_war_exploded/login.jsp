<%@ page import="utilidad.Const" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String message = (String) request.getAttribute(Const.MESSAGE);
    if (message == null) message = "";

    String username = (String) request.getAttribute(Const.USERNAME);
    if (username == null) username = "";

%>
<html>
    <head>
        <title>Login Control</title>
        <link rel="icon" href="images/security_icon.png" />
        <%@include file="WEB-INF/partials-static/meta-bootstrap.html"%>
        <link rel="stylesheet" href="css/login.css" type="text/css">
        <%@include file="WEB-INF/partials-static/scripts-bootstrap.html"%>
    </head>
    <body>
        <div class="login-page">
            <div class="form">
                <h1 class="login-text">Login</h1>
                <form method="post" action="login">
                    <input value="<%=username%>" class="form-control" name="username" placeholder="Nombre usuario"/>
                    <input type="password" class="form-control" name="password" placeholder="Contraseña"/>
                    <button>Entrar</button>
                    <br>
                    <br>
                    <div class="text" style="color: red"><%=message%></div>
                </form>
            </div>
        </div>
    </body>
</html>

