package dao;

import utilidad.Utilidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnetion {


    Connection conectar(){
        Connection connection = null;
        Properties propiedadesBaseDatos = Utilidad.getIntancia().getPropidadesBaseDatos();

        String url = "jdbc:mysql://localhost:3306/dbcontrol_web";/*+
                /*propiedadesBaseDatos.getProperty("servidor")+":"+
                propiedadesBaseDatos.getProperty("puerto")+"/"+
                propiedadesBaseDatos.getProperty("basededatos");*/

        String user = "root";//propiedadesBaseDatos.getProperty("user");
        String pass = "1234";//propiedadesBaseDatos.getProperty("password");

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
