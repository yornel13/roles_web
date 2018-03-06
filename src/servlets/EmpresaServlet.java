package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/empresa")
public class EmpresaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("empresa.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("goEmpleados") != null) {
            System.out.println("entro en indexServlet goLogin");
            resp.sendRedirect("login");
        }
        if (req.getParameter("goClientes") != null) {
            req.getSession().setAttribute("clienteId", "6");
            resp.sendRedirect("clientes");
        }
    }

}
