package by.mordas.project.controller;

import by.mordas.project.command.Command;
import by.mordas.project.command.CommandMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       processRequest(req, resp);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Router router;
        Command command= CommandMap.getInstance().get(request.getParameter("command"));
        router=command.execute(request);
        switch (router.getRouter()){
            case FORWARD: request.getRequestDispatcher(router.getPagePath()).forward(request,response);
            break;
            case REDIRECT:response.sendRedirect(router.getPagePath());
            break;
        }





    }
}
