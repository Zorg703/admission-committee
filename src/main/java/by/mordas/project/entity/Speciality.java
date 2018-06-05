package by.mordas.project.entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/***
 Author: Sergei Mordas
 Date: 14.03.2018
 ***/

public class Speciality extends Entity {

    /** The speciality id. */
    private long specialityId;

    /** The speciality name. */
    private String specialityName;

    /** The recruitment plan. */
    private int recruitmentPlan;

    /** The faculty id. */
    private long facultyId;

    /** The Speciality subjects list. */
    private List<Subject> subjects;

    /** The start date registration on speciality. */
    private LocalDateTime startRegistration;

    /** The end date registration on speciality. */
    private LocalDateTime endRegistration;

    /**
     * Instantiates a new speciality.
     */
    public Speciality() {
        subjects = new ArrayList<>();
    }

    /**
     * Instantiates a new speciality.
     *
     * @param specialityId the speciality id
     * @param specialityName the speciality name
     * @param recruitmentPlan the recruitment plan
     * @param facultyId the faculty id
     * @param subjects the subject list
     * @param start_registration the start date registration on speciality
     * @param end_registration the end date registration on speciality
     */
    public Speciality(long specialityId, String specialityName, int recruitmentPlan, long facultyId, List<Subject> subjects, LocalDateTime start_registration, LocalDateTime end_registration) {
        this.specialityId = specialityId;
        this.specialityName = specialityName;
        this.recruitmentPlan = recruitmentPlan;
        this.facultyId = facultyId;
        this.subjects = subjects;
        this.startRegistration = start_registration;
        this.endRegistration = end_registration;
    }

    /**
     * Gets the speciality id.
     *
     * @return the specialityId
     */
    public long getSpecialityId() {
        return specialityId;
    }

    /**
     * Gets the speciality name.
     *
     * @return the specialityName
     */
    public String getSpecialityName() {
        return specialityName;
    }

    /**
     * Gets the recruitment plan.
     *
     * @return the recruitmentOlan
     */
    public int getRecruitmentPlan() {
        return recruitmentPlan;
    }

    /**
     * Gets the faculty id.
     *
     * @return the facultyId
     */
    public long getFacultyId() {
        return facultyId;
    }

    /**
     * Gets the subjects list.
     *
     * @return the subjects
     */
    public List<Subject> getSubjects() {
        return subjects;
    }

    /**
     * Gets the the start date registration on speciality.
     *
     * @return the startRegistration
     */
    public LocalDateTime getStartRegistration() {
        return startRegistration;
    }

    /**
     * Gets the the end date registration on speciality.
     *
     * @return the endRegistration
     */
    public LocalDateTime getEndRegistration() {
        return endRegistration;
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
     * Sets the speciality name.
     *
     * @param specialityName the new speciality name
     */
    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    /**
     * Sets the recruitment plan.
     *
     * @param recruitmentPlan the new recruitment plan
     */
    public void setRecruitmentPlan(int recruitmentPlan) {
        this.recruitmentPlan = recruitmentPlan;
    }

    /**
     * Sets the faculty id.
     *
     * @param facultyId the new facultyId
     */
    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }


    /**
     * Sets the subjects list.
     *
     * @param subjects the new sublects list
     */
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    /**
     * Sets the start registration date.
     *
     * @param startRegistration the new start registration date
     */
    public void setStartRegistration(LocalDateTime startRegistration) {
        this.startRegistration = startRegistration;
    }

    /**
     * Sets the end registration date.
     *
     * @param endRegistration the new end registration date
     */
    public void setEndRegistration(LocalDateTime endRegistration) {
        this.endRegistration = endRegistration;
    }

    /**
     * Add the subject into list.
     *
     * @param subject the add sublect into list
     */
    public boolean add(Subject subject) {
        return subjects.add(subject);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speciality that = (Speciality) o;
        return specialityId == that.specialityId &&
                recruitmentPlan == that.recruitmentPlan &&
                facultyId == that.facultyId &&
                Objects.equals(specialityName, that.specialityName) &&
                Objects.equals(subjects, that.subjects) &&
                Objects.equals(startRegistration, that.startRegistration) &&
                Objects.equals(endRegistration, that.endRegistration);
    }

    @Override
    public int hashCode() {

        return Objects.hash(specialityId, specialityName, recruitmentPlan, facultyId, subjects, startRegistration, endRegistration);
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "specialityId=" + specialityId +
                ", specialityName='" + specialityName + '\'' +
                ", recruitmentPlan=" + recruitmentPlan +
                ", facultyId=" + facultyId +
                ", subjects=" + subjects +
                ", startRegistration=" + startRegistration +
                ", endRegistration=" + endRegistration +
                "} " + super.toString();
    }
}


