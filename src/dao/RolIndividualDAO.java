package dao;

import models.DetallesEmpleado;
import models.Empresa;
import models.RolIndividual;
import models.Usuario;
import models.Cargo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RolIndividualDAO {

    private Connection conn;
    private Statement stat;
    private ResultSet rs;

    public RolIndividual findByFechaAndUsuarioId(String fecha, Integer usuarioId) {

        RolIndividual rol = null;

        String q = "SELECT * FROM rol_individual where inicio = '"+fecha+"' and usuario_id = "+usuarioId;
        try {

            conn = new DBConnectionGuardias().conectar();
            stat = conn.createStatement();
            rs = stat.executeQuery(q);

            while (rs.next()) {

                rol = new RolIndividual();
                rol.setId(rs.getInt("id"));
                rol.setInicio(rs.getString("inicio"));
                rol.setFinalizo(rs.getString("finalizo"));
                rol.setDetalles(rs.getString("detalles"));
                rol.setFecha(rs.getTimestamp("fecha"));
                rol.setSueldo(rs.getDouble("sueldo"));
                rol.setDias(rs.getDouble("dias"));
                rol.setHorasNormales(rs.getDouble("horas_normales"));
                rol.setHorasSuplementarias(rs.getDouble("horas_suplementarias"));
                rol.setHorasSobreTiempo(rs.getDouble("horas_sobre_tiempo"));
                rol.setTotalHorasExtras(rs.getDouble("total_horas_extras"));
                rol.setSalario(rs.getDouble("salario"));
                rol.setMontoHorasSuplementarias(rs.getDouble("monto_horas_suplementarias"));
                rol.setMontoHorasSobreTiempo(rs.getDouble("monto_horas_sobre_tiempo"));
                rol.setBono(rs.getDouble("bono"));
                rol.setTransporte(rs.getDouble("transporte"));
                rol.setTotalBonos(rs.getDouble("total_bonos"));
                rol.setVacaciones(rs.getDouble("vacaciones"));
                rol.setSubtotal(rs.getDouble("subtotal"));
                rol.setDecimoTercero(rs.getDouble("decimo_tercero"));
                rol.setDecimoCuarto(rs.getDouble("decimo_cuarto"));
                rol.setJubilacionPatronal(rs.getDouble("jubilacion_patronal"));
                rol.setAportePatronal(rs.getDouble("aporte_patronal"));
                rol.setSeguros(rs.getDouble("seguros"));
                rol.setUniformes(rs.getDouble("uniformes"));
                rol.setTotalIngreso(rs.getDouble("total_ingreso"));
                rol.setEmpleado(rs.getString("empleado"));
                rol.setCedula(rs.getString("cedula"));
                rol.setEmpresa(rs.getString("empresa"));
            }
            q = "SELECT * FROM usuario WHERE id = "+usuarioId;
            rs = stat.executeQuery(q);

            while (rs.next()) {
                Usuario usuario = new Usuario();
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

                if (rol != null)
                    rol.setUsuario(usuario);
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
        return rol;
    }

    public RolIndividual findById(Integer id) {

        RolIndividual rolIndividual = null;

        String q = "SELECT * FROM rol_individual WHERE id = "+id;
        try {

            conn = new DBConnectionGuardias().conectar();
            stat = conn.createStatement();
            rs = stat.executeQuery(q);

            while (rs.next()) {

                rolIndividual = new RolIndividual();
                rolIndividual.setId(rs.getInt("id"));
                rolIndividual.setUsuarioId(rs.getInt("usuario_id"));
                rolIndividual.setInicio(rs.getString("inicio"));
                rolIndividual.setFinalizo(rs.getString("finalizo"));
                rolIndividual.setDetalles(rs.getString("detalles"));
                rolIndividual.setFecha(rs.getTimestamp("fecha"));
                rolIndividual.setSueldo(rs.getDouble("sueldo"));
                rolIndividual.setDias(rs.getDouble("dias"));
                rolIndividual.setHorasNormales(rs.getDouble("horas_normales"));
                rolIndividual.setHorasSuplementarias(rs.getDouble("horas_suplementarias"));
                rolIndividual.setHorasSobreTiempo(rs.getDouble("horas_sobre_tiempo"));
                rolIndividual.setTotalHorasExtras(rs.getDouble("total_horas_extras"));
                rolIndividual.setSalario(rs.getDouble("salario"));
                rolIndividual.setMontoHorasSuplementarias(rs.getDouble("monto_horas_suplementarias"));
                rolIndividual.setMontoHorasSobreTiempo(rs.getDouble("monto_horas_sobre_tiempo"));
                rolIndividual.setBono(rs.getDouble("bono"));
                rolIndividual.setTransporte(rs.getDouble("transporte"));
                rolIndividual.setTotalBonos(rs.getDouble("total_bonos"));
                rolIndividual.setVacaciones(rs.getDouble("vacaciones"));
                rolIndividual.setSubtotal(rs.getDouble("subtotal"));
                rolIndividual.setDecimoTercero(rs.getDouble("decimo_tercero"));
                rolIndividual.setDecimoCuarto(rs.getDouble("decimo_cuarto"));
                rolIndividual.setJubilacionPatronal(rs.getDouble("jubilacion_patronal"));
                rolIndividual.setAportePatronal(rs.getDouble("aporte_patronal"));
                rolIndividual.setSeguros(rs.getDouble("seguros"));
                rolIndividual.setUniformes(rs.getDouble("uniformes"));
                rolIndividual.setTotalIngreso(rs.getDouble("total_ingreso"));
                rolIndividual.setEmpleado(rs.getString("empleado"));
                rolIndividual.setCedula(rs.getString("cedula"));
                rolIndividual.setEmpresa(rs.getString("empresa"));
            }

            q = "SELECT * FROM usuario WHERE id = "+rolIndividual.getUsuarioId();
            rs = stat.executeQuery(q);

            while (rs.next()) {
                Usuario usuario = new Usuario();
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
                rolIndividual.setUsuario(usuario);
            }

            q = "SELECT * FROM detalles_empleado WHERE id = "+rolIndividual.getUsuario().getDetallesEmpleadoId();
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
                rolIndividual.getUsuario().setDetallesEmpleado(detallesEmpleado);
            }

            q = "SELECT * FROM empresa WHERE id = "+rolIndividual.getUsuario().getDetallesEmpleado().getEmpresaId();
            rs = stat.executeQuery(q);

            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("id"));
                empresa.setNombre(rs.getString("nombre"));
                empresa.setSiglas(rs.getString("siglas"));
                empresa.setNumeracion(rs.getString("numeracion"));
                rolIndividual.getUsuario().getDetallesEmpleado().setEmpresa(empresa);
            }

            q = "SELECT * FROM cargo WHERE id = "+rolIndividual.getUsuario().getDetallesEmpleado().getCargoId();
            rs = stat.executeQuery(q);

            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("id"));
                cargo.setNombre(rs.getString("nombre"));
                cargo.setActivo(rs.getBoolean("activo"));
                rolIndividual.getUsuario().getDetallesEmpleado().setCargo(cargo);
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
        return rolIndividual;
    }
}
