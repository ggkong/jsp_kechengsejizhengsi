package repository.impl;

import entity.Department;
import repository.ManagerRepository;
import untity.JDBCTools;
import vo.UserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerRepositoryImpl implements ManagerRepository {
    @Override
    public List<UserVO> getAllManager() {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<UserVO> manager = new ArrayList<>();
        String sql = "select DISTINCT account,name from t_user_info";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                manager.add(new UserVO(resultSet.getString(1),resultSet.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return manager;
    }
}
