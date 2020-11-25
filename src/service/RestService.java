package service;

import entity.Rest;
import vo.RestVO;

import java.util.List;

public interface RestService {
    public List<RestVO> allRests(Integer page);
    public Integer count();
    public Integer addRest(Rest rest);
    public List<RestVO> selectRestsByDateAndState(String restStartDate,String restEndDate,String state,Integer page);
    public Integer selectCount(String restStartDate,String restEndDate,String state);
    public RestVO selectRestByRestId(Integer restId);
    public Integer upDateRest(Integer rest_id,String startDate,String endDate,String rest_time,String cause);
    public Integer delRestById(Integer rest_id);
    public int[] delManyRest(String[] delIds);
    public List<RestVO> allRestsWait(Integer page);
    public Integer countRest();
    public List<RestVO> selectRestWaits(String name, String startDate, String endDate, Integer page);
    public Integer getCountSelectRestWaits(String name, String startDate, String endDate);
    public Integer passRestByid(Integer rest_id);
    public Integer refauseRestByid(Integer rest_id);
}
