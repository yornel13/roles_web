package servlets;

import dao.PagoMesDAO;
import dao.RolClienteDAO;
import dao.RolIndividualDAO;
import models.PagoMes;
import models.RolCliente;
import models.RolIndividual;
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

@WebServlet("/rol/individual")
public class RolIndividualServlet extends HttpServlet {

    private RolClienteDAO rolClienteDAO = new RolClienteDAO();
    private RolIndividualDAO rolIndividualDAO = new RolIndividualDAO();
    private PagoMesDAO pagoMesDAO = new PagoMesDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SessionUtility.isExpiry(req, resp)) return;

        String searchId = req.getParameter(Const.ID);
        if (searchId != null) {
            Integer rolId = Integer.valueOf(searchId);
            RolIndividual rolIndividual = rolIndividualDAO.findById(rolId);
            req.setAttribute(Const.ROL_INDIVIDUAL, rolIndividual);
            req.getRequestDispatcher("rol_individual.jsp").forward(req, resp);
            return;
        }

        String fecha = (String) req.getSession().getAttribute(Const.FECHA);
        Integer empleadoId = (Integer) req.getSession().getAttribute(Const.EMPLEADO_ID);

        List<RolCliente> rolesClients = rolClienteDAO.findAllByFechaAndUsuarioId(fecha, empleadoId);
        RolIndividual rolIndividual = rolIndividualDAO.findByFechaAndUsuarioId(fecha, empleadoId);
        if (rolIndividual != null) {
            PagoMes pagoMes = pagoMesDAO.findByRolIndividualId(rolIndividual.getId());
            req.setAttribute(Const.PAGO_MES, pagoMes);
        }
        req.setAttribute(Const.ROL_INDIVIDUAL, rolIndividual);
        req.setAttribute(Const.ROLES_CLIENTE, rolesClients);
        req.setAttribute(Const.FILTER_MONTH,  new Fecha(fecha).getMonthSelect());
        req.getSession().setAttribute(Const.ROLES_CLIENTE, rolesClients);
        req.getRequestDispatcher("roles_empleado.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SessionUtility.isExpiry(req, resp)) return;

        String next = req.getParameter("next");
        String previous = req.getParameter("previous");

        if (next != null) {

            String fecha = (String) req.getSession().getAttribute(Const.FECHA);
            fecha = new Fecha(fecha).plusMonths(1).toString();
            Integer empleadoId = (Integer) req.getSession().getAttribute(Const.EMPLEADO_ID);
            List<RolCliente> rolesClients = rolClienteDAO.findAllByFechaAndUsuarioId(fecha, empleadoId);
            RolIndividual rolIndividual = rolIndividualDAO.findByFechaAndUsuarioId(fecha, empleadoId);
            if (rolIndividual != null) {
                PagoMes pagoMes = pagoMesDAO.findByRolIndividualId(rolIndividual.getId());
                req.setAttribute(Const.PAGO_MES, pagoMes);
            }
            req.setAttribute(Const.ROL_INDIVIDUAL, rolIndividual);
            req.setAttribute(Const.ROLES_CLIENTE, rolesClients);
            req.setAttribute(Const.FILTER_MONTH,  new Fecha(fecha).getMonthSelect());
            req.getSession().setAttribute(Const.ROLES_CLIENTE, rolesClients);
            req.getSession().setAttribute(Const.FECHA, fecha);
            req.getRequestDispatcher("roles_empleado.jsp").forward(req, resp);

        } else if (previous != null) {

            String fecha = (String) req.getSession().getAttribute(Const.FECHA);
            fecha = new Fecha(fecha).minusMonths(1).toString();
            Integer empleadoId = (Integer) req.getSession().getAttribute(Const.EMPLEADO_ID);
            List<RolCliente> rolesClients = rolClienteDAO.findAllByFechaAndUsuarioId(fecha, empleadoId);
            RolIndividual rolIndividual = rolIndividualDAO.findByFechaAndUsuarioId(fecha, empleadoId);
            if (rolIndividual != null) {
                PagoMes pagoMes = pagoMesDAO.findByRolIndividualId(rolIndividual.getId());
                req.setAttribute(Const.PAGO_MES, pagoMes);
            }
            req.setAttribute(Const.ROL_INDIVIDUAL, rolIndividual);
            req.setAttribute(Const.ROLES_CLIENTE, rolesClients);
            req.setAttribute(Const.FILTER_MONTH,  new Fecha(fecha).getMonthSelect());
            req.getSession().setAttribute(Const.ROLES_CLIENTE, rolesClients);
            req.getSession().setAttribute(Const.FECHA, fecha);
            req.getRequestDispatcher("roles_empleado.jsp").forward(req, resp);

        }
    }

}
