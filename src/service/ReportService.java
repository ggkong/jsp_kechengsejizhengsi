package service;

import entity.Report;
import vo.ReportVO;

import java.util.Date;
import java.util.List;

public interface ReportService {
    public Integer addReport(Report report);
    public Integer count();
    public List<ReportVO> allUsers(Integer page);
    public Integer delReportByReportId(Integer report_id);
    public ReportVO getReportById(Integer report_id);
    public Integer upDateReport(ReportVO reportUpDated);
    public List<ReportVO> selectReports(String name, String startDate, String endDate);
    public Integer getCountSelectReport(String name, String startDate, String endDate);
}
