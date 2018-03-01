package dao;

import models.User;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection conn;
    private Statement stat;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public List<User> getUsers(){

        List<User> list = new ArrayList<>();
        String q = "SELECT * FROM user";

        try {

            conn = new DBConnection().conectar();
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
          cerrarRecursos();
        }
        return list;
    }

    public User getRegisteredUser(String username, String password){
        String userRegistered = "SELECT tipo FROM user WHERE username= ? AND password= ?";

        User user = new User();
        try {
            conn = new DBConnection().conectar();
            preparedStatement = conn.prepareStatement(userRegistered);
            preparedStatement.setString(1, "username");
            preparedStatement.setString(2, "password");
            ResultSet resultSet = preparedStatement.executeQuery();

            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setTipo(resultSet.getString("tipo"));

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return user;
    }


    private void cerrarRecursos() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }
