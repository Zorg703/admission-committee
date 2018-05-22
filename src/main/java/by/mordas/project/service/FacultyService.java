package by.mordas.project.service;

import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;

import java.util.List;
import java.util.Optional;

public interface FacultyService {
    Optional<Faculty> addFaculty(String facultyName) throws LogicException;

    boolean deleteFaculty(String facultyId) throws LogicException;

    Optional<Faculty> findFaculty(String facultyId) throws LogicException;

    boolean updateFaculty(Faculty faculty) throws LogicException;

    Optional<List<Faculty>> findAllFaculties() throws LogicException;

    Optional<Faculty> findFaculty(Long facultyId) throws LogicException;
}
