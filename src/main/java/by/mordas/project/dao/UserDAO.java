package by.mordas.project.dao;
import by.mordas.project.entity.User;
import by.mordas.project.entity.Subject;

import java.util.List;
import java.util.Map;


public interface UserDAO extends AbstractDAO<User> {

    List<User> findAllEntity() throws DAOException;

    User findEntityById(long id) throws DAOException;

    boolean delete(long id) throws DAOException;

    void create(User user) throws DAOException;

    User update(User user) throws DAOException;

    boolean findUserByLogin(String login) throws DAOException;

    User findUserByPasswordAndLogin(String login,String password) throws DAOException;

    Map<Subject,Integer> findUserSubjectsAndScores(long id) throws DAOException;

    void changeUserPassword(Long userId,String password) throws DAOException;

    void updateUserSpeciality(User user) throws DAOException;

    void clearUserScore(User user) throws DAOException;

    List<User> findUsersWithLimit(int count) throws DAOException;
}
