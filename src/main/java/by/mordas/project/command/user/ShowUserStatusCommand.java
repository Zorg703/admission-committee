package by.mordas.project.command.user;

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

import java.util.Optional;

public class ShowUserStatusCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private UserService userService;
    private SpecialityService specialityService;
    private static final String IS_OPEN="is_open";

    public ShowUserStatusCommand(){
        specialityService=ServiceFactory.getInstance().getSpecialityService();
        userService=ServiceFactory.getInstance().getUserService();
    }

    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        User user=(User)content.getSessionAttribute(ParamConstant.USER);
            long specialityId = user.getSpecialityId();
            try {
                Optional<Speciality> optionalSpeciality=specialityService.findSpeciality(specialityId);
                if(optionalSpeciality.isPresent()){
                    Speciality speciality=optionalSpeciality.get();
                long facultyId = speciality.getFacultyId();
                boolean isRegistrationOpen=specialityService.checkEndOfSpecialityRegistrationDate(speciality);
                if(isRegistrationOpen){
                    content.setRequestAttribute(ParamConstant.SPECIALITY, speciality);
                    content.setRequestAttribute(IS_OPEN, isRegistrationOpen);
                }
                else {
                    boolean isAccepted = userService.isAccepted(speciality, user);
                    content.setRequestAttribute(ParamConstant.SPECIALITY, speciality);
                    content.setRequestAttribute(ParamConstant.IS_ACCEPTED, isAccepted);
                }
                router.setPagePath(PageConstant.PAGE_SHOW_USER_STATUS);
            }
                else {
                    router.setPagePath(PageConstant.PAGE_SHOW_USER_STATUS);
                    content.setRequestAttribute(ParamConstant.MESSAGE,"You are not registered on speciality");
                }
            }catch (LogicException e) {
                logger.log(Level.ERROR,e.getMessage());
                router.setRouter(Router.RouteType.REDIRECT);
                content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE, e.getMessage());
                router.setPagePath(PageConstant.PAGE_ERROR);
            }

        return router;
    }
}
