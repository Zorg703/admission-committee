package by.mordas.project.entity;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class User extends Entity {
    private String login;
    private String password;
    private long userId;
    private String firstName;
    private String lastName;
    private int certificateMark;
    private long specialityId;
    private Date birthday;
    private Map<Subject,Integer> subjectMark;
    private String email;
    private int roleId;



    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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

    public long getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(long specialityId) {
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
