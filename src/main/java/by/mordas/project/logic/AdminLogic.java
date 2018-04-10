package by.mordas.project.logic;

import by.mordas.project.dao.DAOException;
import by.mordas.project.dao.UserDAO;
import by.mordas.project.dao.impl.FacultyDAOImpl;
import by.mordas.project.dao.impl.UserDAOImpl;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.User;

import java.util.List;

public class AdminLogic implements Logic{
    public List<User> findAllUser(){
        List<User> userList=null;
        UserDAO userDAO=new UserDAOImpl();
        try {
            userList=userDAO.findAllEntity();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public Faculty addFaculty(String facultyName) throws LogicException {
        Faculty faculty=new Faculty();
        try {
            new FacultyDAOImpl().create(faculty);
        } catch (DAOException e) {
            throw new LogicException();
        }
        return faculty;
    }

}
