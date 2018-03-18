package by.mordas.project.dao;

import by.mordas.project.entity.Entity;

import java.util.List;

public interface AbstractDAO<T extends Entity>{
    public abstract List<T> findAllEntity();
    public abstract T findEntityById(int id);
    public abstract boolean delete(int id);
    public abstract void create(T entity);
    public abstract T update(T entity);
}
