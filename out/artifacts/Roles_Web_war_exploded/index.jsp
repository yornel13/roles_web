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
    <h1>Probando page</h1>

    <div class="login-page">
      <div class="form">
        <form class="register-form">
          <input type="text" placeholder="name"/>
          <input type="password" placeholder="password"/>
          <input type="text" placeholder="email address"/>
          <button>create</button>
          <p class="message">Already registered? <a href="#">Sign In</a></p>
        </form>
        <form class="login-form">
          <input type="text" placeholder="username"/>
          <input type="password" placeholder="password"/>
          <button>login</button>
        </form>
      </div>
    </div>

    <form  action="/roles_web/rol_cliente">
      <button>
        Ir a probando
      </button>
    </form>

    <%@include file="WEB-INF/partials-static/scripts-bootstrap.html"%>
  </body>
</html>
