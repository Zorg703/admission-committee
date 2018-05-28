package by.mordas.project.dao;

import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;

import java.util.List;

/***
 Author: Sergei Mordas
 Date: 06.04.2018
 ***/
public interface FacultyDAO extends AbstractDAO<Faculty> {

    /**
     * Find speciality from faculty
     * @return list of speciality
     * @throws DAOException the DAO exception
     */
    List<Speciality> findSpecialityFromFaculty(long id) throws DAOException;
}