package servlets;

import dao.UserDAO;
import models.User;
import utilidad.Utilidad;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProbandoServlet", urlPatterns = "/probando")
public class ProbandoServlet extends HttpServlet {

    private void processRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.getUsers();
        request.setAttribute("dameLista", users);

        RequestDispatcher dispatcher = getServletContext().getNamedDispatcher("IndexServlet");
        dispatcher.include(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequests(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequests(req, resp);
    }

}
