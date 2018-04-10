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

    UserDAO userDAO = new UserDAO();
    ClienteDAO clienteDAO = new ClienteDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    EmpresaDAO empresaDAO = new EmpresaDAO();

    private void processRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter(Const.USERNAME);
        String password = request.getParameter(Const.PASSWORD);


        if(!username.equals(Const.EMPTY) && !password.equals(Const.EMPTY)){

            User user = null;
            try {
                user = userDAO.getUser(username); // -> Validate on Web user table

            } catch (Exception ex) {
                ex.printStackTrace();
                request.setAttribute(Const.MESSAGE, "Problemas de conexion a base de datos!");
                request.setAttribute(Const.USERNAME, username);
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            if(user != null){ //Si es null no existe en user  Web o usuario o contraseña erronea (debe crearce)
                password = SessionUtility.MD5(password);
                if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    SessionUtility.save(user, request, response);
                    if (user.getType() == UserType.ADMINISTRADOR) {
                        response.sendRedirect("admin");
                    } else {
                        response.sendRedirect("login?successful");
                    }
                } else {
                    request.setAttribute(Const.MESSAGE, "Contraseña incorrecta!");
                    request.setAttribute(Const.USERNAME, username);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                System.out.println("verificar usuario en desktop DB");
                //Verifico en la desk DB si existe el usuario y esta relacionado a una empresa

                Usuario usuario = usuarioDAO.findByCedula(username);

                if(usuario != null){
                    if(usuario.getDetallesEmpleado() != null){
                        if(password.equals(username)){
                            password = SessionUtility.MD5(password);
                            //Crea usuario de nuevo ingreso a web
                            userDAO.addUser(usuario.getNombre(), usuario.getApellido(), usuario.getCedula(), username, password,
                                    Const.EMPLEADO, Const.ACTIVE);
                            User userSaved = userDAO.existCedula(usuario.getCedula());
                            SessionUtility.save(userSaved, request, response);
                            response.sendRedirect("login?successful");
                            return;
                        } else {
                            request.setAttribute(Const.MESSAGE, "La contraseña debe coincidir con el usuario!");
                            request.setAttribute(Const.USERNAME, username);
                            request.getRequestDispatcher("login.jsp").forward(request, response);
                        }

                    }else {
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                }else{
                    request.setAttribute(Const.MESSAGE, "El nombre de usuario no esta registrado o no coincide!");
                    request.setAttribute(Const.USERNAME, username);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                request.setAttribute(Const.MESSAGE, "El nombre de usuario no esta registrado!");
                request.setAttribute(Const.USERNAME, username);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute(Const.MESSAGE, "Los campos son requeridos");
            request.setAttribute(Const.USERNAME, username);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter(Const.SUCCESSFUL) != null) {
            User user = (User) request.getSession().getAttribute(Const.USER);
            request.getSession().setAttribute(Const.FECHA, Fecha.getFechaActualInit());
            switch (user.getType()) {
                case EMPRESA:
                    Empresa empresa = empresaDAO.findByRuc(user.getCedula());
                    if (empresa == null) {request.getRequestDispatcher("no_match.jsp").forward(request, response);return;}
                    request.getSession().setAttribute(Const.DATA_USER, empresa);
                    request.getSession().setAttribute(Const.EMPRESA_ID, empresa.getId());
                    response.sendRedirect("empresa");
                    break;
                case CLIENTE:
                    Cliente cliente = clienteDAO.findByRuc(user.getCedula());
                    if (cliente == null) {request.getRequestDispatcher("no_match.jsp").forward(request, response);return;}
                    request.getSession().setAttribute(Const.DATA_USER, cliente);
                    request.getSession().setAttribute(Const.CLIENTE_ID, cliente.getId());
                    response.sendRedirect("rol/cliente");
                    break;
                case EMPLEADO:
                    Usuario usuario = usuarioDAO.findByCedula(user.getCedula());
                    if (usuario == null) {request.getRequestDispatcher("no_match.jsp").forward(request, response);return;}
                    request.getSession().setAttribute(Const.DATA_USER, usuario);
                    request.getSession().setAttribute(Const.EMPLEADO_ID, usuario.getId());
                    response.sendRedirect("rol/individual");
                    System.out.println("Empleado loggeado con exito");
                    break;
            }
        } else if (request.getParameter(Const.LOGOUT) != null) {
            SessionUtility.remove(request, response);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (request.getParameter("profile") != null) {
            response.sendRedirect("/admin?update_profile"); //
        } else if (request.getParameter(Const.EXPIRY) != null) {
            SessionUtility.remove(request, response);
            request.setAttribute(Const.MESSAGE, "La sesión ha expirado");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("logout")!= null){
            HttpSession session = request.getSession();
            session.removeAttribute(session.getAttributeNames().toString());
            session.invalidate();
            response.sendRedirect("/login");
            return;
        }
        processRequests(request, response);

    }


    private void employeeValidator(Usuario usuario){

    }




}

