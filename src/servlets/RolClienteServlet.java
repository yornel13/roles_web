package servlets;

import dao.ControlExtraDAO;
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

@WebServlet("/rol/cliente")
public class RolClienteServlet extends HttpServlet {

    private RolIndividualDAO rolIndividualDAO = new RolIndividualDAO();
    private RolClienteDAO rolClienteDAO = new RolClienteDAO();
    private PagoMesDAO pagoMesDAO = new PagoMesDAO();
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private Integer empleadoId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
        if (SessionUtility.isExpiry(req, resp)) return;
        System.out.println("ROLCLIente SERVELl---------- GET");
        String searchId = req.getParameter(Const.ID);
        String rolClienteId = req.getParameter(Const.ROL_CLIENTE_ID);
        if (searchId != null) {
            Integer rolId = Integer.valueOf(searchId);
            RolCliente rolCliente = rolClienteDAO.findById(rolId);
            if (rolCliente == null) {
                resp.sendRedirect("/error");
            } else {
                List<RolCliente> rolesClients = rolClienteDAO.findAllByFechaAndUsuarioId(rolCliente.getInicio(), rolCliente.getUsuarioId());
                RolIndividual rolIndividual = rolIndividualDAO.findByFechaAndUsuarioId(rolCliente.getInicio(), rolCliente.getUsuarioId());
                if (rolIndividual != null) {
                    PagoMes pagoMes = pagoMesDAO.findByRolIndividualId(rolIndividual.getId());
                    req.setAttribute(Const.PAGO_MES, pagoMes);
                }
                req.setAttribute(Const.ROL_INDIVIDUAL, rolIndividual);
                req.setAttribute(Const.ROLES_CLIENTE, rolesClients);
                req.setAttribute(Const.ROL_CLIENTE, rolCliente);
                req.getRequestDispatcher("rol_empleado.jsp").forward(req, resp);
            }
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
        Integer clienteId = (Integer) req.getSession().getAttribute(Const.CLIENTE_ID);
        List<RolCliente> rolesCliente = getRolesCliente(fecha, clienteId);
        req.setAttribute(Const.ROLES_CLIENTE, rolesCliente);
        req.setAttribute(Const.FILTER_MONTH,  new Fecha(fecha).getMonthSelect());
        req.getSession().setAttribute(Const.ROLES_CLIENTE, rolesCliente);
        req.getRequestDispatcher("roles_cliente.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
        if (SessionUtility.isExpiry(req, resp)) return;

        String next = req.getParameter("next");
        String previous = req.getParameter("previous");
        String search = req.getParameter("search");
        String monthSelect = req.getParameter("month");

        String schedule = req.getParameter("schedule");
        System.out.println("Entro POST RolIndividual horario ="+schedule);

        if(schedule != null){
            System.out.println("SCHEDULE pasoo");

            RolIndividual rolIndividual = (RolIndividual) req.getSession().getAttribute(Const.PRINT_RI);
            List<RolCliente> roles = (List) req.getSession().getAttribute(Const.PRINT_RL);

            List<ControlExtras> controlExtras = new ControlExtraDAO().findAllByEmpleadoIdByDeterminateTime(rolIndividual.getUsuarioId(),
                    new Fecha(rolIndividual.getInicio()).minusDays(7).getDate(),
                    new Fecha(rolIndividual.getFinalizo()).minusDays(7).getDate());

            req.setAttribute(Const.ROL_INDIVIDUAL, rolIndividual);
            req.setAttribute(Const.CONTROL_EXTRA, controlExtras);
            req.setAttribute(Const.ROL_CLIENTE, roles);
            req.getRequestDispatcher("horario.jsp").forward(req, resp);
        }

        if (next != null) {

            String fecha = (String) req.getSession().getAttribute(Const.FECHA);
            fecha = new Fecha(fecha).plusMonths(1).toString();
            Integer clienteId = (Integer) req.getSession().getAttribute(Const.CLIENTE_ID);
            List<RolCliente> rolesCliente = getRolesCliente(fecha, clienteId);
            req.setAttribute(Const.ROLES_CLIENTE, rolesCliente);
            req.setAttribute(Const.FILTER_MONTH,  new Fecha(fecha).getMonthSelect());
            req.getSession().setAttribute(Const.ROLES_CLIENTE, rolesCliente);
            req.getSession().setAttribute(Const.FECHA, fecha);
            req.getRequestDispatcher("roles_cliente.jsp").forward(req, resp);

        } else if (previous != null) {

            String fecha = (String) req.getSession().getAttribute(Const.FECHA);
            fecha = new Fecha(fecha).minusMonths(1).toString();
            Integer clienteId = (Integer) req.getSession().getAttribute(Const.CLIENTE_ID);
            List<RolCliente> rolesCliente = getRolesCliente(fecha, clienteId);
            req.setAttribute(Const.ROLES_CLIENTE, rolesCliente);
            req.setAttribute(Const.FILTER_MONTH,  new Fecha(fecha).getMonthSelect());
            req.getSession().setAttribute(Const.ROLES_CLIENTE, rolesCliente);
            req.getSession().setAttribute(Const.FECHA, fecha);
            req.getRequestDispatcher("roles_cliente.jsp").forward(req, resp);

        } else if (search != null) {

            String fecha = (String) req.getSession().getAttribute(Const.FECHA);
            fecha = new Fecha(fecha).toString();
            String searchDate = req.getParameter(Const.SEARCH_TEXT);

            Predicate<RolCliente> fullNamePredicate = p -> p.getEmpleado().toLowerCase().contains(searchDate.toLowerCase());
            Predicate<RolCliente> dniPredicate = p -> p.getCedula().toLowerCase().contains(searchDate.toLowerCase());
            Predicate<RolCliente> companyPredicate = p -> p.getEmpresa().toLowerCase().contains(searchDate.toLowerCase());
            List<RolCliente> rolesFilter = ((List<RolCliente>) req.getSession().getAttribute(Const.ROLES_CLIENTE))
                    .stream().filter(
                            fullNamePredicate.or(dniPredicate).or(companyPredicate)
            ).collect(Collectors.toList());

            req.setAttribute(Const.FILTER_MONTH,  new Fecha(fecha).getMonthSelect());
            req.setAttribute(Const.ROLES_CLIENTE, rolesFilter);
            req.setAttribute(Const.FILTER_DATA, searchDate);
            req.getRequestDispatcher("roles_cliente.jsp").forward(req, resp);
        } else if (monthSelect != null) {

            String fecha = Fecha.fromMonthSelect(monthSelect).getFecha();
            Integer clienteId = (Integer) req.getSession().getAttribute(Const.CLIENTE_ID);
            List<RolCliente> rolesCliente = getRolesCliente(fecha, clienteId);
            req.setAttribute(Const.ROLES_CLIENTE, rolesCliente);
            req.setAttribute(Const.FILTER_MONTH,  new Fecha(fecha).getMonthSelect());
            req.getSession().setAttribute(Const.ROLES_CLIENTE, rolesCliente);
            req.getSession().setAttribute(Const.FECHA, fecha);
            req.getRequestDispatcher("roles_cliente.jsp").forward(req, resp);
        }
    }

    private  List<RolCliente> getRolesCliente(String fecha, Integer clienteId) throws IOException {

        List<RolCliente> rolesCliente = null;

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
                    rolesCliente = rolClienteDAO.findAllByFechaAndClienteId(fecha, clienteId, empresa.getId());
                    break;
                case CLIENTE:
                    Cliente cliente = (Cliente) req.getSession().getAttribute(Const.DATA_USER);
                    rolesCliente = rolClienteDAO.findAllByFechaAndClienteId(fecha, cliente.getId());
                    break;
            }
        }
        if (rolesCliente == null) {
            System.err.println("user invalid");
            resp.sendRedirect("/login");
        }
        return rolesCliente;
    }
}