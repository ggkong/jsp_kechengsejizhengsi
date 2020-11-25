package repository;

import entity.Department;
import vo.DeptVO;

import java.util.List;

public interface DepartmentRepository {
    public Integer addDeaprtmentTotalUser(Integer departmentId);
    public Integer reduceDeaprtmentTotalUser(Integer departmentId);
    public Integer getTotalUserByDepartmentId(Integer departmentId);
    public List<Department> getAllDepartmentIdAndName();
    public List<DeptVO> getAllDepartment();
}
