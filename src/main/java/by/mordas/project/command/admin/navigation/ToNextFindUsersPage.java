package by.mordas.project.command.admin.navigation;

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
import java.util.Optional;

public class ToNextFindUsersPage implements Command {
    private static final Logger logger= LogManager.getRootLogger();
    private UserService userService;
    public ToNextFindUsersPage(){
        userService= ServiceFactory.getInstance().getUserService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String count=content.getRequestParameter(ParamConstant.COUNTER);
        try{
            Optional<List<User>> optionalUsers=userService.findUserLimitCount(count);
            if(optionalUsers.isPresent()){
                List<User> userList=optionalUsers.get();
                content.setRequestAttribute(ParamConstant.USER_LIST, userList);
                int counter=Integer.valueOf(count);
                content.setRequestAttribute(ParamConstant.COUNTER,counter);

            }
            else {
                content.setRequestAttribute(ParamConstant.MESSAGE,count);
            }
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
