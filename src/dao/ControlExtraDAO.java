package dao;

import models.ControlExtras;

import javax.management.Query;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ControlExtraDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public List<ControlExtras> findAllByEmpleadoIdByDeterminateTime(
            Integer usuarioId, Date inicio, Date fin) {
        List<ControlExtras>  controlExtrasList = new ArrayList<>();

        String q = "SELECT * FROM control_extras WHERE usuario_id = ? and fecha >= ? and fecha <= ? order by fecha";

        connection = new DBConnectionGuardias().conectar();

        try {
            preparedStatement = connection.prepareStatement(q);
            preparedStatement.setInt(1, usuarioId);
            preparedStatement.setDate(2, inicio);
            preparedStatement.setDate(3, fin);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                controlExtrasList.add(new ControlExtras(resultSet.getInt("id"),
                        resultSet.getInt("usuario_id"),
                        resultSet.getInt("cliente_id"),
                        resultSet.getDate("fecha"),
                        resultSet.getString("caso"),
                        resultSet.getDouble("recargo"),
                        resultSet.getDouble("sobretiempo"),
                        resultSet.getTime("entrada"),
                        resultSet.getTime("salida")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return controlExtrasList;
    }





}
