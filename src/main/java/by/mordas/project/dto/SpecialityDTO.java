package by.mordas.project.dto;

import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;

import java.time.LocalDateTime;
import java.util.List;

/***
 Author: Sergei Mordas
 Date: 18.04.2018
 ***/
public class SpecialityDTO {
    private Speciality speciality;
    private boolean registerEnd;
    private int countRegisterUser;
    private boolean specialityFull;
    private int passingScore;

    public SpecialityDTO(){

    }
    public SpecialityDTO(Speciality speciality){
        this.speciality=speciality;
    }

    public long getFacultyId() {
        return speciality.getFacultyId();
    }

    public void setFacultyId(long facultyId) {
        speciality.setFacultyId(facultyId);
    }

    public long getSpecialityId() {
        return speciality.getSpecialityId();
    }

    public void setSpecialityId(long specialityId) {
        speciality.setSpecialityId(specialityId);
    }

    public String getSpecialityName() {
        return speciality.getSpecialityName();
    }

    public void setSpecialityName(String specialityName) {
        speciality.setSpecialityName(specialityName);
    }

    public int getRecruitmentPlan() {
        return speciality.getRecruitmentPlan();
    }

    public void setRecruitmentPlan(int recruitmentPlan) {
        speciality.setRecruitmentPlan(recruitmentPlan);
    }

    public LocalDateTime getStartRegistration() {
        return speciality.getStartRegistration();
    }

    public void setStartRegistration(LocalDateTime startRegistration) {
        speciality.setStartRegistration(startRegistration);
    }

    public LocalDateTime getEndRegistration() {
        return speciality.getEndRegistration();
    }

    public void setEndRegistration(LocalDateTime endRegistration) {
        speciality.setEndRegistration(endRegistration);
    }

    public int getCountRegisterUser() {
        return countRegisterUser;
    }

    public void setCountRegisterUser(int countRegisterUser) {
        this.countRegisterUser = countRegisterUser;
    }

    public int getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(int passingScore) {
        this.passingScore = passingScore;
    }

    public boolean isRegisterEnd() {
        return registerEnd;
    }

    public void setRegisterEnd(boolean registerEnd) {
        this.registerEnd = registerEnd;
    }

    public boolean isSpecialityFull() {
        return specialityFull;
    }

    public void setSpecialityFull(boolean specialityFull) {
        this.specialityFull = specialityFull;
    }
}
