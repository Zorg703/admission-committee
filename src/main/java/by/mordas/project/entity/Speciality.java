package by.mordas.project.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Speciality extends Entity {
    private long specialityId;
    private String specialityName;
    private int recruitmentPlan;
    private long facultyId;
    private List<Subject> subjects;
    private LocalDateTime start_registration;
    private LocalDateTime end_registration;



    public Speciality(){
        subjects=new ArrayList<>();
    }

    public Speciality(long specialityId, String specialityName, int recruitmentPlan, long facultyId, List<Subject> subjects,LocalDateTime start_registration ,LocalDateTime end_registration) {
        this.specialityId = specialityId;
        this.specialityName = specialityName;
        this.recruitmentPlan = recruitmentPlan;
        this.facultyId = facultyId;
        this.subjects = subjects;
        this.start_registration=start_registration;
        this.end_registration = end_registration;
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

    public LocalDateTime getStart_registration() {
        return start_registration;
    }

    public void setStart_registration(LocalDateTime start_registration) {
        this.start_registration = start_registration;
    }

    public LocalDateTime getEnd_registration() {
        return end_registration;
    }

    public void setEnd_registration(LocalDateTime end_registration) {
        this.end_registration = end_registration;
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
