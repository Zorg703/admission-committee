package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;
import by.mordas.project.logic.impl.UserLogicImpl;

import java.util.Map;

public class ShowUserSubjectsCommand implements Command{
    private UserLogicImpl userLogicImpl =new UserLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        User user= (User) content.getSessionAttribute(ParamConstant.USER);
        Map<Subject,Integer> subjectsMap= userLogicImpl.findSubjects(user.getUserId());
        content.setRequestAttribute(ParamConstant.SUBJECTS,subjectsMap);
        router.setPagePath(PageConstant.PAGE_USER_SUBJECTS);
        return router;
    }
}
