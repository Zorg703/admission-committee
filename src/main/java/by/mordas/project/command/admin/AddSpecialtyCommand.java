package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;
import by.mordas.project.logic.AdminLogic;
import by.mordas.project.logic.LogicException;
import by.mordas.project.util.Validator;

import java.util.HashMap;

public class AddSpecialtyCommand implements Command {
    AdminLogic adminLogic=new AdminLogic();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        Speciality speciality=new Speciality();
        HashMap<String,String> parameters=content.getRequestParameters();
        HashMap<String,String> errorMessages=new Validator().checkSpecialtyData(parameters);
        if(errorMessages.isEmpty()){
            try {
                speciality.setFacultyId(Integer.parseInt(parameters.get(ParamConstant.FACULTY_ID)));
                speciality.setSpecialityName(parameters.get(ParamConstant.SPECIALITY_NAME));
                Subject subject1=new Subject();
                subject1.setSubjectId(Integer.parseInt(parameters.get(ParamConstant.FIRST_SUBJECT)));
                speciality.add(subject1);
                Subject subject2=new Subject();
                subject2.setSubjectId(Integer.parseInt(parameters.get(ParamConstant.SECOND_SUBJECT)));
                speciality.add(subject2);
                Subject subject3=new Subject();
                subject3.setSubjectId(Integer.parseInt(parameters.get(ParamConstant.THIRD_SUBJECT)));
                adminLogic.addSpeciality(speciality);
                router.setPagePath(PageConstant.PAGE_ADMIN_SUCCESSFUL);
                router.setRouter(Router.RouteType.REDIRECT);
            } catch (LogicException e) {
                e.printStackTrace();
            }
        }
        else {
            content.setSessionAttribute(ParamConstant.ERROR_MESSAGES,errorMessages);
            content.setSessionAttribute(ParamConstant.SPECIALITY,parameters);
            router.setRouter(Router.RouteType.REDIRECT);
            router.setPagePath(PageConstant.PAGE_ADD_SPECIALITY);
        }
        return router;
    }
}
