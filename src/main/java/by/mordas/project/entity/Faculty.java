package by.mordas.project.entity;

import java.util.ArrayList;

public class Faculty extends Entity {
    private long facultyId;
    private String facultyName;


    public Faculty() {

    }
    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "facultyId=" + facultyId +
                ", facultyName='" + facultyName + '\'' +
                "} " + super.toString();
    }
}
