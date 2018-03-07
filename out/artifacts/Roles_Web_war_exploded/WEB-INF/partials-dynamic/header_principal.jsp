<%@ page import="models.User" %>
<%@ page import="utilidad.Const" %>
<%
    User user = null;
    String username = null;
    String profile = null;
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
        if (user == null || !user.getUsername().equals(username)) user = null;
    }
    if (user == null) response.sendRedirect("/roles_web/index");
    else profile = user.getTipoText()+": "+user.getNombre()+" "+user.getApellido();
%>
<header>
    <div class="ancho">
        <div class="logo">
            <p><a href="">Control Guardias</a></p>
        </div>
        <form method="post" action="/roles_web/login" >
            <div class="usuario">
                <p><a><%=profile%></a></p>
                <button name="logout" class="fa fa-sign-out">
                    <span class="tooltiptext">Salir</span>
                </button>
            </div>
        </form>
    </div>
</header>