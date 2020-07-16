package bookrental;

import java.util.Date;

public class UserRegistered extends AbstractEvent {

    private Long id;
    private String userName;
    private Date regTime;
    private String birth;
    private String sex;
    private String emailAddress;
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getBirth() { return birth; }
    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSex() { return sex; }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}