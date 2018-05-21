package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.service.FacultyService;
import by.mordas.project.service.LogicException;
import by.mordas.project.service.SpecialityService;
import by.mordas.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindSpecialitiesOnChooseFacultyCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private SpecialityService specialityService;
    private FacultyService facultyService;

    public FindSpecialitiesOnChooseFacultyCommand(){
        specialityService=ServiceFactory.getInstance().getSpecialityService();
        facultyService=ServiceFactory.getInstance().getFacultyService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String facultyId=content.getRequestParameter(ParamConstant.FACULTY_ID);
        try {
            List<Speciality> specialities=specialityService.findSpecialitiesOnFaculty(facultyId);
            Faculty faculty =facultyService.findFaculty(facultyId);
            content.setRequestAttribute(ParamConstant.FACULTY,faculty);
            content.setRequestAttribute(ParamConstant.SPECIALITY_LIST,specialities);
            router.setPagePath(PageConstant.PAGE_CHOOSE_FACULTY_AND_SPECIALITY);
        } catch (LogicException e) {
            logger.log(Level.ERROR, e.getMessage());
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }
        return router;
    }
}
