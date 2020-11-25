package controller.work;

import entity.Work;
import service.WorkService;
import service.impl.WorkServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InsertWorkServlet extends HttpServlet {
    private WorkService workService = new WorkServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String account = req.getParameter("account");
        String work_date = req.getParameter("work_date");
        String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");
        String workTime = req.getParameter("workTime");
        String cause = req.getParameter("cause");
//        进行校验 校验
        if (workService.countWorkByAccountAndDate(account,work_date) != 0){
            System.out.println("今天已经存在加班");
            req.setAttribute("errorMessage","今天已经存在加班,去修改今天的加班时间把");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }else {
//            获取前端 传过来 的 workTime
            System.out.println(workTime);
            String[] s1 = workTime.split(":");
            System.out.println(s1);
            Double shi = Double.parseDouble(s1[0]);
            Double fen = Double.parseDouble(s1[1]);
            Double totalTime = shi+fen/60;
            String totalTimeStr = String.valueOf(totalTime);
            Work work = new Work(account,work_date,startTime,endTime,totalTimeStr,cause,"无","0");
            Integer return_work = workService.addWork(work);
            if (return_work != 1){
                req.setAttribute("errorMessage","添加加班失败");
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
                return;
            }else {
                resp.sendRedirect(req.getContextPath()+"/work/showAllWork?page=1");
                return;
            }
        }
    }
}
