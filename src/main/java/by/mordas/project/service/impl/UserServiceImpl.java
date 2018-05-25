package by.mordas.project.service.impl;

import by.mordas.project.command.ParamConstant;
import by.mordas.project.dao.DAOException;
import by.mordas.project.dao.DAOFactory;
import by.mordas.project.dao.UserDAO;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;
import by.mordas.project.service.LogicException;
import by.mordas.project.service.UserService;
import by.mordas.project.service.factory.ServiceFactory;
import by.mordas.project.util.DataValidator;
import by.mordas.project.util.PasswordEncoder;

import java.sql.Date;
import java.util.*;

public class UserServiceImpl  implements UserService {
    private DAOFactory mysqlFactory=DAOFactory.getFactory(DAOFactory.MySQL);

    @Override
    public Optional<List<User>> findAllUser() throws LogicException {
        Optional<List<User>> optional;
        UserDAO userDAO=mysqlFactory.getUserDAO();
        try {
            List<User> userList=userDAO.findAllEntity();
            optional=Optional.ofNullable(userList);
        } catch (DAOException e) {

            throw new LogicException("Problems with findAllUser method",e);
        }
        return optional;
    }

    @Override
    public Optional<User> findUserById(String userId) throws LogicException {
        Optional<User> optional=Optional.empty();
        DataValidator validator=new DataValidator();
        if(validator.checkId(userId)){
            UserDAO userDAO=mysqlFactory.getUserDAO();
            Integer id=Integer.valueOf(userId);
            try {
                User user= userDAO.findEntityById(id);
                optional=Optional.ofNullable(user);

            } catch (DAOException e) {
                throw new LogicException("Problems with findUserById method",e);
            }
        }

        return optional;
    }

    @Override
    public Optional<List<User>> findUsersRegisterOnSpeciality(String id) throws LogicException {
        Optional<List<User>> optional=Optional.empty();
        DataValidator validator=new DataValidator();
        if(validator.checkId(id)){
            try {
               Long specialityId=Long.valueOf(id);
               List<User> userList=mysqlFactory.getSpecialityDAO().findUsersOnSpeciality(specialityId);
               optional=Optional.ofNullable(userList);
           } catch (DAOException e) {
               throw new LogicException("Problems with findUsersRegisterOnFaculty method",e);
           }
        }
        return optional;
    }

    @Override
    public Optional<List<User>> findAllAcceptedUsersOnSpeciality(String specialityId) throws LogicException {
        Optional<List<User>> optionalUsers = findUsersRegisterOnSpeciality(specialityId);
        Optional<Speciality> optionalSpeciality = ServiceFactory.getInstance().getSpecialityService().findSpecialityById(specialityId);
        Optional<List<User>> optional=Optional.empty();
        if (optionalSpeciality.isPresent() && optionalUsers.isPresent()) {
                Speciality speciality = optionalSpeciality.get();
                List<User> users=optionalUsers.get();
                int recruitmentPlan = speciality.getRecruitmentPlan();
                if (users.size() <= recruitmentPlan) {
                    return optionalUsers;
                } else {
                    List<Integer> scores = new ArrayList<>();
                    for (User user : users) {
                        int score = calculateUserSumScore(user);
                        scores.add(score);
                    }
                    int acceptedScore = definePassingScore(speciality);
                    List<User> acceptedUsers = new ArrayList<>();
                    for (User user : users) {
                        int score = calculateUserSumScore(user);
                        if (score >= acceptedScore) {
                            acceptedUsers.add(user);
                        }
                    }
                    return Optional.of(acceptedUsers);
                }


        }


        return optional;
    }

    @Override
    public Map<String, String> changePassword(Long userId, String password1, String password2) throws LogicException {
        DataValidator validator=new DataValidator();
        UserDAO userDAO=mysqlFactory.getUserDAO();
        HashMap<String,String> errorMap=validator.checkChangedPassword(password1,password2);
        if(errorMap.isEmpty()) {
            try{
                password1 = PasswordEncoder.encodePassword(password1);
                userDAO.changeUserPassword(userId, password1);
            } catch (DAOException e) {
                throw new LogicException("Problems with changePassword method", e);
            }
        }
        return errorMap;
    }

    @Override
    public User setUserSpeciality(User user, Long specialityId, HashMap<String, String> parameters) throws LogicException {
        DataValidator validator = new DataValidator();
        String userFirstMark = parameters.get(ParamConstant.FIRST_SUBJECT_MARK);
        String userSecondMark = parameters.get(ParamConstant.SECOND_SUBJECT_MARK);
        String userThirdMark = parameters.get(ParamConstant.THIRD_SUBJECT_MARK);
        String userCertificate = parameters.get(ParamConstant.CERTIFICATE_AVG);
        if (validator.validateUserMarks(userFirstMark, userSecondMark, userThirdMark, userCertificate)) {
            UserDAO userDAO = mysqlFactory.getUserDAO();
            try {
                user.setSpecialityId(specialityId);
                user.setCertificateMark(Integer.valueOf(userCertificate));
                Subject subject1 = new Subject();
                subject1.setSubjectId(Integer.valueOf(parameters.get(ParamConstant.FIRST_SUBJECT)));
                user.put(subject1, Integer.valueOf(userFirstMark));
                Subject subject2 = new Subject();
                subject2.setSubjectId(Integer.valueOf(parameters.get(ParamConstant.SECOND_SUBJECT)));
                user.put(subject2, Integer.valueOf(userSecondMark));
                Subject subject3 = new Subject();
                subject3.setSubjectId(Integer.valueOf(parameters.get(ParamConstant.THIRD_SUBJECT)));
                user.put(subject3, Integer.valueOf(userThirdMark));
                userDAO.updateUserSpeciality(user);
                return user;
            } catch (DAOException e) {
                throw new LogicException("Problems with setUserSpeciality method", e);
            }
        }
        return user;


    }
    @Override
    public boolean isAccepted(Speciality speciality, User user) throws LogicException {

            return calculateUserAvgScore(user) >= definePassingScore(speciality);


    }

    @Override
    public User canceledUserRegistration(User user) throws LogicException {
        try {
            mysqlFactory.getUserDAO().clearUserScore(user);
            user.setSpecialityId(0);
            return user;
        } catch (DAOException e) {
            throw new LogicException("Problems with canceledRegistration method", e);
        }
    }

    @Override
    public Optional<User> findUserLoginAndPassword(String login, String password) throws LogicException {
        UserDAO userDAO=mysqlFactory.getUserDAO();
        DataValidator validator=new DataValidator();
        Optional<User> optional= Optional.empty();
        try {
            if(validator.checkLoginPassword(login,password )/*&& findLogin(login)*/) {
                password= PasswordEncoder.encodePassword(password);
                User user = userDAO.findUserByPasswordAndLogin(login,password);
                optional=Optional.ofNullable(user);
            }
        } catch (DAOException e) {
            throw new LogicException("Problems with findUserByPasswordAndLogin method",e);
        }
        return optional;
    }

    @Override
    public Map<String,String> registerUser(Map<String, String> parameters) throws LogicException {
        DataValidator validator=new DataValidator();
        UserDAO userDAO = mysqlFactory.getUserDAO();
        Map<String,String> errorMap;
        try {
            errorMap=validator.checkUserData(parameters);
            if(errorMap.isEmpty()) {
                User user = new User();
                user.setFirstName(parameters.get(ParamConstant.FIRST_NAME));
                user.setLastName(parameters.get(ParamConstant.LAST_NAME));
                user.setBirthday(Date.valueOf(parameters.get(ParamConstant.BIRTHDAY)));
                user.setEmail(parameters.get(ParamConstant.EMAIL));
                user.setPassword(parameters.get(ParamConstant.PASSWORD_ONE));
                user.setLogin(parameters.get(ParamConstant.LOG_IN));
                user.setPassword(PasswordEncoder.encodePassword(user.getPassword()));
                userDAO.create(user);
            }
        } catch (DAOException e) {
            throw new LogicException("Problems with registerUser method", e);
        }

        return errorMap;
    }


    @Override
    public Optional<List<User>> findUserLimitCount(String count) throws LogicException {
        Optional<List<User>> optionalUsers=Optional.empty();
        DataValidator dataValidator =new DataValidator();
        if(dataValidator.checkCounter(count)){
            int counter=Integer.parseInt(count)*10;
            try {
                List<User> userList=mysqlFactory.getUserDAO().findUsersWithLimit(counter);
                optionalUsers=Optional.ofNullable(userList);
            } catch (DAOException e) {
                throw new LogicException("Problems with findUserLimitCount method", e);
            }
        }
        return optionalUsers;
    }

    @Override
    public int definePassingScore(Speciality speciality) throws LogicException {
        try {
            List<Integer> scores = mysqlFactory.getSpecialityDAO().defineUsersSumScoreRegisterOnSpeciality(speciality.getSpecialityId());
            int counter=0;
            for (Integer score:scores){
                counter+=score;
            }
            return counter/speciality.getRecruitmentPlan();
        } catch (DAOException e) {
            throw new LogicException("Problems with define passing score  method");
        }
    }


    private int calculateUserSumScore(User user){
        int score=user.getCertificateMark();
        for (Map.Entry<Subject,Integer> entry:user.getSubjectMark().entrySet()){
            score+=entry.getValue();
        }
        return score;
    }


    private int calculateUserAvgScore(User user){
        int score=user.getCertificateMark();
        for (Map.Entry<Subject,Integer> entry:user.getSubjectMark().entrySet()){
            score+=entry.getValue();
        }
        return score;
    }

}
