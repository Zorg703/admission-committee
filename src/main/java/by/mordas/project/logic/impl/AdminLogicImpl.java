package by.mordas.project.logic.impl;

import by.mordas.project.command.ParamConstant;
import by.mordas.project.dao.*;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;
import by.mordas.project.logic.AdminLogic;
import by.mordas.project.logic.Logic;
import by.mordas.project.logic.LogicException;
import by.mordas.project.util.DataValidator;
import by.mordas.project.util.DateConverter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminLogicImpl implements AdminLogic {
    private DAOFactory mysqlFactory=DAOFactory.getFactory(DAOFactory.MySQL);

    @Override
    public List<User> findAllUser() throws LogicException {
        List<User> userList=null;
        UserDAO userDAO=mysqlFactory.getUserDAO();
        try {
            userList=userDAO.findAllEntity();
        } catch (DAOException e) {

            throw new LogicException("Problems with findAllUser method",e);
        }
        return userList;
    }

    @Override
    public Faculty addFaculty(String facultyName) throws LogicException {
        DataValidator dataValidator =new DataValidator();
        Faculty faculty =null;
        if(dataValidator.checkFacultyOrSpecialityName(facultyName)) {
            try {
                faculty = new Faculty();
                faculty.setFacultyName(facultyName);
                FacultyDAO facultyDAO = mysqlFactory.getFacultyDAO();
                facultyDAO.create(faculty);
            } catch (DAOException e) {
                throw new LogicException("Problems with addFaculty method", e);
            }
        }
        return faculty;
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
    public Speciality addSpeciality(Speciality speciality) throws LogicException {
        SpecialityDAO specialityDAO=mysqlFactory.getSpecialityDAO();
        try {
            specialityDAO.create(speciality);
        } catch (DAOException e) {
            throw new LogicException("Problems with addSpeciality method",e);
        }
        return speciality;
    }

    @Override
    public User findUserById(String userId) throws LogicException {
        DataValidator validator=new DataValidator();
        User user=null;
        if(validator.checkId(userId)){
            UserDAO userDAO=mysqlFactory.getUserDAO();
            Integer id=Integer.valueOf(userId);
            try {
                user= userDAO.findEntityById(id);

            } catch (DAOException e) {
                throw new LogicException("Problems with findUserById method",e);
            }
        }

        return user;
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
    public HashMap<String,String> addSpeciality(HashMap<String,String> parameters) throws LogicException {
        DataValidator validator=new DataValidator();
        HashMap<String,String> errorMap;
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
    public List<Speciality> findAllSpecialities() throws LogicException {
        List<Speciality> specialities=null;
        try {
           specialities=mysqlFactory.getSpecialityDAO().findAllEntity();
        } catch (DAOException e) {
            throw new LogicException("Problems with findAllSpecialities method",e);
        }
        return specialities;
    }

    @Override
    public List<User> findUsersRegisterOnSpeciality(String id) throws LogicException {
        DataValidator validator=new DataValidator();
        List<User> userList=null;
        if(validator.checkId(id)){
            try {
                Long specialityId=Long.valueOf(id);
                userList=mysqlFactory.getSpecialityDAO().findUsersOnSpeciality(specialityId);
            } catch (DAOException e) {
                throw new LogicException("Problems with findUsersRegisterOnFaculty method",e);
            }
        }
        return userList;
    }

    public Speciality findSpeciality(String id) throws LogicException {
        DataValidator validator=new DataValidator();
        Speciality speciality=null;
        if(validator.checkId(id)){
        Long specialityId=Long.valueOf(id);
            try {
                speciality=mysqlFactory.getSpecialityDAO().findEntityById(specialityId);
            } catch (DAOException e) {
                throw new LogicException("Problems with findUsersRegisterOnFaculty method",e);
            }
        }
        return speciality;
    }

    public Faculty findFacultyOnSpeciality(Speciality speciality) throws LogicException {
        Faculty faculty;
        try {
            faculty=mysqlFactory.getFacultyDAO().findEntityById(speciality.getFacultyId());
        } catch (DAOException e) {
            throw new LogicException("Problems with findFacultyOnSpeciality method",e);
        }
        return faculty;
    }
    @Override
    public Faculty findFaculty(String facultyId) throws LogicException {
        DataValidator validator=new DataValidator();
        Faculty faculty=null;
        if (validator.checkId(facultyId)) {
            long id=Long.valueOf(facultyId);
            try {
                faculty = mysqlFactory.getFacultyDAO().findEntityById(id);
            } catch (DAOException e) {
                throw new LogicException("Problems with findFaculty method",e);
            }
        }
        return faculty;
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
    public Speciality findSpecialityById(String specialityId) throws LogicException {
        DataValidator validator = new DataValidator();
        Speciality speciality = null;
        if (validator.checkId(specialityId)) {
            Long id = Long.valueOf(specialityId);
            try {
                speciality = mysqlFactory.getSpecialityDAO().findEntityById(id);
            } catch (DAOException e) {
                throw new LogicException("Problems with findSpecialityById method", e);
            }

        }
        return speciality;
    }
    @Override
    public HashMap<String, String> updateSpeciality(HashMap<String, String> parameters) throws LogicException {
        DataValidator validator=new DataValidator();
        HashMap<String,String> errorMap;
            try {
                errorMap=validator.checkSpecialtyData(parameters);
                if (errorMap.isEmpty()){
                Speciality speciality=findSpeciality(parameters.get(ParamConstant.SPECIALITY_ID));
                if(speciality!=null) {
                    speciality = new Speciality();
                    speciality.setSpecialityId(Long.parseLong(parameters.get(ParamConstant.SPECIALITY_ID)));
                    speciality.setSpecialityName(parameters.get(ParamConstant.SPECIALITY_NAME));
                    speciality.setRecruitmentPlan(Integer.parseInt(parameters.get(ParamConstant.RECRUITMENT_PLAN)));
                    speciality.setFacultyId(Long.parseLong(parameters.get(ParamConstant.FACULTY_ID)));
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
                    mysqlFactory.getSpecialityDAO().update(speciality);
                }
                }
                else {
                    errorMap.put(ParamConstant.SPECIALITY_ID,parameters.get(ParamConstant.SPECIALITY_ID));
                }
            } catch (DAOException e) {
                throw new LogicException("Problems with updateSpeciality method", e);
            }

        return errorMap;
    }

    @Override
    public List<User> findAllAcceptedUsersOnSpeciality(String specialityId) throws LogicException {
        try {
            List<User> users=findUsersRegisterOnSpeciality(specialityId);
            Speciality speciality=findSpeciality(specialityId);
            int recruitmentPlan=speciality.getRecruitmentPlan();
            if(users.size()<=recruitmentPlan){
                return users;
            }
            else {
                List<Integer> scores=new ArrayList<>();
                for (User user:users){
                    int score=calculateUserSumScore(user);
                    scores.add(score);
                }
                int acceptedScore=calculateSpecialityAcceptedScore(scores,recruitmentPlan);
                List<User> acceptedUsers=new ArrayList<>();
                for (User user:users){
                    int score=calculateUserSumScore(user);
                    if(score>=acceptedScore){
                        acceptedUsers.add(user);
                    }
                }
                return acceptedUsers;
            }
        } catch (LogicException e) {
            throw new LogicException("Problems with findAllAcceptedUsersOnSpeciality method", e);
        }

    }

    @Override
    public List<Faculty> findAllFaculty() throws LogicException {
        try {
            return mysqlFactory.getFacultyDAO().findAllEntity();
        } catch (DAOException e) {
            throw new LogicException("Problems with findAllFaculty method", e);
        }
    }

    @Override
    public List<Speciality> findSpecialitiesOnFaculty(String id) throws LogicException {
        List<Speciality> specialities= null;
        DataValidator validator=new DataValidator();
        if(validator.checkId(id)) {
            SpecialityDAO specialityDAO = mysqlFactory.getSpecialityDAO();
            Integer facultyId=Integer.valueOf(id);
            try {
                specialities = specialityDAO.findSpecialitiesByFacultyID(facultyId);
            } catch (DAOException e) {
                throw new LogicException("Problems with findSpecialitiesOnFaculty method", e);
            }
        }
        return specialities;
    }

    @Override
    public void canceledUserRegistration(long id) throws LogicException {
        User user=new User();
        user.setUserId(id);
        try {
            mysqlFactory.getUserDAO().clearUserScore(user);
        } catch (DAOException e) {
            throw new LogicException("Problems with canceledRegistration method", e);
        }

    }

    @Override
    public HashMap<String, String> updateRegisterOnSpecialityDate(String startDate, String endDate, String specialityId) throws LogicException {
        DataValidator validator=new DataValidator();
        HashMap<String,String> errorMap=validator.checkRegisterDate(startDate, endDate,specialityId);
        if(errorMap.isEmpty()){
            try {
                Speciality speciality=new Speciality();
                speciality.setSpecialityId(Long.valueOf(specialityId));
                speciality.setEndRegistration(DateConverter.getLocaleDateTime(startDate));
                speciality.setStartRegistration(DateConverter.getLocaleDateTime(endDate));
                mysqlFactory.getSpecialityDAO().updateSpecialityRegisterDate(speciality);
            }
             catch (DAOException e) {
                throw new LogicException("Problems with updateRegisterOnSpecialityDate");
            }
        }
        return errorMap;


    }

    private int calculateUserSumScore(User user){
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
