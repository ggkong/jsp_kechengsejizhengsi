package controller.dept;

import repository.DeptInfoRepository;
import repository.impl.DeptInfoRepositoryImpl;
import vo.DeptVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Selectservlet extends HttpServlet {
    private DeptInfoRepository deptInfoRepository =new DeptInfoRepositoryImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String departmentName=req.getParameter("selectDept");
        System.out.println(departmentName);
        DeptVO deptVO= deptInfoRepository.getDeptBydepartmentName(departmentName);
        Integer deptcount = 1;
        if (deptVO.getDepartmentId() == null){
            deptcount = 0;
        }
        req.setAttribute("deptcount",deptcount);
        req.setAttribute("deptcurrpage",1);
        req.setAttribute("selectDept",deptVO);
        req.getRequestDispatcher("/dept/deptSearch2.jsp").forward(req,resp);

    }
}
