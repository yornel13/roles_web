package servlets;

import dao.ClienteDAO;
import models.Cliente;
import models.RolCliente;
import utilidad.Fecha;

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
        List<Cliente> clientes = clienteDAO.findAll();
        req.setAttribute("clients", clientes);
        req.getRequestDispatcher("clientes.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clienteId = req.getParameter("id");
        if (clienteId != null) {
            req.getSession().setAttribute("fecha", Fecha.getFechaActual().withDay("01").minusMonths(1).getFecha());
            req.getSession().setAttribute("clienteId", clienteId);
            resp.sendRedirect("rol/cliente");
            return;
        }
    }

}
