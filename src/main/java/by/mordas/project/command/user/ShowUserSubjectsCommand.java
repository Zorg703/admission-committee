package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;
import by.mordas.project.logic.UserLogic;

import java.util.Map;

public class ShowUserSubjectsCommand implements Command{
    private UserLogic userLogic=new UserLogic();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        User user= (User) content.getSessionAttribute(ParamConstant.USER);
        Map<Subject,Integer> subjectsMap=userLogic.findSubjects(user.getUserId());
        content.setRequestAttribute(ParamConstant.SUBJECTS,subjectsMap);
        router.setPagePath(PageConstant.PAGE_USER_SUBJECTS);
        return router;
    }
}
