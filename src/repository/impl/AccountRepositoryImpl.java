package repository.impl;

import com.mysql.cj.jdbc.JdbcConnection;
import repository.AccountRepository;
import untity.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public Integer insertAccount(String account) {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num = 0;
        String sql = "insert into t_account(account) values (?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,account);
            return_num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return return_num;
        }
    }

    @Override
    public Integer delAccount(String account) {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num = 0;
        String sql = "delete from t_account where account = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,account);
            return_num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return return_num;
        }
    }

    @Override
    public List<String> getAllAccount() {
        String sql = "select account from t_account";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> accounts = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                accounts.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return accounts;
    }
}
