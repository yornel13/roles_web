<%@ page import="models.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Joshuan Marval
  Date: 5/3/2018
  Time: 8:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Control - Admin</title>
    <link rel="icon" href="images/security_icon.png" />
    <link rel="stylesheet" href="css/general-style.css">
    <link rel="stylesheet" href="css/header-style.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%@include file="WEB-INF/partials-static/meta-bootstrap.html"%>
</head>
<body>
    <%@include file="WEB-INF/partials-dynamic/header_principal.jsp" %>

    <br>
    <br>

    <%
        String infoMsg = (String) request.getAttribute("info_msg");
        String infoTitle = "Guardado!";

        System.out.println("infoMsg es: "+infoMsg);

        if(infoMsg != null){
            if(infoMsg.equals("updated") ){
                infoTitle = "Actualizado!";
            }
            if(infoMsg.equals("delete")){
                infoTitle = "Eliminado!";
            }
        }
    %>
    <div class="alert-container container" style=" margin-top: 35px; margin-bottom: 10px; padding-top:20px; height: 100px;">
        <div id="saved_info" class="alert alert-success align-content-center invisible"  >
            <strong><%=infoTitle%></strong> <p id="msg_success_info"></p>
        </div>
    </div>

    <div  class="container" style="padding-top: 0px">

        <div class="table-responsive">

            <table id="user_table" class="table table-striped table-bordered table-hover">
                <thead>
                    <tr class="table-info">
                        <th></th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Cedula</th>
                        <th>Nombre acceso</th>
                        <th>Tipo</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                <%

                    List<User> listaUsuario = (List<User>) request.getAttribute("listaUsuario");

                    for (User userIn :
                            listaUsuario) {

                        String className = "";
                        if(!userIn.getActivo()){
                            className = "table-danger";
                        }
                %>
                    <tr class="<%=className%>" >
                        <td>
                            <form>
                                <button class="searchButton" name="user_id" value="<%=userIn.getId()%>">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                            </form>
                        </td>
                        <td><%=userIn.getNombre()%></td>
                        <td><%=userIn.getApellido()%></td>
                        <td><%=userIn.getCedula()%></td>
                        <td><%=userIn.getUsername()%></td>
                        <td><%=userIn.getTipoText()%></td>
                        <td>
                            <form action="admin">
                                <button id="delete_button" name="delete" style="background-color: transparent;  border: none; cursor:pointer; overflow: hidden; outline:none; " value="<%=userIn.getId()%>">
                                    <span class="glyphicon glyphicon-trash"></span>
                                </button>
                            </form>
                        </td>
                    </tr>
                <%
                    }

                %>
            </tbody>
            </table>
        </div>
    </div>

    <div id="modal_delete" class="modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Eliminar</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>¿Esta seguro que desea eliminar el usuario?.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">Aceptar</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Canel</button>
                </div>
            </div>
        </div>
    </div>


    <span id="type_info" hidden><%=infoMsg%></span>
    <script>

        $(document).ready(function() {

            var typeInfo = $("#type_info").text();
            console.log(typeInfo);

            if (!typeInfo.localeCompare("saved")) {
                console.log(" entro saved");
                $("#saved_info").removeClass("alert alert-success invisible")
                    .addClass("alert alert-success");
                $("#msg_success_info").text("El usuario ha sido creado con exito");
                setTimeout(function() {
                    $("#saved_info").fadeOut(1500);
                },4000);
            }

            if (!typeInfo.localeCompare("save_profile")) {
                console.log(" entro saved");
                $("#saved_info").removeClass("alert alert-success invisible")
                    .addClass("alert alert-success");
                $("#msg_success_info").text("Su perfil ha sido actualizado con exito");
                setTimeout(function() {
                    $("#saved_info").fadeOut(1500);
                },4000);
            }

            if (!typeInfo.localeCompare("updated")) {
                console.log("entro update");
              $("#saved_info").removeClass("alert alert-success invisible")
                  .addClass("alert alert-success");
              $("#msg_success_info").text("El usuario ha sido modificado con exito");
                setTimeout(function() {
                    $("#saved_info").fadeOut(1500);
                },4000);
            }


            if (!typeInfo.localeCompare("delete")) {
                console.log(" entro saved");
                $("#saved_info").removeClass("alert alert-success invisible")
                    .addClass("alert alert-danger");
                $("#msg_success_info").text("El usuario ha sido borrado");
                setTimeout(function () {
                    $("#saved_info").fadeOut(1500);
                }, 4000);
            }

        })

    </script>

</body>
</html>
