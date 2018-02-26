package by.mordas.project.entity;

import java.util.ArrayList;
import java.util.List;

public class Speciality extends Entity {
    private int specialityId;
    private String specialityName;
    private int recruitmentPlan;
    private int facultyId;
    private List<Subject> subjects;


    public Speciality(){
        subjects=new ArrayList<>();
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public int getRecruitmentPlan() {
        return recruitmentPlan;
    }

    public void setRecruitmentPlan(int recruitmentPlan) {
        this.recruitmentPlan = recruitmentPlan;
    }
}
