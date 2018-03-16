package by.mordas.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SessionRequestContent {
    private HashMap<String, Object> requestAttributes;
    private Map<String,String[]> requestParameters;
    private HashMap<String,Object> sessionAttributes;

    public SessionRequestContent() {
        this.requestAttributes = (HashMap<String, Object>) requestAttributes;
        this.requestParameters =new HashMap<>();
        this.sessionAttributes = sessionAttributes;
    }

    public void extractValues(HttpServletRequest request){
        requestParameters=request.getParameterMap();
        Enumeration<String> attributes=request.getAttributeNames();
        while (attributes.hasMoreElements()){
            String name=attributes.nextElement();
            requestAttributes.put(name,request.getAttribute(name));
        }
        HttpSession session=request.getSession();
        attributes=session.getAttributeNames();
        while (attributes.hasMoreElements()){
            String name=attributes.nextElement();
            sessionAttributes.put(name,session.getAttribute(name));
        }

    }
    public void insertAttributes(HttpServletRequest request){

    }

}
