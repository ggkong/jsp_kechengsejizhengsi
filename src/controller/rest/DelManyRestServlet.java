package controller.rest;

import service.RestService;
import service.impl.RestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DelManyRestServlet extends HttpServlet {
    private RestService restService = new RestServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] restIds = req.getParameterValues("delRestId");
        int[] return_nums = restService.delManyRest(restIds);
        for (int num:return_nums){
            if (num != 1){
                req.setAttribute("errorMessage","删除失败");
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
                return;
            }
        }
        resp.sendRedirect(req.getContextPath()+"/rest/showAllRest?page=1");
        return;
    }
}
