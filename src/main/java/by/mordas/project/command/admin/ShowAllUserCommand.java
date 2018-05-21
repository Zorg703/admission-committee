package by.mordas.project.command.admin;

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

import java.util.List;

public class ShowAllUserCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private UserService userService;

    public ShowAllUserCommand(){
        userService=ServiceFactory.getInstance().getUserService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        List<User> userList;
        try {
            userList= userService.findAllUser();
            content.setRequestAttribute(ParamConstant.USER_LIST,userList);
            router.setPagePath(PageConstant.PAGE_SHOW_USERS);
        } catch (LogicException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }

        return router;


    }
}
