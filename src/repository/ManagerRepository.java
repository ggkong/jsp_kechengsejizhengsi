package repository;

import entity.Department;
import vo.UserVO;

import java.util.List;

public interface ManagerRepository {
    public List<UserVO> getAllManager();
}
