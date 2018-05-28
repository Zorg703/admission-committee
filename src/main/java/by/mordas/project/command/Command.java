package by.mordas.project.command;

import by.mordas.project.controller.Router;
import by.mordas.project.controller.SessionRequestContent;

/***
 Author: Sergei Mordas
 Date: 21.04.2018
 ***/
public interface Command {
    /**
     * execute request
     *
     * @param content the SessionRequestContent
     * @return router
     */
    Router execute(SessionRequestContent content);
}
