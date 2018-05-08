package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Speciality;
import by.mordas.project.logic.AdminLogic;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.impl.AdminLogicImpl;

import java.util.ArrayList;
import java.util.List;

public class FindAllSpecialityCommand implements Command{
    private AdminLogic adminLogic=new AdminLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        List<Speciality> specialities=new ArrayList<>();
        try {
            specialities=adminLogic.findAllSpecialities();
            content.setRequestAttribute(ParamConstant.SPECIALITY_LIST,specialities);
            router.setPagePath(PageConstant.PAGE_SHOW_ALL_SPECIALITIES);
        } catch (LogicException e) {
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }
        return router;
    }
}
