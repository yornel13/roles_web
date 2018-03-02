package servlets;

import dao.UserDAO;
import models.User;
import utilidad.Utilidad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private void processRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Llamo servlet");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(!username.isEmpty() && !password.isEmpty()){
            System.out.println("Campos requeridos llenos");
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getRegisteredUser(username, password);

            System.out.println("El username es: "+user.getUsername());

            if(user.getUsername() != null && user.getPassword() != null){
                System.out.println("Usuario es diferente de nulo");
                if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    System.out.println("Usuario paso el login es de tipo: " + user.getTipo());
                    switch (user.getTipo()) {
                        case "A":
                            Utilidad.getIntancia().irAPagina(request, response, getServletContext(), "/rol_cliente.jsp");
                            response.sendRedirect("rol_cliente.jsp");
                            System.out.println("entro en Admin");
                            break;
                        case "E":
                            System.out.println("Entro en empleado");
                            response.sendRedirect("rol_individual.jsp");
                            break;
                    }
                }
            }
            else {
                System.out.println("El usuario no exite o es nulo");
                response.sendRedirect("index.jsp");
            }

        }
        else {
            System.out.println("Se requieren los campos llenos");
            response.sendRedirect("index.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequests(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("goLogin") != null) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            //processRequests(req, resp);
        }
    }
}