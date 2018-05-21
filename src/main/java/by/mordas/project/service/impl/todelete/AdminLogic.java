package by.mordas.project.service.impl.todelete;

import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.User;
import by.mordas.project.service.LogicException;

import java.util.HashMap;
import java.util.List;

public interface AdminLogic extends Logic{
    List<User> findAllUser() throws LogicException;

    Faculty addFaculty(String facultyName) throws LogicException;

    boolean deleteFaculty(String id) throws LogicException;

    User findUserById(String id) throws LogicException;

    boolean deleteSpeciality(String specialityId) throws LogicException;

    List<Speciality> findAllSpecialities() throws LogicException;

    List<User> findUsersRegisterOnSpeciality(String id) throws LogicException;

    Faculty findFacultyOnSpeciality(Speciality speciality) throws LogicException;

    Faculty findFaculty(String facultyId) throws LogicException;

    boolean updateFaculty(Faculty faculty) throws LogicException;

    HashMap<String,String> addSpeciality(HashMap<String,String> parameters) throws LogicException;

    Speciality findSpecialityById(String specialityId) throws LogicException;

    HashMap<String,String> updateSpeciality(HashMap<String,String> parameters) throws LogicException;

    List<User> findAllAcceptedUsersOnSpeciality(String specialityId) throws LogicException;

    List<Faculty> findAllFaculty() throws LogicException;

    List<Speciality> findSpecialitiesOnFaculty(String id) throws LogicException;

    HashMap<String,String> updateRegisterOnSpecialityDate(String startDate, String endDate, String specialityId) throws LogicException;
}
