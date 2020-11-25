package controller.dept;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import repository.DeptInfoRepository;
import service.DeptService;
import service.impl.DeptServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class delDeptServlet extends HttpServlet {
    public DeptService deptService=new DeptServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入删除servlet");
        String departmentId=req.getParameter("departmentId");
        String managername=req.getParameter("managername");
        System.out.println(managername);
        //调用删除方法
        Integer return_num=deptService.delDept(departmentId,managername);
        if (return_num==1){
            System.out.println("删除成功");
            resp.sendRedirect(req.getContextPath()+"/dept/showAllDept?page=1");
        }else{
            System.out.println("删除失败");
            resp.sendRedirect(req.getContextPath()+"/dept/showAllDept?page=1");
        }


    }
}
