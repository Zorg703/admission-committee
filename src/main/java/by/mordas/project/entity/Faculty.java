package by.mordas.project.entity;

import java.util.ArrayList;

public class Faculty extends Entity {
    private int facultyId;
    private String facultyName;
    private ArrayList<Specialty> specialties;

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public ArrayList<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(ArrayList<Specialty> specialties) {
        this.specialties = specialties;
    }
}
