package servlets;

import dao.UserDAO;
import models.User;
import utilidad.Fecha;
import utilidad.Utilidad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private void processRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Llamo login servlet");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(!username.equals("") && !password.equals("")){
            System.out.println("Campos requeridos llenos con username: "+username+" password: "+password);
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getRegisteredUser(username, password);

            System.out.println("El username es: "+user.getUsername());

            if(user.getUsername() != null && user.getPassword() != null){
                System.out.println("Usuario es diferente de nulo");
                if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    System.out.println("Usuario paso el login es de tipo: " + user.getTipo());
                    switch (user.getTipo()) {
                        case "A":
                            System.out.println("entro en Admin");
                            HttpSession session = request.getSession();
                            session.setAttribute("admin", user.getTipo());

                            request.getSession().setAttribute("fecha", Fecha.getFechaActual().withDay("01")
                                    .minusMonths(3).getFecha());
                            request.getSession().setAttribute("clienteId", "06");
                            response.sendRedirect("rol/cliente");
                            break;
                        case "E":
                            System.out.println("Entro en empleado");
                            response.sendRedirect("rol_individual.jsp");
                            break;
                        default:
                            System.out.println("Entro en cliente");
                            response.sendRedirect("rol_individual.jsp");
                            break;
                    }
                }
            }
            else {
                System.out.println("Username o password erroneo");
                response.sendRedirect("login.jsp");
            }

        }
        else {
            System.out.println("Los campos son requeridos");
            response.sendRedirect("login.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // System.out.println("GET");
        System.out.println("GET "+ req.getParameter("logout"));
        if(req.getParameter("logout")!= null){

            HttpSession session = req.getSession();
            System.out.println("Logout con el usuario: "+ session.getAttributeNames().toString());
            session.removeAttribute(session.getAttributeNames().toString());
            session.invalidate();
            resp.sendRedirect("login.jsp");
            return;
        }
        processRequests(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if (req.getParameter("goLogin") != null) {
            System.out.println("Entro post en if");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            System.out.println("Paso post en else");
            processRequests(req, resp);
        }
    }
}