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
import java.util.Optional;

public class ShowAllUserCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private UserService userService;

    public ShowAllUserCommand(){
        userService=ServiceFactory.getInstance().getUserService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        try {
            Optional<List<User>> optionalUsers=userService.findAllUser();
           if(optionalUsers.isPresent()){
               List<User> userList=optionalUsers.get();
               if(userList.size()>10){
                   int pages=userList.size()/10;
                   userList=userList.subList(0,10);
                   content.setSessionAttribute(ParamConstant.PAGES,pages);

               }
               content.setRequestAttribute(ParamConstant.USER_LIST, userList);
           }
           else {
               content.setRequestAttribute(ParamConstant.MESSAGE,optionalUsers);

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
