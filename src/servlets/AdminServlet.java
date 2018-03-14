package servlets;

import dao.UserDAO;
import models.User;
import utilidad.Const;
import utilidad.Utilidad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private String userID;
    private Integer id;
    private Integer activo;
    private String typeInfo;
    private String inputedUsername;


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("-> POST");

        UserDAO userDAO = new UserDAO();
        userID = request.getParameter("update_user");

        /**Catch data*/
        if (userID != null){
            id = Integer.valueOf(userID);
        }
        String nombre =  request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cedula = request.getParameter("cedula");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newpassword");
        String confirmPassword = request.getParameter("confirm_password");
        String tipo = request.getParameter("tipo");

        request.setAttribute("nombre", nombre);
        request.setAttribute("apellido", apellido);
        request.setAttribute("cedula", cedula);
        request.setAttribute("username", username);
        request.setAttribute("tipo", tipo);

        if(request.getParameter("activo") != null){
            activo = Integer.valueOf(request.getParameter("activo"));
        }

        /**Save user*/
        if(request.getParameter("save") != null){

            if(!nombre.isEmpty() && !apellido.isEmpty() && !cedula.isEmpty()){
                User userCedula = userDAO.existCedula(cedula);
                System.out.println("nombre del usuario: "+userCedula.getId());
                if(userCedula.getId() == null ){
                    if(!username.isEmpty()){
                        User usernameExists = userDAO.existUsername(username);
                        if(usernameExists.getId() == null){
                            if(!password.isEmpty()){
                                if(confirmPassword.isEmpty()){
                                    System.out.println("Debe confirmar la contraseña (save)");
                                    typeInfo = "empty_confirm";
                                    request.setAttribute("info_msg", typeInfo );

                                    request.getRequestDispatcher("add-user.jsp").forward(request, response);
                                    return;
                                }
                                if(password.equals(confirmPassword)){
                                    userDAO.addUser( nombre, apellido, cedula, username, password, tipo, Const.ACTIVE);

                                    typeInfo = "saved";
                                    request.setAttribute("info_msg", typeInfo);

                                    List<User> listaUsuario = userDAO.getUsers();
                                    request.setAttribute("listaUsuario", listaUsuario);
                                    request.getRequestDispatcher("admin-user-table.jsp").include(request, response);

                                    return;
                                } else {
                                    System.out.println("Los campos de contraseña y confirmacion no coinciden (save)");
                                    typeInfo = "empty_3";
                                    request.setAttribute("info_msg", typeInfo);
                                    request.getRequestDispatcher("add-user.jsp").forward(request, response);
                                    return;
                                }
                            } else {
                                System.out.println("Los campos de contraseña y confirmacion son requeridos (save)");
                                typeInfo = "empty_4";
                                request.setAttribute("info_msg", typeInfo);
                                request.getRequestDispatcher("add-user.jsp").forward(request, response);
                                return;
                            }
                        } else {
                            System.out.println("El nombre de usuario "+username+" se encuentra en uso (save)");
                            typeInfo = "username_exists";
                            inputedUsername = username;
                            request.setAttribute("info_msg", typeInfo );
                            request.setAttribute("username", inputedUsername);

                            request.getRequestDispatcher("add-user.jsp").forward(request, response);
                        }
                    } else {
                        System.out.println("EL campo Nombre usuario es requerido (save)");
                        typeInfo = "empty_2";
                        request.setAttribute("info_msg", typeInfo);
                        request.getRequestDispatcher("add-user.jsp").forward(request, response);
                        return;
                    }
                } else {
                    System.out.println("Existe un usuario registrado con esta cedula (save)");
                    typeInfo = "same_dni";
                    request.setAttribute("info_msg", typeInfo );

                    request.getRequestDispatcher("add-user.jsp").forward(request, response);
                }
            } else {
                System.out.println("Los campos de nombres y cedula son requeridos (save)");
                typeInfo = "empty_1";
                request.setAttribute("info_msg", typeInfo);
                request.getRequestDispatcher("add-user.jsp").forward(request, response);
            }
        }


        /** update user*/
        if(request.getParameter("update_user") != null){
            User user = userDAO.getUserByID(id);

            if(!nombre.isEmpty() && !apellido.isEmpty() && !cedula.isEmpty()){
                User compareUserCedula = userDAO.existCedula(cedula);
                if(user.getId().equals(compareUserCedula.getId()) ||
                        !user.getId().equals(compareUserCedula.getId())){
                    if(!username.isEmpty()){
                        User compareUsername = userDAO.existUsername(username);
                        if(user.getId().equals(compareUsername.getId()) ||
                                !user.getId().equals(compareUsername.getId())){
                            if(password.isEmpty() && confirmPassword.isEmpty()){
                                password = user.getPassword();
                                confirmPassword = user.getPassword();
                            }
                            if(!password.isEmpty() && confirmPassword.isEmpty()){
                                System.out.println("Debe confirmar la contraseña (Update)");
                                typeInfo = "empty_confirm";
                                request.setAttribute("info_msg", typeInfo );

                                request.setAttribute("user", user);
                                request.getRequestDispatcher("admin.jsp").forward(request, response);
                                return;
                            }
                            if(password.equals(confirmPassword)){

                                UserDAO updateUserDAO = new UserDAO();
                                updateUserDAO.updateUser(id, nombre, apellido, cedula, username, password, tipo, activo);

                                System.out.println("Usuario Actualizado con exito! con ID: "+id);
                                typeInfo = "updated";
                                request.setAttribute("info_msg", typeInfo );

                                request.setAttribute("user", user);
                                List<User> listaUsuario = new UserDAO().getUsers();
                                request.setAttribute("listaUsuario", listaUsuario);
                                request.getRequestDispatcher("admin-user-table.jsp").forward(request, response);
                            } else {
                                System.out.println("Los campos de contraseña y confirmacion no coinciden (Update)");
                                typeInfo = "empty_3";
                                request.setAttribute("info_msg", typeInfo );

                                request.setAttribute("user", user);
                                request.getRequestDispatcher("admin.jsp").forward(request, response);
                            }
                        } else {
                            System.out.println("El nombre de usuario "+username+" se encuentra en uso (Update)");
                            typeInfo = "username_exists";
                            inputedUsername = username;
                            request.setAttribute("info_msg", typeInfo );
                            request.setAttribute("username", inputedUsername);

                            request.setAttribute("user", user);
                            request.getRequestDispatcher("admin.jsp").forward(request, response);
                        }
                    } else {
                        System.out.println("El campo de nombre usuario es requerido (Update)");
                        typeInfo = "empty_2";
                        request.setAttribute("info_msg", typeInfo );

                        request.setAttribute("user", user);
                        request.getRequestDispatcher("admin.jsp").forward(request, response);
                    }
                } else {
                    System.out.println("Existe un usuario registrado con esta cedula (Update)");
                    typeInfo = "same_dni";
                    request.setAttribute("info_msg", typeInfo );

                    request.setAttribute("user", user);
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                }
            } else {
                System.out.println("Los campos de nombres y cedula son requeridos (Update)");
                typeInfo = "empty_1";
                request.setAttribute("info_msg", typeInfo );

                request.setAttribute("user", user);
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            }
        }


        /** Save updated profile*/

        String updateProfile = request.getParameter("save_profile");
        if( updateProfile != null){
            User user = userDAO.getUserByID(id);

            request.setAttribute("user_logged_id", user);

            if(!username.isEmpty() && !password.isEmpty()){
                if(newPassword.equals(confirmPassword)){

                    Integer id = Integer.valueOf(userID);
                    new UserDAO().updateUserProfile(id, username, newPassword);

                    typeInfo = "save";
                    request.setAttribute("info_msg", typeInfo );
                    request.setAttribute("user_logged_id", user);
                    request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
                }else {
                    typeInfo = "empty_2";
                    request.setAttribute("info_msg", typeInfo );
                    request.setAttribute("user_logged_id", user);
                    request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
                }
            }else {
                typeInfo = "empty_1";
                user = userDAO.getUserByID(id);
                request.setAttribute("user_logged_id", user);
                request.setAttribute("info_msg", typeInfo );
                request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("-> GET");

        userID = request.getParameter("user_id");
        UserDAO userDAO = new UserDAO();

        /**Selected user from table*/


        if(userID != null){
            Integer id = Integer.valueOf(userID);
            User user = userDAO.getUserByID(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
            return;
        }

        /**Delete users*/
        String deleteUserID = request.getParameter("delete");
        if(deleteUserID != null){
            Integer deleteID = Integer.parseInt(deleteUserID);
            new UserDAO().deleteUser(deleteID);
            System.out.println("Usuario borrado con exito");
            typeInfo = "delete";
            request.setAttribute("info_msg", typeInfo );
            List<User> listaUsuario = userDAO.getUsers();
            request.setAttribute("listaUsuario", listaUsuario);
            request.getRequestDispatcher("admin-user-table.jsp").forward(request, response);
            return;
        }

        /**Go to user profile*/
        System.out.println("Como llega el ID "+request.getParameter("update_profile"));
        String userLoginID = request.getParameter("update_profile");
        if(userLoginID != null){
            System.out.println("id no es null (PASO)");
            Integer userloggedID = Integer.parseInt(userLoginID);
            User user = userDAO.getUserByID(userloggedID);
            request.setAttribute("user_logged_id", user );
            request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
            return;
        }
        System.out.println("siguio");
        /**Get and load users from DB to table **/

        List<User> listaUsuario = userDAO.getUsers();
        request.setAttribute("listaUsuario", listaUsuario);
        request.getRequestDispatcher("admin-user-table.jsp").forward(request,response);

    }
}
