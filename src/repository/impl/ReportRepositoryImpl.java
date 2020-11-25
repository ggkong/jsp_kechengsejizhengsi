package repository.impl;

import entity.Report;
import repository.ReportRepository;
import untity.JDBCTools;
import vo.ReportVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository {
    @Override
    public Integer insertReport(Report report) {
        String sql = "insert into t_report_record(account,report_date,work_process,work_content,tomorrow_plan,problem,other) values (?,?,?,?,?,?,?)";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,report.getAccount());
            preparedStatement.setDate(2,new java.sql.Date(report.getReportDate().getTime()));
            preparedStatement.setInt(3,report.getWork_process());
            preparedStatement.setString(4,report.getWork_content());
            preparedStatement.setString(5,report.getTomorrow_plan());
            preparedStatement.setString(6,report.getProblem());
            preparedStatement.setString(7,report.getOther());
            return_num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_num;
    }

    @Override
    public Integer getReportNumByTimeAndAccount(String account, Date date) {
        String sql = "select count(*) from t_report_record where account=? and report_date=?";
        Integer num = 0;
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,account);
            preparedStatement.setDate(2,new java.sql.Date(date.getTime()));
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
    public List<ReportVO> allReports(Integer index, Integer limit) {
        List<ReportVO> reportList = new ArrayList<>();
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select report.report_id,users.account,users.name,report.report_date,report.work_process,report.work_content,report.tomorrow_plan,report.problem,report.other from t_report_record as report,t_user_info as users where report.account = users.account limit ?,?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,index);
            preparedStatement.setInt(2,limit);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                reportList.add(new ReportVO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDate(4),resultSet.getInt(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return reportList;
    }

    @Override
    public Integer countReports() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from t_report_record";
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
    public Integer delReportByReportId(Integer report_id) {
        String sql = "delete from t_report_record where report_id = ?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,report_id);
            return_num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_num;
    }

    @Override
    public ReportVO getReportById(Integer report_id) {
        String sql = "select report.report_id,users.account,users.name,report.report_date,report.work_process,report.work_content,report.tomorrow_plan,report.problem,report.other from t_report_record as report,t_user_info as users where report.account = users.account and report.report_id = ?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ReportVO reportVO = new ReportVO();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,report_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                reportVO = new ReportVO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDate(4),resultSet.getInt(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return reportVO;

    }

    @Override
    public Integer upDateReport(ReportVO reportVO) {
        String sql = "update t_report_record set report_date=?,work_process=?,work_content=?,tomorrow_plan=?,problem=?,other=? where report_id =?";
        PreparedStatement preparedStatement = null;
        Connection connection = JDBCTools.getConnection();
        Integer return_num = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1,new java.sql.Date(reportVO.getDate().getTime()));
            preparedStatement.setInt(2,reportVO.getWork_progress());
            preparedStatement.setString(3,reportVO.getWork_content());
            preparedStatement.setString(4,reportVO.getTomorrow_plan());
            preparedStatement.setString(5,reportVO.getProblem());
            preparedStatement.setString(6,reportVO.getOther());
            preparedStatement.setInt(7,reportVO.getReport_id());
            return_num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_num;
    }

    @Override
    public List<ReportVO> selectReports(String name, String startDate, String endDate) {
        String sql = "select report.report_id,users.account,users.name,report.report_date,report.work_process,report.work_content,report.tomorrow_plan,report.problem,report.other from t_report_record as report,t_user_info as users where report.account = users.account and users.name like concat('%',?,'%') and report.report_date between ? and ? limit 0,6";
        List<ReportVO> reportVOList = new ArrayList<>();
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
                reportVOList.add(new ReportVO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDate(4),resultSet.getInt(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return reportVOList;
    }

    @Override
    public Integer countSelectReport(String name, String startDate, String endDate) {
        String sql = "select count(*) from t_report_record as report,t_user_info as users where report.account = users.account and users.name like concat('%',?,'%') and report.report_date between ? and ?";
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
}
