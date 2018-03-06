package servlets;

import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class AdminServlet extends HttpServlet {


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cedula = request.getParameter("cedula");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String tipo = request.getParameter("tipo");
        String activo = request.getParameter("activo");

        System.out.println("Los valores son Nombre: "+nombre+"  Apellido: "+apellido+"  Cedula: "+
                cedula+"  username: "+username+"  Password: "+password+"  Nivel: "+tipo+" Activo: "+activo);

        UserDAO userDAO = new UserDAO();
        Boolean booleanReturned = userDAO.addUser(nombre, apellido, cedula, username, password, tipo, activo);
        
        System.out.println("Usuario creado con exito!");
        response.sendRedirect("admin.jsp");




    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
