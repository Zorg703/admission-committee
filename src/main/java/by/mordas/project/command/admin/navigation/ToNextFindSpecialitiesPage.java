package by.mordas.project.command.admin.navigation;

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

import java.util.List;
import java.util.Optional;

public class ToNextFindSpecialitiesPage implements Command{
    private static Logger logger= LogManager.getRootLogger();
    private SpecialityService specialityService;

    public ToNextFindSpecialitiesPage(){
        specialityService= ServiceFactory.getInstance().getSpecialityService();
    }

    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String count=content.getRequestParameter(ParamConstant.COUNTER);

        try {
            Optional<List<Speciality>> optionalSpecialities=specialityService.findAllSpecialitiesWithLimit(count);
            if(optionalSpecialities.isPresent()) {
                List<Speciality> specialities = optionalSpecialities.get();
                content.setRequestAttribute(ParamConstant.SPECIALITY_LIST, specialities);
                int counter=Integer.valueOf(count);
                content.setRequestAttribute(ParamConstant.COUNTER,counter);
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
