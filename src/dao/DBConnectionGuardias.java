package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionGuardias {


    Connection conectar(){
        Connection connection = null;

        String url = "jdbc:mysql://localhost:3306/dbcontrol?autoReconnect=true&useSSL=false";

        String user = "root";
        String pass = "1234";

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
