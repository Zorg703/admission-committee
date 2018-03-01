package by.mordas.project.command;

import by.mordas.project.controller.Router;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return null;
    }
}
