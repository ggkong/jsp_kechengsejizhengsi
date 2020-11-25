package controller.report;

import service.ReportService;
import service.impl.ReportServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DelReportServlet extends HttpServlet {
    private ReportService reportService = new ReportServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String report_idStr = req.getParameter("report_id");
        Integer report_id = Integer.parseInt(report_idStr);
        System.out.println(report_id);
        if (reportService.delReportByReportId(report_id) != 1){
            req.setAttribute("errorMessage","删除错误");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }else {
            resp.sendRedirect(req.getContextPath()+"/report/showAllReport?page=1");
        }
    }
}
