package dao;

import utilidad.Utilidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionGuardias {


    Connection conectar(){
        Connection connection = null;
        Properties propiedadesBaseDatos = Utilidad.getIntancia().getPropidadesBaseDatos();

        String url = "jdbc:mysql://" +
                propiedadesBaseDatos.getProperty("servidor")+":"+
                propiedadesBaseDatos.getProperty("puerto")+"/"+
                "dbcontrol"+
                "?autoReconnect=true&useSSL=false";

        String user = propiedadesBaseDatos.getProperty("user");
        String pass = propiedadesBaseDatos.getProperty("password");

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
