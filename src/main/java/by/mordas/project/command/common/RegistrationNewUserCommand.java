package by.mordas.project.command.common;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.service.LogicException;
import by.mordas.project.service.UserService;
import by.mordas.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Map;

public class RegistrationNewUserCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private UserService userService;
    public RegistrationNewUserCommand(){
        userService= ServiceFactory.getInstance().getUserService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        Map<String,String> parameters=content.getRequestParameters();
        Map<String,String> errorMessages= null;
        try {
            errorMessages = userService.registerUser(parameters);
            if(errorMessages.isEmpty()){
                router.setRouter(Router.RouteType.REDIRECT);
                router.setPagePath(PageConstant.PAGE_SUCCESS_REGISTRATION);
            }
            else {
                content.setRequestAttribute(ParamConstant.USER_PARAMS,parameters);
                content.setRequestAttribute(ParamConstant.ERROR_MESSAGES ,errorMessages);
                router.setPagePath(PageConstant.PAGE_REGISTRATION);
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
