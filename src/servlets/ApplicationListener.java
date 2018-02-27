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
        propiedadesBaseDatos.getProperty("servidor", servidor);
        propiedadesBaseDatos.getProperty("puerto", puerto);
        propiedadesBaseDatos.getProperty("basededatos", baseDatos);
        propiedadesBaseDatos.getProperty("user", user);
        propiedadesBaseDatos.getProperty("password", pass);

        Utilidad.getIntancia().setPropidadesBaseDatos(propiedadesBaseDatos);

    }



}
