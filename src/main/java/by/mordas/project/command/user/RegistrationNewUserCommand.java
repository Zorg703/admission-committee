package by.mordas.project.command.user;

import by.mordas.project.command.Command;
import by.mordas.project.controller.Router;
import by.mordas.project.entity.User;
import by.mordas.project.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class RegistrationNewUserCommand implements Command {
    UserLogic userLogic=new UserLogic();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router=new Router();
        if(!userLogic.findUserByLogin(request.getParameter("login"))/*|| !validator.checkPassword(request.getParameter("password")*/){
            User user=new User();
            user.setFirstName(request.getParameter("first-name"));
            user.setLastName(request.getParameter("last-name"));
            user.setCertificateMark(Integer.parseInt(request.getParameter("avg")));
            user.setBirthday(Date.valueOf(request.getParameter("birthday")));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password")/*.encodePassword(request.getParameter("password"))*/);
            user.setLogin(request.getParameter("login"));
            user.put(userLogic.findSubject(Integer.valueOf(request.getParameter("first-subject"))), Integer.valueOf(request.getParameter("mark1")));
            user.put(userLogic.findSubject(Integer.valueOf(request.getParameter("second-subject"))), Integer.valueOf(request.getParameter("mark2")));
            user.put(userLogic.findSubject(Integer.valueOf(request.getParameter("third-subject"))), Integer.valueOf(request.getParameter("mark3")));
            userLogic.registerUser(user);

        }
        else {
            //TODO
        }


        return null;
    }

}
