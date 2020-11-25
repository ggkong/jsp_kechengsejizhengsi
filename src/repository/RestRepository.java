package repository;

import entity.Rest;
import vo.RestVO;
import vo.WorkVO;

import java.util.List;

public interface RestRepository {
    public Integer countRests();
    public List<RestVO> allRests(Integer index, Integer limit);
    public Integer insertRest(Rest rest);
    public List<RestVO> selectRests(String restStartDate,String restEndDate,String state,Integer index,Integer limit);
    public Integer selectCount(String restStartDate,String restEndDate,String state);
    public RestVO selectRestByRestId(Integer restId);
    public Integer upDateRest(Integer rest_id, String startDate, String endDate, String rest_time, String cause);
    public Integer delRestById(Integer rest_id);
    public int[] delManyRest(String[] delIds);
    public List<RestVO> allRestsWait(Integer index,Integer limit);
    public Integer countRestsWait();
    public List<RestVO> selectRestWaits(String name, String startDate, String endDate,Integer index,Integer limit);
    public Integer countSelectRestWaits(String name, String startDate, String endDate);
    public Integer passrest(Integer rest_id);
    public Integer refauserest(Integer rest_id);
}
