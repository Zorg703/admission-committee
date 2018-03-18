package by.mordas.project.command;

import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {

    @Override
    public Router execute(SessionRequestContent content) {
        return null;
    }
}
