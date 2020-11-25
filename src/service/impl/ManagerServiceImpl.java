package service.impl;

import entity.Department;
import repository.ManagerRepository;
import repository.impl.ManagerRepositoryImpl;
import service.ManagerService;
import vo.UserVO;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {
    private ManagerRepository managerRepository=new ManagerRepositoryImpl();

    @Override
    public List<UserVO> getManagerName() {
        return managerRepository.getAllManager();
    }
}
