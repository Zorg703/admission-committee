package by.mordas.project.logic.impl;

import by.mordas.project.command.ParamConstant;
import by.mordas.project.dao.*;

import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.UserLogic;
import by.mordas.project.util.DataValidator;
import by.mordas.project.util.PasswordEncoder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserLogicImpl implements UserLogic {
    private static Logger logger= LogManager.getRootLogger();
    private DAOFactory mysqlFactory=DAOFactory.getFactory(DAOFactory.MySQL);

    @Override
    public List<Faculty> findAllFaculties() throws LogicException {
        FacultyDAO facultyDAO=mysqlFactory.getFacultyDAO();
        List<Faculty> list = null;
        try {
            list = facultyDAO.findAllEntity();
        } catch (DAOException e) {
            logger.log(Level.ERROR,e.getMessage());
            throw new LogicException("Problems with findAllFaculty method",e);
        }
        return list;
    }

    @Override
    public List<Speciality> findSpecialitiesByFacultyId(String id) throws LogicException {
        List<Speciality> specialities= null;
        DataValidator validator=new DataValidator();
        if(validator.checkId(id)) {
            SpecialityDAO specialityDAO = mysqlFactory.getSpecialityDAO();
            Integer facultyId=Integer.valueOf(id);
            try {
                specialities = specialityDAO.findSpecialitiesByFacultyID(facultyId);
            } catch (DAOException e) {
                logger.log(Level.ERROR, e.getMessage());
                throw new LogicException("Problems with findSpecialitiesByFacultyId method", e);
            }
        }
        return specialities;
    }

    @Override
    public boolean findUserByLogin(String login) throws LogicException {
        DataValidator validator=new DataValidator();
        if(true) {//todo
            UserDAO userDAO = mysqlFactory.getUserDAO();
            try {
                return userDAO.findUserByLogin(login);
            } catch (DAOException e) {
                logger.log(Level.ERROR, e.getMessage());
                throw new LogicException("Problems with findUserByLogin method", e);
            }
        }
        return false;

    }

    @Override
    public Subject findSubject(String id) throws LogicException {
        Subject subject = null;
        DataValidator validator=new DataValidator();
        if(validator.checkId(id)) {
            Long subjectId=Long.valueOf(id);
            SubjectDAO subjectDAO = mysqlFactory.getSubjectDAO();

            try {
                subject = subjectDAO.findEntityById(subjectId);
            } catch (DAOException e) {
                logger.log(Level.ERROR, e.getMessage());
                throw new LogicException("Problems with findSubject method", e);
            }
        }
        return subject;
    }

    @Override
    public Map<Subject,Integer> findSubjects(Long id) throws LogicException {
        Map<Subject,Integer> subjects=null;
        UserDAO userDAO = mysqlFactory.getUserDAO();
        try {
            subjects = userDAO.findUserSubjectsAndScores(id);
        }
        catch (DAOException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new LogicException("Problems with findSubjects method", e);
        }
        return subjects;
    }
    
    @Override
    public List<Subject> findSubjectsForSpeciality(String specialityId) throws LogicException {
        DataValidator validator=new DataValidator();
        SubjectDAO subjectDAO=mysqlFactory.getSubjectDAO();
        List<Subject> subjects = null;
        if(validator.checkId(specialityId)) {
            try {
                Long id=Long.valueOf(specialityId);
                subjects = subjectDAO.findSubjectsBySpecialityId(id);
            } catch (DAOException e) {
                logger.log(Level.ERROR, e.getMessage());
                throw new LogicException("Problems with findSubjectsForSpeciality method", e);
            }

        }
        return subjects;
    }

    @Override
    public HashMap<String,String> changePassword(Long userId, String password1,String password2 ) throws LogicException {
        DataValidator validator=new DataValidator();
        UserDAO userDAO=mysqlFactory.getUserDAO();
        HashMap<String,String> errorMap=validator.checkChangedPassword(password1,password2);
        if(errorMap.isEmpty()) {
            try{
                password1 = PasswordEncoder.encodePassword(password1);
                userDAO.changeUserPassword(userId, password1);
            } catch (DAOException e) {
                logger.log(Level.ERROR, e.getMessage());
                throw new LogicException("Problems with changePassword method", e);
            }
        }
        return errorMap;
    }
    @Override
    public HashMap<String,String> registerUser(HashMap<String,String> parameters) throws LogicException {
        DataValidator validator=new DataValidator();
        HashMap<String,String> errorMap=validator.checkUserData(parameters);
        UserDAO userDAO = mysqlFactory.getUserDAO();
        if(errorMap.isEmpty()) {
            try {
                User user=new User();
                user.setFirstName(parameters.get(ParamConstant.FIRST_NAME));
                user.setLastName(parameters.get(ParamConstant.LAST_NAME));
                user.setBirthday(Date.valueOf(parameters.get(ParamConstant.BIRTHDAY)));
                user.setEmail(parameters.get(ParamConstant.EMAIL));
                user.setPassword(parameters.get(ParamConstant.PASSWORD_ONE));
                user.setLogin(parameters.get(ParamConstant.LOG_IN));
                user.setPassword(PasswordEncoder.encodePassword(user.getPassword()));
                userDAO.create(user);
            } catch (DAOException e) {
                logger.log(Level.ERROR, e.getMessage());
                throw new LogicException("Problems with registerUser method", e);
            }
        }
        return errorMap;
    }

    @Override
    public User setUserSpeciality(User user,Long specialityId,HashMap<String,String> parameters) throws LogicException {
        DataValidator validator=new DataValidator();
        String userFirstMark=parameters.get(ParamConstant.FIRST_SUBJECT_MARK);
        String userSecondMark=parameters.get(ParamConstant.SECOND_SUBJECT_MARK);
        String userThirdMark=parameters.get(ParamConstant.THIRD_SUBJECT_MARK);
        String userCertificate=parameters.get(ParamConstant.CERTIFICATE_AVG);
        if(validator.validateUserMarks(userFirstMark,userSecondMark,userThirdMark,userCertificate)) {
            UserDAO userDAO = mysqlFactory.getUserDAO();
            try {
                user.setSpecialityId(specialityId);
                user.setCertificateMark(Integer.valueOf(userCertificate));
                Subject subject1=new Subject();
                subject1.setSubjectId(Integer.valueOf(parameters.get(ParamConstant.FIRST_SUBJECT)));
                user.put(subject1, Integer.valueOf(userFirstMark));
                Subject subject2=new Subject();
                subject2.setSubjectId(Integer.valueOf(parameters.get(ParamConstant.SECOND_SUBJECT)));
                user.put(subject2, Integer.valueOf(userSecondMark));
                Subject subject3=new Subject();
                subject3.setSubjectId(Integer.valueOf(parameters.get(ParamConstant.THIRD_SUBJECT)));
                user.put(subject3, Integer.valueOf(userThirdMark));
                userDAO.updateUserSpeciality(user);
                return user;
            } catch (DAOException e) {
                logger.log(Level.ERROR, e.getMessage());
                throw new LogicException("Problems with setUserSpeciality method", e);
            }
        }
        return null;
    }

    @Override
    public Speciality findSpeciality(Long id) throws LogicException {
        Speciality speciality= null;
        try {
            speciality = mysqlFactory.getSpecialityDAO().findEntityById(id);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new LogicException("Problems with findUserSpeciality method", e);
        }
        return speciality;
    }

    @Override
    public Faculty findFaculty(Long facultyId) throws LogicException {
        Faculty faculty=null;
        try {
            faculty=mysqlFactory.getFacultyDAO().findEntityById(facultyId);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new LogicException("Problems with findFaculty method", e);
        }
        return faculty;
    }

    @Override
    public boolean isAccepted(Speciality speciality,User user) throws LogicException {
        try {
            List<Integer> score=mysqlFactory.getSpecialityDAO().defineUsersSumScoreRegisterOnSpeciality(speciality.getSpecialityId());
            int recruitmentPlan=speciality.getRecruitmentPlan();
            if (score.isEmpty() || score.size()<recruitmentPlan) {
                return true;
            }
            else {
                if(calculateUserAvgScore(user)>calculateSpecialityAcceptedScore(score,recruitmentPlan))
                    return true;
            }

        } catch (DAOException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new LogicException("Problems with isAccepted method",e);
        }
        return false;
    }

    private int calculateUserAvgScore(User user){
        int score=user.getCertificateMark();
        for (Map.Entry<Subject,Integer> entry:user.getSubjectMark().entrySet()){
            score+=entry.getValue();
        }
        return score;
    }

    private int calculateSpecialityAcceptedScore(List<Integer> scores,int recruitmentPlan){
        int counter=0;
        for (Integer score:scores){
            counter+=score;
        }
        return counter/recruitmentPlan;
    }
}
