package by.mordas.project.logic.impl;

import by.mordas.project.dao.*;

import by.mordas.project.dao.mysqlimpl.MySQLSubjectDAOImpl;
import by.mordas.project.dao.mysqlimpl.MySQLUserDAOImpl;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;
import by.mordas.project.logic.Logic;
import by.mordas.project.logic.LogicException;
import by.mordas.project.util.PasswordEncoder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class UserLogicImpl implements Logic {
    private static Logger logger= LogManager.getRootLogger();
    private DAOFactory mysqlFactory=DAOFactory.getFactory(DAOFactory.MySQL);

    public List<Faculty> findAllFaculties() throws LogicException {
        FacultyDAO facultyDAO=mysqlFactory.getFacultyDAO();
        List<Faculty> list = null;
        try {
            list = facultyDAO.findAllEntity();
        } catch (DAOException e) {
            logger.log(Level.ERROR,e.getMessage());
            throw new LogicException("Problems with findAllFaculty method",e);
        }
        return list;
    }
    public List<Speciality> findSpecialitiesByFacultyId(int id) throws LogicException {
        SpecialityDAO specialityDAO=mysqlFactory.getSpecialityDAO();
        List<Speciality> specialities= null;
        try {
            specialities = specialityDAO.findSpecialitiesByFacultyID(id);
        } catch (DAOException e) {
            logger.log(Level.ERROR,e.getMessage());
            throw new LogicException("Problems with findSpecialitiesByFacultyId method",e);
        }
        return specialities;
    }

    public void registerUser(User user) throws LogicException {
        UserDAO userDAO=mysqlFactory.getUserDAO();
        user.setPassword(PasswordEncoder.encodePassword(user.getPassword()));
        try {
            userDAO.create(user);
        } catch (DAOException e) {
            logger.log(Level.ERROR,e.getMessage());
            throw new LogicException("Problems with registerUser method",e);
        }

    }
    public boolean findUserByLogin(String login) throws LogicException {
        UserDAO userDAO=mysqlFactory.getUserDAO();
        try {
            return userDAO.findUserByLogin(login);
        } catch (DAOException e) {
            logger.log(Level.ERROR,e.getMessage());
            throw new LogicException("Problems with findUserByLogin method",e);
    }

    }

    public Subject findSubject(Integer id) throws LogicException {
        SubjectDAO subjectDAO=mysqlFactory.getSubjectDAO();
        Subject subject=null;
        try {
            subject=subjectDAO.findEntityById(id);
        } catch (DAOException e) {
            logger.log(Level.ERROR,e.getMessage());
            throw new LogicException("Problems with findSubject method",e);
        }
        return subject;
    }
    public Map<Subject,Integer> findSubjects(Long id) throws LogicException {
        UserDAO userDAO=mysqlFactory.getUserDAO();
        Map<Subject,Integer> subjects=null;
        try {
            subjects=userDAO.findUserSubjectsAndScores(id);
        } catch (DAOException e) {
            logger.log(Level.ERROR,e.getMessage());
            throw new LogicException("Problems with findSubjects method",e);
        }
        return subjects;
    }

    public void changePassword(Long userId,String password) throws LogicException {
        UserDAO userDAO=mysqlFactory.getUserDAO();
        try{
            password= PasswordEncoder.encodePassword(password);
            userDAO.changeUserPassword(userId,password);
        } catch (DAOException e) {
            logger.log(Level.ERROR,e.getMessage());
            throw new LogicException("Problems with changePassword method",e);
        }

    }
    public List<Subject> findSubjectsForSpeciality(int specialityId) throws LogicException {
        SubjectDAO subjectDAO=mysqlFactory.getSubjectDAO();
        List<Subject> subjects=null;
        try {
            subjects=subjectDAO.findSubjectsBySpecialityId(specialityId);
        } catch (DAOException e) {
            logger.log(Level.ERROR,e.getMessage());
            throw new LogicException("Problems with findSubjectsForSpeciality method",e);
        }
        return subjects;
    }

    public void setUserSpeciality(User user) throws LogicException {
        UserDAO userDAO=mysqlFactory.getUserDAO();
        try {
            userDAO.updateUserSpeciality(user);
        } catch (DAOException e) {
            logger.log(Level.ERROR,e.getMessage());
            throw new LogicException("Problems with setUserSpeciality method",e);
        }
    }

}
