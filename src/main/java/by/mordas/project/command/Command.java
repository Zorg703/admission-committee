package by.mordas.project.command;

import javax.servlet.http.HttpServlet;

public interface Command {
    void execute(HttpServlet request);
}
