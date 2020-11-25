package controller.report;

import service.ReportService;
import service.impl.ReportServiceImpl;
import vo.ReportVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowAllReportServlet extends HttpServlet {
    private ReportService reportService = new ReportServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        System.out.println("开始请求");
        Integer page = Integer.parseInt(pageStr);
        List<ReportVO> reportVOList = reportService.allUsers(page);
        req.setAttribute("reportList",reportVOList);
        req.setAttribute("reportCount",reportService.count());
        req.setAttribute("reportCurrPage",page);
        req.setAttribute("startTime","");
        req.setAttribute("endTime","");
        req.setAttribute("selectName","");
        System.out.println("进入这");
        req.getRequestDispatcher("/report/reportSearch.jsp").forward(req,resp);
    }
}
