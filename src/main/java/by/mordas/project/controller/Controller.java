package by.mordas.project.controller;

import by.mordas.project.command.Command;
import by.mordas.project.command.CommandMap;
import by.mordas.project.command.ParamConstant;
import by.mordas.project.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.OperationNotSupportedException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 Author: Sergei Mordas
 Date: 18.04.2018
 ***/

@WebServlet(name = "controller", urlPatterns = "/controller")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().closePool();
        super.destroy();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionRequestContent content = new SessionRequestContent(request);
        String d=request.getCharacterEncoding();
        String s=request.getParameter("faculty_name");
        Command command = CommandMap.getInstance().get(content.getRequestParameter(ParamConstant.COMMAND));
        Router router = command.execute(content);
        request = content.getRequest();
        switch (router.getRouter()) {
            case FORWARD:
                request.getRequestDispatcher(router.getPagePath()).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(router.getPagePath());
                break;
            default: throw new IllegalArgumentException ();
        }


    }
}
