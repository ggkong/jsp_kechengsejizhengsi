package controller.rest;

import com.google.gson.internal.$Gson$Preconditions;
import service.RestService;
import service.impl.RestServiceImpl;
import vo.RestVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateRestServlet extends HttpServlet {
    private RestService restService = new RestServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String restIdStr = req.getParameter("restId");
        if (restIdStr == null || restIdStr == ""){
            req.setAttribute("errorMessage","没有找到");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }
        System.out.println(restIdStr);
        Integer restId = Integer.parseInt(restIdStr);
        RestVO willUpdateRest = restService.selectRestByRestId(restId);
        if (willUpdateRest == null){
            // 错误
            req.setAttribute("errorMessage","没有找到");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }else {
            req.setAttribute("willUpdateRest",willUpdateRest);
            req.getRequestDispatcher("/restManager/restUpdate.jsp").forward(req,resp);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获取参数
        req.setCharacterEncoding("utf-8");
        String restIdStr = req.getParameter("rest_id");
        Integer restId = Integer.parseInt(restIdStr);
        String account = req.getParameter("account");
        String rest_start_date = req.getParameter("rest_start_date");
        String rest_end_date = req.getParameter("rest_end_date");
        String cause = req.getParameter("cause");
        System.out.println(rest_end_date);
        System.out.println(rest_start_date);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date start = df.parse(rest_start_date);
            Date end = df.parse(rest_end_date);
            if (start.before(end)) {
                long l = end.getTime() - start.getTime();
                long day = l / (24 * 60 * 60 * 1000);
                long hour = (l / (60 * 60 * 1000) - day * 24);
                long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
                long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
                System.out.println("" + day + "天" + hour + "小时" + min + "分" + s + "秒");
                Double rest_Time = Double.parseDouble(String.valueOf(day * 24 + hour * 1 + min / 60));
                System.out.println(rest_Time);
                String restTimeStr = rest_Time.toString();
//                掉用更新方法
                if (restService.upDateRest(restId,rest_start_date,rest_end_date,restTimeStr,cause) == 0){
                    req.setAttribute("errorMessage","更新错误");
                    req.getRequestDispatcher("/error.jsp").forward(req,resp);
                    return;
                }else {
                    resp.sendRedirect(req.getContextPath()+"/rest/showAllRest?page=1");
                    return;
                }
            }else {
                req.setAttribute("errorMessage","开始时间大于结束时间");
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage","开始时间大于结束时间");
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
            return;
        }
    }
}
