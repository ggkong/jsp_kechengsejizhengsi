package repository.impl;

import entity.User;
import repository.UserInfoRepository;
import untity.JDBCTools;
import vo.UserVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserInfoRepositoryImpl implements UserInfoRepository {
    @Override
    public  User login(String account, String password) {
        System.out.println("开始访问数据库");
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        String sql = "select * from t_user_info where account = ? and password =?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,account);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11),resultSet.getDate(12),resultSet.getString(13));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return user;
    }

    @Override
    public Integer setLoginState(Integer state, String account, String password) {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "update t_user_info set state = ? where account = ? and password = ?";
        int num = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,state);
            preparedStatement.setString(2,account);
            preparedStatement.setString(3,password);
            num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return num;
    }

    @Override
    public Integer getLoginState(String account, String password) {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer state_num = null;
        String sql = "select state from t_user_info where account = ? and password = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,account);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                state_num = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return state_num;
    }

    @Override
    public Integer insertUser(User user) {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num = 0;
        String sql = "insert into t_user_info(account,password,name,department_id,sex,birthday,mobile,email,user_type,mylevel,create_time,state) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getAccount());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getName());
            preparedStatement.setInt(4,user.getDepartment_id());
            preparedStatement.setString(5,user.getSex());
            preparedStatement.setString(6,user.getBirthday());
            preparedStatement.setString(7,user.getMobile());
            preparedStatement.setString(8,user.getEmail());
            preparedStatement.setString(9,user.getUser_type());
            preparedStatement.setString(10,user.getMylevel());
            preparedStatement.setDate(11, new java.sql.Date(user.getCreate_time().getTime()));
            preparedStatement.setString(12,user.getState());
            return_num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_num;
    }

    @Override
    public Integer delUserByAccountAndName(String account, String name) {
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num = 0;
        String sql = "delete from t_user_info where account = ? and name = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,account);
            preparedStatement.setString(2,name);
            return_num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_num;
    }

    @Override
    public List<UserVO> allUsers(Integer index,Integer limit) {
        List<UserVO>  userVOList = new ArrayList<>();
        Connection connection= JDBCTools.getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet = null;
        //发送sql文
        String sql="select t_user_info.account,t_user_info.name,t_department.department_name,t_user_info.user_type,t_department.create_time,t_user_info.sex,t_user_info.mobile,t_user_info.birthday,t_user_info.email from t_department,t_user_info WHERE t_department.department_id=t_user_info.department_id limit ?,?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,index);
            preparedStatement.setInt(2,limit);
            resultSet = preparedStatement.executeQuery();
            //处理结果
            while(resultSet.next()){
                UserVO userVO = new UserVO(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getDate(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
                userVOList.add(userVO);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return userVOList;
    }

    @Override
    public Integer countUsers() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from t_user_info,t_department where t_department.department_id=t_user_info.department_id";
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
    public Integer getDepartmentIdByAccount(String account) {
        String sql = "select department_id from t_user_info where account = ?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer department_id = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,account);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                department_id = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return department_id;
    }

    @Override
    public User getUserByAccount(String account) {
        String sql = "select account,password,name,department_id,sex,mobile,birthday,email from t_user_info where account = ?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = new User();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,account);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user.setAccount(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
                user.setName(resultSet.getString(3));
                user.setDepartment_id(resultSet.getInt(4));
                user.setSex(resultSet.getString(5));
                user.setMobile(resultSet.getString(6));
                user.setBirthday(resultSet.getString(7));
                user.setEmail(resultSet.getString(8));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return user;
    }

    @Override
    public Integer userUpdate(User user) {
        String sql = "update t_user_info set password = ?,name = ?,department_id = ?,sex = ?,birthday = ?,mobile = ?,email = ? where account = ?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        Integer return_num = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getPassword());
            preparedStatement.setString(2,user.getName());
            preparedStatement.setInt(3,user.getDepartment_id());
            preparedStatement.setString(4,user.getSex());
            preparedStatement.setString(5,user.getBirthday());
            preparedStatement.setString(6,user.getMobile());
            preparedStatement.setString(7,user.getEmail());
            preparedStatement.setString(8,user.getAccount());
            return_num = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
        return return_num;
    }

    @Override
    public String getNameByAccount(String account) {
        String sql = "select name from t_user_info where account = ?";
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String name = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,account);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                name = resultSet.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return name;
    }

    @Override
    public List<UserVO> selectUsers(String name, Integer deptId) {
        List<UserVO>  userVOList = new ArrayList<>();
        Connection connection= JDBCTools.getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet = null;
        //发送sql文
        String sql="select t_user_info.account,t_user_info.name,t_department.department_name,t_user_info.user_type,t_department.create_time,t_user_info.sex,t_user_info.mobile,t_user_info.birthday,t_user_info.email from t_department,t_user_info WHERE t_department.department_id=t_user_info.department_id and t_user_info.name like  concat('%',?,'%')  and t_user_info.department_id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,deptId);
            resultSet = preparedStatement.executeQuery();
            //处理结果
            while(resultSet.next()){
                UserVO userVO = new UserVO(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getDate(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
                userVOList.add(userVO);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return userVOList;
    }

    @Override
    public Integer selectUserCount(String name, Integer deptId) {
        Integer total = 0;
        Connection connection= JDBCTools.getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet = null;
        //发送sql文
        String sql="select count(*) from t_department,t_user_info WHERE t_department.department_id=t_user_info.department_id and t_user_info.name like  concat('%',?,'%')  and t_user_info.department_id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,deptId);
            resultSet = preparedStatement.executeQuery();
            //处理结果
            while(resultSet.next()){
                total = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return total;
    }
    @Override
    public List<Integer> getAllManager() {
        String sql = "select DISTINCT department_id from t_user_info";
        Integer manager=null;
        List<Integer> allmanagers=new ArrayList<>();
        Connection connection = JDBCTools.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                manager=resultSet.getInt(1);
                allmanagers.add(manager);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return allmanagers;
    }


    @Override
    public Integer cleardepartmentNameBydepartmentId(String departmentId) {
        String sql = "update t_user_info set t_user_info.department_id=0 WHERE t_user_info.department_id=?";
        Connection connection = JDBCTools.getConnection();
        Integer return_num=null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String name = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,departmentId);
            return_num = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return return_num;


    }

    public static void main(String[] args) {
        UserInfoRepository userInfoRepository = new UserInfoRepositoryImpl();
//        userInfoRepository.allUsers();
//        System.out.println(userInfoRepository.countUsers());
        System.out.println(userInfoRepository.getNameByAccount("000001"));
    }
}
