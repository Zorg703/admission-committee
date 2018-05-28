package by.mordas.project.controller;

/***
 Author: Sergei Mordas
 Date: 18.04.2018
 ***/

public class Router {
    public enum RouteType{
        FORWARD,REDIRECT
    }
    private String pagePath;
    private RouteType router=RouteType.FORWARD;

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public RouteType getRouter() {
        return router;
    }

    public void setRouter(RouteType router) {
        this.router = router;
    }
}
