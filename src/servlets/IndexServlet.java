package servlets;

import utilidad.Fecha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("goLogin") != null) {
            resp.sendRedirect("login");
        }
        if (req.getParameter("goRolCliente") != null) {
            req.getSession().setAttribute("fecha", Fecha.getFechaActual().withDay("01").minusMonths(1).getFecha());
            req.getSession().setAttribute("clienteId", "6");
            resp.sendRedirect("rol_cliente");
            //req.getRequestDispatcher("rol_cliente.jsp").forward(req, resp);
        }
        if (req.getParameter("goRolesEmpleado") != null) {
            req.getSession().setAttribute("fecha", Fecha.getFechaActual().withDay("01").minusMonths(1).getFecha());
            req.getSession().setAttribute("empleadoId", "99");
            resp.sendRedirect("rol_empleado");
        }
    }
}
