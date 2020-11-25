package controller.user;

import entity.Department;
import entity.User;
import service.DepartmentService;
import service.UserService;
import service.impl.DepartmentServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class updateUserServlet extends HttpServlet {
    private DepartmentService departmentService = new DepartmentServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        System.out.println("拿到的account 是什么");
        System.out.println(account);
        User user = userService.getUserByAccount(account);
        req.setAttribute("userWillUpdate",user);
        req.getRequestDispatcher("/user/userUpdate.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String department_idStr = req.getParameter("department_id");
        Integer department_id = Integer.parseInt(department_idStr);
        String sex = req.getParameter("sex");
        String birthday = req.getParameter("birthday");
        String mobile = req.getParameter("mobile");
        String email = req.getParameter("email");
//        封装成 user 对象
        User userUpdated = new User(account,password,name,department_id,sex,birthday,mobile,email);
        userService.upDateUser(userUpdated);
        resp.sendRedirect(req.getContextPath()+"/user/showAllUser?page=1");
    }
}
