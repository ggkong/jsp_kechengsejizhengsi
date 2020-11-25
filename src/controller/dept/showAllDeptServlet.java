package controller.dept;

import com.google.gson.Gson;
import service.DepartmentService;
import service.DeptService;
import service.impl.DepartmentServiceImpl;
import service.impl.DeptServiceImpl;
import vo.DeptVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class showAllDeptServlet extends HttpServlet {
    private DeptService deptService=new DeptServiceImpl();
    private DepartmentService departmentService = new DepartmentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageStr=req.getParameter("page");
        Integer page=Integer.parseInt(pageStr);
        List<DeptVO> deptVOList=deptService.allDepts(page);
        HttpSession session=req.getSession();
        session.setAttribute("department",departmentService.getAllDepartmentIdAndName());
        session.setAttribute("deptpages",deptService.getDeptPages());
        session.setAttribute("deptcount",deptService.count());
        session.setAttribute("deptcurrpage",page);
        session.setAttribute("deptVOList",deptVOList);
        resp.sendRedirect(req.getContextPath()+"/dept/deptSearch.jsp");
    }
}
