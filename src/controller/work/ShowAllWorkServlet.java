package controller.work;

import service.WorkService;
import service.impl.WorkServiceImpl;
import vo.ReportVO;
import vo.WorkVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAllWorkServlet extends HttpServlet {
    private WorkService workService = new WorkServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        System.out.println("开始请求所有工作");
        Integer page = Integer.parseInt(pageStr);
        List<WorkVO> workVOList = workService.allWorks(page);
        req.setAttribute("workVOList",workVOList);
        req.setAttribute("workCount",workService.count());
        req.setAttribute("workCurrPage",page);
        req.setAttribute("state","1");
        req.setAttribute("startTime","请选择开始时间");
        req.setAttribute("endTime","请选择结束时间");
        req.getRequestDispatcher("/workManager/workSearch.jsp").forward(req,resp);
    }
}
