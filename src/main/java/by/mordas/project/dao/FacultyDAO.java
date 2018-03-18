package by.mordas.project.dao;

import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.pool.ConnectionPool;
import by.mordas.project.pool.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface FacultyDAO extends AbstractDAO<Faculty> {

    List<Faculty> findAllEntity();

    Faculty findEntityById(int id);

    boolean delete(int id);

    void create(Faculty faculty);

    Faculty update(Faculty faculty) ;

    List<Speciality> findSpecialityFromFaculty();
}