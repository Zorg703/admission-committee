package by.mordas.project.logic;

import by.mordas.project.dao.*;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;
import by.mordas.project.util.PasswordEncoder;
import org.apache.logging.log4j.Level;

import java.util.List;
import java.util.Map;

public interface UserLogic extends Logic {
    public List<Faculty> findAllFaculties() throws LogicException;

    public List<Speciality> findSpecialitiesByFacultyId(String id) throws LogicException;

    public void registerUser(User user) throws LogicException;

    public boolean findUserByLogin(String login) throws LogicException;

    public Subject findSubject(String id) throws LogicException;

    public Map<Subject, Integer> findSubjects(String id) throws LogicException;

    public void changePassword(String userId, String password) throws LogicException;

    public List<Subject> findSubjectsForSpeciality(String specialityId) throws LogicException;

    public void setUserSpeciality(User user) throws LogicException;
}