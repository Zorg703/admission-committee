package by.mordas.project.logic;

import by.mordas.project.dao.UserDAO;
import by.mordas.project.entity.User;

/**
 * Created by Enginer on 03.03.2018.
 */
public class CommonLogic {
    public User findUserLoginAndPassword(String login,String password){
        User user;
        user=new UserDAO().findUserByPasswordAndLogin(login,password);
        return user;
    }
}
