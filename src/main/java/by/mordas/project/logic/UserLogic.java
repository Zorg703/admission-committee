package by.mordas.project.logic;

import by.mordas.project.dao.FacultyDAO;
import by.mordas.project.dao.SpecialityDAO;
import by.mordas.project.dao.SubjectDAO;
import by.mordas.project.dao.UserDAO;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;

import java.util.List;

public class UserLogic implements Logic{
public List<Faculty> findAllFaculties(){
    List<Faculty> list=new FacultyDAO().findAllEntity();
    return list;
}
public List<Speciality> findSpecialitiesByFacultyId(int id){
    List<Speciality> specialities=new SpecialityDAO().findSpecialitiesByFacultyID(id);
    return specialities;
}

public boolean registerUser(User user){
        new UserDAO().create(user);

    return true;
}
public boolean findUserByLogin(String login){
return new UserDAO().findUserByLogin(login);
}

public Subject findSubject(Integer id){
    Subject subject;
    subject=new SubjectDAO().findEntityById(id);
    return subject;
}
}
