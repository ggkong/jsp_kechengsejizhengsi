package service.impl;

import entity.Work;
import repository.WorkRepository;
import repository.impl.WorkRepositoryImpl;
import service.WorkService;
import vo.WorkVO;

import java.util.List;

public class WorkServiceImpl implements WorkService {
    private final int LIMIT = 10;
    private final int LIMITWait=5;
    private WorkRepository workRepository = new WorkRepositoryImpl();

    @Override
    public Integer count() {
        return workRepository.countWorks();
    }

    @Override
    public List<WorkVO> allWorks(Integer page) {
        Integer index = (page - 1)*LIMIT;
        return workRepository.allWorks(index,LIMIT);
    }

    @Override
    public Integer countWorkByAccountAndDate(String account, String workDate) {
        return workRepository.countWorkByAccountAndDate(account,workDate);
    }

    @Override
    public Integer addWork(Work work) {
        return workRepository.insertWork(work);
    }

    @Override
    public Integer delWork(String workId) {
        Integer workIdInt = Integer.parseInt(workId);
        return workRepository.delWork(workIdInt);
    }

    @Override
    public List<WorkVO> selectWorks(String State, String startDate, String endDate) {
        return workRepository.selectWorks(State,startDate,endDate);
    }

    @Override
    public Integer getCountSelectWorks(String State, String startDate, String endDate) {
        Integer count_num = workRepository.countSelectWorks(State,startDate,endDate);
        if (count_num >= 6){
            return 6;
        }else {
            return count_num;
        }
    }

    @Override
    public Integer countWait() {
        return workRepository.countWorksWait();
    }

    @Override
    public List<WorkVO> allWorksWait(Integer page) {
        Integer index = (page - 1)*LIMITWait;
        return workRepository.allWorksWait(index,LIMITWait);
    }

    @Override
    public List<WorkVO> selectWorkWaits(String name, String startDate, String endDate,Integer page) {
        Integer index = (page - 1)*LIMITWait;
        return workRepository.selectWorkWaits(name,startDate,endDate,index,LIMITWait);
    }

    @Override
    public Integer getCountSelectWorkWaits(String name, String startDate, String endDate) {
        return workRepository.countSelectWorkWaits(name,startDate,endDate);
    }

    @Override
    public Integer passWorkByid(Integer record_id) {
        return workRepository.passwork(record_id);
    }

    @Override
    public Integer refauseWorkByid(Integer record_id) {
        return workRepository.refausework(record_id);
    }

    @Override
    public WorkVO ShowWorkDetail(String record_idStr) {
        Integer record_id = Integer.parseInt(record_idStr);
        return workRepository.ShowWorkDetail(record_id);
    }
}
