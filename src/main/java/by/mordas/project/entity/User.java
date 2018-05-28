package by.mordas.project.entity;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/***
 Author: Sergei Mordas
 Date: 14.03.2018
 ***/

public class User extends Entity {

    /** The user id. */
    private long userId;

    /** The login. */
    private String login;

    /** The password. */
    private String password;

    /** The email. */
    private String email;

    /** The first name. */
    private String firstName;

    /** The last name. */
    private String lastName;

    /** The certificate mark */
    private int certificateMark;

    /** The speciality id. */
    private long specialityId;

    /** The date of birthday. */
    private Date birthday;

    /** The users map of subject mark. */
    private Map<Subject,Integer> subjectMark;

    /** The role id. */
    private int roleId;

    /**
     * Instantiates a new user.
     */
    public User() {
        subjectMark=new HashMap<>(3);
    }

    /**
     * Instantiates a new user.
     *
     * @param userId the user id
     * @param login the login
     * @param password the password
     * @param email the email
     * @param firstName the first name
     * @param lastName the last name
     * @param certificateMark the certificate mark
     * @param specialityId the speciality id
     * @param birthday the date of birthday
     * @param subjectMark the map of subject mark
     * @param roleId the role id
     */
    public User(long userId, String login, String password, String email, String firstName, String lastName, int certificateMark, long specialityId, Date birthday, Map<Subject, Integer> subjectMark, int roleId) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.certificateMark = certificateMark;
        this.specialityId = specialityId;
        this.birthday = birthday;
        this.subjectMark = subjectMark;
        this.roleId = roleId;
    }
    /**
     * Gets the user id.
     *
     * @return the userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Gets the login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the role id.
     *
     * @return the role id
     */
    public int getRoleId() {
        return roleId;
    }


    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * Gets the map of subject mark.
     *
     * @return the the map of subject mark
     */
    public Map<Subject, Integer> getSubjectMark() {
        return subjectMark;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the certificate mark.
     *
     * @return the certificate mark
     */
    public int getCertificateMark() {
        return certificateMark;
    }

    /**
     * Gets the the date of birthday.
     *
     * @return the date of birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Gets the the speciality id.
     *
     * @return the speciality id
     */
    public long getSpecialityId() {
        return specialityId;
    }

    /**
     * Sets the login.
     *
     * @param login the new login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the role id.
     *
     * @param roleId the new role id
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Sets map of subject mark.
     *
     * @param subjectMark the new map of subject mark.
     */

    public void setSubjectMark(Map<Subject, Integer> subjectMark) {
        this.subjectMark = subjectMark;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }


    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /**
     * Sets the certificate mark.
     *
     * @param certificateMark the new certificate mark
     */
    public void setCertificateMark(int certificateMark) {
        this.certificateMark = certificateMark;
    }


    /**
     * Sets the speciality id.
     *
     * @param specialityId the new speciality id
     */
    public void setSpecialityId(long specialityId) {
        this.specialityId = specialityId;
    }


    /**
     * Sets the date of birthday.
     *
     * @param birthday the new date of birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Put the subject and subject mark into map subject mark.
     *
     * @param key,value the add new pair subject amd mark into  map subjects mark
     */
    public Integer put(Subject key, Integer value) {
        return subjectMark.put(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                certificateMark == user.certificateMark &&
                specialityId == user.specialityId &&
                roleId == user.roleId &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(subjectMark, user.subjectMark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, login, password, email, firstName, lastName, certificateMark, specialityId, birthday, subjectMark, roleId);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
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
}
