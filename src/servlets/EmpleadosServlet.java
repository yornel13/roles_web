package servlets;

import dao.PagoMesDAO;
import dao.RolClienteDAO;
import dao.RolIndividualDAO;
import models.*;
import utilidad.Const;
import utilidad.Fecha;
import utilidad.SessionUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@WebServlet("/rol/empleados")
public class EmpleadosServlet  extends HttpServlet {

    private RolIndividualDAO rolIndividualDAO = new RolIndividualDAO();
    private RolClienteDAO rolClienteDAO = new RolClienteDAO();
    private PagoMesDAO pagoMesDAO = new PagoMesDAO();
    private HttpServletRequest req;
    private HttpServletResponse resp;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
        if (SessionUtility.isExpiry(req, resp)) return;

        String empleadoId = req.getParameter(Const.ID);
        String rolClienteId = req.getParameter(Const.ROL_CLIENTE_ID);
        if (empleadoId != null) {
            String fecha = (String) req.getSession().getAttribute(Const.FECHA);
            List<RolCliente> rolesClients = rolClienteDAO.findAllByFechaAndUsuarioId(fecha, Integer.valueOf(empleadoId));
            RolIndividual rolIndividual = rolIndividualDAO.findByFechaAndUsuarioId(fecha, Integer.valueOf(empleadoId));
            if (rolIndividual != null) {
                PagoMes pagoMes = pagoMesDAO.findByRolIndividualId(rolIndividual.getId());
                req.setAttribute(Const.PAGO_MES, pagoMes);
            }
            req.setAttribute(Const.ROL_INDIVIDUAL, rolIndividual);
            req.setAttribute(Const.ROLES_CLIENTE, rolesClients);
            req.getRequestDispatcher("rol_empleado.jsp").forward(req, resp);
            return;
        } else if (rolClienteId != null) {
            Integer rolId = Integer.valueOf(rolClienteId);
            RolCliente rolCliente = rolClienteDAO.findById(rolId);
            if (rolCliente == null) {
                resp.sendRedirect("/error");
            } else {
                req.setAttribute(Const.ROL_CLIENTE, rolCliente);
                req.getRequestDispatcher("rol_cliente.jsp").forward(req, resp);
            }
            return;
        }

        String fecha = (String) req.getSession().getAttribute(Const.FECHA);
        List<RolIndividual> rolesIndividual = getRolesIndividual(fecha);
        req.setAttribute(Const.ROLES_INDIVIDUAL, rolesIndividual);
        req.setAttribute(Const.FILTER_MONTH,  Fecha.getFechaCorta(fecha));
        req.getSession().setAttribute(Const.ROLES_INDIVIDUAL, rolesIndividual);
        req.getSession().setAttribute(Const.FECHA, fecha);
        req.getRequestDispatcher("empleados.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
        if (SessionUtility.isExpiry(req, resp)) return;

        String next = req.getParameter("next");
        String previous = req.getParameter("previous");
        String search = req.getParameter("search");

        if (next != null) {
            String fecha = (String) req.getSession().getAttribute(Const.FECHA);
            fecha = new Fecha(fecha).plusMonths(1).toString();
            List<RolIndividual> rolesIndividual = getRolesIndividual(fecha);
            req.setAttribute(Const.ROLES_INDIVIDUAL, rolesIndividual);
            req.setAttribute(Const.FILTER_MONTH,  Fecha.getFechaCorta(fecha));
            req.getSession().setAttribute(Const.ROLES_INDIVIDUAL, rolesIndividual);
            req.getSession().setAttribute(Const.FECHA, fecha);
            req.getRequestDispatcher("empleados.jsp").forward(req, resp);

        } else if (previous != null) {
            String fecha = (String) req.getSession().getAttribute(Const.FECHA);
            fecha = new Fecha(fecha).minusMonths(1).toString();
            List<RolIndividual> rolesIndividual = getRolesIndividual(fecha);
            req.setAttribute(Const.ROLES_INDIVIDUAL, rolesIndividual);
            req.setAttribute(Const.FILTER_MONTH,  Fecha.getFechaCorta(fecha));
            req.getSession().setAttribute(Const.ROLES_INDIVIDUAL, rolesIndividual);
            req.getSession().setAttribute(Const.FECHA, fecha);
            req.getRequestDispatcher("empleados.jsp").forward(req, resp);

        } else if (search != null) {
            String fecha = (String) req.getSession().getAttribute(Const.FECHA);
            fecha = new Fecha(fecha).toString();
            String searchDate = req.getParameter(Const.SEARCH_TEXT);

            Predicate<RolIndividual> fullNamePredicate = p -> p.getEmpleado().toLowerCase().contains(searchDate.toLowerCase());
            Predicate<RolIndividual> dniPredicate = p -> p.getCedula().toLowerCase().contains(searchDate.toLowerCase());
            List<RolIndividual> rolesFilter = ((List<RolIndividual>) req.getSession().getAttribute(Const.ROLES_INDIVIDUAL))
                    .stream().filter(fullNamePredicate.or(dniPredicate)).collect(Collectors.toList());

            req.setAttribute(Const.FILTER_MONTH,  Fecha.getFechaCorta(fecha));
            req.setAttribute(Const.ROLES_INDIVIDUAL, rolesFilter);
            req.setAttribute(Const.FILTER_DATA, searchDate);
            req.getRequestDispatcher("empleados.jsp").forward(req, resp);
        }
    }

    private  List<RolIndividual> getRolesIndividual(String fecha) throws IOException {

        List<RolIndividual> rolesIndividual = null;

        User user = null;
        String username = null;
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(Const.USERNAME)) {
                    username = cookie.getValue();
                }
            }
        }
        if(username != null) {
            user = (User) req.getSession().getAttribute(Const.USER);
            if (user == null || !user.getUsername().equals(username)) user = null;
        }
        if (user != null) {
            switch (user.getType()) {
                case EMPRESA:
                    Empresa empresa = (Empresa) req.getSession().getAttribute(Const.DATA_USER);
                    rolesIndividual = rolIndividualDAO.findByFechaAndEmpresaId(fecha, empresa.getId());
                    break;
            }
        }
        if (rolesIndividual == null) {
            System.err.println("user invalid");
            resp.sendRedirect("/login");
        }
        return rolesIndividual;
    }

}