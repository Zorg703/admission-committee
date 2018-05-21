package by.mordas.project.service.factory;

import by.mordas.project.service.*;
import by.mordas.project.service.impl.*;

public class ServiceFactory {
    private final static ServiceFactory instance=new ServiceFactory();
    private UserService userService=new UserServiceImpl();
    private FacultyService facultyService=new FacultyServiceImpl();
    private SpecialityService specialityService=new SpecialityServiceImpl();
    private SubjectService subjectService=new SubjectServiceImpl();
    private ServiceFactory(){
    }

    public static ServiceFactory getInstance(){
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public FacultyService getFacultyService() {
        return facultyService;
    }

    public SpecialityService getSpecialityService() {
        return specialityService;
    }

    public SubjectService getSubjectService() {
        return subjectService;
    }
}
