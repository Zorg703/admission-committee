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

public class ToNextFindUsersPage implements Command {
    private UserService userService;
    public ToNextFindUsersPage(){
        userService= ServiceFactory.getInstance().getUserService();
    }
    @Override
    public Router execute(SessionRequestContent content) {
        Router router=new Router();
        String count=content.getRequestParameter(ParamConstant.COUNTER);
       // int count2=(Integer)content.getRequestAttribute(ParamConstant.COUNTER);
//        if(count.equals("0")){
//            count="5";
//        }
        try{
            Optional<List<User>> optionalUsers=userService.findUserLimitCount(count);
            if(optionalUsers.isPresent()){
                List<User> userList=optionalUsers.get();
                content.setRequestAttribute(ParamConstant.USER_LIST, userList);
                int counter=Integer.valueOf(count);

                    content.setRequestAttribute(ParamConstant.COUNTER,counter);

            }
            else {

            }
            router.setPagePath(PageConstant.PAGE_SHOW_USERS);
        } catch (LogicException e) {
            e.printStackTrace();
        }
        return router;
    }
}
