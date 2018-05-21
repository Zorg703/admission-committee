package by.mordas.project.service;

import by.mordas.project.entity.Subject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SubjectService {
    Optional<Map<Subject, Integer>> findSubjects(Long id) throws LogicException;

    Optional<List<Subject>> findSubjectsForSpeciality(String specialityId) throws LogicException;


}
