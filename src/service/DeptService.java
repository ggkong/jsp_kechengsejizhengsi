package service;

import vo.DeptVO;

import java.util.List;

public interface DeptService {
    public List<DeptVO> allDepts(Integer page);
    public Integer getDeptPages();
    public Integer count();
    public DeptVO getDeptBydepartmentId(String departmentId);
    public Integer upDateDept(DeptVO deptVO,String departmentId);
    public Integer delDept(String departmentId,String managername);
    public Integer addDept(DeptVO deptVO);
}
