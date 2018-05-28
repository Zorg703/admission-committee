package by.mordas.project.dao;

import by.mordas.project.dao.factoryimpl.MySQLDAOFactory;
import by.mordas.project.dao.factoryimpl.OracleDAOFactory;

/***
 Author: Sergei Mordas
 Date: 06.04.2018
 ***/
public abstract class DAOFactory
{
    /* The Constant instance. */
    public static final int MySQL=1;

    /* The Constant instance. */
    public static final int Oracle=2;

    /**
     * Instantiates a new DAO factory.
     */
    public static DAOFactory getFactory(int whichFactory){
        switch (whichFactory){
            case MySQL:
                return new MySQLDAOFactory();
            case Oracle:
                return new OracleDAOFactory();
            default:
                throw new UnsupportedOperationException();
        }


    }

    /**
     * Gets the user DAO.
     *
     * @return the user DAO
     */
    public abstract UserDAO getUserDAO();

    /**
     * Gets the speciality DAO.
     *
     * @return the speciality DAO
     */
    public abstract SpecialityDAO getSpecialityDAO();

    /**
     * Gets the subject DAO.
     *
     * @return the subject DAO
     */
    public abstract SubjectDAO getSubjectDAO();

    /**
     * Gets the faculty DAO.
     *
     * @return the faculty DAO
     */
    public abstract FacultyDAO getFacultyDAO();
}
