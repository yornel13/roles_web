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
  </head>
  <body>
    <h1>Probando page</h1>

    <div class="container">
      <form class="form-signin">
        <h2 class="form-signin-heading">Please login</h2>
        <input type="text" class="form-control" name="username" placeholder="Email Address" required="" autofocus="" />
        <input type="password" class="form-control" name="password" placeholder="Password" required=""/>
        <label class="checkbox">
          <input type="checkbox" value="remember-me" id="rememberMe" name="rememberMe"> Remember me
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
      </form>
    </div>

    <form  action="/roles_web/probando">
      <button>
        Ir a probando
      </button>
    </form>

    <%@include file="WEB-INF/partials-static/scripts-bootstrap.html"%>
  </body>
</html>
