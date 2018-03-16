<%@ page import="utilidad.SessionUtility" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String profile = SessionUtility.getProfile(request, response);
%>
<header>
     <script src="http://code.jquery.com/jquery-latest.min.js"></script>
     <script src="href=https://www.jqueryscript.net/tags.php?/twitter/twitter-bootstrap-hover-dropdown.js"></script>

    <div class="ancho">
        <div class="logo">
            <p><a href="">Control Guardias</a></p>
        </div>
        <div class="btn-toolbar" style="width: 70%;height: 70px;padding-top: 20px">
            <div class="btn-group" style="position: absolute;right:10%;">
                <button class="btn dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="1000"  style="background: transparent;
                padding-right: 10px;                                                                                        color: white;
                                                                                                                            font-size: 13px;
                                                                                                                            text-decoration: none;
                                                                                                                            text-transform: uppercase;
                                                                                                                            font-weight: bold;
                                                                                                                            letter-spacing: 1px;
                                                                                                                            font-family: rale">
                    <%=profile%>
                </button>
                <ul class="dropdown-menu">
                    <li><a tabindex="-1" href="/roles_web/login?profile">Ir a perfil</a></li>
                    <li><a tabindex="-1" href="/roles_web/login?logout">Salir</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>