package by.mordas.project.logic.impl;

import by.mordas.project.dao.DAOException;
import by.mordas.project.dao.DAOFactory;
import by.mordas.project.dao.UserDAO;
import by.mordas.project.dao.mysqlimpl.MySQLUserDAOImpl;
import by.mordas.project.entity.User;
import by.mordas.project.logic.Logic;
import by.mordas.project.logic.LogicException;
import by.mordas.project.util.PasswordEncoder;
import by.mordas.project.util.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Enginer on 03.03.2018.
 */
public class CommonLogicImpl implements Logic {
    private static Logger logger= LogManager.getRootLogger();
    private DAOFactory mysqlFactory=DAOFactory.getFactory(DAOFactory.MySQL);

    public User findUserLoginAndPassword(String login,String password) throws LogicException {
        UserDAO userDAO=mysqlFactory.getUserDAO();
        User user=null;
        if(Validator.checkLoginPassword(login,password)) {
            try {
                user=userDAO.findUserByPasswordAndLogin(login, PasswordEncoder.encodePassword(password));
            } catch (DAOException e) {
                logger.log(Level.ERROR,e.getMessage());
                throw new LogicException("Problems with findUserByPasswordAndLogin method",e);
            }
        }
        return user;
    }

}
