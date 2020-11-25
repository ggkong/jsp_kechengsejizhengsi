package controller;

import entity.User;
import repository.impl.MyDesptopRepositoryImpl;
import vo.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class myDesktopServlet extends HttpServlet {
    private MyDesptopRepositoryImpl myDesptopRepository = new MyDesptopRepositoryImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询所有的 加班申请通过以及 请假申请
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String account = null;
        try {
            account = user.getAccount();
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage","系统错误");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }
        System.out.println(user.getAccount());
        if (account == null || account == ""){
            req.setAttribute("errorMessage","系统错误");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }else {
//           查询所有的加班申请
            List<Message> workString = myDesptopRepository.workMessage(account);
//            查询所有的请假申请
            List<Message> restString = myDesptopRepository.restMessage(account);
            List<String> Mymessage = new ArrayList<>();
            for (Message work:workString){
                String workMessage = "";
                if (work.getState().equals("1")){
                    workMessage = "您在"+work.getTime()+"的加班申请已通过";
                }else {
                    workMessage = "您在"+work.getTime()+"的加班申请已驳回";
                }
                Mymessage.add(workMessage);
            }
            for (Message rest:restString){
                String restMessage = "";
                if (rest.getState().equals("1")){
                    restMessage = "您在"+rest.getTime()+"的请假申请已通过";
                }else {
                    restMessage = "您在"+rest.getTime()+"的加班申请已驳回";
                }
                Mymessage.add(restMessage);
            }
            req.setAttribute("Mymessage",Mymessage);
            req.getRequestDispatcher("/mydesktop/myDesktop.jsp").forward(req,resp);
        }
    }
}
