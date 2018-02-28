package by.mordas.project.command;

import by.mordas.project.controller.Router;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request);
}
