package by.mordas.project.command.admin;

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

import java.util.List;
import java.util.Optional;

public class ShowAcceptedUsersCommand implements Command{
    /***
     Author: Sergei Mordas
     Date: 22.04.2018
     ***/
    private static Logger logger= LogManager.getRootLogger();
    private UserService userService;
    private SpecialityService specialityService;

    public ShowAcceptedUsersCommand(){
        userService=ServiceFactory.getInstance().getUserService();
        specialityService=ServiceFactory.getInstance().getSpecialityService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String specialityId = content.getRequestParameter(ParamConstant.SPECIALITY_ID);

        try {
            Optional<Speciality> optionalSpeciality=specialityService.findSpecialityById(specialityId);
            Optional<List<User>> optionalUsers=userService.findAllAcceptedUsersOnSpeciality(specialityId);
            if(optionalSpeciality.isPresent() && optionalUsers.isPresent()) {
                Speciality speciality =optionalSpeciality.get();
                List <User> acceptedUsers =optionalUsers.get();
                if(acceptedUsers.size()>10){
                    int pages=acceptedUsers.size()/10;
                    acceptedUsers=acceptedUsers.subList(0,10);
                    content.setSessionAttribute(ParamConstant.PAGES,pages);
                }
                content.setRequestAttribute(ParamConstant.SPECIALITY, speciality);
                content.setRequestAttribute(ParamConstant.USER_LIST, acceptedUsers);
            }
            router.setPagePath(PageConstant.PAGE_SHOW_ACCEPTED_USERS);

        } catch (LogicException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }
        return router;
    }
}
