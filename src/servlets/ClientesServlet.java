package servlets;

import dao.ClienteDAO;
import models.Cliente;
import utilidad.Const;
import utilidad.Fecha;
import utilidad.SessionUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/clientes")
public class ClientesServlet extends HttpServlet {

    ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SessionUtility.isExpiry(req, resp)) return;

        List<Cliente> clientes = clienteDAO.findAll();
        req.setAttribute(Const.CLIENTES, clientes);
        req.getRequestDispatcher("clientes.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SessionUtility.isExpiry(req, resp)) return;

        String clienteId = req.getParameter(Const.ID);
        if (clienteId != null) {
            req.getSession().setAttribute(Const.FECHA, Fecha.getFechaActualInit());
            req.getSession().setAttribute(Const.CLIENTE_ID, Integer.valueOf(clienteId));
            resp.sendRedirect("rol/cliente");
            return;
        }
    }

}
