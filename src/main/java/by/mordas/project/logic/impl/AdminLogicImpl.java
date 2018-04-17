package by.mordas.project.logic.impl;

import by.mordas.project.dao.*;
import by.mordas.project.dao.mysqlimpl.MySQLFacultyDAOImpl;
import by.mordas.project.dao.mysqlimpl.MySQLSpecialityDAOImpl;
import by.mordas.project.dao.mysqlimpl.MySQLUserDAOImpl;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.User;
import by.mordas.project.logic.Logic;
import by.mordas.project.logic.LogicException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AdminLogicImpl implements Logic {
    private static Logger logger= LogManager.getRootLogger();
    private DAOFactory mysqlFactory=DAOFactory.getFactory(DAOFactory.MySQL);

    public List<User> findAllUser() throws LogicException {
        List<User> userList=null;
        UserDAO userDAO=mysqlFactory.getUserDAO();
        try {
            userList=userDAO.findAllEntity();
        } catch (DAOException e) {
            logger.log(Level.ERROR,e.getMessage());
            throw new LogicException("Problems with findAllUser method",e);
        }
        return userList;
    }

    public Faculty addFaculty(String facultyName) throws LogicException {
        Faculty faculty=new Faculty();
        faculty.setFacultyName(facultyName);
        FacultyDAO facultyDAO=mysqlFactory.getFacultyDAO();
        try {
            facultyDAO.create(faculty);
        } catch (DAOException e) {
            logger.log(Level.ERROR,e.getMessage());
            throw new LogicException("Problems with addFaculty method",e);
        }
        return faculty;
    }

    public boolean deleteFaculty(int id) throws LogicException {
        FacultyDAO facultyDAO=mysqlFactory.getFacultyDAO();
        try {
           return facultyDAO.delete(id);
        } catch (DAOException e) {
            logger.log(Level.ERROR,e.getMessage());
            throw new LogicException("Problems with deleteFaculty method",e);
        }
    }

    public Speciality addSpeciality(Speciality speciality) throws LogicException {
        SpecialityDAO specialityDAO=mysqlFactory.getSpecialityDAO();
        try {
            specialityDAO.create(speciality);
        } catch (DAOException e) {
            logger.log(Level.ERROR,e.getMessage());
            throw new LogicException("Problems with addSpeciality method",e);
        }
        return speciality;
    }

    public User findUserById(String userId) throws LogicException {
        UserDAO userDAO=mysqlFactory.getUserDAO();
        Integer id=Integer.valueOf(userId);
        try {
           User user= userDAO.findEntityById(id);
           return user;
        } catch (DAOException e) {
            logger.log(Level.ERROR,e.getMessage());
            throw new LogicException("Problems with findUserById method",e);
        }
    }

    public boolean deleteSpeciality(String specialityId) throws LogicException {
        SpecialityDAO specialityDAO=mysqlFactory.getSpecialityDAO();
        Integer id=Integer.valueOf(specialityId);
        try {
            return specialityDAO.delete(id);
        } catch (DAOException e) {
            logger.log(Level.ERROR,e.getMessage());
            throw new LogicException("Problems with deleteSpeciality method",e);
        }
    }
}
