package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Speciality;
import by.mordas.project.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowSpecialitiesCommand implements Command {
    UserLogic userLogic=new UserLogic();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        List<Speciality> specialities;
        Integer id=Integer.parseInt(content.getRequestParameter(ParamConstant.ID));
        specialities=userLogic.findSpecialitiesByFacultyId(id);
        content.setRequestAttribute(ParamConstant.SPECIALITIES,specialities);
        router.setPagePath(PageConstant.PAGE_SHOW_SPECIALITIES);
        return router;
    }
}
