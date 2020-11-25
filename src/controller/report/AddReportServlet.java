package controller.report;

import entity.Report;
import repository.ReportRepository;
import service.AccountService;
import service.ReportService;
import service.impl.AccountServiceImpl;
import service.impl.ReportServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddReportServlet extends HttpServlet {
    private AccountService accountService = new AccountServiceImpl();
    private ReportService reportService = new ReportServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String account = req.getParameter("account");
        String dateStr = req.getParameter("date");
        System.out.println(dateStr);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String name = req.getParameter("name");
        String work_processStr = req.getParameter("work_process");
        Integer work_process = Integer.parseInt(work_processStr);
        String work_content = req.getParameter("work_content");
        String tomorrow_plan = req.getParameter("tomorrow_plan");
        String problem = req.getParameter("problem");
        String other = req.getParameter("other");
//        新建一个对象
        Report report = new Report(account,date,work_process,work_content,tomorrow_plan,problem,other);
        System.out.println(report);
//        查询 account 存不存在
        List<String> accounts = accountService.getAllAccount();
        for (String acc: accounts){
            System.out.println(acc);
            if (acc.equals(report.getAccount())){
                Integer return_num = 0;
                return_num = reportService.addReport(report);
                if (return_num == 0){
                    req.setAttribute("errorMessage","今天已经存在日志");
                    req.getRequestDispatcher("/error.jsp").forward(req,resp);
                    return;
                }
                // 成功
                resp.sendRedirect(req.getContextPath()+"/report/showAllReport?page=1");
                return;
            }
        }
        req.setAttribute("errorMessage","添加的账户不存在");
        req.getRequestDispatcher("/error.jsp").forward(req,resp);

    }
}
