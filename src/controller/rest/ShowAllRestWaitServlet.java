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

public class ShowAllRestWaitServlet extends HttpServlet {
    private RestService restService = new RestServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
        String pageStr=req.getParameter("page");
        System.out.println("进入休假审批");
        Integer page=Integer.parseInt(pageStr);
        String url="/rest/showAllRestwait?page=";
        List<RestVO> restVOList=restService.allRestsWait(page);
        req.setAttribute("restVOList",restVOList);
        req.setAttribute("restCount",restService.countRest());
        req.setAttribute("restCurrPage",page);
        req.setAttribute("url",url);
        req.getRequestDispatcher("/restsyouninn/restsyouninnSearch.jsp").forward(req,resp);
    }
}
