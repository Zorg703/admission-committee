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
import java.util.Optional;

/***
 Author: Sergei Mordas
 Date: 23.04.2018
 ***/
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
            Optional<Faculty> optionalFaculty =facultyService.findFaculty(facultyId);
            Optional<List<Speciality>> optionalSpecialities=specialityService.findSpecialitiesOnFaculty(facultyId);
            if(optionalFaculty.isPresent() && optionalSpecialities.isPresent()){
                List<Speciality> specialities=optionalSpecialities.get();
                Faculty faculty=optionalFaculty.get();
                content.setRequestAttribute(ParamConstant.SPECIALITY_LIST,specialities);
                content.setRequestAttribute(ParamConstant.FACULTY,faculty);
            }
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
