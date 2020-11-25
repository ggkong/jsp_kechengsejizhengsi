package controller.work;

import service.WorkService;
import service.impl.WorkServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DelWorkServlet extends HttpServlet {
    private WorkService workService = new WorkServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String record_id = req.getParameter("record_id");
        System.out.println(record_id);
        Integer return_id = workService.delWork(record_id);
        if (return_id != 1){
            req.setAttribute("errorMessage","删除失败");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }else {
            resp.sendRedirect(req.getContextPath()+"/work/showAllWork?page=1");
            return;
        }
    }
}
