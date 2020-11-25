package controller.rest;

import service.RestService;
import service.WorkService;
import service.impl.RestServiceImpl;
import vo.RestVO;
import vo.WorkVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SelectRestWaitServlet extends HttpServlet {

    private RestService restService=new RestServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("进入选择");
        String start_time = req.getParameter("restStartDate");
        System.out.println("start:"+start_time);
        String end_time = req.getParameter("restEndDate");
        System.out.println("end:"+end_time);
        String restname=req.getParameter("Restname");
        System.out.println("restname:"+restname);
        String pageStr = req.getParameter("page");
        if (pageStr == null || pageStr == ""){
            pageStr = "1";
        }
        Integer page = Integer.parseInt(pageStr);

        if ( restname==""||restname==null||start_time == "" || start_time ==null ||end_time == "" || end_time == null){
            // 存在一个空 返回去
            req.setAttribute("errorMessage","三个参数都不能为空");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date start_date = new Date();
            Date end_date = new Date();
            try {
                start_date = simpleDateFormat.parse(start_time);
                end_date = simpleDateFormat.parse(end_time);
            } catch (ParseException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage","时间错误");
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
                return;
            }
            System.out.println("开始处理");
            String url="/rest/selectRestrelax?page=";
            List<RestVO> restVOList = restService.selectRestWaits(restname,start_time,end_time,page);
            req.setAttribute("restVOList",restVOList);
            req.setAttribute("restCount",restService.getCountSelectRestWaits(restname,start_time,end_time));
            req.setAttribute("restCurrPage",page);
            req.setAttribute("restname",restname);
            req.setAttribute("startTime",start_time);
            req.setAttribute("endTime",end_time);
            req.setAttribute("url",url);
            req.getRequestDispatcher("/restsyouninn/restsyouninnSearch.jsp").forward(req,resp);
            return;


        }

    }
}
