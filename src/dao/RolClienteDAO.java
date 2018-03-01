package dao;

import models.RolCliente;
import utilidad.Numeros;

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

        List<RolCliente> list = new ArrayList<>();

        String q = "SELECT * FROM rol_cliente "
                        + "JOIN usuario "
                        + "ON usuario.id = rol_cliente.usuario_id "
                        + "JOIN detalles_empleado "
                        + "ON detalles_empleado.id = usuario.detalles_empleado_id "
                        + "where inicio = '"+fecha+"' "
                        + "and cliente_id = "+clienteId;
        try {

            conn = new DBConnectionGuardias().conectar();
            stat = conn.createStatement();
            rs = stat.executeQuery(q);

            while (rs.next()) {

                RolCliente rolCliente = new RolCliente();
                rolCliente.setId(rs.getInt("id"));
                rolCliente.setInicio(rs.getString("inicio"));
                rolCliente.setFinalizo(rs.getString("finalizo"));
                rolCliente.setDetalles(rs.getString("detalles"));
                rolCliente.setFecha(rs.getTimestamp("fecha"));
                rolCliente.setSueldo(rs.getDouble("sueldo"));
                rolCliente.setDias(rs.getDouble("dias"));
                rolCliente.setHorasNormales(rs.getDouble("horas_normales"));
                rolCliente.setHorasSuplementarias(rs.getDouble("horas_suplementarias"));
                rolCliente.setHorasSobreTiempo(rs.getDouble("horas_sobre_tiempo"));
                rolCliente.setTotalHorasExtras(rs.getDouble("total_horas_extras"));
                rolCliente.setSalario(rs.getDouble("salario"));
                rolCliente.setMontoHorasSuplementarias(rs.getDouble("monto_horas_suplementarias"));
                rolCliente.setMontoHorasSobreTiempo(rs.getDouble("monto_horas_sobre_tiempo"));
                rolCliente.setMontoHorasExtras(Numeros.round(rs.getDouble("monto_horas_sobre_tiempo")
                        +rs.getDouble("monto_horas_suplementarias")));
                rolCliente.setBono(rs.getDouble("bono"));
                rolCliente.setTransporte(rs.getDouble("transporte"));
                rolCliente.setTotalBonos(rs.getDouble("total_bonos"));
                rolCliente.setVacaciones(rs.getDouble("vacaciones"));
                rolCliente.setSubtotal(rs.getDouble("subtotal"));
                rolCliente.setDecimoTercero(rs.getDouble("decimo_tercero"));
                rolCliente.setDecimoCuarto(rs.getDouble("decimo_cuarto"));
                rolCliente.setJubilacionPatronal(rs.getDouble("jubilacion_patronal"));
                rolCliente.setAportePatronal(rs.getDouble("aporte_patronal"));
                rolCliente.setSeguros(rs.getDouble("seguros"));
                rolCliente.setUniformes(rs.getDouble("uniformes"));
                rolCliente.setTotalIngreso(rs.getDouble("total_ingreso"));
                rolCliente.setEmpleado(rs.getString("empleado"));
                rolCliente.setCedula(rs.getString("cedula"));
                rolCliente.setEmpresa(rs.getString("empresa"));
                rolCliente.setClienteNombre(rs.getString("cliente_nombre"));
                rolCliente.setClienteId(rs.getInt("cliente_id"));
                rolCliente.setUsuarioId(rs.getInt("usuario_id"));
                list.add(rolCliente);
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
        return list;
    }
}
