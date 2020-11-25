package repository.impl;

import untity.JDBCTools;
import vo.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyDesptopRepositoryImpl {
    public List<Message> restMessage(String account){
        String sql = "select rest_start_date,state from t_rest_record where account = ? and state <> '0';";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Message> restMessage = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,account);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                restMessage.add(new Message(resultSet.getString(1),resultSet.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return restMessage;
    }

    public List<Message> workMessage(String account){
        String sql = "select work_date,state from t_work_record where account = ? and state <> '0';";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Message> workMessage = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,account);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                workMessage.add(new Message(resultSet.getString(1),resultSet.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return workMessage;
    }
}
