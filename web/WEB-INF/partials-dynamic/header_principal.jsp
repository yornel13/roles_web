<%@ page import="utilidad.SessionUtility" %>
<%
    String profile = SessionUtility.getProfile(request, response);
%>
<header>
    <div class="ancho">
        <div class="logo">
            <p><a href="">Control Guardias</a></p>
        </div>
        <form action="/roles_web/login" >
            <div class="usuario">
                <p><a><%=profile%></a></p>
                <button name="logout" class="fa fa-sign-out">
                    <span class="tooltiptext">Salir</span>
                </button>
            </div>
        </form>
    </div>
</header>