package by.mordas.project.logic;

import by.mordas.project.dao.SubjectDAO;
import by.mordas.project.dao.UserDAO;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;
import by.mordas.project.util.Encoder;

/**
 * Created by Enginer on 03.03.2018.
 */
public class CommonLogic implements Logic{
    public User findUserLoginAndPassword(String login,String password){
        User user;
        user=new UserDAO().findUserByPasswordAndLogin(login,Encoder.encodePassword(password));
        return user;
    }

}
