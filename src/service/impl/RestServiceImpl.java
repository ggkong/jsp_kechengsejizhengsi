package service.impl;

import entity.Rest;
import repository.RestRepository;
import repository.impl.RestRepositoryImpl;
import service.RestService;
import vo.RestVO;

import java.util.List;

public class RestServiceImpl implements RestService {
    private final int LIMIT = 10;
    private final int LIMITWait = 5;
    private RestRepository restRepository = new RestRepositoryImpl();
    @Override
    public List<RestVO> allRests(Integer page) {
        Integer index = (page - 1)*LIMIT;
        return restRepository.allRests(index,LIMIT);
    }

    @Override
    public Integer count() {
        return restRepository.countRests();
    }

    @Override
    public Integer addRest(Rest rest) {
        return restRepository.insertRest(rest);
    }


    @Override
    public List<RestVO> selectRestsByDateAndState(String restStartDate, String restEndDate, String state, Integer page) {
        Integer index = (page - 1)*LIMIT;
        return restRepository.selectRests(restStartDate,restEndDate,state,index,LIMIT);
    }

    @Override
    public Integer selectCount(String restStartDate, String restEndDate, String state) {
        return restRepository.selectCount(restStartDate,restEndDate,state);
    }

    @Override
    public RestVO selectRestByRestId(Integer restId) {
        return restRepository.selectRestByRestId(restId);
    }

    @Override
    public Integer upDateRest(Integer rest_id, String startDate, String endDate, String rest_time, String cause) {
        return restRepository.upDateRest(rest_id,startDate,endDate,rest_time,cause);
    }

    @Override
    public Integer delRestById(Integer rest_id) {
        return restRepository.delRestById(rest_id);
    }

    @Override
    public int[] delManyRest(String[] delIds) {
        return restRepository.delManyRest(delIds);
    }

    @Override
    public List<RestVO> allRestsWait(Integer page) {
        Integer index = (page - 1)*LIMITWait;
        return restRepository.allRestsWait(index,LIMITWait);
    }

    @Override
    public Integer countRest() {
        return restRepository.countRestsWait();
    }

    @Override
    public List<RestVO> selectRestWaits(String name, String startDate, String endDate, Integer page) {
        Integer index = (page - 1)*LIMITWait;
        return restRepository.selectRestWaits(name,startDate,endDate,index,LIMITWait);
    }

    @Override
    public Integer getCountSelectRestWaits(String name, String startDate, String endDate) {
        return restRepository.countSelectRestWaits(name,startDate,endDate);
    }

    @Override
    public Integer passRestByid(Integer rest_id) {
        return restRepository.passrest(rest_id);
    }

    @Override
    public Integer refauseRestByid(Integer rest_id) {
        return restRepository.refauserest(rest_id);
    }
}
