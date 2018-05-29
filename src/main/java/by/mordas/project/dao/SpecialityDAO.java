package by.mordas.project.dao;

import by.mordas.project.entity.User;
import by.mordas.project.entity.Speciality;

import java.util.List;

/***
 Author: Sergei Mordas
 Date: 06.04.2018
 ***/
public interface SpecialityDAO extends AbstractDAO<Speciality> {

    /**
     * Find users register on speciality
     *
     * @param id user id
     * @return listo of users
     * @throws DAOException the DAO exception
     */
    List<User> findUsersOnSpeciality(long id) throws DAOException;

    /**
     * Define summ of users scores
     *
     * @param specialityId the speciality id
     * @return list of scores
     * @throws DAOException the DAO exception
     */
    List<Integer> defineUsersSumScoreRegisterOnSpeciality(long specialityId) throws DAOException;

    /**
     * Update date to register on speciality
     *
     * @param speciality the speciality
     * @throws DAOException the DAO exception
     */
    void updateSpecialityRegisterDate(Speciality speciality) throws DAOException;

    /**
     * Find users on speciality with limit
     *
     * @param count the count of users
     * @param specialityId the speciality id
     * @return list of user with limit count
     * @throws DAOException the DAO exception
     */
    List<User> findUsersOnSpecialityWithLimit(Long specialityId,int count) throws DAOException;

    /**
     * Find specialities with limit
     *
     * @param count the count of users
     * @return list of specialities with limit count
     * @throws DAOException the DAO exception
     */
    List<Speciality> findSpecialitiesWithLimit(int count) throws DAOException;
}
