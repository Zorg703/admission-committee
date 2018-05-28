package by.mordas.project.service.impl;

import by.mordas.project.dao.DAOException;
import by.mordas.project.dao.DAOFactory;
import by.mordas.project.dao.FacultyDAO;
import by.mordas.project.dao.SpecialityDAO;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.service.FacultyService;
import by.mordas.project.service.LogicException;
import by.mordas.project.util.DataValidator;

import java.util.List;
import java.util.Optional;

/***
 Author: Sergei Mordas
 Date: 20.05.2018
 ***/
public class FacultyServiceImpl implements FacultyService {
    private DAOFactory mysqlFactory=DAOFactory.getFactory(DAOFactory.MySQL);

    @Override
    public Optional<Faculty> addFaculty(String facultyName) throws LogicException {
        DataValidator dataValidator =new DataValidator();
        Optional<Faculty> optional=Optional.empty();
        if(dataValidator.checkFacultyOrSpecialityName(facultyName)) {
            try {
                Faculty faculty=new Faculty();
                faculty.setFacultyName(facultyName);
                FacultyDAO facultyDAO = mysqlFactory.getFacultyDAO();
                facultyDAO.create(faculty);
                optional= Optional.ofNullable(faculty);
            } catch (DAOException e) {
                throw new LogicException("Problems with addFaculty method", e);
            }
        }
        return optional;
    }

    @Override
    public boolean deleteFaculty(String facultyId) throws LogicException {
        DataValidator validator=new DataValidator();
        if(validator.checkId(facultyId)) {
            Long id=Long.valueOf(facultyId);
            FacultyDAO facultyDAO = mysqlFactory.getFacultyDAO();
            try {
                return facultyDAO.delete(id);
            } catch (DAOException e) {
                throw new LogicException("Problems with deleteFaculty method", e);
            }
        }
        return false;
    }

    @Override
    public Optional<Faculty> findFaculty(String facultyId) throws LogicException {
        Optional<Faculty> optional=Optional.empty();
        DataValidator validator=new DataValidator();
        if (validator.checkId(facultyId)) {
            long id=Long.valueOf(facultyId);
            try {
                Faculty faculty = mysqlFactory.getFacultyDAO().findEntityById(id);
                optional=Optional.ofNullable(faculty);
            } catch (DAOException e) {
                throw new LogicException("Problems with findFaculty method",e);
            }
        }
        return optional;
    }

    @Override
    public boolean updateFaculty(Faculty faculty) throws LogicException {
        DataValidator validator=new DataValidator();
        if(validator.checkFacultyOrSpecialityName(faculty.getFacultyName())){
            try {
                mysqlFactory.getFacultyDAO().update(faculty);
                return true;
            } catch (DAOException e) {
                throw new LogicException("Problems with updateFaculty method",e);
            }
        }
        return false;
    }

    @Override
    public Optional<List<Faculty>> findAllFaculties() throws LogicException {
        Optional<List<Faculty>> optional;
        FacultyDAO facultyDAO=mysqlFactory.getFacultyDAO();
        try {
            List<Faculty> list = facultyDAO.findAllEntity();
            optional=Optional.ofNullable(list);
        } catch (DAOException e) {
            throw new LogicException("Problems with findAllFaculty method",e);
        }
        return optional;
    }

    @Override
    public Optional<Faculty> findFaculty(Long facultyId) throws LogicException {
        Optional<Faculty> optional;
        try {
            Faculty faculty=mysqlFactory.getFacultyDAO().findEntityById(facultyId);
            optional=Optional.ofNullable(faculty);
        } catch (DAOException e) {
            throw new LogicException("Problems with findFaculty method", e);
        }
        return optional;
    }

}
