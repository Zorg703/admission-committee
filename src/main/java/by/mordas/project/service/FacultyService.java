package by.mordas.project.service;

import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;

import java.util.List;
import java.util.Optional;

/***
 Author: Sergei Mordas
 Date: 20.04.2018
 ***/

public interface FacultyService {

    /**
     * Create the new faculty
     *
     * @param facultyName the faculty name
     * @return The optional Faculty
     * @throws LogicException the Logic exception
     */
    Optional<Faculty> addFaculty(String facultyName) throws LogicException;

    /**
     * Delete the faculty
     *
     * @param facultyId the faculty id
     * @return true if it successful
     * @throws LogicException the Logic exception
     */
    boolean deleteFaculty(String facultyId) throws LogicException;

    /**
     * Find faculty by id
     *
     * @param facultyId the faculty id
     * @return The optional list of faculty
     * @throws LogicException the Logic exception
     */
    Optional<Faculty> findFaculty(String facultyId) throws LogicException;

    /**
     * Update the faculty
     *
     * @param faculty the new faculty
     * @return true if it successful
     * @throws LogicException the Logic exception
     */
    boolean updateFaculty(Faculty faculty) throws LogicException;

    /**
     * Find all faculties
     *
     * @return optional list of faculty
     * @throws LogicException the Logic exception
     */
    Optional<List<Faculty>> findAllFaculties() throws LogicException;

    /**
     * Find faculty by faculty id
     *
     * @param facultyId the faculty id
     * @return optional of faculty
     * @throws LogicException the Logic exception
     */
    Optional<Faculty> findFaculty(Long facultyId) throws LogicException;


}
