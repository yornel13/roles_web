package servlets;

import dao.RolClienteDAO;
import dao.RolIndividualDAO;
import models.RolCliente;
import models.RolIndividual;
import utilidad.Fecha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/rol/individual")
public class RolIndividualServlet extends HttpServlet {

    RolClienteDAO rolClienteDAO = new RolClienteDAO();
    RolIndividualDAO rolIndividualDAO = new RolIndividualDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String searchId = req.getParameter("id");
        if (searchId != null) {
            Integer rolId = Integer.valueOf(searchId);
            RolIndividual rolIndividual = rolIndividualDAO.findById(rolId);
            req.setAttribute("rol", rolIndividual);
            req.getRequestDispatcher("rol_individual.jsp").forward(req, resp);
            return;
        }

        String fecha = (String) req.getSession().getAttribute("fecha");
        Integer empleadoId = Integer.valueOf((String) req.getSession().getAttribute("empleadoId"));

        List<RolCliente> rolesClients = rolClienteDAO.findAllByFechaAndUsuarioId(fecha, empleadoId);
        RolIndividual rolIndividual = rolIndividualDAO.findByFechaAndUsuarioId(fecha, empleadoId);
        req.setAttribute("rolIndividual", rolIndividual);
        req.setAttribute("roles", rolesClients);
        req.setAttribute("mes",  new Fecha(fecha).getMonthName()+" "+new Fecha(fecha).getAnoInt());
        req.getSession().setAttribute("rolesClients", rolesClients);
        req.getRequestDispatcher("roles_empleado.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String next = req.getParameter("next");
        String previous = req.getParameter("previous");

        if (next != null) {

            String fecha = (String) req.getSession().getAttribute("fecha");
            fecha = new Fecha(fecha).plusMonths(1).toString();
            Integer empleadoId = Integer.valueOf((String) req.getSession().getAttribute("empleadoId"));
            List<RolCliente> rolesClients = rolClienteDAO.findAllByFechaAndUsuarioId(fecha, empleadoId);
            RolIndividual rolIndividual = rolIndividualDAO.findByFechaAndUsuarioId(fecha, empleadoId);
            req.setAttribute("rolIndividual", rolIndividual);
            req.setAttribute("roles", rolesClients);
            req.setAttribute("mes",  new Fecha(fecha).getMonthName()+" "+new Fecha(fecha).getAnoInt());
            req.getSession().setAttribute("rolesClients", rolesClients);
            req.getSession().setAttribute("fecha", fecha);
            req.getRequestDispatcher("roles_empleado.jsp").forward(req, resp);

        } else if (previous != null) {

            String fecha = (String) req.getSession().getAttribute("fecha");
            fecha = new Fecha(fecha).minusMonths(1).toString();
            Integer empleadoId = Integer.valueOf((String) req.getSession().getAttribute("empleadoId"));
            List<RolCliente> rolesClients = rolClienteDAO.findAllByFechaAndUsuarioId(fecha, empleadoId);
            RolIndividual rolIndividual = rolIndividualDAO.findByFechaAndUsuarioId(fecha, empleadoId);
            req.setAttribute("rolIndividual", rolIndividual);
            req.setAttribute("roles", rolesClients);
            req.setAttribute("mes",  new Fecha(fecha).getMonthName()+" "+new Fecha(fecha).getAnoInt());
            req.getSession().setAttribute("rolesClients", rolesClients);
            req.getSession().setAttribute("fecha", fecha);
            req.getRequestDispatcher("roles_empleado.jsp").forward(req, resp);

        }
    }

}
