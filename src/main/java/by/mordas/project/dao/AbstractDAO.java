package by.mordas.project.dao;

import by.mordas.project.entity.Entity;

import java.util.List;

public interface AbstractDAO<T extends Entity>{
    public abstract List<T> findAllEntity() throws DAOException;
    public abstract T findEntityById(long id) throws DAOException;
    public abstract boolean delete(long id) throws DAOException;
    public abstract void create(T entity) throws DAOException;
    public abstract T update(T entity) throws DAOException;
}
