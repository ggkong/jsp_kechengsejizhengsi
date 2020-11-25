package controller.rest;

import service.RestService;
import service.impl.RestServiceImpl;
import vo.RestVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SelectRestServlet extends HttpServlet {
    private RestService restService = new RestServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String restStartDate = req.getParameter("restStartDate");
        String restEndDate = req.getParameter("restEndDate");
        String restState = req.getParameter("restState");
        System.out.println(restStartDate);
        System.out.println(restEndDate);
        System.out.println(restState);
        String pageStr = req.getParameter("page");
        if (pageStr == "" || pageStr == null){
            pageStr = "1";
        }
        if (restStartDate == null || restEndDate == null || restState == null || restStartDate == "" || restEndDate == "" || restState == ""){
            req.setAttribute("errorMessage","参数不能为空");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start_date = new Date();
        Date end_date = new Date();
        try {
            start_date = simpleDateFormat.parse(restStartDate);
            end_date = simpleDateFormat.parse(restEndDate);
        } catch (ParseException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage","时间错误");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }
        if (start_date.before(end_date)){
            Integer page = Integer.parseInt(pageStr);
            List<RestVO> restVOList = restService.selectRestsByDateAndState(restStartDate,restEndDate,restState,page);
            req.setAttribute("restVOList",restVOList);
            req.setAttribute("restCount",restService.selectCount(restStartDate,restEndDate,restState));
            req.setAttribute("restCurrPage",page);
            req.setAttribute("pageUrl",req.getContextPath()+"/rest/selectRest?page=");
            req.setAttribute("restStartDate",restStartDate);
            req.setAttribute("restEndDate",restEndDate);
            req.setAttribute("restState",restState);
            req.getRequestDispatcher("/restManager/restSearch.jsp").forward(req,resp);
            return;
        }else {
            req.setAttribute("errorMessage","开始时间大于结束时间");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }
    }
}
