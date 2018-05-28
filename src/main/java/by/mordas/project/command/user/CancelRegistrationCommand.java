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

/***
 Author: Sergei Mordas
 Date: 11.05.2018
 ***/
public class CancelRegistrationCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private UserService userService;

    public CancelRegistrationCommand(){
        userService= ServiceFactory.getInstance().getUserService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        User user=(User)content.getSessionAttribute(ParamConstant.USER);
        try {
            user=userService.canceledUserRegistration(user);
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.USER,user);
            router.setPagePath(PageConstant.PAGE_USER_SUCCESS);
        } catch (LogicException e) {
            logger.log(Level.ERROR,e.getMessage());
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }

        return router;
    }
}
