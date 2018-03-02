package dao;

import models.User;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection conn;
    private Statement stat;
    private ResultSet rs;

    public List<User> getUsers(){

        List<User> list = new ArrayList<>();
        final String q = "SELECT * FROM user";

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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
          cerrarRecursos();
        }
        return list;
    }

    public User getRegisteredUser(String username, String password) throws IOException{
        final String userRegistered = "SELECT * FROM user WHERE username=? AND password=?";
        System.out.println("DAO username: "+username+" password: "+password);
        User user = new User();

        try {
            conn = new DBConnection().conectar();
            PreparedStatement preparedStatement = conn.prepareStatement(userRegistered);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Valor de la col username: "+resultSet.getString("username"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setTipo(resultSet.getString("tipo"));

            System.out.println("UserDAO viene: "+user.getUsername());

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }finally {
            cerrarRecursos();
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
