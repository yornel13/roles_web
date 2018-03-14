package utilidad;

import models.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionUtility {

    public static Integer expiry = new Integer(15*60);

    public static Boolean save(User user, HttpServletRequest request, HttpServletResponse response) {
        Cookie loginCookie = new Cookie(Const.USERNAME, user.getUsername());
        loginCookie.setMaxAge(expiry);
        response.addCookie(loginCookie);
        request.getSession().setAttribute(Const.USER, user);

        System.out.println(request.getServletContext().getSessionTimeout());
        return true;
    }

    public static String getProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        if (user == null) response.sendRedirect("/roles_web/login?expiry");
        else profile = user.getProfile();

        return profile;
    }

    public static User getUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = null;
        String username = null;
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
        if (user == null) {
            response.sendRedirect("/roles_web/login?expiry");
            return null;
        }

        return user;
    }

    public static Boolean isActive(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = null;
        String username = null;
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
        if (user == null) response.sendRedirect("/roles_web/login?expiry");
        else return true;

        System.err.println("Session is expired");
        return false;
    }

    public static Boolean isExpiry(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return !isActive(request, response);
    }

    public static Boolean remove(HttpServletRequest request, HttpServletResponse response) {

        Cookie loginCookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(Const.USERNAME)){
                    loginCookie = cookie;
                    break;
                }
            }
        }
        if(loginCookie != null){
            loginCookie.setMaxAge(0);
            response.addCookie(loginCookie);
            return true;
        }
        return false;
    }
}
