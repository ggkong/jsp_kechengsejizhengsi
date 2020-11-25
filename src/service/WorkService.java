package service;


import entity.Work;
import vo.ReportVO;
import vo.WorkVO;

import java.util.List;

public interface WorkService {
    public Integer count();
    public List<WorkVO> allWorks(Integer page);
    public Integer countWorkByAccountAndDate(String account,String workDate);
    public Integer addWork(Work work);
    public Integer delWork(String workId);
    public List<WorkVO> selectWorks(String State, String startDate, String endDate);
    public Integer getCountSelectWorks(String State, String startDate, String endDate);
    public Integer countWait();
    public List<WorkVO> allWorksWait(Integer page);
    public List<WorkVO> selectWorkWaits(String name, String startDate, String endDate, Integer page);
    public Integer getCountSelectWorkWaits(String name, String startDate, String endDate);
    public Integer passWorkByid(Integer record_id);
    public Integer refauseWorkByid(Integer record_id);
    public WorkVO ShowWorkDetail(String record_idStr);
}
