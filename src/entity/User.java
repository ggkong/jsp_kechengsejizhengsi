package entity;

import java.util.Date;

public class User {
    private Integer id;
    private String account;
    private String password;
    private String name;
    private Integer department_id;
    private String sex;
    private String birthday;
    private String mobile;
    private String email;
    private String user_type;
    private String mylevel;
    private Date create_time;
    private String state;

    public User(String account, String password, String name, Integer department_id, String sex, String birthday, String mobile, String email) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.department_id = department_id;
        this.sex = sex;
        this.birthday = birthday;
        this.mobile = mobile;
        this.email = email;
    }

    public User(String account, String password, String name, Integer department_id, String sex, String birthday, String mobile, String email, String user_type, String mylevel, Date create_time, String state) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.department_id = department_id;
        this.sex = sex;
        this.birthday = birthday;
        this.mobile = mobile;
        this.email = email;
        this.user_type = user_type;
        this.mylevel = mylevel;
        this.create_time = create_time;
        this.state = state;
    }

    public String toStringForUpDate() {
        return "User{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", department_id=" + department_id +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User(Integer id, String account, String password, String name, Integer department_id, String sex, String birthday, String mobile, String email, String user_type, String mylevel, Date create_time, String state) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.name = name;
        this.department_id = department_id;
        this.sex = sex;
        this.birthday = birthday;
        this.mobile = mobile;
        this.email = email;
        this.user_type = user_type;
        this.mylevel = mylevel;
        this.create_time = create_time;
        this.state = state;
    }

    public String toStringNoId() {
        return "User{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", department_id=" + department_id +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", user_type='" + user_type + '\'' +
                ", mylevel='" + mylevel + '\'' +
                ", create_time=" + create_time +
                ", state='" + state + '\'' +
                '}';
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getMylevel() {
        return mylevel;
    }

    public void setMylevel(String mylevel) {
        this.mylevel = mylevel;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", department_id=" + department_id +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", user_type='" + user_type + '\'' +
                ", mylevel='" + mylevel + '\'' +
                ", create_time=" + create_time +
                ", state='" + state + '\'' +
                '}';
    }
}
