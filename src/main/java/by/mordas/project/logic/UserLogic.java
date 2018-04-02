package by.mordas.project.logic;

import by.mordas.project.dao.*;
import by.mordas.project.dao.impl.FacultyDAOImpl;
import by.mordas.project.dao.impl.SpecialityDAOImpl;
import by.mordas.project.dao.impl.SubjectDAOImpl;
import by.mordas.project.dao.impl.UserDAOImpl;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;
import by.mordas.project.util.PasswordEncoder;
import by.mordas.project.util.Validator;

import java.util.List;
import java.util.Map;

public class UserLogic implements Logic{
    public List<Faculty> findAllFaculties(){
        List<Faculty> list
                = null;
        try {
            list = new FacultyDAOImpl().findAllEntity();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Speciality> findSpecialitiesByFacultyId(int id){
        List<Speciality> specialities= null;
        try {
            specialities = new SpecialityDAOImpl().findSpecialitiesByFacultyID(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return specialities;
    }

    public void registerUser(User user) {
        user.setPassword(PasswordEncoder.encodePassword(user.getPassword()));
        try {
            new UserDAOImpl().create(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }
    public boolean findUserByLogin(String login){
        try {
            return new UserDAOImpl().findUserByLogin(login);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Subject findSubject(Integer id){
        Subject subject=null;
        try {
            subject=new SubjectDAOImpl().findEntityById(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return subject;
    }
    public Map<Subject,Integer> findSubjects(Integer id){
            Map<Subject,Integer> subjects=null;
        try {
            subjects=new UserDAOImpl().findUserSubjectsAndScores(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public void changePassword(Integer userId,String password) throws LogicException {
        try{
            password= PasswordEncoder.encodePassword(password);
            new UserDAOImpl().changeUserPassword(userId,password);
        } catch (DAOException e) {
            throw new LogicException();
        }

    }
    public List<Subject> findSubjectsForSpeciality(int specialityId) throws LogicException {
        List<Subject> subjects=null;
        try {
            subjects=new SubjectDAOImpl().findSubjectsBySpecialityId(specialityId);
        } catch (DAOException e) {
            throw new LogicException();
        }
        return subjects;
    }

}
