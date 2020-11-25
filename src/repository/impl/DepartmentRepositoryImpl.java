package repository.impl;

import entity.Department;
import repository.DepartmentRepository;
import untity.JDBCTools;
import vo.DeptVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepositoryImpl implements DepartmentRepository {

    @Override
    public Integer addDeaprtmentTotalUser(Integer departmentId) {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num = 0;
        Integer total_num = getTotalUserByDepartmentId(departmentId);
        total_num++;
        String sql = "update t_department set total_user = ? where department_id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,total_num);
            preparedStatement.setInt(2,departmentId);
            return_num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_num;
    }

    @Override
    public Integer reduceDeaprtmentTotalUser(Integer departmentId) {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num = 0;
        Integer total_num = getTotalUserByDepartmentId(departmentId);
        total_num--;
        String sql = "update t_department set total_user = ? where department_id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,total_num);
            preparedStatement.setInt(2,departmentId);
            return_num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_num;
    }

    @Override
    public Integer getTotalUserByDepartmentId(Integer departmentId) {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer totalNum = null;
        String sql = "select total_user from t_department where department_id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,departmentId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                totalNum = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return totalNum;
    }

    @Override
    public List<Department> getAllDepartmentIdAndName() {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Department> departments = new ArrayList<>();
        String sql = "select department_id,department_name from t_department";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                departments.add(new Department(resultSet.getInt(1),resultSet.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return departments;
    }

    @Override
    public List<DeptVO> getAllDepartment() {
        String sql = "select dept.department_id,dept.department_name,users.name,dept.total_user,dept.create_time from t_department as dept,t_user_info as users where dept.manager = users.account order by dept.department_id";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<DeptVO> allDepartmentList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                allDepartmentList.add(new DeptVO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getDate(5)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return allDepartmentList;
    }
}
