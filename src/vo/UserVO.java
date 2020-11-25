package vo;

import java.util.Date;

public class UserVO {
    private String account;
    private String name;
    private String department_name;
    private String user_type;
    private Date create_time;
    private String sex;
    private String mobile;
    private String birthday;
    private String email;

    public UserVO(String account, String name) {
        this.account = account;
        this.name = name;
    }

    public UserVO() {
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", department_name='" + department_name + '\'' +
                ", user_type='" + user_type + '\'' +
                ", create_time=" + create_time +
                ", sex='" + sex + '\'' +
                ", mobile='" + mobile + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public UserVO(String account, String name, String department_name, String user_type, Date create_time, String sex, String mobile, String birthday, String email) {
        this.account = account;
        this.name = name;
        this.department_name = department_name;
        this.user_type = user_type;
        this.create_time = create_time;
        this.sex = sex;
        this.mobile = mobile;
        this.birthday = birthday;
        this.email = email;
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

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
