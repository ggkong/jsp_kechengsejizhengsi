package repository.impl;

import entity.Work;
import repository.WorkRepository;
import untity.JDBCTools;
import vo.WorkVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkRepositoryImpl implements WorkRepository {
    @Override
    public Integer insertWork(Work work) {
        String sql = "insert into t_work_record(account,work_date,start_time,end_time,work_time,work_cause,remark,state) values (?,?,?,?,?,?,?,?)";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,work.getAccount());
            preparedStatement.setString(2,work.getWork_date());
            preparedStatement.setString(3,work.getStart_time());
            preparedStatement.setString(4,work.getEnd_time());
            preparedStatement.setString(5,work.getWork_time());
            preparedStatement.setString(6,work.getWork_cause());
            preparedStatement.setString(7,work.getRemark());
            preparedStatement.setString(8,work.getState());
            return_num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_num;
    }

    @Override
    public Integer countWorkByAccountAndDate(String account, String workDate) {
        String sql = "select count(*) from t_work_record where account=? and work_date=?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer count_num = 0;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,account);
            preparedStatement.setString(2,workDate);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                count_num = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return count_num;
    }

    @Override
    public Integer countWorks() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from t_work_record";
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
    public List<WorkVO> allWorks(Integer index, Integer limit) {
        String sql = "select work.record_id,users.account,users.name,work.work_date,work.start_time,work.end_time,work.work_time,work.work_cause,work.remark,work.state from t_work_record as work,t_user_info as users where work.account=users.account limit ?,?";
        List<WorkVO> workVOS = new ArrayList<>();
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,index);
            preparedStatement.setInt(2,limit);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                workVOS.add(new WorkVO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return workVOS;
    }

    @Override
    public Integer delWork(Integer record_id) {
        String sql = "delete from t_work_record where record_id = ?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,record_id);
            return_num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_num;
    }

    @Override
    public List<WorkVO> selectWorks(String state, String startDate, String endDate) {
        String sql = "select work.record_id,users.account,users.name,work.work_date,work.start_time,work.end_time,work.work_time,work.work_cause,work.remark,work.state  from t_work_record as work,t_user_info as users where work.account=users.account and work.state = ? and work.work_date between ? and ? limit 0,6";
        List<WorkVO> workVOS = new ArrayList<>();
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, state);
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, endDate);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                workVOS.add(new WorkVO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return workVOS;
    }


    @Override
    public Integer countSelectWorks(String state, String startDate, String endDate) {
        String sql = "select count(*) from t_work_record as work,t_user_info as users where work.account=users.account and work.state = ? and work.work_date between ? and ?";
        Integer num = 0;
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,state);
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
    public Integer countWorksWait() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from t_work_record where state='0' ";
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
    public List<WorkVO> allWorksWait(Integer index, Integer limit) {
        String sql = "select work.record_id,users.account,users.name,work.work_date,work.start_time,work.end_time,work.work_time,work.work_cause,work.remark,work.state from t_work_record as work,t_user_info as users where work.account=users.account and work.state='0' limit ?,?";
        List<WorkVO> workVOS = new ArrayList<>();
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,index);
            preparedStatement.setInt(2,limit);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                workVOS.add(new WorkVO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return workVOS;
    }

    @Override
    public List<WorkVO> selectWorkWaits(String name, String startDate, String endDate,Integer index,Integer limit) {
        String sql = "select work.record_id,users.account,users.name,work.work_date,work.start_time,work.end_time,work.work_time,work.work_cause,work.remark,work.state  from t_work_record as work,t_user_info as users where work.account=users.account and work.state='0' and users.name = ? and work.work_date between ? and ? limit ?,?";
        List<WorkVO> workVOS = new ArrayList<>();
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
                workVOS.add(new WorkVO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return workVOS;
    }

    @Override
    public Integer countSelectWorkWaits(String name, String startDate, String endDate) {
        String sql = "select count(*) from t_work_record as work,t_user_info as users where work.account=users.account and work.state='0' and  users.name = ? and work.work_date between ? and ?";
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
    public Integer passwork(Integer record_id) {

        String sql="update t_work_record SET state='1' WHERE record_id=?";
        Integer num = 0;
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,record_id);
            num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return num;
    }

    @Override
    public Integer refausework(Integer record_id) {
        String sql="update t_work_record SET state='2' WHERE record_id=?";
        Integer num = 0;
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,record_id);
            num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return num;
    }

    @Override
    public WorkVO ShowWorkDetail(Integer record_id) {
        String sql = "select work.record_id,users.account,users.name,work.work_date,work.start_time,work.end_time,work.work_time,work.work_cause,work.remark,work.state  from t_work_record as work,t_user_info as users where work.account=users.account and work.record_id=?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        WorkVO workVO = new WorkVO();
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,record_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                workVO = new WorkVO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return workVO;
    }

}

