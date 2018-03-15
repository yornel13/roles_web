package dao;

import com.sun.org.apache.regexp.internal.RE;
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
        final String userRegistered = "SELECT * FROM user WHERE username = ? AND password = ?";

        User user = new User();

        try {
            conn = new DBConnection().conectar();
            PreparedStatement preparedStatement = conn.prepareStatement(userRegistered);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setCedula(rs.getString("cedula"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setTipo(rs.getString("tipo"));
                user.setActivo(rs.getBoolean("activo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }finally {
            cerrarRecursos();
        }
        return user;
    }

    public Integer updateUser(Integer id, String nombre, String apellido, String cedula,
                           String username, String password, String tipo, Integer activo){

        final String updateUser = "UPDATE user " +
                                  "SET nombre=?, apellido=?, cedula=?, username=?, password=?, tipo=?, activo=? " +
                                  "WHERE id="+id;

        Integer updated = 0;
        try{
            conn = new DBConnection().conectar();
            PreparedStatement preparedStatement = conn.prepareStatement(updateUser);

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);
            preparedStatement.setString(3, cedula);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, password);
            preparedStatement.setString(6, tipo);
            preparedStatement.setInt(7, activo);

            updated = preparedStatement.executeUpdate();

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            cerrarRecursos();
        }
        return updated;
    }

    public void updateUserProfile(Integer id, String userName, String newPassword) {

        String updateProfile = "UPDATE user SET username=?, password=? WHERE id="+id;

        try {
            conn = new DBConnection().conectar();
            PreparedStatement preparedStatement = conn.prepareStatement(updateProfile);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, newPassword);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public Boolean addUser(String nombre, String apellido, String cedula, String username, String password, String tipo, Integer activo){
        final String addUser = "INSERT INTO user " +
                                      "(nombre, apellido, cedula, username, password, tipo, activo)" +
                                      "VALUES (?,?,?,?,?,?,?)";

        Boolean booleanReturn = false;

        try {
            conn = new DBConnection().conectar();
            PreparedStatement preparedStatement = conn.prepareStatement(addUser);

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);
            preparedStatement.setString(3, cedula);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, password);
            preparedStatement.setString(6, tipo);
            preparedStatement.setInt(7, activo);
            booleanReturn = preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            cerrarRecursos();
        }
        return booleanReturn;
    }

    public User getUserByID(Integer id) {
        String userByID = "SELECT * FROM user WHERE id = "+id;

        User user = new User();

        try {
            conn = new DBConnection().conectar();
            Statement statement  = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(userByID);

            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setNombre(resultSet.getString("nombre"));
                user.setApellido(resultSet.getString("apellido"));
                user.setCedula(resultSet.getString("cedula"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setTipo(resultSet.getString("tipo"));
                user.setActivo(resultSet.getBoolean("activo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Boolean deleteUser(Integer id){
        String deleteUserByID ="DELETE FROM user WHERE id=?";

        try {
            conn = new DBConnection().conectar();
            PreparedStatement preparedStatement = conn.prepareStatement(deleteUserByID);
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }

    }

    public User getUserByUsername(String username){
        String existUser ="SELECT * FROM user WHERE username=?";
        User user = new User();
        conn = new DBConnection().conectar();
        try {
           PreparedStatement preparedStatement = conn.prepareStatement(existUser);
           preparedStatement.setString(1, username);

           ResultSet resultSet  = preparedStatement.executeQuery();


            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setNombre(resultSet.getString("nombre"));
                user.setApellido(resultSet.getString("apellido"));
                user.setCedula(resultSet.getString("cedula"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setTipo(resultSet.getString("tipo"));
                user.setActivo(resultSet.getBoolean("activo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return user;
    }

    public User existCedula(String cedula){
        String existUser ="SELECT id FROM user WHERE cedula=?";
        User user = new User();
        conn = new DBConnection().conectar();
        try {
           PreparedStatement preparedStatement = conn.prepareStatement(existUser);
           preparedStatement.setString(1, cedula);
           ResultSet resultSet  = preparedStatement.executeQuery();


            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
            }

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
