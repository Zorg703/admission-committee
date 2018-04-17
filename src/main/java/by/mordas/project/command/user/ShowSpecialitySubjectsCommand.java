package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Subject;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.impl.UserLogicImpl;

import java.util.List;

public class ShowSpecialitySubjectsCommand implements Command {
    UserLogicImpl userLogicImpl =new UserLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        int specialityId= Integer.parseInt(content.getRequestParameter(ParamConstant.SPECIALITY));
        try {
            List<Subject> subjects= userLogicImpl.findSubjectsForSpeciality(specialityId);
            router.setPagePath(PageConstant.PAGE_REGISTER_ON_FACULTY);
            content.setRequestAttribute(ParamConstant.SUBJECTS,subjects);
            content.setSessionAttribute(ParamConstant.SPECIALITY,specialityId);

        } catch (LogicException e) {
            e.printStackTrace();
        }

        return router;
    }
}
