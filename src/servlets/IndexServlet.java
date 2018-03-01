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

@WebServlet(name = "IndexServlet", urlPatterns = "/indexLogin")
public class IndexServlet extends HttpServlet {

    private void processRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Llamo servlet");
        String username = request.getParameter("username");
        String password = request.getParameter("password");



        if(!username.isEmpty() && !password.isEmpty()){
            System.out.println("Campos requridos llenos");
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getRegisteredUser(username, password);

            if(user != null){
                System.out.println("Usuario es diferente de nulo");
                if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    System.out.println("Usuario paso el login es de tipo: " + user.getTipo());
                    switch (user.getTipo()) {
                        case "A":
                            System.out.println("entro en Admin");
                            Utilidad.getIntancia().irAPagina(request, response, getServletContext(), "/rol_cliente.jsp");
                            break;
                        case "E":
                            response.sendRedirect("rol_individual.jsp");
                            break;
                    }
                }
            }
            else {
                System.out.println("El usuario no exite");
                response.sendRedirect("index.jsp");
            }

        }

        else {
            System.out.println("Los campos entan vacio");
        }





    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequests(req, resp);
        System.out.println("Paso en doGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequests(req, resp);
        System.out.println("Paso en doPOst");
    }
}
