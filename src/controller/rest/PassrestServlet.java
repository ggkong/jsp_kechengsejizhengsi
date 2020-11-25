package controller.rest;

import service.RestService;
import service.impl.RestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PassrestServlet extends HttpServlet {
    private RestService restService=new RestServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rest_idstr=req.getParameter("rest_id");
        System.out.println(rest_idstr);
        Integer rest_id=Integer.parseInt(rest_idstr);
        Integer pass=restService.passRestByid(rest_id);
        if(pass==1){
            System.out.println("已通过");
        }
        else{
            System.out.println("未通过");
        }
        req.getRequestDispatcher("/rest/showAllRestwait?page=1").forward(req,resp);

    }
}
