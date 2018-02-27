package dao;

import models.User;
import utilidad.Utilidad;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection conn;
    private Statement stat;
    private ResultSet rs;

    public List<User> getUsers(){

        List<User> list = new ArrayList<>();
        String q = "SELECT * FROM user";

        try {

            conn = new DBConnetion().conectar();
            stat = conn.createStatement();
            rs = stat.executeQuery(q);

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setCedula(rs.getString("cedula"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setTipo(rs.getString("tipo"));
                user.setActivo(rs.getBoolean("activo"));
                list.add(user);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
                stat.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

  /*  public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.getUsers();
        System.out.println("tamano: "+users.size());
    }*/

}
