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



    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post admin");

        /**Save user*/
        String nombre =  request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cedula = request.getParameter("cedula");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String tipo = request.getParameter("tipo");
        String activo = request.getParameter("activo");

        if((nombre != null && apellido != null) && (username != null && password != null)){
            UserDAO userDAO = new UserDAO();
            userDAO.addUser(nombre, apellido, cedula, username, password, tipo, activo);

            System.out.println("Usuario creado con exito!");
            response.sendRedirect("admin.jsp");
        }






        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get admin");
        UserDAO userDAO = new UserDAO();
        /**Selected user from table*/
        String useID = request.getParameter("user_id");
        if(useID != null){
            Integer id = Integer.valueOf(useID);
            User user = userDAO.getUserByID(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
            return;
        }

        /**Get and load users to table **/
        List<User> listaUsuario = userDAO.getUsers();
        request.setAttribute("listaUsuario", listaUsuario);
        request.getRequestDispatcher("admin-user-table.jsp").forward(request,response);


        processRequest(request, response);
    }
}
