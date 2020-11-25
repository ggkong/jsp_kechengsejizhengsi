package repository.impl;

import entity.Rest;
import repository.RestRepository;
import untity.JDBCTools;
import vo.RestVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestRepositoryImpl implements RestRepository {

    @Override
    public Integer countRests() {
        String sql = "select count(*) from t_rest_record as rest,t_user_info as users where rest.account = users.account";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer num = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                num = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return num;
    }

    @Override
    public List<RestVO> allRests(Integer index, Integer limit) {
        String sql = "select rest.rest_id,rest.account,users.name,rest.rest_start_date,rest.rest_end_date,rest.rest_time,rest.rest_cause,rest.remark,rest.state from t_rest_record as rest,t_user_info as users where rest.account = users.account limit ?,?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<RestVO> restVOS = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,index);
            preparedStatement.setInt(2,limit);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                restVOS.add(new RestVO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return restVOS;
    }

    @Override
    public Integer insertRest(Rest rest) {
        String sql = "insert into t_rest_record(account,rest_start_date,rest_end_date,rest_time,rest_cause,remark,state) values (?,?,?,?,?,?,?)";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,rest.getAccount());
            preparedStatement.setString(2,rest.getRest_start_date());
            preparedStatement.setString(3,rest.getRest_end_date());
            preparedStatement.setString(4,rest.getRest_time());
            preparedStatement.setString(5,rest.getRest_cause());
            preparedStatement.setString(6,rest.getRemark());
            preparedStatement.setString(7,rest.getState());
            return_num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_num;
    }

    @Override
    public List<RestVO> selectRests(String restStartDate, String restEndDate, String state, Integer index, Integer limit) {
        String sql = "select rest.rest_id,rest.account,users.name,rest.rest_start_date,rest.rest_end_date,rest.rest_time,rest.rest_cause,rest.remark,rest.state from t_rest_record as rest,t_user_info as users where rest.account = users.account and rest.rest_start_date>? and rest.rest_end_date<? and rest.state = ? limit ?,?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<RestVO> restVOS = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,restStartDate);
            preparedStatement.setString(2,restEndDate);
            preparedStatement.setString(3,state);
            preparedStatement.setInt(4,index);
            preparedStatement.setInt(5,limit);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                restVOS.add(new RestVO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return restVOS;
    }

    @Override
    public Integer selectCount(String restStartDate, String restEndDate, String state) {
        String sql = "select count(*) from t_rest_record as rest,t_user_info as users where rest.account = users.account and rest.rest_start_date>? and rest.rest_end_date<? and rest.state = ?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer num = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,restStartDate);
            preparedStatement.setString(2,restEndDate);
            preparedStatement.setString(3,state);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                num = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return num;
    }

    @Override
    public RestVO selectRestByRestId(Integer restId) {
        String sql = "select rest.rest_id,rest.account,users.name,rest.rest_start_date,rest.rest_end_date,rest.rest_time,rest.rest_cause,rest.remark,rest.state from t_rest_record as rest,t_user_info as users where rest.rest_id=? and rest.account = users.account";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        RestVO restVO = new RestVO();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,restId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                restVO = new RestVO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return restVO;
    }

    @Override
    public Integer upDateRest(Integer rest_id, String startDate, String endDate, String rest_time, String cause) {
        String sql = "update t_rest_record set rest_start_date=?,rest_end_date=?,rest_time=?,rest_cause=? where rest_id=?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,startDate);
            preparedStatement.setString(2,endDate);
            preparedStatement.setString(3,rest_time);
            preparedStatement.setString(4,cause);
            preparedStatement.setInt(5,rest_id);
            return_num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_num;
    }

    @Override
    public Integer delRestById(Integer rest_id) {
        String sql = "delete from t_rest_record where rest_id = ?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,rest_id);
            return_num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_num;
    }

    @Override
    public int[] delManyRest(String[] delIds) {
        String sql = "delete from t_rest_record where rest_id = ?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        int[] return_nums = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (String id:delIds) {
                Integer delId = Integer.parseInt(id);
                preparedStatement.setInt(1,delId);
                preparedStatement.addBatch();
            }
            return_nums = preparedStatement.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_nums;
    }
    @Override
    public List<RestVO> allRestsWait(Integer index, Integer limit) {
        String sql = "select rest.rest_id,users.account,users.name,rest.rest_start_date,rest.rest_end_date,rest.rest_time,rest.rest_cause,rest.remark,rest.state from t_rest_record as rest,t_user_info as users where rest.account=users.account and rest.state='0' limit ?,?";
        List<RestVO> restVOS = new ArrayList<>();
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,index);
            preparedStatement.setInt(2,limit);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                restVOS.add(new RestVO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return restVOS;
    }

    @Override
    public Integer countRestsWait() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from t_rest_record where state='0' ";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return count;
    }

    @Override
    public List<RestVO> selectRestWaits(String name, String startDate, String endDate, Integer index, Integer limit) {
        String sql = "select rest.rest_id,users.account,users.name,rest.rest_start_date,rest.rest_end_date,rest.rest_time,rest.rest_cause,rest.remark,rest.state from t_rest_record as rest,t_user_info as users where rest.account=users.account and users.name=? and  rest.state='0' AND rest.rest_start_date>? and rest.rest_end_date<? limit ?,?";
        List<RestVO> restVOS = new ArrayList<>();
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, endDate);
            preparedStatement.setInt(4,index);
            preparedStatement.setInt(5,limit);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                restVOS.add(new RestVO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return restVOS;
    }

    @Override
    public Integer countSelectRestWaits(String name, String startDate, String endDate) {
        String sql = "select count(*) from t_rest_record as rest,t_user_info as users where rest.account=users.account and users.name = ?  and rest.state='0' and  rest.rest_start_date>? and rest.rest_end_date<? ";
        Integer num = 0;
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,startDate);
            preparedStatement.setString(3,endDate);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                num = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return num;
    }

    @Override
    public Integer passrest(Integer rest_id) {
        String sql="update t_rest_record SET state='1' WHERE rest_id=?";
        Integer num = 0;
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,rest_id);
            num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return num;
    }

    @Override
    public Integer refauserest(Integer rest_id) {
        String sql="update t_rest_record SET state='2' WHERE rest_id=?";
        Integer num = 0;
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,rest_id);
            num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return num;
    }
}
