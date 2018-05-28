package by.mordas.project.command.gotopage;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Speciality;
import by.mordas.project.service.LogicException;
import by.mordas.project.service.SpecialityService;
import by.mordas.project.service.factory.ServiceFactory;

import java.util.Optional;

/***
 Author: Sergei Mordas
 Date: 03.05.2018
 ***/
public class GoToChangeRegisterDatePage implements Command {
    private SpecialityService specialityService;

    public GoToChangeRegisterDatePage(){
        specialityService= ServiceFactory.getInstance().getSpecialityService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String specialityId=content.getRequestParameter(ParamConstant.SPECIALITY_ID);
        try {
            Optional<Speciality> optionalSpeciality=specialityService.findSpecialityById(specialityId);
            optionalSpeciality.ifPresent(speciality -> content.setRequestAttribute(ParamConstant.SPECIALITY, speciality));
            router.setPagePath(PageConstant.PAGE_CHANGE_REGISTRATION_DATE);
        } catch (LogicException e) {
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }
        return router;
    }
}
