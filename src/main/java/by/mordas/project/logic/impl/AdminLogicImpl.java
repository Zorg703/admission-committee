package by.mordas.project.logic.impl;

import by.mordas.project.dao.*;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.User;
import by.mordas.project.logic.AdminLogic;
import by.mordas.project.logic.Logic;
import by.mordas.project.logic.LogicException;
import by.mordas.project.util.DataValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AdminLogicImpl implements AdminLogic {
    private static Logger logger= LogManager.getRootLogger();
    private DAOFactory mysqlFactory=DAOFactory.getFactory(DAOFactory.MySQL);

    @Override
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

    @Override
    public Faculty addFaculty(String facultyName) throws LogicException {
        DataValidator dataValidator =new DataValidator();
        Faculty faculty =null;
        if(dataValidator.checkFacultyOrSpecialityName(facultyName)) {
            try {
                faculty = new Faculty();
                faculty.setFacultyName(facultyName);
                FacultyDAO facultyDAO = mysqlFactory.getFacultyDAO();
                facultyDAO.create(faculty);
            } catch (DAOException e) {
                logger.log(Level.ERROR, e.getMessage());
                throw new LogicException("Problems with addFaculty method", e);
            }
        }
        return faculty;
    }

    @Override
    public boolean deleteFaculty(String facultyId) throws LogicException {
        DataValidator validator=new DataValidator();
        if(validator.checkId(facultyId)) {
            Integer id=Integer.valueOf(facultyId);
            FacultyDAO facultyDAO = mysqlFactory.getFacultyDAO();
            try {
                return facultyDAO.delete(id);
            } catch (DAOException e) {
                logger.log(Level.ERROR, e.getMessage());
                throw new LogicException("Problems with deleteFaculty method", e);
            }
        }
        return false;
    }

    @Override
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

    @Override
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

    @Override
    public boolean deleteSpeciality(String specialityId) throws LogicException {
        DataValidator validator=new DataValidator();
        if(validator.checkId(specialityId)) {
            SpecialityDAO specialityDAO = mysqlFactory.getSpecialityDAO();
            Integer id = Integer.valueOf(specialityId);
            try {
                return specialityDAO.delete(id);
            } catch (DAOException e) {
                logger.log(Level.ERROR, e.getMessage());
                throw new LogicException("Problems with deleteSpeciality method", e);
            }
        }
        return false;
    }
}
