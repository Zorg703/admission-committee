package by.mordas.project.command.admin.navigation;

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

public class ToNextFindUserRegisterOnSpecialityPage implements Command {
    private static final Logger logger= LogManager.getRootLogger();
    private UserService userService;
    private SpecialityService specialityService;

    public ToNextFindUserRegisterOnSpecialityPage(){
        userService= ServiceFactory.getInstance().getUserService();
        specialityService=ServiceFactory.getInstance().getSpecialityService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String specialityId=content.getRequestParameter(ParamConstant.SPECIALITY_ID);
        String count=content.getRequestParameter(ParamConstant.COUNTER);
        try{
            Optional<List<User>> optionalUsers=userService.findUsersRegisterOnSpecialityWithLimit(specialityId,count);
            Optional<Speciality> optionalSpeciality=specialityService.findSpecialityById(specialityId);
            if(optionalUsers.isPresent() &&optionalSpeciality.isPresent())
            {
                Speciality speciality=optionalSpeciality.get();
                List<User> userList=optionalUsers.get();
                content.setRequestAttribute(ParamConstant.SPECIALITY,speciality);
                content.setRequestAttribute(ParamConstant.USER_LIST, userList);
                int counter=Integer.valueOf(count);
                content.setRequestAttribute(ParamConstant.COUNTER,counter);

            }
            else {
                content.setRequestAttribute(ParamConstant.MESSAGE,count);
            }
            router.setPagePath(PageConstant.PAGE_FIND_ALL_USERS_REGISTER_ON_SPECIALITY);
        } catch (LogicException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }
        return router;
    }
}
