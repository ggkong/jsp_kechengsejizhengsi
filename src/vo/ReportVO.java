package vo;

import java.util.Date;

public class ReportVO {
    private Integer report_id;
    private String account;
    private String name;
    private Date date;
    private Integer work_progress;
    private String work_content;
    private String tomorrow_plan;
    private String problem;
    private String other;

    public ReportVO(Integer report_id, String account, Date date, Integer work_progress, String work_content, String tomorrow_plan, String problem, String other) {
        this.report_id = report_id;
        this.account = account;
        this.date = date;
        this.work_progress = work_progress;
        this.work_content = work_content;
        this.tomorrow_plan = tomorrow_plan;
        this.problem = problem;
        this.other = other;
    }

    @Override
    public String toString() {
        return "ReportVO{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", work_progress=" + work_progress +
                ", work_content='" + work_content + '\'' +
                ", tomorrow_plan='" + tomorrow_plan + '\'' +
                ", problem='" + problem + '\'' +
                ", other='" + other + '\'' +
                '}';
    }

    public ReportVO(Integer report_id, String account, String name, Date date, Integer work_progress, String work_content, String tomorrow_plan, String problem, String other) {
        this.report_id = report_id;
        this.account = account;
        this.name = name;
        this.date = date;
        this.work_progress = work_progress;
        this.work_content = work_content;
        this.tomorrow_plan = tomorrow_plan;
        this.problem = problem;
        this.other = other;
    }

    public String toStringAll() {
        return "ReportVO{" +
                "report_id=" + report_id +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", work_progress=" + work_progress +
                ", work_content='" + work_content + '\'' +
                ", tomorrow_plan='" + tomorrow_plan + '\'' +
                ", problem='" + problem + '\'' +
                ", other='" + other + '\'' +
                '}';
    }

    public ReportVO() {
    }

    public Integer getReport_id() {
        return report_id;
    }

    public void setReport_id(Integer report_id) {
        this.report_id = report_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getWork_progress() {
        return work_progress;
    }

    public void setWork_progress(Integer work_progress) {
        this.work_progress = work_progress;
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

    public ReportVO(String account, String name, Date date, Integer work_progress, String work_content, String tomorrow_plan, String problem, String other) {
        this.account = account;
        this.name = name;
        this.date = date;
        this.work_progress = work_progress;
        this.work_content = work_content;
        this.tomorrow_plan = tomorrow_plan;
        this.problem = problem;
        this.other = other;
    }
}
