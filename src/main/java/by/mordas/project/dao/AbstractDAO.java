package by.mordas.project.dao;

import by.mordas.project.entity.Entity;

import java.util.List;

/***
 Author: Sergei Mordas
 Date: 06.03.2018
 ***/
public interface AbstractDAO<T extends Entity>{

    /**
     * Find all entities.
     *
     * @return the List<Entity>
     * @throws DAOException the DAO exception
     */
    List<T> findAllEntity() throws DAOException;

    /**
     * Find Entity by id.
     *
     * @param id the ID of Entity
     * @return the Entity
     * @throws DAOException the DAO exception
     */
    T findEntityById(long id) throws DAOException;

    /**
     * Delete Entity by id.
     *
     * @param id the ID of Entity
     * @throws DAOException the DAO exception
     */
    boolean delete(long id) throws DAOException;

    /**
     * Create the new Entity.
     *
     * @param entity the Entity
     * @throws DAOException the DAO exception
     */
    void create(T entity) throws DAOException;

    /**
     * Update Entity.
     *
     * @param entity the Entity
     * @throws DAOException the DAO exception
     */
    T update(T entity) throws DAOException;
}
