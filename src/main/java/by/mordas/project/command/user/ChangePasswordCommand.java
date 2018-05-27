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
import java.util.Optional;

public class ChangePasswordCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private UserService userService;

    public ChangePasswordCommand(){
        userService=ServiceFactory.getInstance().getUserService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        User user=(User)content.getSessionAttribute(ParamConstant.USER);
        String oldPassword=content.getRequestParameter(ParamConstant.OLD_PASSWORD);
        String login=user.getLogin();
        String password1=content.getRequestParameter(ParamConstant.PASSWORD_ONE);
        String password2=content.getRequestParameter(ParamConstant.PASSWORD_TWO);

        Long userID=user.getUserId();
        Map<String,String> errorMap= null;
        try {
            Optional<User> optionalUser=userService.findUserLoginAndPassword(login,oldPassword);
            if(optionalUser.isPresent()) {
                errorMap = userService.changePassword(userID, password1, password2);
                if (errorMap.isEmpty()) {
                    router.setPagePath(PageConstant.PAGE_USER_SUCCESS);
                    router.setRouter(Router.RouteType.REDIRECT);
                    // content.setSessionAttribute(SUCCESS_CHANGED,SUCCESS_CHANGED);
                } else {
                    router.setPagePath(PageConstant.PAGE_CHANGE_PASSWORD);
                    content.setRequestAttribute(ParamConstant.ERROR_MESSAGES, errorMap);
                }
            }
            else {
                router.setPagePath(PageConstant.PAGE_CHANGE_PASSWORD);
                content.setRequestAttribute(ParamConstant.MESSAGE, oldPassword);
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
