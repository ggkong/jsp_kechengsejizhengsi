package controller.user;

import entity.User;
import repository.AccountRepository;
import repository.impl.AccountRepositoryImpl;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AddUserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private AccountRepository accountRepository = new AccountRepositoryImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String department_idStr = req.getParameter("department_id");
        Integer department_id = null;
        department_id = Integer.parseInt(department_idStr);
        String sex = req.getParameter("sex");
        String birthday = req.getParameter("birthday");
        String mobile = req.getParameter("mobile");
        String email = req.getParameter("email");
        // 默认添加的员工 为 普通员工
        String user_type = "2";
        String mylevel = "2";
        Date create_time = new Date();
        String state = "0";
        //如果 账户的 account 已经存在 就将其 返回回去 不允许 进行 添加
        List<String> accounts = accountRepository.getAllAccount();
        if (account == null || account == ""){
            resp.sendRedirect(req.getContextPath()+"/user/userInsert.jsp");
            return;
        }
        for (String acc: accounts) {
            if (acc.equals(account)){
//                如果出现 account 一样的情况 返回回去
                req.setAttribute("errorMessage","工号重复不能添加");
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
                return;
            }
        }
        User user = new User(account,password,name,department_id,sex,birthday,mobile,email,user_type,mylevel,create_time,state);
        userService.insertUser(user);
        // 添加到
        resp.sendRedirect(req.getContextPath()+"/user/showAllUser?page=1");

    }
}
