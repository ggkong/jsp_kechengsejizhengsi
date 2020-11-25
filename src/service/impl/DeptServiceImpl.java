package service.impl;

import repository.AccountRepository;
import repository.DepartmentRepository;
import repository.DeptInfoRepository;
import repository.UserInfoRepository;
import repository.impl.AccountRepositoryImpl;
import repository.impl.DepartmentRepositoryImpl;
import repository.impl.DeptInfoRepositoryImpl;
import repository.impl.UserInfoRepositoryImpl;
import service.DeptService;
import vo.DeptVO;

import java.util.List;

public class DeptServiceImpl implements DeptService {
    private DeptInfoRepository deptInfoRepository = new DeptInfoRepositoryImpl();
    private DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
    private AccountRepository accountRepository = new AccountRepositoryImpl();
    private UserInfoRepository userInfoRepository = new UserInfoRepositoryImpl();
    private final int LIMIT = 4;   // 设置 每一页显示 四条


    @Override
    public List<DeptVO> allDepts(Integer page) {
        Integer index = (page - 1) * LIMIT;
        return deptInfoRepository.allDepts(index, LIMIT);

    }

    @Override
    public Integer getDeptPages() {
        Integer count = deptInfoRepository.countDepts();
        if (count % LIMIT == 0) {
            return count / LIMIT;
        } else {
            return count / LIMIT + 1;
        }
    }

    @Override
    public Integer count() {
        return deptInfoRepository.countDepts();

    }

    @Override
    public DeptVO getDeptBydepartmentId(String departmentId) {
        return deptInfoRepository.getDeptBydepartmentId(departmentId);


    }

    @Override
    public Integer upDateDept(DeptVO deptVO, String departmentId) {
        Integer return_num = 0;

        DeptVO depthave = deptInfoRepository.getDeptBydepartmentId(departmentId);
        System.out.println("原来的account为" + depthave.getAccount());
        System.out.println("===================================");

        if (depthave.getAccount().equals(deptVO.getAccount())) {
            System.out.println("不需要更改用户角色");
            //没有进行管理员变动
            //直接进行更新便可
            deptInfoRepository.deptUpdate(deptVO);
            return 1;
        } else {
            //进行用户角色改变，考虑到主管和员工的变动
            String user_type = deptInfoRepository.getUserTypeByAccount(deptVO.getAccount());

            if (user_type.equals("2") || user_type.equals("1")) {
                //更改职务
                return_num = deptInfoRepository.upDateusertypeByAccount(deptVO.getAccount(), "1", "1");
                //原来的职务判断须不需要更改
                deptInfoRepository.deptUpdate(deptVO);
                List<String> deptAllManagers = deptInfoRepository.allManageraccount();
                if (deptAllManagers.contains(depthave.getAccount())) {
                    System.out.println("不必更改");
                    //无需更改原来的职务

                } else {
                    System.out.println("需要更改并且已更改");
                    deptInfoRepository.upDateusertypeByAccount(depthave.getAccount(), "2", "2");

                }

            } else if (user_type.equals("0")) {
                deptInfoRepository.deptUpdate(deptVO);
                List<String> deptAllManagers = deptInfoRepository.allManageraccount();
                if (deptAllManagers.contains(depthave.getAccount())) {
                    System.out.println("不必更改");
                    //无需更改原s来的职务
                } else {
                    System.out.println("需要更改并且已更改");
                    deptInfoRepository.upDateusertypeByAccount(depthave.getAccount(), "2", "2");

                }
            }
        }
        return return_num;
    }

    @Override
    public Integer delDept(String departmentId, String managername) {
        System.out.println("departmentid是"+departmentId);
        Integer departmentIdint=Integer.parseInt(departmentId);
        Integer return_num = 0;
        DeptVO willdelDept = deptInfoRepository.getDeptBydepartmentId(departmentId);
        String manager_id = willdelDept.getAccount();
        //开始进行删除
        return_num = deptInfoRepository.delDepts(departmentId);
        List<String> deptAllManagers = deptInfoRepository.allManagername();
        //如果所删除的数据的managername仍然存在于部门管理表中
        if (deptAllManagers.contains(managername)) {
            System.out.println("不需要改变身份");
        } else {
            System.out.println("需要更改身份");
            deptInfoRepository.upDateusertypeByAccount(manager_id, "2", "2");
        }
        //得到所有员工的部门，来确定需不需要删除员工的部门
        List<Integer> usermanager= userInfoRepository.getAllManager();
        System.out.println();
        if (usermanager.contains(departmentIdint)){
            //需要对用户的部门清空
            userInfoRepository.cleardepartmentNameBydepartmentId(departmentId);
            System.out.println("清空成功");
            //如若清空部门则需要对编号为0的部门进行加一
            deptInfoRepository.insertcount();
        }
        else {
            System.out.println("不需要更改员工部门");
        }
        return return_num;
    }

    @Override
    public Integer addDept(DeptVO deptVO) {
        deptInfoRepository.addDept(deptVO);
        return null ;
    }
}
