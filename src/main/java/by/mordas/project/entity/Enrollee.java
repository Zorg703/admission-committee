package by.mordas.project.entity;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Enrollee extends Entity {
    private String login;
    private String password;
    private int enrolleeId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private int certificateMark;
    private int specialityId;
    private Date birthday;
    private Map<Integer,Integer> subjectList=new HashMap<>(3);
    private boolean isRegister;

    public boolean isRegister() {
        return isRegister;
    }

    public void setRegister(boolean register) {
        isRegister = register;
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

    public int getEnrolleeId() {
        return enrolleeId;
    }

    public void setEnrolleeId(int enrolleeId) {
        this.enrolleeId = enrolleeId;
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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
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
}
