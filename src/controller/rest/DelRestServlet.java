package controller.rest;

import service.RestService;
import service.impl.RestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DelRestServlet extends HttpServlet {
    private RestService restService = new RestServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rest_idStr = req.getParameter("rest_id");
        Integer rest_id = Integer.parseInt(rest_idStr);
        if (restService.delRestById(rest_id) == 0){
            req.setAttribute("errorMessage","删除失败");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }else {
            resp.sendRedirect(req.getContextPath()+"/rest/showAllRest?page=1");
            return;
        }
    }
}
