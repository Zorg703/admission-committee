//package by.mordas.project.logic.impl.old;
//
//import by.mordas.project.command.ParamConstant;
//import by.mordas.project.dao.DAOException;
//import by.mordas.project.dao.DAOFactory;
//import by.mordas.project.dao.UserDAO;
//import by.mordas.project.entity.User;
//import by.mordas.project.service.CommonLogic;
//import by.mordas.project.service.Logic;
//import by.mordas.project.service.LogicException;
//import by.mordas.project.util.PasswordEncoder;
//import by.mordas.project.util.DataValidator;
//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.sql.Date;
//import java.util.HashMap;
//
///**
// * Created by Enginer on 03.03.2018.
// */
//public class CommonLogicImpl implements CommonLogic {
//    private static Logger logger= LogManager.getRootLogger();
//    private DAOFactory mysqlFactory=DAOFactory.getFactory(DAOFactory.MySQL);
//
//    @Override
//    public User findUserLoginAndPassword(String login,String password) throws LogicException {
//        UserDAO userDAO=mysqlFactory.getUserDAO();
//        DataValidator validator=new DataValidator();
//        User user=null;
//        try {
//            if(validator.checkLoginPassword(login,password )/*&& findLogin(login)*/) {
//                password= PasswordEncoder.encodePassword(password);
//                user = userDAO.findUserByPasswordAndLogin(login,password);
//            }
//        } catch (DAOException e) {
//            throw new LogicException("Problems with findUserByPasswordAndLogin method",e);
//        }
//        return user;
//    }
//    @Override
//    public HashMap<String,String> registerUser(HashMap<String, String> parameters) throws LogicException {
//        DataValidator validator=new DataValidator();
//
//        UserDAO userDAO = mysqlFactory.getUserDAO();
//        HashMap<String,String> errorMap;
//        try {
//            errorMap=validator.checkUserData(parameters);
//            if(errorMap.isEmpty()) {
//                User user = new User();
//                user.setFirstName(parameters.get(ParamConstant.FIRST_NAME));
//                user.setLastName(parameters.get(ParamConstant.LAST_NAME));
//                user.setBirthday(Date.valueOf(parameters.get(ParamConstant.BIRTHDAY)));
//                user.setEmail(parameters.get(ParamConstant.EMAIL));
//                user.setPassword(parameters.get(ParamConstant.PASSWORD_ONE));
//                user.setLogin(parameters.get(ParamConstant.LOG_IN));
//                user.setPassword(PasswordEncoder.encodePassword(user.getPassword()));
//                userDAO.create(user);
//            }
//        } catch (DAOException e) {
//            logger.log(Level.ERROR, e.getMessage());
//            throw new LogicException("Problems with registerUser method", e);
//        }
//
//        return errorMap;
//    }
//    private boolean findLogin(String login) throws DAOException {
//        UserDAO userDAO=mysqlFactory.getUserDAO();
//        return userDAO.findUserByLogin(login);
//    }
//
//}
