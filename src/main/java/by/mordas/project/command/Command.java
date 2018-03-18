package by.mordas.project.command;

import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;

public interface Command {
    Router execute(SessionRequestContent content);
}
