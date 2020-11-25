package controller.user;

import repository.UserInfoRepository;
import repository.impl.UserInfoRepositoryImpl;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DelManyUserServle extends HttpServlet {
    private UserInfoRepository userInfoRepository = new UserInfoRepositoryImpl();
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] accounts = req.getParameterValues("account");
        System.out.println(accounts);
        if (accounts.length != 0 && accounts != null){
//            调用删除方法
            for (String account:accounts) {
                String name = userInfoRepository.getNameByAccount(account);
                System.out.println(name);
                userService.delUser(account,name);
            }
        }
        resp.sendRedirect(req.getContextPath()+"/user/showAllUser?page=1");
    }
}
