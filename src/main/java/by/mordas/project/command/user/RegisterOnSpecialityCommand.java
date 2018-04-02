package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.User;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.UserLogic;
import by.mordas.project.util.Validator;

public class RegisterOnSpecialityCommand implements Command {
    UserLogic userLogic=new UserLogic();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        Integer specialityId= (Integer) content.getSessionAttribute(ParamConstant.SPECIALITY);
        User user=(User) content.getSessionAttribute(ParamConstant.USER);
        String userFirstMark=content.getRequestParameter(ParamConstant.FIRST_SUBJECT_MARK);
        String userSecondMark=content.getRequestParameter(ParamConstant.SECOND_SUBJECT_MARK);
        String userThirdMark=content.getRequestParameter(ParamConstant.THIRD_SUBJECT_MARK);
        if(new Validator().validateUserMarks(userFirstMark,userSecondMark,userThirdMark)) {
            user.setSpecialityId(specialityId);
            user.put(Integer.valueOf(content.getRequestParameter(ParamConstant.FIRST_SUBJECT)), Integer.valueOf(userFirstMark));
            user.put(Integer.valueOf(content.getRequestParameter(ParamConstant.SECOND_SUBJECT)), Integer.valueOf(userSecondMark));
            user.put(Integer.valueOf(content.getRequestParameter(ParamConstant.THIRD_SUBJECT)), Integer.valueOf(userThirdMark));
            try {
                userLogic.setUserSpeciality(user);
                router.setPagePath(PageConstant.PAGE_USER_SUCCESS);
                content.setSessionAttribute(ParamConstant.USER,user);
            } catch (LogicException e) {
                e.printStackTrace();
            }
        }
        else {
            router.setPagePath(PageConstant.PAGE_REGISTER_ON_FACULTY);
            content.setSessionAttribute(ParamConstant.MESSAGE,ParamConstant.MESSAGE);
            router.setRouter(Router.RouteType.REDIRECT);
        }

        return router;
    }
}
