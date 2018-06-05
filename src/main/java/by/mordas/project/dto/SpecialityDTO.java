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
    /** This is speciality*/
    private Speciality speciality;

    /** This is speciality register end status*/
    private boolean registerEnd;

    /**This is count of registered users on speciality*/
    private int countRegisterUser;

    /** This speciality full status*/
    private boolean specialityFull;

    /**This is passing scores on speciality*/
    private int passingScore;

    /**
     * Instantiates a new speciality dto.
     */
    public SpecialityDTO(){

    }


    /**
     *Instantiates a new speciality dto.
     *
     * @param speciality the speciality
     */
    public SpecialityDTO(Speciality speciality){
        this.speciality=speciality;
    }

    /**
     *
     * @param speciality the speciality
     * @param registerEnd the speciality register end status
     * @param countRegisterUser the count of registered users on specialit
     * @param specialityFull the speciality full status
     * @param passingScore the passing scores on speciality
     */
    public SpecialityDTO(Speciality speciality, boolean registerEnd, int countRegisterUser, boolean specialityFull, int passingScore) {
        this.speciality = speciality;
        this.registerEnd = registerEnd;
        this.countRegisterUser = countRegisterUser;
        this.specialityFull = specialityFull;
        this.passingScore = passingScore;
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
