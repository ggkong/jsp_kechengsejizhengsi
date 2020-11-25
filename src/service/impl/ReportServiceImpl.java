package service.impl;

import entity.Report;
import repository.ReportRepository;
import repository.impl.ReportRepositoryImpl;
import service.ReportService;
import vo.ReportVO;

import java.util.Date;
import java.util.List;

public class ReportServiceImpl implements ReportService {
    private final int LIMIT = 10;   // 每页显示四条日志
    private ReportRepository reportRepository = new ReportRepositoryImpl();
    @Override
    public Integer addReport(Report report) {
//        如果 已经存在了日志,确保 一天只有一个 日志存在
        if (reportRepository.getReportNumByTimeAndAccount(report.getAccount(),report.getReportDate()) != 0){
            return 0;
        }else {
            return reportRepository.insertReport(report);
        }
    }

    @Override
    public Integer count() {
        return reportRepository.countReports();
    }

    @Override
    public List<ReportVO> allUsers(Integer page) {
        Integer index = (page - 1)*LIMIT;
        return reportRepository.allReports(index,LIMIT);
    }

    @Override
    public Integer delReportByReportId(Integer report_id) {
        return reportRepository.delReportByReportId(report_id);
    }

    @Override
    public ReportVO getReportById(Integer report_id) {
        return reportRepository.getReportById(report_id);
    }


    @Override
    public Integer upDateReport(ReportVO reportUpDated) {
        Integer return_num = 0;
        // 查找修改后的 日期是不是已经存在 日志
        ReportVO reportVOWillUpDate = reportRepository.getReportById(reportUpDated.getReport_id());
        //如果不进行日期之间的修改
        if (reportVOWillUpDate.getDate().equals(reportUpDated.getDate())){
            // 进行修改
            return_num = reportRepository.upDateReport(reportUpDated);
            return return_num;
        }
        // 如果进行日期的修改
        else {
//            看看将要修改的日期含不含 有日志
            if (reportRepository.getReportNumByTimeAndAccount(reportUpDated.getAccount(),reportUpDated.getDate())!=0){
//                含有日志 直接返回 表示有问题 含有日志
                return 0;
            }else {
                // 进行修改
                return_num = reportRepository.upDateReport(reportUpDated);
                return return_num;
            }
        }
    }

    @Override
    public List<ReportVO> selectReports(String name, String startDate, String endDate) {
        return reportRepository.selectReports(name,startDate,endDate);
    }

    @Override
    public Integer getCountSelectReport(String name, String startDate, String endDate) {
        Integer count_num = reportRepository.countSelectReport(name,startDate,endDate);
        if (count_num >= 6){
            return 6;
        }else {
            return count_num;
        }
    }


}
