package controller.work;

import service.WorkService;
import service.impl.WorkServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PassworkServlet extends HttpServlet {
    private WorkService workService=new WorkServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String record_idstr=req.getParameter("record_id");
        System.out.println(record_idstr);
        Integer record_id=Integer.parseInt(record_idstr);
        Integer pass=workService.passWorkByid(record_id);
        if(pass==1){
            System.out.println("已通过");
        }
        else{
            System.out.println("未通过");
        }
        req.getRequestDispatcher("/work/showAllWorkwait?page=1").forward(req,resp);
    }
}
