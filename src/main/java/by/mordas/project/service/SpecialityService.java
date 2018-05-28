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
    Optional<List<Speciality>> findSpecialitiesOnFaculty(String id) throws LogicException;

    Map<String, String> updateRegisterOnSpecialityDate(String startDate, String endDate, String specialityId) throws LogicException;

    boolean deleteSpeciality(String specialityId) throws LogicException;

    Map<String,String> addSpeciality(Map<String,String> parameters) throws LogicException;

    Optional<List<Speciality>> findAllSpecialities() throws LogicException;

    Optional<Speciality> findSpecialityById(String specialityId) throws LogicException;

    Map<String, String> updateSpeciality(Map<String, String> parameters) throws LogicException;

    Optional<Speciality> findSpeciality(Long id) throws LogicException;

    boolean checkEndOfSpecialityRegistrationDate(Speciality speciality);

}
