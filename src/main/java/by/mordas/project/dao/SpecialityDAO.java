package by.mordas.project.dao;

import by.mordas.project.entity.User;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;
import by.mordas.project.pool.ConnectionPool;
import by.mordas.project.pool.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface SpecialityDAO extends AbstractDAO<Speciality> {

    List<Speciality> findAllEntity();

    Speciality findEntityById(int id);

    List<Speciality> findSpecialitiesByFacultyID(int id);

    boolean delete(int id);

    void create(Speciality specialty);

    Speciality update(Speciality specialty);

    List<User> findUserOnSpeciality(int id);



}
