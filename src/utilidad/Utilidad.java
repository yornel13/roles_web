package utilidad;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class Utilidad {


    private static  final Utilidad intancia = new Utilidad();
    private Properties propidadesBaseDatos;


    private Utilidad(){}

    public static Utilidad getIntancia(){
        return intancia;
    }

    public void setPropidadesBaseDatos(Properties propidadesBaseDatos) {
        this.propidadesBaseDatos = propidadesBaseDatos;
    }

    public Properties getPropidadesBaseDatos(){
        return propidadesBaseDatos;
    }

    public void irAPagina(HttpServletRequest request, HttpServletResponse response, ServletContext context, String url)
            throws ServletException, IOException{
        RequestDispatcher despachador = context.getRequestDispatcher(url);
        despachador.forward(request, response);
    }
}
