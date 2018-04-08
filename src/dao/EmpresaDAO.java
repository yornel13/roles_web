package dao;

import models.Empresa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmpresaDAO {

    private Connection conn;
    private Statement stat;
    private ResultSet rs;

    public Empresa findByRuc(String ruc) {

        Empresa empresa = null;

        String q = "SELECT * FROM empresa WHERE numeracion = '"+ruc+"'";
        try {

            conn = new DBConnectionGuardias().conectar();
            stat = conn.createStatement();
            rs = stat.executeQuery(q);

            while (rs.next()) {

                empresa = new Empresa();
                empresa.setId(rs.getInt("id"));
                empresa.setNombre(rs.getString("nombre"));
                empresa.setSiglas(rs.getString("siglas"));
                empresa.setNumeracion(rs.getString("numeracion"));
                empresa.setDireccion(rs.getString("direccion"));
                empresa.setTelefono1(rs.getString("telefono_1"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stat.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return empresa;
    }
}
