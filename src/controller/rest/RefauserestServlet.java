package controller.rest;

import service.RestService;
import service.WorkService;
import service.impl.RestServiceImpl;
import service.impl.WorkServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RefauserestServlet extends HttpServlet {
    private RestService restService=new RestServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rest_idstr=req.getParameter("rest_id");
        System.out.println(rest_idstr);
        Integer rest_id=Integer.parseInt(rest_idstr);
        Integer pass=restService.refauseRestByid(rest_id);
        if(pass==1){
            System.out.println("已驳回");
        }
        else{
            System.out.println("未驳回");
        }
        req.getRequestDispatcher("/rest/showAllRestwait?page=1").forward(req,resp);
    }

}
