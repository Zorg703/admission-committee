package by.mordas.project.dao;
import by.mordas.project.entity.User;
import by.mordas.project.entity.Subject;
import by.mordas.project.pool.ConnectionPool;
import by.mordas.project.pool.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public interface UserDAO extends AbstractDAO<User> {

    List<User> findAllEntity() throws DAOException;

    User findEntityById(int id) throws DAOException;

    boolean delete(int id) throws DAOException;

    void create(User user) throws DAOException;

    User update(User user) throws DAOException;

    boolean findUserByLogin(String login) throws DAOException;

    User findUserByPasswordAndLogin(String login,String password) throws DAOException;

    Map<Subject,Integer> findUserSubjectsAndScores(int id) throws DAOException;

    void changeUserPassword(Integer userId,String password) throws DAOException;
}
