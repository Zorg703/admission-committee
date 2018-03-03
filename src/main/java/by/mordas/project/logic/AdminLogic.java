package by.mordas.project.logic;

import by.mordas.project.dao.UserDAO;
import by.mordas.project.entity.User;

import java.util.List;

public class AdminLogic {
public List<User> findAllUser(){
    List<User> userList;
    UserDAO userDAO=new UserDAO();
    userList=userDAO.findAllEntity();
    return userList;
}
}
