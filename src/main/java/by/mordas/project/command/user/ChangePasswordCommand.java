package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.User;
import by.mordas.project.service.LogicException;
import by.mordas.project.service.UserService;
import by.mordas.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ChangePasswordCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private UserService userService;

    public ChangePasswordCommand(){
        userService=ServiceFactory.getInstance().getUserService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String password1=content.getRequestParameter(ParamConstant.PASSWORD_ONE);
        String password2=content.getRequestParameter(ParamConstant.PASSWORD_TWO);
        Long userID=((User)content.getSessionAttribute(ParamConstant.USER)).getUserId();
        Map<String,String> errorMap= null;
        try {
            errorMap = userService.changePassword(userID,password1,password2);
            if(errorMap.isEmpty()){
                router.setPagePath(PageConstant.PAGE_USER_SUCCESS);
                router.setRouter(Router.RouteType.REDIRECT);
               // content.setSessionAttribute(SUCCESS_CHANGED,SUCCESS_CHANGED);
            }
            else {
                router.setPagePath(PageConstant.PAGE_CHANGE_PASSWORD);
                content.setRequestAttribute(ParamConstant.ERROR_MESSAGES,errorMap);
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
