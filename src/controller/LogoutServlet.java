package controller;

import entity.User;
import service.LogoutService;
import service.impl.LogoutServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    private LogoutService logoutService = new LogoutServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入登出");
//        将session 中的 user 清除
        HttpSession session = req.getSession();
        try {
            User user = (User) session.getAttribute("user");
            logoutService.setLoginState(0,user.getAccount(),user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
            return;
        }
        session.invalidate();
        resp.sendRedirect(req.getContextPath()+"/login.jsp");
    }
}
