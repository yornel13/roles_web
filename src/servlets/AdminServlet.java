package servlets;

import dao.UserDAO;
import models.User;
import utilidad.Const;
import utilidad.SessionUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private String userToUpdateID;
    private Integer id;
    private Integer activo;
    private String typeInfo;
    private String inputedUsername;
    private String deleteUserID;
    private UserDAO userDAO = new UserDAO();


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("-> POST");

        userToUpdateID = request.getParameter("update_user");
        User userLogged = SessionUtility.getUser(request,response);

        /**Catch data*/
        if (userToUpdateID != null){
            id = Integer.valueOf(userToUpdateID);
        }
        String nombre =  request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String cedula = request.getParameter("cedula");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String currentPassword = request.getParameter("current_password");
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


        /**Selected user from table*/

        userToUpdateID = request.getParameter("user_id");
        if(userToUpdateID != null){
            Integer id = Integer.valueOf(userToUpdateID);
            User user = userDAO.getUserByID(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
            return;
        }

        /**Save user*/
        if(request.getParameter("save") != null){

            if(!nombre.isEmpty() && !apellido.isEmpty() && !cedula.isEmpty()){
                User userCedula = userDAO.existCedula(cedula);
                System.out.println("nombre del usuario: "+userCedula.getId());
                if(userCedula.getId() == null ){
                    if(!username.isEmpty()){
                        User usernameExists = userDAO.getUserByUsername(username);
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
                        compareUserCedula.getId() == null  ){
                    if(!username.isEmpty()){
                        User compareUsername = userDAO.getUserByUsername(username);
                        if(user.getId().equals(compareUsername.getId()) ||
                                compareUsername.getId() == null){
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


        /**  Update profile*/
        if(request.getParameter("save_profile") != null){
            if(username != null){
                if(!username.isEmpty()){
                    User user = userDAO.getUserByUsername(username);
                    if(user.getId() == null){ // puedo cambiar
                        if(!password.isEmpty()){
                            if(userLogged.getPassword().equals(password)){
                                userDAO.updateUsername(userLogged.getId(), username);
                                userLogged = userDAO.getUserByID(userLogged.getId());
                                System.out.println("Cambio exitoso");
                                typeInfo = "save_profile";
                                request.setAttribute("info_msg", typeInfo );

                                request.setAttribute("user_logged_id", userLogged);
                                request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);

                            }else {
                                System.out.println("Contraseña invalida");
                                typeInfo = "empty_3";
                                request.setAttribute("info_msg", typeInfo );

                                request.setAttribute("user_logged_id", userLogged);
                                request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
                            }
                        }else {
                            System.out.println("La contraseña es requerida para el cambio de usuaro");
                            typeInfo = "empty_2";
                            request.setAttribute("info_msg", typeInfo );

                            request.setAttribute("user_logged_id", userLogged);
                            request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);

                        }

                    }else{
                        if(userLogged.getId().equals(user.getId())){
                            System.out.println("redireccion a profile");
                            request.setAttribute("user_logged_id", userLogged);
                            request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);

                        }else{
                            System.out.println("El nombre usuario tal esta en uso");
                            typeInfo = "username_exists";
                            inputedUsername = username;
                            request.setAttribute("info_msg", typeInfo );
                            request.setAttribute("username", inputedUsername);

                            request.setAttribute("user_logged_id", userLogged);
                            request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
                        }
                    }

                }else{
                    System.out.println("El campo de usuario esta vacio");
                    typeInfo = "empty_1";
                    request.setAttribute("info_msg", typeInfo );

                    request.setAttribute("user_logged_id", userLogged);
                    request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
                }
            }

            // Password validator
            if (!currentPassword.isEmpty()){
                if (!newPassword.isEmpty()){
                    if (!confirmPassword.isEmpty()) {
                        if(newPassword.equals(confirmPassword)){
                            System.out.println("Mi pass: "+userLogged.getPassword()+"  currentpass es: "+currentPassword);
                            if (userLogged.getPassword().equals(currentPassword)){


                                userDAO.updateUserPassword(userLogged.getId(), newPassword);
                                System.out.println("Cambio de contraseña exitoso save_pass");
                                typeInfo = "save_pass";
                                request.setAttribute("info_msg", typeInfo );

                                request.setAttribute("user_logged_id", userLogged);
                                request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
                            }else {
                                System.out.println("La contraseñas actual es invalida!");
                                typeInfo = "wrong_pass2";
                                request.setAttribute("info_msg", typeInfo );

                                request.setAttribute("user_logged_id", userLogged);
                                request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
                            }
                        }else {
                            System.out.println("Las contraseñas no coincinden");
                            typeInfo = "wrong_pass1";
                            request.setAttribute("info_msg", typeInfo );

                            request.setAttribute("user_logged_id", userLogged);
                            request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
                        }
                    }else{
                        System.out.println("El campo de confirmacio es requerido");
                        typeInfo = "empty_pass3";
                        request.setAttribute("info_msg", typeInfo );

                        request.setAttribute("user_logged_id", userLogged);
                        request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
                    }
                }else{
                    System.out.println("Debe especificar la nueva contraseña");
                    typeInfo = "empty_pass2";
                    request.setAttribute("info_msg", typeInfo );

                    request.setAttribute("user_logged_id", userLogged);
                    request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
                }
            }else{
                System.out.println("El campo de contraseña es requerido para el cambio de clave");
                typeInfo = "empty_pass1";
                request.setAttribute("info_msg", typeInfo );

                request.setAttribute("user_logged_id", userLogged);
                request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
            }
        }


        /**Delete user*/
        String modalID  = request.getParameter("delete_modal_button");
        System.out.println(" el id usuario a borrar es:  "+modalID);
        if(modalID != null){
            Integer deleteID = Integer.parseInt(modalID);
            new UserDAO().deleteUser(deleteID);
            System.out.println("Usuario borrado con exito");
            typeInfo = "delete";
            request.setAttribute("info_msg", typeInfo );
            List<User> listaUsuario = userDAO.getUsers();
            request.setAttribute("listaUsuario", listaUsuario);
            request.getRequestDispatcher("admin-user-table.jsp").forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**Go to user profile*/
        if(request.getParameter("update_profile") != null){
            System.out.println("id no es null (PASO)");
            Integer userloggedID = SessionUtility.getUser(request, response).getId();
            User user = userDAO.getUserByID(userloggedID);
            request.setAttribute("user_logged_id", user );
            request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
            return;
        }

        /**Get and load users from DB to table **/
        List<User> listaUsuario = userDAO.getUsers();
        request.setAttribute("listaUsuario", listaUsuario);
        request.getRequestDispatcher("admin-user-table.jsp").forward(request,response);

    }
}
