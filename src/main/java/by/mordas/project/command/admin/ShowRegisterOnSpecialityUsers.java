package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.User;
import by.mordas.project.service.*;
import by.mordas.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/***
 Author: Sergei Mordas
 Date: 06.05.2018
 ***/
public class ShowRegisterOnSpecialityUsers implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private static final String IS_REGISTRATION_OPEN="is_registration_open";
    private UserService userService;
    private SpecialityService specialityService;

    public  ShowRegisterOnSpecialityUsers(){
        userService=ServiceFactory.getInstance().getUserService();
        specialityService=ServiceFactory.getInstance().getSpecialityService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router = new Router();
        String specialityId = content.getRequestParameter(ParamConstant.SPECIALITY_ID);
        try {
            Optional<List<User>> optionalUsers=userService.findUsersRegisterOnSpeciality(specialityId);
            Optional<Speciality> optionalSpeciality=specialityService.findSpecialityById(specialityId);
            if(optionalSpeciality.isPresent() && optionalUsers.isPresent()){
                Speciality speciality=optionalSpeciality.get();
                List<User> users=optionalUsers.get();
                boolean isRegistrationOpen=specialityService.checkEndOfSpecialityRegistrationDate(speciality);
                if(users.size()>10){
                    int pages=users.size()/10;
                    users=users.subList(0,10);
                    content.setSessionAttribute(ParamConstant.PAGES,pages);
                }
                content.setRequestAttribute(IS_REGISTRATION_OPEN,isRegistrationOpen);
                content.setRequestAttribute(ParamConstant.SPECIALITY,speciality);
                content.setRequestAttribute(ParamConstant.USER_LIST,users);
                router.setPagePath(PageConstant.PAGE_FIND_ALL_USERS_REGISTER_ON_SPECIALITY);
            }
            else {
                router.setPagePath(PageConstant.PAGE_FIND_ALL_USERS_REGISTER_ON_SPECIALITY);
                content.setRequestAttribute(ParamConstant.MESSAGE,specialityId);
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
