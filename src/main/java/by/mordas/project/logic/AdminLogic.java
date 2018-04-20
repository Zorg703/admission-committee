package by.mordas.project.logic;

import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.User;

import java.util.List;

public interface AdminLogic extends Logic{
    public List<User> findAllUser() throws LogicException;

    public Faculty addFaculty(String facultyName) throws LogicException;

    public boolean deleteFaculty(String id) throws LogicException;

    public Speciality addSpeciality(Speciality speciality) throws LogicException;

    public User findUserById(String id) throws LogicException;

    public boolean deleteSpeciality(String specialityId) throws LogicException;
}
