package by.mordas.project.logic;

import by.mordas.project.dao.FacultyDAO;
import by.mordas.project.dao.SpecialityDAO;
import by.mordas.project.dao.UserDAO;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.User;

import java.util.List;

public class UserLogic {
public List<Faculty> findAllFaculties(){
    List<Faculty> list=new FacultyDAO().findAllEntity();
    return list;
}
public List<Speciality> findSpecialitiesByFacultyId(int id){
    List<Speciality> specialities=new SpecialityDAO().findSpecialitiesByFacultyID(id);
    return specialities;
}

public User registerUser(){
    User user=new User();
    return user;
}
}
