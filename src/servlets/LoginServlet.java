package servlets;

import dao.ClienteDAO;
import dao.EmpresaDAO;
import dao.UserDAO;
import dao.UsuarioDAO;
import models.Cliente;
import models.Empresa;
import models.User;
import models.Usuario;
import utilidad.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private String typeInfo;
    UserDAO userDAO = new UserDAO();
    ClienteDAO clienteDAO = new ClienteDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    EmpresaDAO empresaDAO = new EmpresaDAO();

    private void processRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter(Const.USERNAME);
        String password = request.getParameter(Const.PASSWORD);

        if(!username.equals(Const.EMPTY) && !password.equals(Const.EMPTY)){

            User user = userDAO.getRegisteredUser(username, password);
            System.out.println("use viene: "+ user);
            if(user.getId() != null){
                System.out.println("paso user");
                if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {

                    if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {

                        SessionUtility.save(user, request, response);
                        if (user.getType() == UserType.ADMINISTRADOR) {
                            response.sendRedirect("admin");
                        } else {
                            response.sendRedirect("login?successful");
                        }
                    }
                }
            } else {
                typeInfo = "wrong_2";
                request.setAttribute("info_msg", typeInfo );
                System.out.println("Usuario o contraseña invalido!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } else {
            typeInfo = "wrong_1";
            request.setAttribute("info_msg", typeInfo );
            System.out.println("Los campos de nombre usuario y contraseña son requeridos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter(Const.SUCCESSFUL) != null) {
            User user = (User) req.getSession().getAttribute(Const.USER);
            req.getSession().setAttribute(Const.FECHA, Fecha.getFechaActualInit());
            switch (user.getType()) {
                case EMPRESA:
                    Empresa empresa = empresaDAO.findByRuc(user.getCedula());
                    req.getSession().setAttribute(Const.DATA_USER, empresa);
                    req.getSession().setAttribute(Const.EMPRESA_ID, empresa.getId());
                    resp.sendRedirect("empresa");
                    break;
                case CLIENTE:
                    Cliente cliente = clienteDAO.findByRuc(user.getCedula());
                    req.getSession().setAttribute(Const.DATA_USER, cliente);
                    req.getSession().setAttribute(Const.CLIENTE_ID, cliente.getId());
                    resp.sendRedirect("rol/cliente");
                    break;
                case EMPLEADO:
                    Usuario usuario = usuarioDAO.findByCedula(user.getCedula());
                    req.getSession().setAttribute(Const.DATA_USER, usuario);
                    req.getSession().setAttribute(Const.EMPLEADO_ID, usuario.getId());
                    resp.sendRedirect("rol/individual");
                    break;
            }
        } else if (req.getParameter(Const.LOGOUT) != null) {
            SessionUtility.remove(req, resp);
            req.getRequestDispatcher("login.jsp").include(req, resp);
        } else if (req.getParameter("profile") != null) {
            /**********************************************/
            /*******Redireccionar aqui a perfil************/
            /**********************************************/
            resp.sendRedirect("/roles_web/admin?update_profile="+SessionUtility.getUser(req, resp).getId()); // <----------------------
        } else if (req.getParameter(Const.EXPIRY) != null) {
            SessionUtility.remove(req, resp);
            req.setAttribute(Const.MESSAGE, "La sesión ha expirado");
            req.getRequestDispatcher("login.jsp").include(req, resp);
        } else {
            req.getRequestDispatcher("login.jsp").include(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("logout")!= null){

            HttpSession session = req.getSession();
            System.out.println("Logout con el usuario: "+ session.getAttributeNames().toString());
            session.removeAttribute(session.getAttributeNames().toString());
            session.invalidate();
            resp.sendRedirect("login");
            return;
        }


        if (req.getParameter("goLogin") != null) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            processRequests(req, resp);
        }
    }
}