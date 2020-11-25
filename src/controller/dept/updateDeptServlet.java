package controller.dept;

import entity.User;
import service.DepartmentService;
import service.DeptService;
import service.ManagerService;
import service.UserService;
import service.impl.DepartmentServiceImpl;
import service.impl.DeptServiceImpl;
import service.impl.ManagerServiceImpl;
import service.impl.UserServiceImpl;
import vo.DeptVO;
import vo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class updateDeptServlet extends HttpServlet {
    private DepartmentService departmentService = new DepartmentServiceImpl();
    private DeptService deptService = new DeptServiceImpl();
    private ManagerService managerService=new ManagerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("departmentId");
        List<UserVO> managerName=managerService.getManagerName();
        DeptVO deptVO = deptService.getDeptBydepartmentId(account);
        req.setAttribute("deptWillUpdate",deptVO);
        req.setAttribute("managers",managerName);
        req.getRequestDispatcher("/dept/deptUpdate.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        String departmentIdstr = req.getParameter("departmentId");
        Integer departmentId=Integer.parseInt(departmentIdstr);
        String departmentName = req.getParameter("departmentName");
        String manager_id=req.getParameter("manager_id");

//        封装成 DeptVo对象
        DeptVO deptwillupdate=new DeptVO();
        deptwillupdate.DeptVObyaccount(departmentId,departmentName,manager_id);
        deptService.upDateDept(deptwillupdate,departmentIdstr);
        resp.sendRedirect(req.getContextPath()+"/dept/showAllDept?page=1");
    }
}
