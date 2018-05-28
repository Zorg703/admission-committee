package by.mordas.project.dao;
import by.mordas.project.entity.User;
import by.mordas.project.entity.Subject;

import java.util.List;
import java.util.Map;

/***
 Author: Sergei Mordas
 Date: 06.04.2018
 ***/
public interface UserDAO extends AbstractDAO<User> {

    /**
     * Find user by login.
     *
     * @param login the login
     * @return the user
     * @throws DAOException the DAO exception
     */
    boolean findUserByLogin(String login) throws DAOException;

    /**
     * Find user by login and password.
     *
     * @param login the login
     * @param password the password
     * @return the user
     * @throws DAOException the DAO exception
     */
    User findUserByPasswordAndLogin(String login,String password) throws DAOException;

    /**
     * Find user subjects and scores by user id.
     *
     * @param id the user id
     * @return the map with subject key and scores value
     * @throws DAOException the DAO exception
     */
    Map<Subject,Integer> findUserSubjectsAndScores(long id) throws DAOException;

    /**
     * Change user password
     *
     * @param userId the user id
     * @param password the new password
     * @throws DAOException the DAO exception
     */
    void changeUserPassword(Long userId,String password) throws DAOException;

    /**
     * Update user speciality
     * @param user the user
     * @throws DAOException the DAO exception
     */
    void updateUserSpeciality(User user) throws DAOException;

    /**
     * Clear user score
     * @param user the user
     * @throws DAOException the DAO exception
     */
    void clearUserScore(User user) throws DAOException;

    /**
     * Find users with limit
     *
     * @param count the count of users
     * @return list of user with limit coun
     * @throws DAOException the DAO exception
     */
    List<User> findUsersWithLimit(int count) throws DAOException;
}
