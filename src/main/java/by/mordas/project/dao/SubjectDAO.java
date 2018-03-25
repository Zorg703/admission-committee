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

    Subject findEntityById(int id);

    boolean delete(int id);

    void create(Subject subject);

    Subject update(Subject subject);

    List<Subject> findSubjectByUserId(int id);
}
