<%--
  Created by IntelliJ IDEA.
  User: Yornel
  Date: 27/2/2018
  Time: 10:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Control</title>
    <%@include file="WEB-INF/partials-static/meta-bootstrap.html"%>
    <link rel="stylesheet" href="css/login.css" type="text/css">

</head>
<body>


<div class="login-page">
    <div class="form">
        <h1 class="login-text">Login</h1>
        <form method="post" action="login">
            <input class="form-control" name="username" placeholder="Nombre usuario"/>
            <input type="password" class="form-control" name="password" placeholder="Contraseña"/>
            <button>Entrar</button>
        </form>
    </div>
</div>

<%@include file="WEB-INF/partials-static/scripts-bootstrap.html"%>
</body>
</html>

