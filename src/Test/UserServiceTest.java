package Test;

import com.google.gson.Gson;
import controller.work.InsertWorkServlet;
import entity.Department;
import entity.Report;
import entity.Rest;
import entity.Work;
import org.omg.PortableInterceptor.INACTIVE;
import repository.*;
import repository.impl.*;
import service.ReportService;
import service.RestService;
import service.UserService;
import service.WorkService;
import service.impl.ReportServiceImpl;
import service.impl.RestServiceImpl;
import service.impl.UserServiceImpl;
import service.impl.WorkServiceImpl;
import vo.DeptVO;
import vo.ReportVO;
import vo.UserVO;
import vo.WorkVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServiceTest {
    public static void main(String[] args) {
//        UserService userService = new UserServiceImpl();
//        UserInfoRepository userInfoRepository = new UserInfoRepositoryImpl();
//        DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
//        AccountRepository accountRepository = new AccountRepositoryImpl();
//        ReportRepository reportRepository = new ReportRepositoryImpl();
//        WorkRepository workRepository = new WorkRepositoryImpl();
//        System.out.println(workRepository.countSelectWorks("1","2020-11-15","2020-11-19"));
//        WorkService workService = new WorkServiceImpl();
//        List<WorkVO> workVOS = workService.allWorks(3);
//        for (WorkVO workVO:workVOS){
//            System.out.println(workVO);
//        }
//        String[] time = {"2&30","3&50"};
//        for (String t:time){
//            String[] ts = t.split("&");
//            System.out.println(ts[0]);
//            System.out.println(ts[1]);
//        }
//        Rest rest =
        RestService restService = new RestServiceImpl();
//        for (int j = 1;j<9;j++){
//            for (int i = 10;i<29;i++){
//                Rest rest = new Rest("000001","2020-0"+j+"-"+i+" "+"09:00:00","2020-0"+j+"-"+i+" "+"11:00:00","2.0","代码写多了,我想歇歇","没有备注","0");
//                restService.addRest(rest);
//            }
//        }
        System.out.println(restService.selectRestByRestId(156));




    }
}
