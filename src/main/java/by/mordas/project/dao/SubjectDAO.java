package by.mordas.project.dao;

import by.mordas.project.entity.Subject;

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
