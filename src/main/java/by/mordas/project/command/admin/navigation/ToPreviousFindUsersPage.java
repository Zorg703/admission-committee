package by.mordas.project.command.admin.navigation;

import by.mordas.project.command.Command;
import by.mordas.project.command.PageConstant;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;
import by.mordas.project.entity.User;
import by.mordas.project.service.LogicException;
import by.mordas.project.service.UserService;
import by.mordas.project.service.factory.ServiceFactory;

import java.util.List;
import java.util.Optional;

public class ToPreviousFindUsersPage implements Command {
    private UserService userService;
    public ToPreviousFindUsersPage(){
        userService= ServiceFactory.getInstance().getUserService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String counter=content.getRequestParameter(ParamConstant.COUNTER);
        Optional<List<User>> optionalUsers= Optional.empty();
        try {
            optionalUsers = userService.findUserLimitCount(counter);
            if(optionalUsers.isPresent()) {
                List<User> userList = optionalUsers.get();
                content.setRequestAttribute(ParamConstant.USER_LIST, userList);
            }
            router.setPagePath(PageConstant.PAGE_SHOW_USERS);
        } catch (LogicException e) {
            e.printStackTrace();
        }

        return router;
    }
}