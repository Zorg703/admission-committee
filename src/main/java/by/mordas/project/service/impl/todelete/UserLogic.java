package by.mordas.project.service.impl.todelete;


import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;
import by.mordas.project.service.LogicException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserLogic extends Logic {
    List<Faculty> findAllFaculties() throws LogicException;

    List<Speciality> findSpecialitiesByFacultyId(String id) throws LogicException;

   // boolean findUserByLogin(String login) throws LogicException;

    Map<Subject, Integer> findSubjects(Long id) throws LogicException;

    HashMap<String,String> changePassword(Long userId, String password1, String password2) throws LogicException;

    List<Subject> findSubjectsForSpeciality(String specialityId) throws LogicException;

    User setUserSpeciality(User user, Long specialityId, HashMap<String, String> parameters) throws LogicException;

    Speciality findSpeciality(Long id) throws LogicException;

    Faculty findFaculty(Long facultyId) throws LogicException;

    boolean isAccepted(Speciality speciality,User user) throws LogicException;

    boolean checkEndOfSpecialityRegistrationDate(Speciality speciality);

    User canceledUserRegistration(User user) throws LogicException;
}