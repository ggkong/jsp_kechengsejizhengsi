package service;

import entity.Department;
import vo.DeptVO;

import java.util.List;

public interface DepartmentService {
    public List<Department> getAllDepartmentIdAndName();
    public List<DeptVO> getAllDepartment();
}
