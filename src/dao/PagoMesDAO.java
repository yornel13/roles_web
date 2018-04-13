package dao;

import models.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PagoMesDAO {

    private Connection conn;
    private Statement stat;
    private ResultSet rs;

    public PagoMes findByRolIndividualId(Integer rolIndividualId) {

        PagoMes pago = null;
        List<PagoMesItem> pagoMesItems;

        String q = "SELECT * FROM pago_mes where rol_individual_id = "+rolIndividualId;
        try {

            conn = new DBConnectionGuardias().conectar();
            stat = conn.createStatement();
            rs = stat.executeQuery(q);

            while (rs.next()) {
                pago = new PagoMes();
                pago.setId(rs.getInt("id"));
                pago.setUsuarioId(rs.getInt("usuario_id"));
                pago.setInicioMes(rs.getString("inicio_mes"));
                pago.setFinMes(rs.getString("fin_mes"));
                pago.setFecha(rs.getTimestamp("fecha"));
                pago.setMonto(rs.getDouble("monto"));
            }

            pagoMesItems = new ArrayList<>();
            q = "SELECT * FROM pago_mes_item WHERE pago_mes_id = "+pago.getId();
            rs = stat.executeQuery(q);

            while (rs.next()) {
                PagoMesItem item = new PagoMesItem();
                item.setId(rs.getInt("id"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setDias(rs.getDouble("dias"));
                item.setHoras(rs.getDouble("horas"));
                item.setIngreso(rs.getDouble("ingreso"));
                item.setDeduccion(rs.getDouble("deduccion"));
                item.setClave(rs.getString("clave"));

                if (item != null)
                    pagoMesItems.add(item);
            }

            pago.setPagosItems(pagoMesItems);
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
        return pago;
    }

}
