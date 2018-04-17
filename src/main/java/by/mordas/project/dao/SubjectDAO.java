package by.mordas.project.dao;

import by.mordas.project.entity.Subject;
import by.mordas.project.pool.ConnectionPool;
import by.mordas.project.pool.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface SubjectDAO extends AbstractDAO<Subject> {

    List<Subject> findAllEntity() throws DAOException;

    Subject findEntityById(long id) throws DAOException;

    boolean delete(long id) throws DAOException;

    void create(Subject subject) throws DAOException;

    Subject update(Subject subject) throws DAOException;

    List<Subject> findSubjectByUserId(long id) throws DAOException;
    List<Subject> findSubjectsBySpecialityId(long id) throws DAOException;

}
