package by.mordas.project.command.admin;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.User;
import by.mordas.project.logic.impl.AdminLogicImpl;
import by.mordas.project.logic.LogicException;
import by.mordas.project.util.DataValidator;

public class FindUserCommand implements Command {
    AdminLogicImpl adminLogicImpl =new AdminLogicImpl();
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String userId=content.getRequestParameter(ParamConstant.USER_ID);
        if(new DataValidator().checkId(userId)){
            try {
               User user= adminLogicImpl.findUserById(userId);
               content.setRequestAttribute(ParamConstant.USER,user);
               router.setPagePath(PageConstant.PAGE_FIND_USER_BY_ID);
            } catch (LogicException e) {
                e.printStackTrace();
            }
        }
        else {
            content.setRequestAttribute(ParamConstant.MESSAGE,userId);
            router.setPagePath(PageConstant.PAGE_FIND_USER_BY_ID);
        }
        return router;
    }
}
