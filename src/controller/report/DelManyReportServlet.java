package controller.report;

import service.ReportService;
import service.impl.ReportServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DelManyReportServlet extends HttpServlet {
    private ReportService reportService = new ReportServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] delList = req.getParameterValues("delReportId");
        if (delList == null){
            System.out.println("没有");
            resp.sendRedirect(req.getContextPath()+"/report/showAllReport?page=1");
            return;
        }else {
            for (String delIdStr: delList) {
                Integer delId = Integer.parseInt(delIdStr);
                reportService.delReportByReportId(delId);
            }
        }
        resp.sendRedirect(req.getContextPath()+"/report/showAllReport?page=1");
    }
}
