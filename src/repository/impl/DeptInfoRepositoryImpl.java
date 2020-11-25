package repository.impl;

import repository.DeptInfoRepository;
import untity.JDBCTools;
import vo.DeptVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeptInfoRepositoryImpl implements DeptInfoRepository {
    @Override
    public List<DeptVO> allDepts(Integer index, Integer limit) {
        List<DeptVO> deptVOList=new ArrayList<>();
        Connection connection= JDBCTools.getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        //发送sql文
        String sql="SELECT t_department.department_id,department_name,t_user_info.`name`,total_user,t_department.create_time FROM `t_department`,t_user_info WHERE t_department.manager=t_user_info.account  ORDER BY t_department.department_id ASC limit ?,? ";
        try {
            preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setInt(1,index);
            preparedStatement.setInt(2,limit);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                DeptVO deptVO=new DeptVO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getDate(5));
                deptVOList.add(deptVO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return  deptVOList;
    }

    @Override
    public List<String> allDepartmentName() {
        List<String> deptVOList=new ArrayList<>();
        Connection connection=JDBCTools.getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        //发送sql文
        String sql="select department_name from t_department";
        try {
            preparedStatement =connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                String deptVO=resultSet.getString(1);
                deptVOList.add(deptVO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return  deptVOList;
    }

    @Override
    public List<Integer> allDepartmentId() {
        List<Integer> deptVOList=new ArrayList<>();
        Connection connection=JDBCTools.getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        //发送sql文
        String sql="select department_id from t_department";
        try {
            preparedStatement =connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Integer deptVO=resultSet.getInt(1);
                deptVOList.add(deptVO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return  deptVOList;
    }

    @Override
    public List<String> allManageraccount() {
        List<String> allManageraccount=new ArrayList<>();
        String account=null;
        List<DeptVO> deptVOList=new ArrayList<>();
        Connection connection=JDBCTools.getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        //发送sql文
        String sql="SELECT t_user_info.account FROM `t_department`,t_user_info WHERE t_department.manager=t_user_info.account  ORDER BY t_department.department_id ASC ";
        try {
            preparedStatement =connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                account= resultSet.getString(1);
                allManageraccount.add(account);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return  allManageraccount;
    }
    @Override
    public List<String> allManagername() {
        List<String> allManagername=new ArrayList<>();
        String account=null;
        List<DeptVO> deptVOList=new ArrayList<>();
        Connection connection=JDBCTools.getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        //发送sql文
        String sql="SELECT t_user_info.`name` FROM `t_department`,t_user_info WHERE t_department.manager=t_user_info.account  ORDER BY t_department.department_id ASC";
        try {
            preparedStatement =connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                account= resultSet.getString(1);
                allManagername.add(account);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return  allManagername;
    }

    @Override
    public Integer countDepts() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from t_department  ";
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
    public DeptVO getDeptBydepartmentId(String departmentId) {
        String sql = "SELECT t_department.department_id,department_name,t_user_info.`name`,total_user,t_department.create_time,t_user_info.account FROM `t_department`,t_user_info WHERE t_department.manager=t_user_info.account and t_department.department_id=?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DeptVO deptVO = new DeptVO();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,departmentId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                deptVO.setDepartmentId(resultSet.getInt(1));
                deptVO.setDepartmentName(resultSet.getString(2));
                deptVO.setManagerName(resultSet.getString(3));
                deptVO.setTotal_user(resultSet.getInt(4));
                deptVO.setCreateTime(resultSet.getDate(5));
                deptVO.setAccount(resultSet.getString(6));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return deptVO;
    }

    @Override
    public Integer deptUpdate(DeptVO deptVO) {
        String sql = "update t_department SET department_name=?,manager=? where department_id=?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,deptVO.getDepartmentName());
            preparedStatement.setString(2,deptVO.getAccount());
            preparedStatement.setInt(3,deptVO.getDepartmentId());

            return_num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_num;
    }

    @Override
    public String getUserTypeByAccount(String account) {
        String sql="select user_type from t_user_info where account=?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String user_type=null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,account);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user_type=(resultSet.getString(1));


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return user_type;
    }

    @Override
    public Integer upDateusertypeByAccount(String account,String user_type,String mylevel) {
        String sql="UPDATE t_user_info set user_type=?,mylevel=? where account=?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num=0;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user_type);
            preparedStatement.setString(2,mylevel);
            preparedStatement.setString(3,account);
            return_num=preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_num;

    }

    @Override
    public Integer delDepts(String departmentIdstr) {
        Integer departmentId=Integer.parseInt(departmentIdstr);
        String sql="delete from t_department where department_id=?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num=0;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,departmentId);
            return_num=preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_num;
    }

    @Override
    public Integer insertcount() {

        String sql="update t_department SET total_user=total_user+1 WHERE department_id=0;";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num=0;

        try {
            preparedStatement = connection.prepareStatement(sql);
            return_num=preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_num;

    }

    @Override
    public Integer addDept(DeptVO deptVO) {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num = 0;
        String sql="INSERT into t_department VALUES(?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,deptVO.getDepartmentId());
            preparedStatement.setString(2,deptVO.getDepartmentName());
            preparedStatement.setString(3,deptVO.getAccount());
            preparedStatement.setInt(4,deptVO.getTotal_user());
            preparedStatement.setDate(5,new java.sql.Date(deptVO.getCreateTime().getTime()));
            return_num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_num;

    }

    @Override
    public DeptVO getDeptBydepartmentName(String departmentName) {
        String sql = "SELECT t_department.department_id,department_name,t_user_info.`name`,total_user,t_department.create_time,t_user_info.account FROM `t_department`,t_user_info WHERE t_department.manager=t_user_info.account and t_department.department_name=?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DeptVO deptVO = new DeptVO();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,departmentName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                deptVO.setDepartmentId(resultSet.getInt(1));
                deptVO.setDepartmentName(resultSet.getString(2));
                deptVO.setManagerName(resultSet.getString(3));
                deptVO.setTotal_user(resultSet.getInt(4));
                deptVO.setCreateTime(resultSet.getDate(5));
                deptVO.setAccount(resultSet.getString(6));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return deptVO;
    }
}
