package dao;

import models.ControlExtras;
import models.PagoVacaciones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagoVacacionesDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public PagoVacaciones findInDeterminateYearByUsuarioId(String year, Integer usuarioId) {

        PagoVacaciones pagoVacaciones = null;

        String q = "SELECT * FROM pago_vacaciones WHERE usuario_id = ? and SUBSTR(inicio, 1, 4) = ?";

        connection = new DBConnectionGuardias().conectar();

        try {
            preparedStatement = connection.prepareStatement(q);
            preparedStatement.setInt(1, usuarioId);
            preparedStatement.setString(2, year);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                pagoVacaciones = new PagoVacaciones();
                pagoVacaciones.setId(resultSet.getInt("id"));
                pagoVacaciones.setDias(resultSet.getInt("dias"));
                pagoVacaciones.setInicio(resultSet.getString("inicio"));
                pagoVacaciones.setFinalizo(resultSet.getString("finalizo"));
                pagoVacaciones.setGoceInicio(resultSet.getDate("goce_inicio"));
                pagoVacaciones.setGoceFin(resultSet.getDate("goce_fin"));
                pagoVacaciones.setFecha(resultSet.getTimestamp("fecha"));
                pagoVacaciones.setSueldo(resultSet.getDouble("sueldo"));
                pagoVacaciones.setDevengado(resultSet.getDouble("devengado"));
                pagoVacaciones.setMonto(resultSet.getDouble("monto"));
                pagoVacaciones.setAporte(resultSet.getDouble("aporte"));
                pagoVacaciones.setValor(resultSet.getDouble("valor"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return pagoVacaciones;
    }





}
