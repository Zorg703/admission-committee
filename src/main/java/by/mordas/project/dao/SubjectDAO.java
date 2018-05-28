package by.mordas.project.dao;

import by.mordas.project.entity.Subject;

import java.util.List;

/***
 Author: Sergei Mordas
 Date: 06.04.2018
 ***/
public interface SubjectDAO extends AbstractDAO<Subject> {

    /**
     * Find subjects for speciality to register
     *
     * @param id the speciality id
     * @return the list of subjects
     * @throws DAOException the dao exception
     */
    List<Subject> findSubjectsBySpecialityId(long id) throws DAOException;

}
