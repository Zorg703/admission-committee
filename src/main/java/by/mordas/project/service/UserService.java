package by.mordas.project.service;

import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/***
 Author: Sergei Mordas
 Date: 20.04.2018
 ***/
public interface UserService {

    /**
     * Find all users.
     *
     * If a value is present, returns the value, else return Optional.empty.
     *
     * @return optional list of users
     * @throws LogicException the Logic exception
     */
    Optional<List<User>> findAllUser() throws LogicException;

    /**
     * Find user by user id.
     *
     * If a value is present, returns the value, else return Optional.empty.
     *
     * @param userId the user id
     * @return optional of user
     * @throws LogicException the Logic exception
     */
    Optional<User> findUserById(String userId) throws LogicException;

    /**
     * Find all users that register on speciality by speciality id.
     *
     * If a value is present, returns the value, else return Optional.empty.
     *
     * @param id the specialty id
     * @return optional list of users
     * @throws LogicException the Logic exception
     */
    Optional<List<User>> findUsersRegisterOnSpeciality(String id) throws LogicException;

    /**
     * Find all users who are accepted on specialty
     *
     * If a value is present, returns the value, else return Optional.empty.
     *
     * @param specialityId the speciality id
     * @return optional list of users
     * @throws LogicException the Logic exception
     */
    Optional<List<User>> findAllAcceptedUsersOnSpeciality(String specialityId) throws LogicException;

    /**
     * Change user password.
     *
     * @param userId the user id
     * @param password1 the password one
     * @param password2 the password two
     * @return empty map if passwords is correct
     * @throws LogicException the Logic exception
     */
    Map<String,String> changePassword(Long userId, String password1, String password2 ) throws LogicException;

    /**
     *Register user on speciality.
     *
     * @param user is the user
     * @param specialityId is the speciality id
     * @param parameters is the parameters map with speciality data
     * @return user with register on speciality
     * @throws LogicException the Logic exception
     */
    User setUserSpeciality(User user,Long specialityId,HashMap<String,String> parameters) throws LogicException;

    /**
     * Check the user accepted on speciality or not.
     *
     * @param speciality the speciality
     * @param user the user
     * @return true if user is accepted
     * @throws LogicException the Logic exception
     */
    boolean isAccepted(Speciality speciality, User user) throws LogicException;

    /**
     *Cancel the user registration on speciality.
     *
     * @param user the user
     * @return user with empty speciality
     * @throws LogicException the Logic exception
     */
    User canceledUserRegistration(User user) throws LogicException;

    /**
     * Find user by login and password.
     *
     * If a value is present, returns the value, else return Optional.empty.
     *
     * @param login the login
     * @param password the password
     * @return optional of user
     * @throws LogicException the Logic exception
     */
    Optional<User> findUserLoginAndPassword(String login, String password) throws LogicException;

    /**
     * Register new user.
     *
     * @param parameters the map with users parameters
     * @return empty map if the user data is correct
     * @throws LogicException the Logic exception
     */
    Map<String,String> registerUser(Map<String, String> parameters) throws LogicException;

    /**
     * Find users with limit count
     *
     * If a value is present, returns the value, else return Optional.empty.
     *
     * @param count is the count of return users
     * @return optional list of users
     * @throws LogicException the Logic exception
     */
    Optional<List<User>> findUserLimitCount(String count) throws LogicException;

    /**
     *
     * Define passing score on speciality
     *
     * @param speciality the speciality
     * @return value of passing score
     * @throws LogicException the Logic exception
     */
    int definePassingScore(Speciality speciality) throws LogicException;
}
