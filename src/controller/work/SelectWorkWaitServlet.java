package controller.work;

import service.WorkService;
import service.impl.WorkServiceImpl;
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

public class SelectWorkWaitServlet extends HttpServlet {
    private WorkService workService=new WorkServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String start_time = req.getParameter("startTime");
        String end_time = req.getParameter("endTime");
        String workname=req.getParameter("Workname");
        String pageStr = req.getParameter("page");
        if (pageStr == null || pageStr == ""){
            pageStr = "1";
        }
        Integer page = Integer.parseInt(pageStr);

        if ( workname==""||workname==null||start_time == "" || start_time ==null ||end_time == "" || end_time == null){
            // 存在一个空 返回去
            req.setAttribute("errorMessage","三个参数都不能为空");
            req.getRequestDispatcher(req.getContextPath()+"/error.jsp").forward(req,resp);
            return;
        }else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
                String url="/work/selectWait?page=";
                List<WorkVO> workVOList = workService.selectWorkWaits(workname,start_time,end_time,page);
                req.setAttribute("workVOList",workVOList);
                req.setAttribute("workCount",workService.getCountSelectWorkWaits(workname,start_time,end_time));
                req.setAttribute("workCurrPage",page);
                req.setAttribute("workname",workname);
                req.setAttribute("startTime",start_time);
                req.setAttribute("endTime",end_time);
                req.setAttribute("url",url);
                req.getRequestDispatcher("/worksyouninn/worksyouninnSearch.jsp").forward(req,resp);
                return;


        }

    }
}
