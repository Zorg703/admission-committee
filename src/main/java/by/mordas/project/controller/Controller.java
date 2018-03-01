package by.mordas.project.controller;

import by.mordas.project.command.Command;
import by.mordas.project.command.CommandMap;
import by.mordas.project.command.admin.ShowAllUserCommand;
import by.mordas.project.pool.ConnectionPool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "controller", urlPatterns = "/controller")
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

    @Override
    public void destroy() {
        ConnectionPool.closePool();
        super.destroy();
    }

    @Override
    public void init() throws ServletException {

        super.init();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router;
        //Command command= CommandMap.getInstance().get(request.getParameter("command"));
        Command command=new ShowAllUserCommand();
        router=command.execute(request);


        switch (router.getRouter()){
            case FORWARD: request.getRequestDispatcher(router.getPagePath()).forward(request,response);
            break;

        }






    }
}
