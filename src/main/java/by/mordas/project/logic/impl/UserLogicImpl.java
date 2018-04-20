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
import by.mordas.project.logic.UserLogic;
import by.mordas.project.util.DataValidator;
import by.mordas.project.util.PasswordEncoder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class UserLogicImpl implements UserLogic {
    private static Logger logger= LogManager.getRootLogger();
    private DAOFactory mysqlFactory=DAOFactory.getFactory(DAOFactory.MySQL);

    @Override
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

    @Override
    public List<Speciality> findSpecialitiesByFacultyId(String id) throws LogicException {
        List<Speciality> specialities= null;
        DataValidator validator=new DataValidator();
        if(validator.checkId(id)) {
            SpecialityDAO specialityDAO = mysqlFactory.getSpecialityDAO();
            Integer facultyId=Integer.valueOf(id);
            try {
                specialities = specialityDAO.findSpecialitiesByFacultyID(facultyId);
            } catch (DAOException e) {
                logger.log(Level.ERROR, e.getMessage());
                throw new LogicException("Problems with findSpecialitiesByFacultyId method", e);
            }
        }
        return specialities;
    }

    @Override
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

    @Override
    public boolean findUserByLogin(String login) throws LogicException {
        DataValidator validator=new DataValidator();
        if(true) {//todo
            UserDAO userDAO = mysqlFactory.getUserDAO();
            try {
                return userDAO.findUserByLogin(login);
            } catch (DAOException e) {
                logger.log(Level.ERROR, e.getMessage());
                throw new LogicException("Problems with findUserByLogin method", e);
            }
        }
        return false;

    }

    @Override
    public Subject findSubject(String id) throws LogicException {
        Subject subject = null;
        DataValidator validator=new DataValidator();
        if(validator.checkId(id)) {
            Long subjectId=Long.valueOf(id);
            SubjectDAO subjectDAO = mysqlFactory.getSubjectDAO();

            try {
                subject = subjectDAO.findEntityById(subjectId);
            } catch (DAOException e) {
                logger.log(Level.ERROR, e.getMessage());
                throw new LogicException("Problems with findSubject method", e);
            }
        }
        return subject;
    }

    @Override
    public Map<Subject,Integer> findSubjects(String id) throws LogicException {
        Map<Subject,Integer> subjects=null;
        DataValidator validator=new DataValidator();
        if(validator.checkId(id)) {
            UserDAO userDAO = mysqlFactory.getUserDAO();

            try {
                Long userId = Long.valueOf(id);
                subjects = userDAO.findUserSubjectsAndScores(userId);
            } catch (DAOException e) {
                logger.log(Level.ERROR, e.getMessage());
                throw new LogicException("Problems with findSubjects method", e);
            }
        }
        return subjects;
    }

    @Override
    public void changePassword(String userId,String password) throws LogicException {
        DataValidator validator=new DataValidator();
        UserDAO userDAO=mysqlFactory.getUserDAO();
        if(validator.checkLoginPassword(userId,password)) {
            try {
                Long id=Long.valueOf(userId);
                password = PasswordEncoder.encodePassword(password);
                userDAO.changeUserPassword(id, password);
            } catch (DAOException e) {
                logger.log(Level.ERROR, e.getMessage());
                throw new LogicException("Problems with changePassword method", e);
            }
        }
    }

    @Override
    public List<Subject> findSubjectsForSpeciality(String specialityId) throws LogicException {
        DataValidator validator=new DataValidator();
        SubjectDAO subjectDAO=mysqlFactory.getSubjectDAO();
        List<Subject> subjects = null;
        if(validator.checkId(specialityId)) {
            try {
                Long id=Long.valueOf(specialityId);
                subjects = subjectDAO.findSubjectsBySpecialityId(id);
            } catch (DAOException e) {
                logger.log(Level.ERROR, e.getMessage());
                throw new LogicException("Problems with findSubjectsForSpeciality method", e);
            }

        }
        return subjects;
    }

    @Override
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
