package by.mordas.project.entity;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class User extends Entity {
    private String login;
    private String password;
    private int userId;
    private String firstName;
    private String lastName;
    private int certificateMark;
    private int specialityId;
    private Date birthday;
    private Map<Subject,Integer> subjectMark;
    private String email;

    public User() {
        subjectMark=new HashMap<>(3);
    }

    public Map<Subject, Integer> getSubjectMark() {
        return subjectMark;
    }

    public void setSubjectMark(Map<Subject, Integer> subjectMark) {
        this.subjectMark = subjectMark;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", certificateMark=" + certificateMark +
                ", specialityId=" + specialityId +
                ", birthday=" + birthday +
                ", subjectMark=" + subjectMark +
                ", email='" + email + '\'' +
                "} " + super.toString();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCertificateMark() {
        return certificateMark;
    }

    public void setCertificateMark(int certificateMark) {
        this.certificateMark = certificateMark;
    }

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer put(Subject key, Integer value) {
        return subjectMark.put(key, value);
    }
}
