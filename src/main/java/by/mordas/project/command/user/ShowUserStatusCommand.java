package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.User;
import by.mordas.project.logic.AdminLogic;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.UserLogic;
import by.mordas.project.logic.impl.AdminLogicImpl;
import by.mordas.project.logic.impl.UserLogicImpl;

public class ShowUserStatusCommand implements Command {
    private UserLogic userLogic=new UserLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        User user=(User)content.getSessionAttribute(ParamConstant.USER);
        if(user.getSpecialityId()!=0) {
            long specialityId = user.getSpecialityId();
            Speciality speciality;
            Faculty faculty;
            try {
                speciality = userLogic.findSpeciality(specialityId);
                long facultyId = speciality.getFacultyId();
                faculty = userLogic.findFaculty(facultyId);
                boolean isAccepted = userLogic.isAccepted(speciality, user);
                content.setRequestAttribute(ParamConstant.FACULTY, faculty);
                content.setRequestAttribute(ParamConstant.SPECIALITY, speciality);
                content.setRequestAttribute(ParamConstant.IS_ACCEPTED, isAccepted);
                router.setPagePath(PageConstant.PAGE_SHOW_USER_STATUS);
            } catch (LogicException e) {
                router.setRouter(Router.RouteType.REDIRECT);
                content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE, e.getMessage());
                router.setPagePath(PageConstant.PAGE_ERROR);
            }
        }
        else {
            router.setPagePath(PageConstant.PAGE_SHOW_USER_STATUS);
            content.setRequestAttribute(ParamConstant.MESSAGE,"You are not registered on speciality");
        }
        return router;
    }
}
