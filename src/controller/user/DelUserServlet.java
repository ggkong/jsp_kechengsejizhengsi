package controller.user;

import service.impl.UserServiceImpl;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DelUserServlet extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("调用删除方法");
        String account = req.getParameter("account");
        String name = req.getParameter("name");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] accountBytes = decoder.decodeBuffer(account);
        byte[] nameBytes = decoder.decodeBuffer(name);
        String accountDecode = new String(accountBytes);
        String nameDecoder = new String(nameBytes);
        System.out.println(accountDecode+nameDecoder);

//        调用删除方法

        Integer return_num = userService.delUser(accountDecode,nameDecoder);
        if (return_num == 1){
            System.out.println("删除成功");
            resp.sendRedirect(req.getContextPath()+"/user/showAllUser?page=1");
        }else {
            System.out.println("删除失败");
            resp.sendRedirect(req.getContextPath()+"/user/showAllUser?page=1");
        }
    }
}
