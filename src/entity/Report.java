package entity;

import java.util.Date;

public class Report {
    private String account;
    private Date reportDate;
    private Integer work_process;
    private String work_content;
    private String tomorrow_plan;
    private String problem;
    private String other;

    @Override
    public String toString() {
        return "Report{" +
                "account='" + account + '\'' +
                ", reportDate=" + reportDate +
                ", work_process=" + work_process +
                ", work_content='" + work_content + '\'' +
                ", tomorrow_plan='" + tomorrow_plan + '\'' +
                ", problem='" + problem + '\'' +
                ", other='" + other + '\'' +
                '}';
    }

    public Report(String account, Date reportDate, Integer work_process, String work_content, String tomorrow_plan, String problem, String other) {
        this.account = account;
        this.reportDate = reportDate;
        this.work_process = work_process;
        this.work_content = work_content;
        this.tomorrow_plan = tomorrow_plan;
        this.problem = problem;
        this.other = other;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Integer getWork_process() {
        return work_process;
    }

    public void setWork_process(Integer work_process) {
        this.work_process = work_process;
    }

    public String getWork_content() {
        return work_content;
    }

    public void setWork_content(String work_content) {
        this.work_content = work_content;
    }

    public String getTomorrow_plan() {
        return tomorrow_plan;
    }

    public void setTomorrow_plan(String tomorrow_plan) {
        this.tomorrow_plan = tomorrow_plan;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
