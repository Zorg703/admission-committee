package by.mordas.project.logic;

import by.mordas.project.dao.impl.UserDAOImpl;
import by.mordas.project.entity.User;
import by.mordas.project.util.PasswordEncoder;

/**
 * Created by Enginer on 03.03.2018.
 */
public class CommonLogic implements Logic{
    public User findUserLoginAndPassword(String login,String password){
        User user;
        user=new UserDAOImpl().findUserByPasswordAndLogin(login, PasswordEncoder.encodePassword(password));
        return user;
    }

}
