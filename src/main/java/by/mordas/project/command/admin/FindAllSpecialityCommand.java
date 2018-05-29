package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Speciality;
import by.mordas.project.service.LogicException;
import by.mordas.project.service.SpecialityService;
import by.mordas.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/***
 Author: Sergei Mordas
 Date: 06.05.2018
 ***/
public class FindAllSpecialityCommand implements Command{
    private static Logger logger= LogManager.getRootLogger();
    private SpecialityService specialityService;

    public FindAllSpecialityCommand(){
        specialityService=ServiceFactory.getInstance().getSpecialityService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();

        try {
            Optional<List<Speciality>> optionalSpecialities=specialityService.findAllSpecialities();
            if(optionalSpecialities.isPresent()) {
                List<Speciality> specialities = optionalSpecialities.get();
                if(specialities.size()>10){
                    int pages=specialities.size()/10;
                    specialities.subList(0,10);
                    content.setSessionAttribute(ParamConstant.PAGES,pages);
                }
                content.setRequestAttribute(ParamConstant.SPECIALITY_LIST, specialities);
            }
            router.setPagePath(PageConstant.PAGE_SHOW_ALL_SPECIALITIES);
        } catch (LogicException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }
        return router;
    }
}
