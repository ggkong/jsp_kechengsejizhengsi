package repository;

import entity.Report;
import vo.ReportVO;

import java.util.Date;
import java.util.List;

public interface ReportRepository {
    public Integer insertReport(Report report);
    public Integer getReportNumByTimeAndAccount(String account, Date date);
    public List<ReportVO> allReports(Integer index, Integer limit);
    public Integer countReports();
    public Integer delReportByReportId(Integer report_id);
    public ReportVO getReportById(Integer report_id);
    public Integer upDateReport(ReportVO reportVO);
    public List<ReportVO> selectReports(String name, String startDate, String endDate);
    public Integer countSelectReport(String name, String startDate, String endDate);
}
