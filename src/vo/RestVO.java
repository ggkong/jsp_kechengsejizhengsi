package vo;

public class RestVO {
    private Integer restId;
    private String account;
    private String name;
    private String rest_start_date;
    private String rest_end_date;
    private String rest_time;
    private String rest_cause;
    private String remark;
    private String state;

    public RestVO() {
    }

    public RestVO(Integer restId, String account, String name, String rest_start_date, String rest_end_date, String rest_time, String rest_cause, String remark, String state) {
        this.restId = restId;
        this.account = account;
        this.name = name;
        this.rest_start_date = rest_start_date;
        this.rest_end_date = rest_end_date;
        this.rest_time = rest_time;
        this.rest_cause = rest_cause;
        this.remark = remark;
        this.state = state;
    }

    @Override
    public String toString() {
        return "RestVO{" +
                "restId=" + restId +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", rest_start_date='" + rest_start_date + '\'' +
                ", rest_end_date='" + rest_end_date + '\'' +
                ", rest_time='" + rest_time + '\'' +
                ", rest_cause='" + rest_cause + '\'' +
                ", remark='" + remark + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public Integer getRestId() {
        return restId;
    }

    public void setRestId(Integer restId) {
        this.restId = restId;
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

    public String getRest_start_date() {
        return rest_start_date;
    }

    public void setRest_start_date(String rest_start_date) {
        this.rest_start_date = rest_start_date;
    }

    public String getRest_end_date() {
        return rest_end_date;
    }

    public void setRest_end_date(String rest_end_date) {
        this.rest_end_date = rest_end_date;
    }

    public String getRest_time() {
        return rest_time;
    }

    public void setRest_time(String rest_time) {
        this.rest_time = rest_time;
    }

    public String getRest_cause() {
        return rest_cause;
    }

    public void setRest_cause(String rest_cause) {
        this.rest_cause = rest_cause;
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
}
