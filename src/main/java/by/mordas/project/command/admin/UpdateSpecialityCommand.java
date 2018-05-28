package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.service.LogicException;
import by.mordas.project.service.SpecialityService;
import by.mordas.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/***
 Author: Sergei Mordas
 Date: 02.05.2018
 ***/
public class UpdateSpecialityCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private SpecialityService specialityService;

    public UpdateSpecialityCommand(){
        specialityService= ServiceFactory.getInstance().getSpecialityService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        HashMap<String,String> parameters=content.getRequestParameters();
        try {
            Map<String,String> errorMap=specialityService.updateSpeciality(parameters);
            if(errorMap.isEmpty()){
                router.setRouter(Router.RouteType.REDIRECT);
                router.setPagePath(PageConstant.PAGE_ADMIN_SUCCESSFUL);
                }
                else {
                    router.setPagePath(PageConstant.PAGE_UPDATE_SPECIALITY);
                    content.setSessionAttribute(ParamConstant.ERROR_MESSAGES,errorMap);
                    content.setSessionAttribute(ParamConstant.SPECIALITY,parameters);
                    router.setRouter(Router.RouteType.REDIRECT);
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
