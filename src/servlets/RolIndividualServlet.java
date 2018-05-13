package servlets;

import dao.PagoMesDAO;
import dao.PagoVacacionesDAO;
import dao.RolClienteDAO;
import dao.RolIndividualDAO;
import models.PagoMes;
import models.PagoVacaciones;
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
import java.util.Calendar;
import java.util.List;

import static utilidad.Numeros.round;

@WebServlet("/rol/individual")
public class RolIndividualServlet extends HttpServlet {

    private RolClienteDAO rolClienteDAO = new RolClienteDAO();
    private RolIndividualDAO rolIndividualDAO = new RolIndividualDAO();
    private PagoMesDAO pagoMesDAO = new PagoMesDAO();
    private Integer empleadoId;
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
        empleadoId = (Integer) req.getSession().getAttribute(Const.EMPLEADO_ID);

        List<RolCliente> rolesClients = rolClienteDAO.findAllByFechaAndUsuarioId(fecha, empleadoId);
        RolIndividual rolIndividual = rolIndividualDAO.findByFechaAndUsuarioId(fecha, empleadoId);
        if (rolIndividual != null) {
            PagoMes pagoMes = pagoMesDAO.findByRolIndividualId(rolIndividual.getId());
            req.setAttribute(Const.PAGO_MES, pagoMes);
            req.setAttribute(Const.VACATION_DATE, getTextVacaciones(rolIndividual));
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
        String monthSelect = req.getParameter("month");

        if (next != null) {

            String fecha = (String) req.getSession().getAttribute(Const.FECHA);
            fecha = new Fecha(fecha).plusMonths(1).toString();
            empleadoId = (Integer) req.getSession().getAttribute(Const.EMPLEADO_ID);
            List<RolCliente> rolesClients = rolClienteDAO.findAllByFechaAndUsuarioId(fecha, empleadoId);
            RolIndividual rolIndividual = rolIndividualDAO.findByFechaAndUsuarioId(fecha, empleadoId);
            if (rolIndividual != null) {
                PagoMes pagoMes = pagoMesDAO.findByRolIndividualId(rolIndividual.getId());
                req.setAttribute(Const.PAGO_MES, pagoMes);
                req.setAttribute(Const.VACATION_DATE, getTextVacaciones(rolIndividual));
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
                req.setAttribute(Const.VACATION_DATE, getTextVacaciones(rolIndividual));
            }
            req.setAttribute(Const.ROL_INDIVIDUAL, rolIndividual);
            req.setAttribute(Const.ROLES_CLIENTE, rolesClients);
            req.setAttribute(Const.FILTER_MONTH,  new Fecha(fecha).getMonthSelect());
            req.getSession().setAttribute(Const.ROLES_CLIENTE, rolesClients);
            req.getSession().setAttribute(Const.FECHA, fecha);
            req.getRequestDispatcher("roles_empleado.jsp").forward(req, resp);

        } else if (monthSelect != null) {

            String fecha = Fecha.fromMonthSelect(monthSelect).getFecha();
            Integer empleadoId = (Integer) req.getSession().getAttribute(Const.EMPLEADO_ID);
            List<RolCliente> rolesClients = rolClienteDAO.findAllByFechaAndUsuarioId(fecha, empleadoId);
            RolIndividual rolIndividual = rolIndividualDAO.findByFechaAndUsuarioId(fecha, empleadoId);
            if (rolIndividual != null) {
                PagoMes pagoMes = pagoMesDAO.findByRolIndividualId(rolIndividual.getId());
                req.setAttribute(Const.PAGO_MES, pagoMes);
                req.setAttribute(Const.VACATION_DATE, getTextVacaciones(rolIndividual));
            }
            req.setAttribute(Const.ROL_INDIVIDUAL, rolIndividual);
            req.setAttribute(Const.ROLES_CLIENTE, rolesClients);
            req.setAttribute(Const.FILTER_MONTH,  new Fecha(fecha).getMonthSelect());
            req.getSession().setAttribute(Const.ROLES_CLIENTE, rolesClients);
            req.getSession().setAttribute(Const.FECHA, fecha);
            req.getRequestDispatcher("roles_empleado.jsp").forward(req, resp);

        }
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
