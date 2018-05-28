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
    Optional<Faculty> addFaculty(String facultyName) throws LogicException;

    boolean deleteFaculty(String facultyId) throws LogicException;

    Optional<Faculty> findFaculty(String facultyId) throws LogicException;

    boolean updateFaculty(Faculty faculty) throws LogicException;

    Optional<List<Faculty>> findAllFaculties() throws LogicException;

    Optional<Faculty> findFaculty(Long facultyId) throws LogicException;

    Optional<List<Speciality>> findSpecialitiesByFacultyId(String id) throws LogicException;
}
