package service.impl;


import repository.UserInfoRepository;
import repository.impl.UserInfoRepositoryImpl;
import service.LogoutService;

public class LogoutServiceImpl implements LogoutService {
    private UserInfoRepository userInfoRepository = new UserInfoRepositoryImpl();

    @Override
    public Integer setLoginState(Integer state, String account, String password) {
        return userInfoRepository.setLoginState(state, account, password);
    }
}
