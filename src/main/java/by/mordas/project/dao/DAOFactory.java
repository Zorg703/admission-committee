package by.mordas.project.dao;

import by.mordas.project.dao.factoryimpl.MySQLDAOFactory;
import by.mordas.project.dao.factoryimpl.OracleDAOFactory;

public abstract class DAOFactory {
    public static final int MySQL=1;
    public static final int Oracle=2;

    public static DAOFactory getFactory(int whichFactory){
        switch (whichFactory){
            case MySQL:
                return new MySQLDAOFactory();
            case Oracle:
                return new OracleDAOFactory();
            default:

                throw new UnsupportedOperationException();//todo
        }


    }
    public abstract UserDAO getUserDAO();
    public abstract SpecialityDAO getSpecialityDAO();
    public abstract SubjectDAO getSubjectDAO();
    public abstract FacultyDAO getFacultyDAO();
}
