package by.mordas.project.service;

import by.mordas.project.entity.Speciality;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/***
 Author: Sergei Mordas
 Date: 20.04.2018
 ***/

public interface SpecialityService {

    /**
     * Find specialities on faculty by faculty id
     *
     * If a value is present, returns the value, else return Optional.empty
     *
     * @param id the faculty id
     * @return optional of the speciality list
     * @throws LogicException the Logic exception
     */
    Optional<List<Speciality>> findSpecialitiesOnFaculty(String id) throws LogicException;

    /**
     * Update date of register on speciality(date start registration and date end registration)
     *
     * If a value is present, returns the value, else return Optional.empty
     *
     * @param startDate the new start date
     * @param endDate the new end date
     * @param specialityId the speciality id to change date
     * @return empty map if start and end date is correct
     * @throws LogicException the Logic exception
     */
    Map<String, String> updateRegisterOnSpecialityDate(String startDate, String endDate, String specialityId) throws LogicException;

    /**
     * Delete speciality by speciality id.
     * @param specialityId the speciality id
     * @return true if it is successful
     * @throws LogicException the Logic exception
     */
    boolean deleteSpeciality(String specialityId) throws LogicException;

    /**
     * Create the new speciality.
     *
     * @param parameters the parameters with speciality content
     * @return empty map if parameters is correct
     * @throws LogicException the Logic exception
     */
    Map<String,String> addSpeciality(Map<String,String> parameters) throws LogicException;

    /**
     * Find all speciality
     *
     * @return the optional of specialities
     * @throws LogicException the Logic exception
     */
    Optional<List<Speciality>> findAllSpecialities() throws LogicException;

    /**
     * Find speciality by speciality id
     *
     * If a value is present, returns the value, else return Optional.empty
     *
     * @param specialityId the speciality
     * @return optional of speciality
     * @throws LogicException the Logic exception
     */
    Optional<Speciality> findSpecialityById(String specialityId) throws LogicException;

    /**
     * Update speciality
     *
     * @param parameters  the map of speciality parameters
     * @return empty map if parameters is correct
     * @throws LogicException the Logic exception
     */
    Map<String, String> updateSpeciality(Map<String, String> parameters) throws LogicException;

    /**
     * Find speciality by speciality id
     *
     * If a value is present, returns the value, else return Optional.empty
     *
     * @param id the speciality id
     * @return optional of speciality
     * @throws LogicException the Logic exception
     */
    Optional<Speciality> findSpeciality(Long id) throws LogicException;

    /**
     * Check the end of speciality register date
     *
     * @param speciality the speciality
     * @return true if register on speciality is active
     */
    boolean checkEndOfSpecialityRegistrationDate(Speciality speciality);

    /**
     * Find all speciality
     *
     * If a value is present, returns the value, else return Optional.empty
     *
     * @param count the count of returns users
     * @return the optional of specialities
     * @throws LogicException the Logic exception
     */
    Optional<List<Speciality>> findAllSpecialitiesWithLimit(String count) throws LogicException;
}
