package by.mordas.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SessionRequestContent {
    private HashMap<String, Object> requestAttributes;
    private HashMap<String,String> requestParameters;
    private HashMap<String,Object> sessionAttributes;
    private HashMap<String,String> requestHeaders;
    private HttpServletRequest request;
    private HttpSession session;

    public SessionRequestContent(HttpServletRequest request) {
        this.request=request;
        requestAttributes = new HashMap<>();
        requestParameters =new HashMap<>();
        sessionAttributes = new HashMap<>();
        requestHeaders=new HashMap<>();
        session=request.getSession();

    }

    public void extractValues(){
        Enumeration<String> parameters=request.getParameterNames();
        while (parameters.hasMoreElements()){
            String name=parameters.nextElement();
            requestParameters.put(name,request.getParameter(name));
        }
        Enumeration<String> attributes=request.getAttributeNames();
        while (attributes.hasMoreElements()){
            String name=attributes.nextElement();
            requestAttributes.put(name,request.getAttribute(name));
        }
        attributes=session.getAttributeNames();
        while (attributes.hasMoreElements()){
            String name=attributes.nextElement();
            sessionAttributes.put(name,session.getAttribute(name));
        }
        attributes=request.getHeaderNames();
        while (attributes.hasMoreElements()){
            String name=attributes.nextElement();
            requestHeaders.put(name,request.getHeader(name));
        }
    }

    public void setRequestAttribute(String name,Object value){
        request.setAttribute(name, value);
    }

    public void setSessionAttribute(String name,Object value){
        request.getSession().setAttribute(name, value);
    }

    public Object getRequestAttribute(String attribute) {
        return requestAttributes.get(attribute);
    }

    public String getRequestParameter(String parameter) {
        return requestParameters.get(parameter);
    }

    public Object getSessionAttribute(String attribute) {
        return sessionAttributes.get(attribute);
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public String getHeader(String value){
        return requestHeaders.get(value);
    }

    public HashMap<String, Object> getRequestAttributesMap() {
        return requestAttributes;
    }

    public HashMap<String, String> getRequestParameters() {
        return requestParameters;
    }

    public HashMap<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    public HashMap<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public HttpSession getSession() {
        return session;
    }
}
