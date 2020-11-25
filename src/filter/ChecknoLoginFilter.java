package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ChecknoLoginFilter implements Filter {
    private FilterConfig filterConfig;
    //储存要检查的session中的key值
    private String checksessionkey;
    //储存非法登陆转向的的界面
    private String redirectUrl;
    //储存要拦截的url列表
    private List checkurllist= new ArrayList();


    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub

        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        HttpSession session=req.getSession();
        System.out.println("是否包含"+checkRequestUrlinList(req));
        System.out.println("session的值为"+session.getAttribute("account"));
        //非法登录：获取的ip地址在拦截的列表中并且获取的session中的value为空,为非法登录
        if(checkRequestUrlinList(req)&&session.getAttribute(checksessionkey)==null){
            res.sendRedirect(req.getContextPath()+redirectUrl);
            return;
        }
        chain.doFilter(req, res);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //接受初始化参数
        this.filterConfig=filterConfig;
        //给要检查的session的key值进行赋值
        this.checksessionkey=filterConfig.getInitParameter("checksessionkey");
        //给非法登录的转向界面赋值
        this.redirectUrl=filterConfig.getInitParameter("redirectUrl");
        //给要拦截的url列表赋值
        String str=filterConfig.getInitParameter("checkurlList");
        if(str!=null){
            StringTokenizer st=new StringTokenizer(str,";");
            checkurllist.clear();
            while(st.hasMoreTokens()){
                checkurllist.add(st.nextToken());
            }

        }
    }

    @Override
    public void destroy() {

    }
    public boolean checkRequestUrlinList(HttpServletRequest request){
        String url = request.getServletPath()+(request.getPathInfo()==null?"":request.getPathInfo());
        System.out.println(url);
        return checkurllist.contains(url);
    }

}
