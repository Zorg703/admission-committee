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

    List<User> findAllEntity();

    User findEntityById(int id);

    boolean delete(int id);

    void create(User user);

    User update(User user);

    boolean findUserByLogin(String login);

    User findUserByPasswordAndLogin(String login,String password);


}
