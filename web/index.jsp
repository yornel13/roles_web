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
    <title>::Inicio::</title>
    <link rel="icon" href="images/security_icon.png" />
  </head>
  <body>
    <div>
      <div class="form">
        <form method="post" action="login">
          <button name="goLogin">Ir a login</button>
        </form>
        <form method="post" action="index">
          <button name="goRolCliente">Ir a rol cliente</button>
        </form>
        <form method="post" action="index">
          <button name="goRolesEmpleado">Ir a roles empleado</button>
        </form>
      </div>
    </div>

  </body>
</html>
