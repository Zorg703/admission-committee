package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.User;
import by.mordas.project.service.SpecialityService;
import by.mordas.project.service.UserService;
import by.mordas.project.service.factory.ServiceFactory;
import by.mordas.project.service.LogicException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class FindUserCommand implements Command {
    /***
     Author: Sergei Mordas
     Date: 22.04.2018
     ***/
    private static Logger logger= LogManager.getRootLogger();
    private UserService userService;
    private SpecialityService specialityService;
    private final static String USER_FIND="user_find";

    public FindUserCommand(){
        userService=ServiceFactory.getInstance().getUserService();
        specialityService=ServiceFactory.getInstance().getSpecialityService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String userId=content.getRequestParameter(ParamConstant.USER_ID);

            try {
                Optional<User> optionalUser=userService.findUserById(userId);
                if(optionalUser.isPresent()) {
                    User user=optionalUser.get();
                    Speciality speciality=specialityService.findSpecialityById(String.valueOf(user.getSpecialityId())).get();
                    content.setRequestAttribute(USER_FIND, user);
                    content.setRequestAttribute(ParamConstant.SPECIALITY,speciality);
                    router.setPagePath(PageConstant.PAGE_FIND_USER_BY_ID);
                }
                else {
                    content.setRequestAttribute(ParamConstant.MESSAGE,userId);
                    router.setPagePath(PageConstant.PAGE_FIND_USER_BY_ID);
                }
            } catch (LogicException e) {
                logger.log(Level.ERROR, e.getMessage());
                router.setRouter(Router.RouteType.REDIRECT);
                content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
                router.setPagePath(PageConstant.PAGE_ERROR);

            }


        return router;
    }
}
