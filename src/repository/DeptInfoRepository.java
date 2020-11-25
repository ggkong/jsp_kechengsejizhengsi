package repository;

import vo.DeptVO;

import java.util.List;

public interface DeptInfoRepository {
    public List<DeptVO> allDepts(Integer index, Integer limit);
    public List<String> allDepartmentName();
    public List<Integer> allDepartmentId();
    public List<String> allManageraccount();
    public List<String> allManagername();
    public Integer countDepts();
    public DeptVO getDeptBydepartmentId(String departmentId);
    public Integer deptUpdate(DeptVO deptVO);
    public String getUserTypeByAccount(String account);
    public Integer upDateusertypeByAccount(String account,String user_type,String mylevel);
    public Integer delDepts(String departmentId);
    public Integer insertcount();
    public Integer addDept(DeptVO deptVO);
    public DeptVO getDeptBydepartmentName(String departmentName);
}
