package by.mordas.project.controller;

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
