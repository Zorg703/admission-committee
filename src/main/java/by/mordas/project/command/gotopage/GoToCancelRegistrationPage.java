package by.mordas.project.command.gotopage;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.User;
import by.mordas.project.service.LogicException;
import by.mordas.project.service.SpecialityService;
import by.mordas.project.service.factory.ServiceFactory;

import java.util.Optional;

public class GoToCancelRegistrationPage implements Command {
    private SpecialityService specialityService;
    public GoToCancelRegistrationPage(){
        specialityService= ServiceFactory.getInstance().getSpecialityService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        User user=(User)content.getSessionAttribute(ParamConstant.USER);
            try {
                Optional<Speciality> optionalSpeciality = specialityService.findSpeciality(user.getSpecialityId());
                if (user.getSpecialityId() == 0 || !optionalSpeciality.isPresent()) {
                    router.setPagePath(PageConstant.PAGE_SHOW_USER_STATUS);
                } else {
                    Speciality speciality =optionalSpeciality.get();
                    content.setRequestAttribute(ParamConstant.SPECIALITY, speciality);
                    router.setPagePath(PageConstant.PAGE_CANCEL_REGISTRATION);
                }
            }catch (LogicException e) {
                router.setRouter(Router.RouteType.REDIRECT);
                content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
                router.setPagePath(PageConstant.PAGE_ERROR);
            }
        return router;
    }
}
