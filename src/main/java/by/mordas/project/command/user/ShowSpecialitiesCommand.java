package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Speciality;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.impl.UserLogicImpl;

import java.util.List;

public class ShowSpecialitiesCommand implements Command {
    UserLogicImpl userLogicImpl =new UserLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        List<Speciality> specialities;
        String id=content.getRequestParameter(ParamConstant.ID);
        try {
            specialities= userLogicImpl.findSpecialitiesByFacultyId(id);
            content.setRequestAttribute(ParamConstant.SPECIALITY_LIST,specialities);
            router.setPagePath(PageConstant.PAGE_SHOW_SPECIALITIES);
        } catch (LogicException e) {
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }

        return router;
    }
}
