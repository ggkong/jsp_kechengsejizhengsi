package controller.report;

import service.ReportService;
import service.impl.ReportServiceImpl;
import sun.misc.Ref;
import vo.ReportVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SelectServlet extends HttpServlet {
    private ReportService reportService = new ReportServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String start_time = req.getParameter("start_time");
        String end_time = req.getParameter("end_time");
        System.out.println(name);
        System.out.println(start_time);
        System.out.println(end_time);
//        如果有一个为空 将其返回
        if (name == "" || name == null || start_time == "" || start_time ==null ||end_time == "" || end_time == null){
            // 存在一个空 返回去
            req.setAttribute("errorMessage","三个参数都不能为空");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }else {
            // 进入下面的查询
//            生成时间
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
            if (start_date.before(end_date)){
                //正常
                System.out.println("开始处理");
                List<ReportVO> reportVOList = reportService.selectReports(name,start_time,end_time);
                req.setAttribute("reportList",reportVOList);
                req.setAttribute("reportCount",reportService.getCountSelectReport(name,start_time,end_time));
                req.setAttribute("reportCurrPage",1);
                req.setAttribute("startTime",start_time);
                req.setAttribute("endTime",end_time);
                req.setAttribute("selectName",name);
                req.getRequestDispatcher("/report/reportSearch.jsp").forward(req,resp);
                return;
            }else {
                req.setAttribute("errorMessage","开始时间大于结束时间");
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
                return;
            }

        }
    }
}
