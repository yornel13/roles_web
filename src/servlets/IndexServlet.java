package servlets;

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
        if (req.getParameter("goLogin") != null) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
        if (req.getParameter("goRolCliente") != null) {
            req.getRequestDispatcher("/rol_cliente.jsp").forward(req, resp);
        }
        System.out.println("index get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("index post");
    }
}
