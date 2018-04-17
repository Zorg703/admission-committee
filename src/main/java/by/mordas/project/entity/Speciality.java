package by.mordas.project.entity;

import java.util.ArrayList;
import java.util.List;

public class Speciality extends Entity {
    private long specialityId;
    private String specialityName;
    private int recruitmentPlan;
    private long facultyId;
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

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public long getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(long specialityId) {
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

    public boolean add(Subject subject) {
        return subjects.add(subject);
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "specialityId=" + specialityId +
                ", specialityName='" + specialityName + '\'' +
                ", recruitmentPlan=" + recruitmentPlan +
                ", facultyId=" + facultyId +
                ", subjects=" + subjects +
                "} " + super.toString();
    }
}
