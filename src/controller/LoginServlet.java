package controller;

import com.mysql.cj.Session;
import entity.Department;
import entity.User;
import service.DepartmentService;
import service.LoginService;
import service.impl.DepartmentServiceImpl;
import service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private LoginService loginService = new LoginServiceImpl();
    private DepartmentService departmentService = new DepartmentServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String []flag=req.getParameterValues("flag");
        Integer state_num = loginService.getLoginState(account,password);

        //检验是否需要记住密码
        //需要记住密码
        if(flag!=null&&flag.length>0){
            Cookie cookie1 =   new Cookie("account",account);
            Cookie  cookie2 =   new Cookie("password",password);
            cookie1.setMaxAge(10*24*60*60);
            cookie2.setMaxAge(10*24*60*60);
            resp.addCookie(cookie1);
            resp.addCookie(cookie2);
        } else{
            //取消记住密码
            Cookie  cookies[] =  req.getCookies();
            for(Cookie c:cookies){
                if(c.getName().equals("account")){
                    c.setMaxAge(0);
                }
                if(c.getName().equals("password")){
                    c.setMaxAge(0);
                }
                resp.addCookie(c);
            }
        }


        // == null 可不是瞎写的 为了防止 空指针错误
        String piccode=(String) req.getSession().getAttribute("piccode");
        String checkCode=req.getParameter("checkCode");  //取值
        checkCode=checkCode.toUpperCase();  //把字符全部转换为大写的（此语句可以用于验证码不区分大小写）
        resp.setContentType("text/html;charset=gbk");//解决乱码问题
        PrintWriter out = resp.getWriter();

        if(state_num == null){
            req.setAttribute("errorMassage","账号或密码错误");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }
        if (state_num != 0){
            System.out.println("已经存在登录的账号，不要重复登录或者根本就没有账号");
            req.setAttribute("errorMassage","账号已在异地登陆");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }

        if (!(checkCode.equals(piccode))){
            req.setAttribute("errorMassage","验证码错误");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }
        User user = null;
        user = loginService.login(account,password);
        if (user == null){
            System.out.println("登录失败");

            resp.sendRedirect(req.getContextPath()+"/login.jsp");
            return;
        }else {
            System.out.println("登录成功");
            HttpSession session = req.getSession();
            session.setAttribute("account",account);
            List<Department> departments = departmentService.getAllDepartmentIdAndName();
            session.setAttribute("user",user);
            session.setAttribute("departments",departments);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            session.setAttribute("loginTime",df.format(new Date()));
            resp.sendRedirect(req.getContextPath()+"/main.jsp");
            return;
        }

    }
}
