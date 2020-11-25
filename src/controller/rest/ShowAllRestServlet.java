package controller.rest;

import service.RestService;
import service.impl.RestServiceImpl;
import vo.RestVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAllRestServlet extends HttpServlet {
    private RestService restService = new RestServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        System.out.println("开始请求所有的请假");
        Integer page = Integer.parseInt(pageStr);
        List<RestVO> restVOList = restService.allRests(page);
        req.setAttribute("restVOList",restVOList);
        req.setAttribute("restCount",restService.count());
        req.setAttribute("restCurrPage",page);
        req.setAttribute("pageUrl",req.getContextPath()+"/rest/showAllRest?page=");
        req.getRequestDispatcher("/restManager/restSearch.jsp").forward(req,resp);
    }
}
