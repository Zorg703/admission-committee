package by.mordas.project.dao.factoryimpl;

import by.mordas.project.dao.*;
import by.mordas.project.dao.mysqlimpl.MySQLFacultyDAOImpl;
import by.mordas.project.dao.mysqlimpl.MySQLSpecialityDAOImpl;
import by.mordas.project.dao.mysqlimpl.MySQLSubjectDAOImpl;
import by.mordas.project.dao.mysqlimpl.MySQLUserDAOImpl;

public class MySQLDAOFactory extends DAOFactory {

    @Override
    public UserDAO getUserDAO() {
        return new MySQLUserDAOImpl();
    }

    @Override
    public SpecialityDAO getSpecialityDAO() {
        return new MySQLSpecialityDAOImpl();
    }

    @Override
    public SubjectDAO getSubjectDAO() {
        return new MySQLSubjectDAOImpl();
    }

    @Override
    public FacultyDAO getFacultyDAO() {
        return new MySQLFacultyDAOImpl();
    }
}
