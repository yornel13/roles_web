package dao;

import models.*;
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

    public List<RolCliente> findAllByFechaAndClienteId(String fecha, Integer clienteId, Integer empresaId) {

        List<RolCliente> list = new ArrayList<>();

        String q = "SELECT * FROM rol_cliente "
                + "JOIN usuario "
                + "ON usuario.id = rol_cliente.usuario_id "
                + "JOIN detalles_empleado "
                + "ON detalles_empleado.id = usuario.detalles_empleado_id "
                + "where inicio = '"+fecha+"' "
                + "and cliente_id = "+clienteId+" "
                + "and empresa_id = "+empresaId;
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

    public List<RolCliente> findAllByFechaAndUsuarioId(String fecha, Integer usuarioId) {

        List<RolCliente> list = new ArrayList<>();

        String q = "SELECT * FROM rol_cliente where inicio = '"+fecha+"' and usuario_id = "+usuarioId;
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

                if (list != null)
                    for (RolCliente rolCliente:
                         list) {
                        rolCliente.setUsuario(usuario);
                    }
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

    public RolCliente findById(Integer id) {

        RolCliente rolCliente = null;

        String q = "SELECT * FROM rol_cliente WHERE id = "+id;
        try {

            conn = new DBConnectionGuardias().conectar();
            stat = conn.createStatement();
            rs = stat.executeQuery(q);

            while (rs.next()) {

                rolCliente = new RolCliente();
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
            }

            q = "SELECT * FROM usuario WHERE id = "+rolCliente.getUsuarioId();
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
                rolCliente.setUsuario(usuario);
            }

            q = "SELECT * FROM detalles_empleado WHERE id = "+rolCliente.getUsuario().getDetallesEmpleadoId();
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
                rolCliente.getUsuario().setDetallesEmpleado(detallesEmpleado);
            }

            q = "SELECT * FROM empresa WHERE id = "+rolCliente.getUsuario().getDetallesEmpleado().getEmpresaId();
            rs = stat.executeQuery(q);

            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("id"));
                empresa.setNombre(rs.getString("nombre"));
                empresa.setSiglas(rs.getString("siglas"));
                empresa.setNumeracion(rs.getString("numeracion"));
                rolCliente.getUsuario().getDetallesEmpleado().setEmpresa(empresa);
            }

            q = "SELECT * FROM cargo WHERE id = "+rolCliente.getUsuario().getDetallesEmpleado().getCargoId();
            rs = stat.executeQuery(q);

            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("id"));
                cargo.setNombre(rs.getString("nombre"));
                cargo.setActivo(rs.getBoolean("activo"));
                rolCliente.getUsuario().getDetallesEmpleado().setCargo(cargo);
            }

            q = "SELECT * FROM cliente WHERE id = "+rolCliente.getClienteId();
            rs = stat.executeQuery(q);

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDetalles(rs.getString("detalles"));
                cliente.setRuc(rs.getString("ruc"));
                cliente.setActivo(rs.getBoolean("activo"));
                rolCliente.setCliente(cliente);
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
        return rolCliente;
    }
}
