package by.mordas.project.dao;

import by.mordas.project.entity.User;
import by.mordas.project.entity.Speciality;

import java.util.List;

public interface SpecialityDAO extends AbstractDAO<Speciality> {

    List<Speciality> findAllEntity() throws DAOException;

    Speciality findEntityById(long id) throws DAOException;

    List<Speciality> findSpecialitiesByFacultyID(long id) throws DAOException;

    boolean delete(long id) throws DAOException;

    void create(Speciality specialty) throws DAOException;

    Speciality update(Speciality specialty) throws DAOException;

    List<User> findUsersOnSpeciality(long id) throws DAOException;

    public List<Integer> defineUsersSumScoreRegisterOnSpeciality(long specialityId) throws DAOException;


    void updateSpecialityRegisterDate(Speciality speciality) throws DAOException;
}
