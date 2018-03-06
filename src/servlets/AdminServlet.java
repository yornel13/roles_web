package servlets;

import dao.UserDAO;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDAO userDAO = new UserDAO();
        List<User> listaUsuario = userDAO.getUsers();
        request.setAttribute("listaUsuario", listaUsuario);
        request.getRequestDispatcher("admin-user-table.jsp").forward(request,response);
/*
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cedula = request.getParameter("cedula");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String tipo = request.getParameter("tipo");
        String activo = request.getParameter("activo");

        System.out.println("Los valores son Nombre: "+nombre+"  Apellido: "+apellido+"  Cedula: "+
                cedula+"  username: "+username+"  Password: "+password+"  Nivel: "+tipo+" Activo: "+activo);


        Boolean booleanReturned = userDAO.addUser(nombre, apellido, cedula, username, password, tipo, activo);

        System.out.println("Usuario creado con exito!");
        response.sendRedirect("admin.jsp");

*/


    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post admin");
           processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get admin");
        processRequest(request, response);
    }
}
