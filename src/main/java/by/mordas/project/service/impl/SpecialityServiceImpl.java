package by.mordas.project.service.impl;

import by.mordas.project.command.ParamConstant;
import by.mordas.project.dao.DAOException;
import by.mordas.project.dao.DAOFactory;
import by.mordas.project.dao.FacultyDAO;
import by.mordas.project.dao.SpecialityDAO;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;
import by.mordas.project.service.LogicException;
import by.mordas.project.service.SpecialityService;
import by.mordas.project.util.DataValidator;
import by.mordas.project.util.DateConverter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/***
 Author: Sergei Mordas
 Date: 20.05.2018
 ***/
public class SpecialityServiceImpl implements SpecialityService{
    private DAOFactory mysqlFactory=DAOFactory.getFactory(DAOFactory.MySQL);

    @Override
    public Optional<List<Speciality>> findSpecialitiesOnFaculty(String id) throws LogicException {
        Optional<List<Speciality>> optional=Optional.empty();
        DataValidator validator=new DataValidator();
        if(validator.checkId(id)) {
            FacultyDAO facultyDAO= mysqlFactory.getFacultyDAO();
            Long facultyId=Long.valueOf(id);
            try {
                List<Speciality> specialities = facultyDAO.findSpecialityFromFaculty(facultyId);
                optional=Optional.ofNullable(specialities);
            } catch (DAOException e) {
                throw new LogicException("Problems with findSpecialitiesOnFaculty method", e);
            }
        }
        return optional;
    }

    @Override
    public Map<String, String> updateRegisterOnSpecialityDate(String startDate, String endDate, String specialityId) throws LogicException {
        DataValidator validator=new DataValidator();
        Map<String,String> errorMap=validator.checkRegisterDate(startDate, endDate,specialityId);
        if(errorMap.isEmpty()){
            try {
                Speciality speciality=new Speciality();
                speciality.setSpecialityId(Long.valueOf(specialityId));
                speciality.setStartRegistration(DateConverter.getLocaleDateTime(startDate));
                speciality.setEndRegistration(DateConverter.getLocaleDateTime(endDate));
                mysqlFactory.getSpecialityDAO().updateSpecialityRegisterDate(speciality);
            }
            catch (DAOException e) {
                throw new LogicException("Problems with updateRegisterOnSpecialityDate");
            }
        }
        return errorMap;
    }

    @Override
    public boolean deleteSpeciality(String specialityId) throws LogicException {
        DataValidator validator=new DataValidator();
        if(validator.checkId(specialityId)) {
            SpecialityDAO specialityDAO = mysqlFactory.getSpecialityDAO();
            Long id = Long.valueOf(specialityId);
            try {
                return specialityDAO.delete(id);
            } catch (DAOException e) {
                throw new LogicException("Problems with deleteSpeciality method", e);
            }
        }
        return false;
    }

    @Override
    public Map<String, String> addSpeciality(Map<String, String> parameters) throws LogicException {
        DataValidator validator=new DataValidator();
        Map<String,String> errorMap;
        try {
            errorMap=validator.checkSpecialtyData(parameters);
            if(errorMap.isEmpty()) {
                Speciality speciality = new Speciality();
                speciality.setFacultyId(Integer.parseInt(parameters.get(ParamConstant.FACULTY_ID)));
                speciality.setSpecialityName(parameters.get(ParamConstant.SPECIALITY_NAME));
                speciality.setRecruitmentPlan(Integer.parseInt(parameters.get(ParamConstant.RECRUITMENT_PLAN)));
                speciality.setStartRegistration(DateConverter.getLocaleDateTime(parameters.get(ParamConstant.START_REGISTRATION)));
                speciality.setEndRegistration(DateConverter.getLocaleDateTime(parameters.get(ParamConstant.END_REGISTRATION)));
                Subject subject1 = new Subject();
                subject1.setSubjectId(Integer.parseInt(parameters.get(ParamConstant.FIRST_SUBJECT)));
                speciality.add(subject1);
                Subject subject2 = new Subject();
                subject2.setSubjectId(Integer.parseInt(parameters.get(ParamConstant.SECOND_SUBJECT)));
                speciality.add(subject2);
                Subject subject3 = new Subject();
                subject3.setSubjectId(Integer.parseInt(parameters.get(ParamConstant.THIRD_SUBJECT)));
                speciality.add(subject3);
                SpecialityDAO specialityDAO = mysqlFactory.getSpecialityDAO();
                specialityDAO.create(speciality);
            }
        }
        catch (DAOException e) {
            throw new LogicException("Problems with addSpeciality method", e);
        }
        return errorMap;
    }

    @Override
    public Optional<List<Speciality>> findAllSpecialities() throws LogicException {
        Optional<List<Speciality>> optional;
        try {
            List<Speciality> specialities=mysqlFactory.getSpecialityDAO().findAllEntity();
            optional=Optional.ofNullable(specialities);
        } catch (DAOException e) {
            throw new LogicException("Problems with findAllSpecialities method",e);
        }
        return optional;
    }

    @Override
    public Optional<Speciality> findSpecialityById(String specialityId) throws LogicException {
        Optional<Speciality> optional=Optional.empty();
        DataValidator validator = new DataValidator();
        Speciality speciality = null;
        if (validator.checkId(specialityId)) {
            Long id = Long.valueOf(specialityId);
            try {
                speciality = mysqlFactory.getSpecialityDAO().findEntityById(id);
                optional=Optional.ofNullable(speciality);
            } catch (DAOException e) {
                throw new LogicException("Problems with findSpecialityById method", e);
            }

        }
        return optional;
    }

    @Override
    public Map<String, String> updateSpeciality(Map<String, String> parameters) throws LogicException {
        DataValidator validator=new DataValidator();
        Map<String,String> errorMap;
        try {
            errorMap=validator.checkSpecialtyData(parameters);
            if(errorMap.isEmpty()) {
                Speciality speciality = new Speciality();
                speciality.setFacultyId(Integer.parseInt(parameters.get(ParamConstant.FACULTY_ID)));
                speciality.setSpecialityName(parameters.get(ParamConstant.SPECIALITY_NAME));
                speciality.setRecruitmentPlan(Integer.parseInt(parameters.get(ParamConstant.RECRUITMENT_PLAN)));
                speciality.setStartRegistration(DateConverter.getLocaleDateTime(parameters.get(ParamConstant.START_REGISTRATION)));
                speciality.setEndRegistration(DateConverter.getLocaleDateTime(parameters.get(ParamConstant.END_REGISTRATION)));
                Subject subject1 = new Subject();
                subject1.setSubjectId(Integer.parseInt(parameters.get(ParamConstant.FIRST_SUBJECT)));
                speciality.add(subject1);
                Subject subject2 = new Subject();
                subject2.setSubjectId(Integer.parseInt(parameters.get(ParamConstant.SECOND_SUBJECT)));
                speciality.add(subject2);
                Subject subject3 = new Subject();
                subject3.setSubjectId(Integer.parseInt(parameters.get(ParamConstant.THIRD_SUBJECT)));
                speciality.add(subject3);
                SpecialityDAO specialityDAO = mysqlFactory.getSpecialityDAO();
                specialityDAO.create(speciality);
            }
        }
        catch (DAOException e) {
            throw new LogicException("Problems with addSpeciality method", e);
        }
        return errorMap;
    }



    @Override
    public Optional<Speciality> findSpeciality(Long id) throws LogicException {
        Optional<Speciality> optional;
        try {
            Speciality speciality = mysqlFactory.getSpecialityDAO().findEntityById(id);
            optional=Optional.ofNullable(speciality);
        } catch (DAOException e) {
            throw new LogicException("Problems with findUserSpeciality method", e);
        }
        return optional;
    }

    @Override
    public Optional<List<Speciality>> findAllSpecialitiesWithLimit(String count) throws LogicException {
        Optional<List<Speciality>> optional=Optional.empty();
        DataValidator dataValidator=new DataValidator();
        try {
            if(dataValidator.checkCounter(count)) {
                Integer counter=Integer.parseInt(count);
                List<Speciality> specialities = mysqlFactory.getSpecialityDAO().findSpecialitiesWithLimit(counter);
                optional = Optional.ofNullable(specialities);
            }
        } catch (DAOException e) {
            throw new LogicException("Problems with findAllSpecialities method",e);
        }
        return optional;
    }

    @Override
    public boolean checkEndOfSpecialityRegistrationDate(Speciality speciality) {
        LocalDateTime now=LocalDateTime.now();
        return speciality.getEndRegistration().isAfter(now);

    }
}
