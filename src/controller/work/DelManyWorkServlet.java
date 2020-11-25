package controller.work;

import service.WorkService;
import service.impl.WorkServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DelManyWorkServlet extends HttpServlet {
    private WorkService workService = new WorkServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] delWorks = req.getParameterValues("delWorkId");
        for (String delId:delWorks) {
            Integer return_num = workService.delWork(delId);
            if (return_num!=1){
                req.setAttribute("errorMessage","删除失败");
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
                return;
            }
        }
        resp.sendRedirect(req.getContextPath()+"/work/showAllWork?page=1");
        return;
    }
}
