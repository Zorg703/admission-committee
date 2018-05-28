package by.mordas.project.command.common;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.User;
import by.mordas.project.service.UserService;
import by.mordas.project.service.factory.ServiceFactory;
import by.mordas.project.service.LogicException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
/***
 Author: Sergei Mordas
 Date: 30.03.2018
 ***/
public class LogInCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private UserService userService;

    public LogInCommand(){
        userService=ServiceFactory.getInstance().getUserService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String login=content.getRequestParameter(ParamConstant.LOG_IN);
        String password= content.getRequestParameter(ParamConstant.PASSWORD);
        try{
        Optional<User> optionalUser=userService.findUserLoginAndPassword(login,password);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.USER,user);
            router.setPagePath(PageConstant.PAGE_MAIN);
        }
            else {
                content.setRequestAttribute(ParamConstant.MESSAGE,ParamConstant.MESSAGE);
                content.setRequestAttribute(ParamConstant.LOGIN_PARAM,content.getRequestParameters());
                router.setPagePath(PageConstant.PAGE_LOGIN);
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
