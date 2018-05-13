package servlets;

import dao.PagoMesDAO;
import dao.PagoVacacionesDAO;
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
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static utilidad.Numeros.round;

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

        System.out.println("get");

        String empleadoId = req.getParameter(Const.ID);
        String rolClienteId = req.getParameter(Const.ROL_CLIENTE_ID);
        if (empleadoId != null) {
            String fecha = (String) req.getSession().getAttribute(Const.FECHA);
            List<RolCliente> rolesClients = rolClienteDAO.findAllByFechaAndUsuarioId(fecha, Integer.valueOf(empleadoId));
            RolIndividual rolIndividual = rolIndividualDAO.findByFechaAndUsuarioId(fecha, Integer.valueOf(empleadoId));
            if (rolIndividual != null) {
                PagoMes pagoMes = pagoMesDAO.findByRolIndividualId(rolIndividual.getId());
                req.setAttribute(Const.PAGO_MES, pagoMes);
                req.setAttribute(Const.VACATION_DATE, getTextVacaciones(rolIndividual));
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
        req.setAttribute(Const.FILTER_MONTH,  new Fecha(fecha).getMonthSelect());
        req.getSession().setAttribute(Const.ROLES_INDIVIDUAL, rolesIndividual);
        req.getSession().setAttribute(Const.FECHA, fecha);
        req.getRequestDispatcher("empleados.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
        if (SessionUtility.isExpiry(req, resp)) return;

        System.out.println("post");

        String next = req.getParameter("next");
        String previous = req.getParameter("previous");
        String search = req.getParameter("search");
        String monthSelect = req.getParameter("month");

        if (next != null) {
            String fecha = (String) req.getSession().getAttribute(Const.FECHA);
            fecha = new Fecha(fecha).plusMonths(1).toString();
            List<RolIndividual> rolesIndividual = getRolesIndividual(fecha);
            req.setAttribute(Const.ROLES_INDIVIDUAL, rolesIndividual);
            req.setAttribute(Const.FILTER_MONTH,  new Fecha(fecha).getMonthSelect());
            req.getSession().setAttribute(Const.ROLES_INDIVIDUAL, rolesIndividual);
            req.getSession().setAttribute(Const.FECHA, fecha);
            req.getRequestDispatcher("empleados.jsp").forward(req, resp);

        } else if (previous != null) {
            String fecha = (String) req.getSession().getAttribute(Const.FECHA);
            fecha = new Fecha(fecha).minusMonths(1).toString();
            List<RolIndividual> rolesIndividual = getRolesIndividual(fecha);
            req.setAttribute(Const.ROLES_INDIVIDUAL, rolesIndividual);
            req.setAttribute(Const.FILTER_MONTH,  new Fecha(fecha).getMonthSelect());
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

            req.setAttribute(Const.FILTER_MONTH,  new Fecha(fecha).getMonthSelect());
            req.setAttribute(Const.ROLES_INDIVIDUAL, rolesFilter);
            req.setAttribute(Const.FILTER_DATA, searchDate);
            req.getRequestDispatcher("empleados.jsp").forward(req, resp);
        } else if (monthSelect != null) {
            String fecha = Fecha.fromMonthSelect(monthSelect).getFecha();
            List<RolIndividual> rolesIndividual = getRolesIndividual(fecha);
            req.setAttribute(Const.ROLES_INDIVIDUAL, rolesIndividual);
            req.setAttribute(Const.FILTER_MONTH,  new Fecha(fecha).getMonthSelect());
            req.getSession().setAttribute(Const.ROLES_INDIVIDUAL, rolesIndividual);
            req.getSession().setAttribute(Const.FECHA, fecha);
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

    private String getTextVacaciones(RolIndividual rolIndividual) {
        Fecha inicioVac = new Fecha("01", "01", new Fecha(rolIndividual.getInicio()).getAno())
                .minusYears(1);
        PagoVacaciones pagoVacaciones = new PagoVacacionesDAO()
                .findInDeterminateYearByUsuarioId(inicioVac.getAno(), rolIndividual.getUsuario().getId());

        Integer dias = getVacacionesFromThisMonth(pagoVacaciones, new Fecha(rolIndividual.getInicio()));
        if (dias > 0) {
            String range = rangoVacaciones(pagoVacaciones, new Fecha(rolIndividual.getInicio()));
            return dias+" dias de vacaciones. Del "+range;
        } else {
            return null;
        }
    }

    private Integer getVacacionesFromThisMonth(PagoVacaciones pagoVacaciones, Fecha inicio) {

        if (pagoVacaciones != null) {
            Calendar calIni = Calendar.getInstance();
            calIni.setTime(pagoVacaciones.getGoceInicio());

            Calendar calFin = Calendar.getInstance();
            calFin.setTime(pagoVacaciones.getGoceFin());

            Integer mes1int = calIni.get(Calendar.MONTH)+1;
            Integer mes2int = calFin.get(Calendar.MONTH)+1;
            Integer dias1int = 0;
            Integer dias2int = 0;
            Double montoMes1Dou = 0d;
            Double montoMes2Dou = 0d;
            if (mes1int != mes2int) {
                if (mes1int+1 == mes2int) {
                    dias2int = calFin.get(Calendar.DAY_OF_MONTH);
                    dias1int = pagoVacaciones.getDias() - dias2int;
                    Double valorDia = pagoVacaciones.getValor()
                            /pagoVacaciones.getDias().doubleValue();
                    montoMes1Dou = round(valorDia*dias1int);
                    montoMes2Dou = round(valorDia*dias2int);
                }
            } else {
                if (inicio.getMesInt() == mes1int) {
                    return pagoVacaciones.getDias();
                }
            }
            if (inicio.getMesInt() == mes1int) {
                return dias1int;
            }
            if (inicio.getMesInt() == mes2int) {
                return dias2int;
            }
        }
        return 0;
    }

    private String rangoVacaciones(PagoVacaciones pagoVacaciones, Fecha inicio) {
        if (pagoVacaciones != null) {
            Calendar calIni = Calendar.getInstance();
            calIni.setTime(pagoVacaciones.getGoceInicio());

            Calendar calFin = Calendar.getInstance();
            calFin.setTime(pagoVacaciones.getGoceFin());

            Integer mes1int = calIni.get(Calendar.MONTH)+1;
            Integer mes2int = calFin.get(Calendar.MONTH)+1;

            Integer day1int = calIni.get(Calendar.DAY_OF_MONTH);
            Integer day2int = calFin.get(Calendar.DAY_OF_MONTH);

            if (mes1int != mes2int) {
                if (mes1int+1 == mes2int) {

                }
            } else {
                if (inicio.getMesInt() == mes1int) {
                    return Fecha.getFechaConMes(pagoVacaciones.getGoceInicio())+" al "+
                            Fecha.getFechaConMes(pagoVacaciones.getGoceFin());
                }
            }
            if (inicio.getMesInt() == mes1int) {
                return Fecha.getFechaConMes(pagoVacaciones.getGoceInicio())+" al "+
                        Fecha.getFechaConMes(inicio.getFromLastDayMonth());
            }
            if (inicio.getMesInt() == mes2int) {
                return Fecha.getFechaConMes(inicio.getFromFirstDayMonth())+" al "+
                        Fecha.getFechaConMes(pagoVacaciones.getGoceFin());
            }
        }
        return "";
    }

}