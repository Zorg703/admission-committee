package by.mordas.project.logic;

import by.mordas.project.dao.DAOException;
import by.mordas.project.dao.UserDAO;
import by.mordas.project.dao.impl.UserDAOImpl;
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
}
