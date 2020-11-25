package service;

import entity.User;
import vo.UserVO;

import java.util.List;

public interface UserService {
    public Integer insertUser(User user);
    public Integer delUser(String account,String name);
    public List<UserVO> allUsers(Integer page);
    public Integer getUserPages();
    public Integer count();
    public User getUserByAccount(String account);
    public Integer upDateUser(User user);
    public List<UserVO> selectUsers(String name,Integer deptId);
}
