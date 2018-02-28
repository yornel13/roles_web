package dao;

import models.RolCliente;
import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RolClienteDAO {

    private Connection conn;
    private Statement stat;
    private ResultSet rs;

    public List<RolCliente> findAllByFechaAndClienteId(String fecha, Integer clienteId) {

        String q = "SELECT * FROM rol_cliente "
                        + "JOIN usuario "
                        + "ON usuario.id = rol_cliente.usuario_id "
                        + "JOIN detalles_empleado "
                        + "ON detalles_empleado.id = usuario.detalles_empleado_id "
                        + "where inicio = : "+fecha+" "
                        + "and cliente_id = : "+clienteId;

        List<RolCliente> list = new ArrayList<>();

        try {

            conn = new DBConnectionGuardias().conectar();
            stat = conn.createStatement();
            rs = stat.executeQuery(q);

            while (rs.next()) {
                RolCliente rolCliente = new RolCliente();
                rolCliente.setId(rs.getInt("id"));
                /*rolCliente.setNombre(rs.getString("nombre"));
                rolCliente.setApellido(rs.getString("apellido"));
                rolCliente.setCedula(rs.getString("cedula"));
                rolCliente.setUsername(rs.getString("username"));
                rolCliente.setPassword(rs.getString("password"));
                rolCliente.setTipo(rs.getString("tipo"));
                rolCliente.setActivo(rs.getBoolean("activo"));*/
                list.add(rolCliente);
            }


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
}
