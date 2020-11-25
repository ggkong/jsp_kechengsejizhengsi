package controller.work;

import service.WorkService;
import service.impl.WorkServiceImpl;
import vo.WorkVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowWorkDetailServlet extends HttpServlet {
    private WorkService workService = new WorkServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String workId = req.getParameter("workId");
        System.out.println(workId);
        WorkVO workVO = workService.ShowWorkDetail(workId);
        System.out.println(workVO);
        req.setAttribute("workShow",workVO);
        req.getRequestDispatcher("/workManager/workShow.jsp").forward(req,resp);
    }
}
