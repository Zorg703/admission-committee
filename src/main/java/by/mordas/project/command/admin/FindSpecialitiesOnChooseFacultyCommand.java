package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.logic.AdminLogic;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.impl.AdminLogicImpl;

import java.util.List;

public class FindSpecialitiesOnChooseFacultyCommand implements Command {
    private AdminLogic adminLogic=new AdminLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String facultyId=content.getRequestParameter(ParamConstant.FACULTY_ID);
        try {
            List<Speciality> specialities=adminLogic.findSpecialitiesOnFaculty(facultyId);
            Faculty faculty =adminLogic.findFaculty(facultyId);
            content.setRequestAttribute(ParamConstant.FACULTY,faculty);
            content.setRequestAttribute(ParamConstant.SPECIALITY_LIST,specialities);
            router.setPagePath(PageConstant.PAGE_CHOOSE_FACULTY_AND_SPECIALITY);
        } catch (LogicException e) {
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }



        return router;
    }
}
