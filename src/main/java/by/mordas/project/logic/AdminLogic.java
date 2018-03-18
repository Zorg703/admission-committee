package by.mordas.project.logic;

import by.mordas.project.dao.UserDAO;
import by.mordas.project.dao.impl.UserDAOImpl;
import by.mordas.project.entity.User;

import java.util.List;

public class AdminLogic implements Logic{
public List<User> findAllUser(){
    List<User> userList;
    UserDAO userDAO=new UserDAOImpl();
    userList=userDAO.findAllEntity();
    return userList;
}
}
