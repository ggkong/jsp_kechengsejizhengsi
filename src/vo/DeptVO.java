package vo;

import java.util.Date;


public class DeptVO {
    private Integer departmentId;
    private String departmentName;
    private String managerName;
    private Integer total_user;
    private Date createTime;
    private String account;


    public DeptVO(Integer departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public DeptVO(Integer departmentId, String departmentName, Integer total_user, Date createTime, String account) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.total_user = total_user;
        this.createTime = createTime;
        this.account = account;
    }

    public DeptVO(Integer departmentId) {
        this.departmentId = departmentId;
    }
    public void DetVOdepartmentname(String departmentName){
        this.departmentName = departmentName;
    }

    public DeptVO(String account) {
        this.account = account;
    }

    public DeptVO(Integer departmentId, String departmentName, String managerName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.managerName = managerName;
    }

    public void DeptVObyaccount(Integer departmentId, String departmentName, String account ) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.account = account;
    }

    public DeptVO(Integer departmentId, String departmentName, String managerName, Integer total_user, Date createTime) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.managerName = managerName;
        this.total_user = total_user;
        this.createTime = createTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public DeptVO(){


    }

    public DeptVO(Integer departmentId, String departmentName, String managerName, Integer total_user, Date createTime, String account) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.managerName = managerName;
        this.total_user = total_user;
        this.createTime = createTime;
        this.account = account;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Integer getTotal_user() {
        return total_user;
    }

    public void setTotal_user(Integer total_user) {
        this.total_user = total_user;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DeptVO{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", managerName='" + managerName + '\'' +
                ", total_user=" + total_user +
                ", createTime=" + createTime +
                ", account='" + account + '\'' +
                '}';
    }
}
