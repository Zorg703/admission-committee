package by.mordas.project.dao;

import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;

import java.util.List;

public interface FacultyDAO extends AbstractDAO<Faculty> {

    List<Faculty> findAllEntity() throws DAOException;

    Faculty findEntityById(long id) throws DAOException;

    boolean delete(long id) throws DAOException;

    void create(Faculty faculty) throws DAOException;

    Faculty update(Faculty faculty) throws DAOException;

    List<Speciality> findSpecialityFromFaculty() throws DAOException;
}