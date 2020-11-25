package service;

import entity.User;

public interface LoginService {
    public User login(String account,String password);
    public Integer getLoginState(String account,String password);
}
