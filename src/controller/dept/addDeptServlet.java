package controller.dept;

import repository.DeptInfoRepository;
import repository.impl.DeptInfoRepositoryImpl;
import service.DeptService;
import service.ManagerService;
import service.impl.DeptServiceImpl;
import service.impl.ManagerServiceImpl;
import vo.DeptVO;
import vo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class addDeptServlet extends HttpServlet {
    private ManagerService managerService=new ManagerServiceImpl();
    private DeptService deptService = new DeptServiceImpl();
    private  DeptInfoRepository deptInfoRepository = new DeptInfoRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserVO> managerName = managerService.getManagerName();
        req.setAttribute("managers", managerName);
        req.getRequestDispatcher("/dept/deptInsert.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String departmentId=req.getParameter("departmentId");
        Integer departmentIDInt=Integer.parseInt(departmentId);
        String departmentName=req.getParameter("departmentName");
        String manager_id=req.getParameter("manager_id");
        //默认传入注册时间
        Date today=new Date();
        //默认传入人数为0
        Integer total_user=0;
        System.out.println("取出的数据为"+departmentId+departmentName+manager_id);
        DeptVO deptVO=new DeptVO(departmentIDInt,departmentName,total_user,today,manager_id);
        //得到所有的部门编号，所添加的不能重复
        List<String> deptVOS=deptInfoRepository.allDepartmentName();
        List<Integer> deptVOSid=deptInfoRepository.allDepartmentId();
        if(deptVOSid.contains(departmentIDInt)){
            System.out.println("添加的部门编号已存在");
            resp.sendRedirect(req.getContextPath()+"/dept/showAllDept?page=1");
            return;

        }else if(deptVOS.contains(departmentName)){
            System.out.println("添加的部门名称已存在");
            resp.sendRedirect(req.getContextPath()+"/dept/showAllDept?page=1");
            return;

        }
        else {

            deptService.addDept(deptVO);
            System.out.println("可以添加");
            //判断是否更改职务
            String user_type=deptInfoRepository.getUserTypeByAccount(manager_id);
            if (user_type.equals("2")){
                deptInfoRepository.upDateusertypeByAccount(manager_id,"1","1");
                System.out.println("需要更改且更改职务成功");
            }
            resp.sendRedirect(req.getContextPath()+"/dept/showAllDept?page=1");
        }



    }
}
