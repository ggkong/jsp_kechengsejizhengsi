package controller.user;

import com.google.gson.internal.$Gson$Preconditions;
import repository.UserInfoRepository;
import repository.impl.UserInfoRepositoryImpl;
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

public class SelectUserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private DepartmentService departmentService = new DepartmentServiceImpl();
    private UserInfoRepository userInfoRepository = new UserInfoRepositoryImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deptIdStr = req.getParameter("deptId");
        String name = req.getParameter("name");
        System.out.println(deptIdStr+name);
        if (name == null || name =="" || deptIdStr == "" || deptIdStr ==null){
            req.setAttribute("errorMessage","查询失败两个参数都不能为空");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }else {
//            进行操作;
            Integer deptId = Integer.parseInt(deptIdStr);
            List<UserVO> userVOList = userService.selectUsers(name,deptId);
            HttpSession session=req.getSession();
            session.setAttribute("departments",departmentService.getAllDepartmentIdAndName());
            session.setAttribute("pages",1);
            session.setAttribute("currpage",1);
            session.setAttribute("userVOList",userVOList);
            session.setAttribute("count",userInfoRepository.selectUserCount(name,deptId));
            session.setAttribute("selectName",name);
            resp.sendRedirect(req.getContextPath()+"/user/userSearch.jsp");
            return;
        }
    }
}
