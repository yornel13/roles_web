<%@ page import="models.User" %>
<%@ page import="utilidad.Const" %>
<%
    User user = null;
    String username = null;
    String profile = null;
    String userID = null;
    Cookie[] cookies = request.getCookies();
    if(cookies != null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(Const.USERNAME)) {
                username = cookie.getValue();
            }
        }
    }

    if(username != null) {
        user = (User) request.getSession().getAttribute(Const.USER);
        if(user != null){
            userID = user.getId().toString();
        }
    }
    if (user == null || !user.getUsername().equals(username)) user = null;

    if (user == null) response.sendRedirect("/roles_web/index");
    else profile = user.getTipoText() + ": " + user.getNombre() + " " + user.getApellido();

%>
<header>
    <div class="ancho">
        <div class="logo">
            <p><a href="">Control Guardias</a></p>
        </div>

        <form>
        <form  method="post" action="login">
            <div>
                <button name="logout" class="fa fa-sign-out">
                    <span class="tooltiptext">Salir</span>
                </button>
            </div>
        </form>

        <form  class="usuario"  action="admin">
                <button id="profile_button" name="update_profile" style=" width: auto;" value="<%=userID%>">
                    <a id="profile_text" style="font-size: medium;" ><%=profile%></a>
                </button>
        </form>

        </form>
    </div>
</header>