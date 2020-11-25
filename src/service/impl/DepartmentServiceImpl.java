package service.impl;

import entity.Department;
import repository.DepartmentRepository;
import repository.impl.DepartmentRepositoryImpl;
import service.DepartmentService;
import vo.DeptVO;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
    @Override
    public List<Department> getAllDepartmentIdAndName() {
        return departmentRepository.getAllDepartmentIdAndName();
    }

    @Override
    public List<DeptVO> getAllDepartment() {
        return departmentRepository.getAllDepartment();
    }
}
