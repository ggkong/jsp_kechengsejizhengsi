package service.impl;

import entity.User;
import repository.AccountRepository;
import repository.DepartmentRepository;
import repository.UserInfoRepository;
import repository.impl.AccountRepositoryImpl;
import repository.impl.DepartmentRepositoryImpl;
import repository.impl.UserInfoRepositoryImpl;
import service.UserService;
import vo.UserVO;

import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserInfoRepository userInfoRepository = new UserInfoRepositoryImpl();
    private DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
    private AccountRepository accountRepository = new AccountRepositoryImpl();
    private final int LIMIT = 4;   // 设置 每一页显示 四条

    @Override
    public Integer insertUser(User user) {
//        1 将user 对象插入到 数据库中
//        2 更新depart 表中的人数
//        3 更新account 中的account 号
        Integer return_num = 0;
        if (userInfoRepository.insertUser(user) != 0){
            // 插入成功
            departmentRepository.addDeaprtmentTotalUser(user.getDepartment_id());
            accountRepository.insertAccount(user.getAccount());
            return_num = 1;
        }else {
            return 0;
        }
        return return_num;
    }

    @Override
    public List<UserVO> allUsers(Integer page) {
        Integer index = (page - 1)*LIMIT;
        return userInfoRepository.allUsers(index,LIMIT);
    }

    @Override
    public Integer getUserPages() {
        Integer count = userInfoRepository.countUsers();
        if (count % LIMIT == 0){
            return count/LIMIT;
        }else {
            return count/LIMIT + 1;
        }
    }

    @Override
    public Integer count() {
        return userInfoRepository.countUsers();
    }

    @Override
    public User getUserByAccount(String account) {
        return userInfoRepository.getUserByAccount(account);
    }

    @Override
    public Integer upDateUser(User user) {
//        查询出数据库现在已经有的User
        Integer return_num = 0;
        User userwillUpdate = userInfoRepository.getUserByAccount(user.getAccount());
        if (userwillUpdate.getDepartment_id().equals(user.getDepartment_id())){
            // 如果没有发生部门之间的调动
//            直接进行更新即可 或者进行一次删除一次添加
            userInfoRepository.userUpdate(user);
            return 1;
        }else {
//            进行修改 部门人数  将要被修改的部门减一 将要被增加的部门 加一
            departmentRepository.addDeaprtmentTotalUser(user.getDepartment_id());
            departmentRepository.reduceDeaprtmentTotalUser(userwillUpdate.getDepartment_id());
//            调用修改方法
            userInfoRepository.userUpdate(user);
            return 1;
        }

    }

    @Override
    public List<UserVO> selectUsers(String name, Integer deptId) {
        return userInfoRepository.selectUsers(name,deptId);
    }

    @Override
    public Integer delUser(String account, String name) {
        Integer return_num = 0;
        Integer departmentId = userInfoRepository.getDepartmentIdByAccount(account);
        if (userInfoRepository.delUserByAccountAndName(account,name) != 0){
            System.out.println(departmentId);
            System.out.println("上面是查询的部门id结果");
            departmentRepository.reduceDeaprtmentTotalUser(departmentId);
            accountRepository.delAccount(account);
            return_num = 1;
        }else {
            return 0;
        }
        return return_num;
    }
}
