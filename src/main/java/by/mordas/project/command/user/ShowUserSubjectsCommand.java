package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;
import by.mordas.project.service.LogicException;
import by.mordas.project.service.SubjectService;
import by.mordas.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Optional;

/***
 Author: Sergei Mordas
 Date: 12.04.2018
 ***/
public class ShowUserSubjectsCommand implements Command{
    private static Logger logger= LogManager.getRootLogger();
    private SubjectService subjectService;
    public ShowUserSubjectsCommand(){
        subjectService=ServiceFactory.getInstance().getSubjectService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        User user= (User) content.getSessionAttribute(ParamConstant.USER);
        try {
            Optional<Map<Subject,Integer>> optionalMap=subjectService.findUserSubjectsScore(user.getUserId());
            if(optionalMap.isPresent() &&!optionalMap.get().isEmpty()) {
                Map<Subject,Integer> subjectsMap= optionalMap.get();
                content.setRequestAttribute(ParamConstant.SUBJECTS,subjectsMap);
                router.setPagePath(PageConstant.PAGE_USER_SUBJECTS);
            }
            else {
                router.setPagePath(PageConstant.PAGE_USER_SUBJECTS);
                content.setRequestAttribute(ParamConstant.EMPTY_SUBJECTS,user.getSpecialityId());
            }
        } catch (LogicException e) {
            logger.log(Level.ERROR,e.getMessage());
            router.setRouter(Router.RouteType.REDIRECT);
            content.setSessionAttribute(ParamConstant.EXCEPTION_MESSAGE,e.getMessage());
            router.setPagePath(PageConstant.PAGE_ERROR);
        }
        return router;
    }
}
