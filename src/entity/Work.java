package entity;

public class Work {
    private Integer record_id;
    private String account;
    private String work_date;
    private String start_time;
    private String end_time;
    private String work_time;
    private String work_cause;
    private String remark;
    private String state;

    public Work(Integer record_id, String account, String work_date, String start_time, String end_time, String work_time, String work_cause, String remark, String state) {
        this.record_id = record_id;
        this.account = account;
        this.work_date = work_date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.work_time = work_time;
        this.work_cause = work_cause;
        this.remark = remark;
        this.state = state;
    }

    public Work(String account, String work_date, String start_time, String end_time, String work_time, String work_cause, String remark, String state) {
        this.account = account;
        this.work_date = work_date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.work_time = work_time;
        this.work_cause = work_cause;
        this.remark = remark;
        this.state = state;
    }

    public Integer getRecord_id() {
        return record_id;
    }

    public void setRecord_id(Integer record_id) {
        this.record_id = record_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getWork_date() {
        return work_date;
    }

    public void setWork_date(String work_date) {
        this.work_date = work_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getWork_time() {
        return work_time;
    }

    public void setWork_time(String work_time) {
        this.work_time = work_time;
    }

    public String getWork_cause() {
        return work_cause;
    }

    public void setWork_cause(String work_cause) {
        this.work_cause = work_cause;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Work{" +
                "record_id=" + record_id +
                ", account='" + account + '\'' +
                ", work_date='" + work_date + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", work_time='" + work_time + '\'' +
                ", work_cause='" + work_cause + '\'' +
                ", remark='" + remark + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
