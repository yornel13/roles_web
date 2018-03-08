package servlets;

import dao.RolIndividualDAO;
import models.Empresa;
import models.RolIndividual;
import models.User;
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
    private HttpServletRequest req;
    private HttpServletResponse resp;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
        if (SessionUtility.isExpiry(req, resp)) return;

        String empleadoId = req.getParameter(Const.ID);
        if (empleadoId != null) {
            String fecha = (String) req.getSession().getAttribute(Const.FECHA);
            req.getSession().setAttribute(Const.FECHA, fecha);
            req.getSession().setAttribute(Const.EMPLEADO_ID, Integer.valueOf(empleadoId));
            resp.sendRedirect("individual");
            return;
        }

        String fecha = (String) req.getSession().getAttribute(Const.FECHA);
        List<RolIndividual> rolesIndividual = getRolesIndividual(fecha);
        req.setAttribute(Const.ROLES_INDIVIDUAL, rolesIndividual);
        req.setAttribute(Const.FILTER_MONTH,  Fecha.getFechaCorta(fecha));
        req.getSession().setAttribute(Const.ROLES_INDIVIDUAL, rolesIndividual);
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
            resp.sendRedirect("/roles_web/login");
        }
        return rolesIndividual;
    }

}