package by.mordas.project.entity;

import java.util.Objects;

/***
 Author: Sergei Mordas
 Date: 14.03.2018
 ***/

public class Subject extends Entity {

    /** The subject id. */
    private long subjectId;

    /** The subject name. */
    private String subjectName;

    /**
     * Instantiates a new speciality.
     */
    public Subject() {
    }

    /**
     * Instantiates a new faculty.
     *
     * @param subjectId the subject id
     * @param subjectName the subject name
     */
    public Subject(long subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    /**
     * Gets the subject id.
     *
     * @return the subjectId
     */
    public long getSubjectId() {
        return subjectId;
    }


    /**
     * Gets the subject name.
     *
     * @return the subjectId
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Sets the subject name.
     *
     * @param subjectId the new subject id
     */
    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * Sets the subject name.
     *
     * @param subjectName the new subject name
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return subjectId == subject.subjectId &&
                Objects.equals(subjectName, subject.subjectName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(subjectId, subjectName);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                "} " + super.toString();
    }

}

