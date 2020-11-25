package controller.user;

import service.DepartmentService;
import service.UserService;
import service.impl.DepartmentServiceImpl;
import service.impl.UserServiceImpl;
import vo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowAllUserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private DepartmentService departmentService = new DepartmentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        System.out.println(pageStr);
        Integer page = Integer.parseInt(pageStr);
        System.out.println("现在请求的是第"+pageStr+"页");
        List<UserVO> userVOList = userService.allUsers(page);
        HttpSession session=req.getSession();
        session.setAttribute("departments",departmentService.getAllDepartmentIdAndName());
        session.setAttribute("pages",userService. getUserPages());
        session.setAttribute("count",userService.count());
        session.setAttribute("currpage",page);
        session.setAttribute("userVOList",userVOList);
        session.setAttribute("selectName","");
        resp.sendRedirect(req.getContextPath()+"/user/userSearch.jsp");
    }
}
