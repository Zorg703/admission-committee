package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.service.SpecialityService;
import by.mordas.project.service.factory.ServiceFactory;
import by.mordas.project.service.LogicException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/***
 Author: Sergei Mordas
 Date: 09.03.2018
 ***/
public class AddSpecialtyCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private SpecialityService specialityService;

    public AddSpecialtyCommand( ) {
        specialityService=ServiceFactory.getInstance().getSpecialityService();
    }

    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        HashMap<String,String> parameters=content.getRequestParameters();
        try {
        Map<String,String> errorMessages=specialityService.addSpeciality(parameters);
        if(errorMessages.isEmpty()){
            router.setPagePath(PageConstant.PAGE_ADMIN_SUCCESSFUL);
            router.setRouter(Router.RouteType.REDIRECT);
        }
        else {
            content.setRequestAttribute(ParamConstant.ERROR_MESSAGES,errorMessages);
            content.setRequestAttribute(ParamConstant.SPECIALITY,parameters);
            router.setPagePath(PageConstant.PAGE_ADD_SPECIALITY);
        }
        } catch (LogicException e) {
            logger.log(Level.ERROR,e.getMessage());
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }
        return router;
    }
}
