package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.User;
import by.mordas.project.service.LogicException;
import by.mordas.project.service.SpecialityService;
import by.mordas.project.service.UserService;
import by.mordas.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Optional;

/***
 Author: Sergei Mordas
 Date: 13.05.2018
 ***/
public class RegisterOnSpecialityCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private UserService userService;
    private SpecialityService specialityService;
    public RegisterOnSpecialityCommand(){
        userService=ServiceFactory.getInstance().getUserService();
        specialityService=ServiceFactory.getInstance().getSpecialityService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        HashMap<String,String> parameters=content.getRequestParameters();
        Long specialityId=Long.valueOf((String) content.getSessionAttribute(ParamConstant.SPECIALITY_ID));
        User user=(User) content.getSessionAttribute(ParamConstant.USER);


        try {
            Optional<Speciality> speciality=specialityService.findSpeciality(specialityId);
            if(speciality.isPresent() && !specialityService.checkEndOfSpecialityRegistrationDate(speciality.get())) {
                user = userService.setUserSpeciality(user, specialityId, parameters);
                if (user.getSpecialityId() != 0) {
                    router.setRouter(Router.RouteType.REDIRECT);
                    router.setPagePath(PageConstant.PAGE_USER_SUCCESS);
                    content.setSessionAttribute(ParamConstant.USER, user);
                } else {
                    router.setPagePath(PageConstant.PAGE_REGISTER_ON_FACULTY);
                    content.setRequestAttribute(ParamConstant.MESSAGE, specialityId);
                }
            }
            router.setPagePath(PageConstant.PAGE_REGISTER_ON_FACULTY);
            content.setRequestAttribute(ParamConstant.END_REGISTRATION, specialityId);
        } catch (LogicException e) {
            logger.log(Level.ERROR,e.getMessage());
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }
        return router;
    }
}
