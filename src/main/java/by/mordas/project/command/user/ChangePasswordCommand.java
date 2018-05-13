package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.User;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.impl.UserLogicImpl;
import by.mordas.project.util.DataValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class ChangePasswordCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private UserLogicImpl userLogicImpl =new UserLogicImpl();
    private String SUCCESS_CHANGED="changed";
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String password1=content.getRequestParameter(ParamConstant.PASSWORD_ONE);
        String password2=content.getRequestParameter(ParamConstant.PASSWORD_TWO);
        Long userID=((User)content.getSessionAttribute(ParamConstant.USER)).getUserId();
        HashMap<String,String> errorMap= null;
        try {
            errorMap = userLogicImpl.changePassword(userID,password1,password2);
            if(errorMap.isEmpty()){
                router.setPagePath(PageConstant.PAGE_USER_SUCCESS);
                router.setRouter(Router.RouteType.REDIRECT);
               // content.setSessionAttribute(SUCCESS_CHANGED,SUCCESS_CHANGED);
            }
            else {
                router.setRouter(Router.RouteType.REDIRECT);
                router.setPagePath(PageConstant.PAGE_CHANGE_PASSWORD);
                content.setSessionAttribute(ParamConstant.ERROR_MESSAGES,errorMap);
            }
        } catch (LogicException e) {
            logger.log(Level.ERROR,e.getMessage());
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }



        return router;
    }
}
