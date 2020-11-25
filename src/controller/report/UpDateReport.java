package controller.report;
import service.ReportService;
import service.impl.ReportServiceImpl;
import vo.ReportVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UpDateReport extends HttpServlet {
    private ReportService reportService = new ReportServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String report_idStr = req.getParameter("report_id");
        Integer report_id = Integer.parseInt(report_idStr);
        System.out.println(report_id);
        ReportVO reportVO = reportService.getReportById(report_id);
        if (reportVO == null){
            req.setAttribute("errorMessage","系统错误没有查到要修改的日志");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }else {
            int[] workProgressArray = {0,10,20,30,40,50,60,70,80,90,100};
            req.setAttribute("workProgressArray",workProgressArray);
            req.setAttribute("reportWillUpDate",reportVO);
            req.getRequestDispatcher("/report/reportUpdate.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String report_idStr = req.getParameter("report_id");
        System.out.println("是什么"+report_idStr);
        Integer report_id = Integer.parseInt(report_idStr);
        String account = req.getParameter("account");
        String dateStr = req.getParameter("date");
        System.out.println(dateStr);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);
        String name = req.getParameter("name");
        String work_processStr = req.getParameter("work_process");
        Integer work_process = Integer.parseInt(work_processStr);
        String work_content = req.getParameter("work_content");
        String tomorrow_plan = req.getParameter("tomorrow_plan");
        String problem = req.getParameter("problem");
        String other = req.getParameter("other");
        ReportVO reportUpDated = new ReportVO(report_id,account,date,work_process,work_content,tomorrow_plan,problem,other);
        // 调用修改方法
        Integer return_num = reportService.upDateReport(reportUpDated);
        if (return_num == 0){
//            修改失败
            req.setAttribute("errorMessage","不能修改到已经存在的日志，一个用户一天只能有一个日志");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }else {
            resp.sendRedirect(req.getContextPath()+"/report/showAllReport?page=1");
        }
    }
}
