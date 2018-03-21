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

    List<Speciality> findAllEntity() throws DAOException;

    Speciality findEntityById(int id) throws DAOException;

    List<Speciality> findSpecialitiesByFacultyID(int id) throws DAOException;

    boolean delete(int id) throws DAOException;

    void create(Speciality specialty) throws DAOException;

    Speciality update(Speciality specialty) throws DAOException;

    List<User> findUserOnSpeciality(int id) throws DAOException;



}
