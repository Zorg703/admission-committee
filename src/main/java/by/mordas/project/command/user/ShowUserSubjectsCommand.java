package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.impl.UserLogicImpl;

import java.util.Map;

public class ShowUserSubjectsCommand implements Command{
    private UserLogicImpl userLogicImpl =new UserLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        User user= (User) content.getSessionAttribute(ParamConstant.USER);
        Map<Subject,Integer> subjectsMap= null;
        try {
            subjectsMap = userLogicImpl.findSubjects(user.getUserId());
            if(subjectsMap!=null) {
                content.setRequestAttribute(ParamConstant.SUBJECTS,subjectsMap);
                router.setPagePath(PageConstant.PAGE_USER_SUBJECTS);
            }
            else {
                router.setPagePath(PageConstant.PAGE_USER_SUBJECTS);
                content.setRequestAttribute(ParamConstant.EMPTY_SUBJECTS,ParamConstant.EMPTY_SUBJECTS);
            }
        } catch (LogicException e) {
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }

        return router;
    }
}
