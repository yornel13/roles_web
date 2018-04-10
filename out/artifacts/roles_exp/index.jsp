<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>::HOME::</title>
    <link rel="icon" href="images/security_icon.png" />
  </head>
  <body>
    <div>
      <!--<div class="form">
        <form method="post" action="login">
          <button name="goLogin">Ir a login</button>
        </form>
        <form method="post" action="index">
          <button name="goRolCliente">Ir a rol cliente</button>
        </form>
        <form method="post" action="index">
          <button name="goRolesEmpleado">Ir a roles empleado</button>
        </form>
        <form method="post" action="index">
          <button name="goRolesEmpresa">Ir a roles empresa</button>
        </form>
      </div>-->
      <%
        response.sendRedirect("login");
      %>
    </div>

  </body>
</html>
