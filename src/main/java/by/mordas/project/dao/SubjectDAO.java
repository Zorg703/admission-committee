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

    List<Subject> findAllEntity();

    Subject findEntityById(int id) throws DAOException;

    boolean delete(int id) throws DAOException;

    void create(Subject subject) throws DAOException;

    Subject update(Subject subject) throws DAOException;

    List<Subject> findSubjectByUserId(int id) throws DAOException;
    List<Subject> findSubjectsBySpecialityId(int id) throws DAOException;

}
