package by.mordas.project.logic.impl;

import by.mordas.project.dao.DAOException;
import by.mordas.project.dao.DAOFactory;
import by.mordas.project.dao.UserDAO;
import by.mordas.project.entity.User;
import by.mordas.project.logic.CommonLogic;
import by.mordas.project.logic.Logic;
import by.mordas.project.logic.LogicException;
import by.mordas.project.util.PasswordEncoder;
import by.mordas.project.util.DataValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Enginer on 03.03.2018.
 */
public class CommonLogicImpl implements CommonLogic {
    private static Logger logger= LogManager.getRootLogger();
    private DAOFactory mysqlFactory=DAOFactory.getFactory(DAOFactory.MySQL);

    @Override
    public User findUserLoginAndPassword(String login,String password) throws LogicException {
        UserDAO userDAO=mysqlFactory.getUserDAO();
        DataValidator validator=new DataValidator();
        User user=null;
        try {
            if(validator.checkLoginPassword(login,password )/*&& findLogin(login)*/) {
                password= PasswordEncoder.encodePassword(password);
                user = userDAO.findUserByPasswordAndLogin(login,password);
            }
        } catch (DAOException e) {
            logger.log(Level.ERROR,e.getMessage());
            throw new LogicException("Problems with findUserByPasswordAndLogin method",e);
        }
        return user;
    }

    private boolean findLogin(String login) throws DAOException {
        UserDAO userDAO=mysqlFactory.getUserDAO();
        return userDAO.findUserByLogin(login);
    }

}
