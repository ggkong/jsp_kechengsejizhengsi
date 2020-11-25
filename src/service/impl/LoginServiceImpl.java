package service.impl;

import entity.User;
import repository.UserInfoRepository;
import repository.impl.UserInfoRepositoryImpl;
import service.LoginService;

public class LoginServiceImpl implements LoginService {
    private UserInfoRepository userInfoRepository = new UserInfoRepositoryImpl();

    @Override
    public User login(String account, String password) {
        User user = userInfoRepository.login(account,password);
        // 设置为已经更改的状态
        Integer return_num = userInfoRepository.setLoginState(1,account,password);
        if (return_num != 1){
            // 出错
            user = null;
            return user;
        }else {
            return user;
        }

    }

    @Override
    public Integer getLoginState(String account, String password) {
        return userInfoRepository.getLoginState(account,password);
    }


    public static void main(String[] args) {
        LoginService loginService = new LoginServiceImpl();
        System.out.println(loginService.login("000001","123123"));
    }
}
