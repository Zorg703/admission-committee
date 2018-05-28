package by.mordas.project.service.impl;

import by.mordas.project.dao.DAOException;
import by.mordas.project.dao.DAOFactory;
import by.mordas.project.dao.SubjectDAO;
import by.mordas.project.dao.UserDAO;
import by.mordas.project.entity.Subject;
import by.mordas.project.service.LogicException;
import by.mordas.project.service.SubjectService;
import by.mordas.project.util.DataValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/***
 Author: Sergei Mordas
 Date: 10.05.2018
 ***/
public class SubjectServiceImpl implements SubjectService {
    private DAOFactory mysqlFactory=DAOFactory.getFactory(DAOFactory.MySQL);

    @Override
    public Optional<Map<Subject, Integer>> findSubjects(Long id) throws LogicException {
        Optional<Map<Subject, Integer>> optional;
        UserDAO userDAO = mysqlFactory.getUserDAO();
        try {
            Map<Subject,Integer> subjects= userDAO.findUserSubjectsAndScores(id);
            optional=Optional.ofNullable(subjects);
        }
        catch (DAOException e) {
            throw new LogicException("Problems with findSubjects method", e);
        }
        return optional;
    }

    @Override
    public Optional<List<Subject>> findSubjectsForSpeciality(String specialityId) throws LogicException {
        Optional<List<Subject>> optional=Optional.empty();
        DataValidator validator=new DataValidator();
        SubjectDAO subjectDAO=mysqlFactory.getSubjectDAO();
        if(validator.checkId(specialityId)) {
            try {
                Long id=Long.valueOf(specialityId);
                List<Subject> subjects= subjectDAO.findSubjectsBySpecialityId(id);
                optional= Optional.ofNullable(subjects);
            } catch (DAOException e) {
                throw new LogicException("Problems with findSubjectsForSpeciality method", e);
            }

        }
        return optional;
    }
}
