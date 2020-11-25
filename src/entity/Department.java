package entity;

import java.util.Date;

public class Department {
    private Integer departmentId;
    private String departmentName;
    private String manager;
    private Integer total_user;
    private Date createTime;

    public Department(Integer departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department() {
    }

    public String toStringIdAndName() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", manager='" + manager + '\'' +
                ", total_user=" + total_user +
                ", createTime=" + createTime +
                '}';
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

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
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

    public Department(Integer departmentId, String departmentName, String manager, Integer total_user, Date createTime) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.manager = manager;
        this.total_user = total_user;
        this.createTime = createTime;
    }
}
