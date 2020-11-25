package controller.work;

import service.WorkService;
import service.impl.WorkServiceImpl;
import vo.WorkVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAllWorkWaitServlet extends HttpServlet {
    private WorkService workService=new WorkServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
        String pageStr = req.getParameter("page");
        System.out.println("进入审批界面");
        Integer page = Integer.parseInt(pageStr);
        String url="/work/showAllWorkwait?page=";
        List<WorkVO> workVOList = workService.allWorksWait(page);
        req.setAttribute("workVOList",workVOList);
        req.setAttribute("workCount",workService.countWait());
        req.setAttribute("workCurrPage",page);
        req.setAttribute("url",url);
        req.getRequestDispatcher("/worksyouninn/worksyouninnSearch.jsp").forward(req,resp);
    }
}
