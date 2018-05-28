package by.mordas.project.entity;

import java.util.Objects;

/***
 Author: Sergei Mordas
 Date: 14.03.2018
 ***/

public class Faculty extends Entity {

    /** The faculty id. */
    private long facultyId;

    /** The faculty name. */
    private String facultyName;

    /**
     * Instantiates a new faculty.
     */
    public Faculty() {

    }

    /**
     * Instantiates a new faculty.
     *
     * @param facultyId the faculty id
     * @param facultyName the faculty name
     */
    public Faculty(long facultyId, String facultyName) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
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
     * Gets the faculty name.
     *
     * @return the faculty name
     */
    public String getFacultyName() {
        return facultyName;
    }

    /**
     * Sets the faculty id.
     *
     * @param facultyId the new faculty id
     */
    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    /**
     * Sets the facultyId.
     *
     * @param facultyName the new faculty name
     */
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return facultyId == faculty.facultyId &&
                Objects.equals(facultyName, faculty.facultyName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(facultyId, facultyName);
    }


    @Override
    public String toString() {
        return "Faculty{" +
                "facultyId=" + facultyId +
                ", facultyName='" + facultyName + '\'' +
                "} " + super.toString();
    }
}
