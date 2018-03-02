package servlets;

import dao.RolClienteDAO;
import dao.UserDAO;
import models.RolCliente;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/rol_cliente")
public class RolClienteServlet extends HttpServlet {

    private void processRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RolClienteDAO rolClienteDAO = new RolClienteDAO();
        List<RolCliente> rolClientes = rolClienteDAO.findAllByFechaAndClienteId("20170201", 6);
        request.setAttribute("dameLista", rolClientes);

        RequestDispatcher dispatcher = getServletContext().getNamedDispatcher("IndexServlet");
        dispatcher.include(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequests(req, resp);
        String str1 = req.getParameter("next");
        System.out.println("You clicked do get " + str1 + " submit button<br>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequests(req, resp);
        String str1 = req.getParameter("next");
        System.out.println("You clicked do post " + str1 + " submit button<br>");
    }

}