package repository;

import entity.User;
import vo.UserVO;

import java.util.List;

public interface UserInfoRepository {
    public User login(String account,String password);
    public Integer setLoginState(Integer state, String account, String password);
    public Integer getLoginState(String account,String password);
    public Integer insertUser(User user);
    public Integer delUserByAccountAndName(String account,String name);
    public List<UserVO> allUsers(Integer index,Integer limit);
    public Integer countUsers();
    public Integer getDepartmentIdByAccount(String account);
    public User getUserByAccount(String account);
    public Integer userUpdate(User user);
    public String getNameByAccount(String account);
    public List<UserVO> selectUsers(String name, Integer deptId);
    public Integer selectUserCount(String name,Integer deptId);
    public List<Integer> getAllManager();
    public Integer cleardepartmentNameBydepartmentId(String departmentId);
}
