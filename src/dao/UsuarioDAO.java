package dao;

import models.DetallesEmpleado;
import models.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {

    private Connection conn;
    private Statement stat;
    private ResultSet rs;

    public Usuario findByCedula(String cedula) {

        Usuario usuario = null;

        String q = "SELECT * FROM usuario WHERE cedula = '"+cedula+"'";
        try {

            conn = new DBConnectionGuardias().conectar();
            stat = conn.createStatement();
            rs = stat.executeQuery(q);

            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setCedula(rs.getString("cedula"));
                usuario.setEmail(rs.getString("email"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDetallesEmpleadoId(rs.getInt("detalles_empleado_id"));
                usuario.setCreacion(rs.getTimestamp("creacion"));
                usuario.setUltimaModificacion(rs.getTimestamp("ultima_modificacion"));
                usuario.setActivo(rs.getBoolean("activo"));
                usuario.setNacimiento(rs.getTimestamp("nacimiento"));
                usuario.setSexo(rs.getString("sexo"));
            }

            q = "SELECT * FROM detalles_empleado WHERE id = "+usuario.getDetallesEmpleadoId();
            rs = stat.executeQuery(q);

            while (rs.next()) {
                DetallesEmpleado detallesEmpleado = new DetallesEmpleado();
                detallesEmpleado.setId(rs.getInt("id"));
                detallesEmpleado.setEmpresaId(rs.getInt("empresa_id"));
                detallesEmpleado.setFechaInicio(rs.getTimestamp("fecha_inicio"));
                detallesEmpleado.setFechaContrato(rs.getTimestamp("fecha_contrato"));
                detallesEmpleado.setCargoId(rs.getInt("cargo_id"));
                detallesEmpleado.setSueldo(rs.getDouble("sueldo"));
                detallesEmpleado.setQuincena(rs.getDouble("quincena"));
                detallesEmpleado.setAcumulaDecimos(rs.getBoolean("acumula_decimos"));
                detallesEmpleado.setNroCuenta(rs.getString("nro_cuenta"));
                usuario.setDetallesEmpleado(detallesEmpleado);
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
        return usuario;
    }
}
