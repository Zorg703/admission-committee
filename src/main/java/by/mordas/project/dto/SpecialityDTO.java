package by.mordas.project.dto;

import by.mordas.project.entity.Speciality;

public class SpecialityDTO {
    private Speciality speciality;
    private boolean isRegisterEnd;
    private int countRegisterUser;
    private boolean isSpecialityFull;

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public boolean isRegisterEnd() {
        return isRegisterEnd;
    }

    public void setRegisterEnd(boolean registerEnd) {
        isRegisterEnd = registerEnd;
    }

    public int getCountRegisterUser() {
        return countRegisterUser;
    }

    public void setCountRegisterUser(int countRegisterUser) {
        this.countRegisterUser = countRegisterUser;
    }

    public boolean isSpecialityFull() {
        return isSpecialityFull;
    }

    public void setSpecialityFull(boolean specialityFull) {
        isSpecialityFull = specialityFull;
    }
}
