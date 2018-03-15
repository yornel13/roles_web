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

        UserDAO userDAO = new UserDAO();
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
            request.setAttribute("user_logged_id", userLogged);

            if(!username.isEmpty()){
                User compareUsername = userDAO.getUserByUsername(username);
                if(compareUsername.getId() == null){ // Cambio de usuario
                    if(!password.isEmpty()){

                        if(userLogged.getPassword().equals(password)){
                            System.out.println("Nombre ususario cambiado con exito");

                            Integer id = userLogged.getId();
                            new UserDAO().updateUserProfile(id, username, newPassword);

                            typeInfo = "save_profile";
                            request.setAttribute("info_msg", typeInfo );

                            List<User> listaUsuario = userDAO.getUsers();
                            request.setAttribute("listaUsuario", listaUsuario);
                            request.getRequestDispatcher("admin-user-table.jsp").include(request, response);
                        }else{
                            System.out.println("Contraseña invalida para el cambio de Nombre usuario");
                            typeInfo = "empty_3";
                            request.setAttribute("info_msg", typeInfo );
                            request.setAttribute("user_logged_id", userLogged);
                            request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
                        }
                    } else {
                        System.out.println("Campo contraseña es requerido para el cambio de nombre usuario");
                        typeInfo = "empty_2";
                        request.setAttribute("info_msg", typeInfo );
                        request.setAttribute("user_logged_id", userLogged);
                        request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
                    }
                }else{

                    if(userLogged.getId().equals(compareUsername.getId())){ // ES el mismo nombre -> soy yo
                        if(password.isEmpty()){
                            if(newPassword != null || confirmPassword != null){
                                System.out.println("Debe colocar la contraseña actual para efectuar cualquier cambio");
                                typeInfo = "empty_7";

                                request.setAttribute("info_msg", typeInfo );
                                request.setAttribute("user_logged_id", userLogged);
                                request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
                            } else { // redirect
                                inputedUsername = username;
                                request.setAttribute("username", inputedUsername);
                                request.setAttribute("user_logged_id", userLogged);
                                request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
                            }
                        } else { // Cambio contraseña y username

                            if(userLogged.getPassword().equals(password)){
                                if(!newPassword.isEmpty() && !confirmPassword.isEmpty()){
                                    if(newPassword.equals(confirmPassword)){
                                        Integer id = userLogged.getId();
                                        new UserDAO().updateUserProfile(id, username, newPassword);

                                        typeInfo = "save_profile";
                                        request.setAttribute("info_msg", typeInfo );

                                        List<User> listaUsuario = userDAO.getUsers();
                                        request.setAttribute("listaUsuario", listaUsuario);
                                        request.getRequestDispatcher("admin-user-table.jsp").forward(request, response);
                                    } else {
                                        System.out.println("La contraseña nueva no coincide con el campo de confirmacion");
                                        typeInfo = "empty_5";
                                        request.setAttribute("info_msg", typeInfo );
                                        request.setAttribute("user_logged_id", userLogged);
                                        request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
                                    }
                                } else {
                                    System.out.println("Los campos de nueva contraseña y confirmacion son requeridos");
                                    typeInfo = "empty_6";
                                    request.setAttribute("info_msg", typeInfo );
                                    request.setAttribute("user_logged_id", userLogged);
                                    request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
                                }
                            } else {
                                System.out.println("Contraseña invalida para el cambio de clave");
                                typeInfo = "empty_4";
                                request.setAttribute("info_msg", typeInfo );
                                request.setAttribute("user_logged_id", userLogged);
                                request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
                            }
                        }


                    } else { // Esta ocupado el nombre usuario
                        System.out.println("El nombre de usuario "+username+" se encuentra en uso (save)");
                        typeInfo = "username_exists";
                        inputedUsername = username;
                        request.setAttribute("info_msg", typeInfo );
                        request.setAttribute("username", inputedUsername);

                        request.getRequestDispatcher("change_username_pass.jsp").forward(request, response);
                    }
                }
            }else {
                System.out.println("Campo nombre usuario requerido");
                typeInfo = "empty_1";
                request.setAttribute("user_logged_id", userLogged);
                request.setAttribute("info_msg", typeInfo );
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

        /**Go to user profile por POST no por aqui*/
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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("-> GET");

        /**Get and load users from DB to table **/
        List<User> listaUsuario = userDAO.getUsers();
        request.setAttribute("listaUsuario", listaUsuario);
        request.getRequestDispatcher("admin-user-table.jsp").forward(request,response);

    }
}
