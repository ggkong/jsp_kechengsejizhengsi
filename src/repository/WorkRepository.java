package repository;

import entity.Work;
import vo.WorkVO;

import java.util.List;

public interface WorkRepository {
    public Integer insertWork(Work work);
    public Integer countWorkByAccountAndDate(String account,String workDate);
    public Integer countWorks();
    public List<WorkVO> allWorks(Integer index, Integer limit);
    public Integer delWork(Integer record_id);
    public List<WorkVO> selectWorks(String state, String startDate, String endDate);
    public Integer countSelectWorks(String state, String startDate, String endDate);
    public Integer countWorksWait();
    public List<WorkVO> allWorksWait(Integer index,Integer limit);
    public List<WorkVO> selectWorkWaits(String name, String startDate, String endDate,Integer index,Integer limit);
    public Integer countSelectWorkWaits(String name, String startDate, String endDate);
    public Integer passwork(Integer record_id);
    public Integer refausework(Integer record_id);
    public WorkVO ShowWorkDetail(Integer record_id);
}
