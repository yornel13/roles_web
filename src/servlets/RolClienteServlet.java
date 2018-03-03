package servlets;

import dao.RolClienteDAO;
import dao.UserDAO;
import models.RolCliente;
import models.User;
import utilidad.Fecha;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@WebServlet("/rol_cliente")
public class RolClienteServlet extends HttpServlet {

    RolClienteDAO rolClienteDAO = new RolClienteDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fecha = (String) req.getSession().getAttribute("fecha");
        Integer clienteId = Integer.valueOf((String) req.getSession().getAttribute("clienteId"));

        List<RolCliente> rolClientes = rolClienteDAO.findAllByFechaAndClienteId(fecha, clienteId);
        req.setAttribute("dameLista", rolClientes);
        req.setAttribute("mes",  new Fecha(fecha).getMonthName()+" "+new Fecha(fecha).getAnoInt());
        req.getSession().setAttribute("rolClientes", rolClientes);
        req.getRequestDispatcher("rol_cliente.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String next = req.getParameter("next");
        String previous = req.getParameter("previous");
        String search = req.getParameter("search");
        if (next != null) {

            String fecha = (String) req.getSession().getAttribute("fecha");
            fecha = new Fecha(fecha).plusMonths(1).toString();
            Integer clienteId = Integer.valueOf((String) req.getSession().getAttribute("clienteId"));
            List<RolCliente> rolClientes = rolClienteDAO.findAllByFechaAndClienteId(fecha, clienteId);
            req.setAttribute("dameLista", rolClientes);
            req.setAttribute("mes",  new Fecha(fecha).getMonthName()+" "+new Fecha(fecha).getAnoInt());
            req.getSession().setAttribute("rolClientes", rolClientes);
            req.getSession().setAttribute("fecha", fecha);
            req.getRequestDispatcher("rol_cliente.jsp").forward(req, resp);

        } else if (previous != null) {

            String fecha = (String) req.getSession().getAttribute("fecha");
            fecha = new Fecha(fecha).minusMonths(1).toString();
            Integer clienteId = Integer.valueOf((String) req.getSession().getAttribute("clienteId"));
            List<RolCliente> rolClientes = rolClienteDAO.findAllByFechaAndClienteId(fecha, clienteId);
            req.setAttribute("dameLista", rolClientes);
            req.setAttribute("mes",  new Fecha(fecha).getMonthName()+" "+new Fecha(fecha).getAnoInt());
            req.getSession().setAttribute("rolClientes", rolClientes);
            req.getSession().setAttribute("fecha", fecha);
            req.getRequestDispatcher("rol_cliente.jsp").forward(req, resp);

        } else if (search != null) {

            String fecha = (String) req.getSession().getAttribute("fecha");
            fecha = new Fecha(fecha).toString();
            String searchDate = req.getParameter("text");

            Predicate<RolCliente> fullNamePredit = p -> p.getEmpleado().toLowerCase().contains(searchDate.toLowerCase());
            Predicate<RolCliente> cedulaPredit = p -> p.getCedula().toLowerCase().contains(searchDate.toLowerCase());
            Predicate<RolCliente> empresaPredit = p -> p.getEmpresa().toLowerCase().contains(searchDate.toLowerCase());
            List<RolCliente> rolesFilter = ((List<RolCliente>) req.getSession().getAttribute("rolClientes"))
                    .stream().filter(
                            fullNamePredit.or(cedulaPredit).or(empresaPredit)
            ).collect(Collectors.toList());

            req.setAttribute("mes",  new Fecha(fecha).getMonthName()+" "+new Fecha(fecha).getAnoInt());
            req.setAttribute("dameLista", rolesFilter);
            req.setAttribute("searchDate", searchDate);
            req.getRequestDispatcher("rol_cliente.jsp").forward(req, resp);
        }

    }

}