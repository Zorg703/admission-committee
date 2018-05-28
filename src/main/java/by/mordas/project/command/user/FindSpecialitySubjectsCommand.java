package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;
import by.mordas.project.service.*;
import by.mordas.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/***
 Author: Sergei Mordas
 Date: 13.05.2018
 ***/
public class FindSpecialitySubjectsCommand implements Command {
    private static Logger logger= LogManager.getRootLogger();
    private SpecialityService specialityService;
    private SubjectService subjectService;

    public FindSpecialitySubjectsCommand(){
        specialityService=ServiceFactory.getInstance().getSpecialityService();
        subjectService=ServiceFactory.getInstance().getSubjectService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String specialityId= content.getRequestParameter(ParamConstant.SPECIALITY_ID);
        try {
            Optional<Speciality> optionalSpeciality=specialityService.findSpecialityById(specialityId);
            Optional<List<Subject>> optionalSubjects=subjectService.findSubjectsForSpeciality(specialityId);
            if(optionalSpeciality.isPresent() && optionalSubjects.isPresent()) {
                Speciality speciality=optionalSpeciality.get();
                List<Subject> subjects=optionalSubjects.get();
                boolean isActiveRegistration = specialityService.checkEndOfSpecialityRegistrationDate(speciality);
                if (isActiveRegistration) {
                    router.setPagePath(PageConstant.PAGE_REGISTER_ON_FACULTY);
                    content.setRequestAttribute(ParamConstant.SUBJECTS, subjects);
                    content.setSessionAttribute(ParamConstant.SPECIALITY_ID, specialityId);
                }
                else {
                    router.setPagePath(PageConstant.PAGE_REGISTER_ON_FACULTY);
                    content.setRequestAttribute(ParamConstant.ERROR_MESSAGES,speciality);
                }
            }
            else {
                router.setPagePath(PageConstant.PAGE_REGISTER_ON_FACULTY);
                content.setRequestAttribute(ParamConstant.MESSAGE,specialityId);
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
