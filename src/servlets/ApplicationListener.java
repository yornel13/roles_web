package servlets;


import utilidad.Utilidad;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Properties;

@WebListener
public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String servidor = sce.getServletContext().getInitParameter("servidor");
        String puerto = sce.getServletContext().getInitParameter("puerto");
        String baseDatos = sce.getServletContext().getInitParameter("basededatos");
        String user = sce.getServletContext().getInitParameter("user");
        String pass = sce.getServletContext().getInitParameter("password");

        Properties propiedadesBaseDatos = new Properties();
        propiedadesBaseDatos.setProperty("servidor", servidor);
        propiedadesBaseDatos.setProperty("puerto", puerto);
        propiedadesBaseDatos.setProperty("basededatos", baseDatos);
        propiedadesBaseDatos.setProperty("user", user);
        propiedadesBaseDatos.setProperty("password", pass);

        Utilidad.getIntancia().setPropidadesBaseDatos(propiedadesBaseDatos);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }


}
