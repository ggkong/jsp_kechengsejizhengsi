package controller.dept;


import repository.DeptInfoRepository;
import repository.impl.DeptInfoRepositoryImpl;
import service.DeptService;
import service.impl.DeptServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class delManyDeptServlet extends HttpServlet {
    private DeptInfoRepository deptInfoRepository=new DeptInfoRepositoryImpl();
    private DeptService deptService=new DeptServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入");
        String manyDept[]=req.getParameterValues("quanbu");
        for (String s:manyDept){
            String dept[]=s.split("&");
            Integer return_num=deptService.delDept(dept[0],dept[1]);
            if (return_num==0){
                System.out.println("删除失败");
                return;
            }
        }
        resp.sendRedirect(req.getContextPath()+"/dept/showAllDept?page=1");

    }
}
