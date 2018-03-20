package by.mordas.project.logic;

import by.mordas.project.dao.*;
import by.mordas.project.dao.impl.FacultyDAOImpl;
import by.mordas.project.dao.impl.SpecialityDAOImpl;
import by.mordas.project.dao.impl.SubjectDAOImpl;
import by.mordas.project.dao.impl.UserDAOImpl;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;
import by.mordas.project.util.PasswordEncoder;

import java.util.List;

public class UserLogic implements Logic{
public List<Faculty> findAllFaculties(){
    List<Faculty> list
            =new FacultyDAOImpl().findAllEntity();
    return list;
}
public List<Speciality> findSpecialitiesByFacultyId(int id){
    List<Speciality> specialities=new SpecialityDAOImpl().findSpecialitiesByFacultyID(id);
    return specialities;
}

public void registerUser(User user) {
    user.setPassword(PasswordEncoder.encodePassword(user.getPassword()));
        new UserDAOImpl().create(user);

}
public boolean findUserByLogin(String login){
return new UserDAOImpl().findUserByLogin(login);
}

public Subject findSubject(Integer id){
    Subject subject;
    subject=new SubjectDAOImpl().findEntityById(id);
    return subject;
}
}
