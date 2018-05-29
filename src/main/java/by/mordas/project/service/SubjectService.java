package by.mordas.project.service;

import by.mordas.project.entity.Subject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/***
 Author: Sergei Mordas
 Date: 20.04.2018
 ***/
public interface SubjectService {

    /**
     * Find user's subjects score by user id.
     *
     * @param id the user id
     * @return map of subjects and scores
     * @throws LogicException the Logic exception
     */
    Optional<Map<Subject, Integer>> findUserSubjectsScore(Long id) throws LogicException;

    /**
     * Find subjects for speciality to user able to register by speciality id.
     *
     * If a value is present, returns the value, else return Optional.empty
     *
     * @param specialityId the speciality id
     * @return optional of subjects list
     * @throws LogicException the logic exception
     */
    Optional<List<Subject>> findSubjectsForSpeciality(String specialityId) throws LogicException;


}
