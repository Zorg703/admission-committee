package by.mordas.project.util;

import by.mordas.project.command.ParamConstant;
import by.mordas.project.dao.DAOException;
import by.mordas.project.dao.DAOFactory;
import by.mordas.project.dao.FacultyDAO;
import by.mordas.project.dao.UserDAO;
import by.mordas.project.entity.Faculty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 Author: Sergei Mordas
 Date: 20.04.2018
 ***/

public class DataValidator {
    private static Logger logger= LogManager.getRootLogger();
    private static final String FACULTY_SPECIALITY_NAME_REGEX="([А-Я]{1}([а-я]{2,50}(\\s)?)+)|[A-Z]{1}([a-z]{2,50}(\\s)?)+";
    private static final String ID_REGEX="[1-9](\\d){0,18}";
    private static final String FIRST_NAME_REGEX="[А-Я]{1}[а-я]{2,50}|[A-Z]{1}[a-z]{2,50}";
    private static final String LAST_NAME_REGEX="[А-Я]{1}[а-я]{2,50}|[A-Z]{1}[a-z]{2,50}";
    private static final String DATE_REGEX="(((19\\d\\d)|(200\\d)|(2010))-((0[1-9]|1[012])-(0[1-9]|[12]\\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31))";
    private static final String MARK_REGEX="[1-9]\\d?|100";
    private static final String LOGIN_REGEX="(\\w){3,30}";
    private static final String PASSWORD_REGEX="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}";
    private static final String EMAIL_REGEX="^[\\w]+[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private static final String SUBJECT_ID="[1-9]";
    private static final String RECRUITMENT_PLAN="[1-9]\\d{0,4}";
    private static final String LOGIN_BUSY ="login_busy";
    private static final String DATE_TIME_REGEX="(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2})";
    private static final String COUNTER_REGEX="\\d{0,6}";


    /**
     *Check user login contains in data base.
     *
     * @param login the login
     * @return true if it contains
     * @throws DAOException the DAO exception
     */
    private boolean checkLogin(String login) throws DAOException {
        DAOFactory mysqlFactory=DAOFactory.getFactory(DAOFactory.MySQL);
        UserDAO userDAO=mysqlFactory.getUserDAO();
        return userDAO.findUserByLogin(login);
    }

    /**
     * Check login and password for compliance with pattern
     *
     * @param login the login
     * @param password the password
     * @return true if password and login compliance
     */
    public boolean checkLoginPassword(String login,String password){
        return checkData(LOGIN_REGEX,login) && checkData(PASSWORD_REGEX,password);
    }

    /**
     * Check data for compliance with pattern
     *
     * @param REGEX is the regex
     * @param data is the data
     * @return true if compliance
     */
    private boolean checkData(final String REGEX, String data){
        if(data!=null) {
            Pattern loginPattern = Pattern.compile(REGEX);
            Matcher matcher = loginPattern.matcher(data);
            return matcher.matches();
        }
            return false;
    }

    /**
     *Validate user data
     *
     * @param parameterMap the param map
     * @return empty map if successful
     * @throws DAOException the DAO exception
     */
    public Map<String,String> checkUserData(Map<String,String> parameterMap) throws DAOException {
        Map<String,String> errorMessageMap=new HashMap<>();
        if(!checkData(FIRST_NAME_REGEX,parameterMap.get(ParamConstant.FIRST_NAME))){
            errorMessageMap.put(ParamConstant.FIRST_NAME,parameterMap.get(ParamConstant.FIRST_NAME));
        }
        if(!checkData(LAST_NAME_REGEX,parameterMap.get(ParamConstant.LAST_NAME))){
            errorMessageMap.put(ParamConstant.LAST_NAME,parameterMap.get(ParamConstant.LAST_NAME));
        }
        if(!checkData(LOGIN_REGEX,parameterMap.get(ParamConstant.LOG_IN))) {
            errorMessageMap.put(ParamConstant.LOG_IN, parameterMap.get(ParamConstant.LOG_IN));
        }
        if(checkData(LOGIN_REGEX,parameterMap.get(ParamConstant.LOG_IN)) && !checkLogin(parameterMap.get(ParamConstant.LOG_IN))) {
            errorMessageMap.put(LOGIN_BUSY,parameterMap.get(ParamConstant.LOG_IN));
        }
        if(!checkData(DATE_REGEX,parameterMap.get(ParamConstant.BIRTHDAY))){
            errorMessageMap.put(ParamConstant.BIRTHDAY,parameterMap.get(ParamConstant.BIRTHDAY));
        }
        if(!checkData(EMAIL_REGEX,parameterMap.get(ParamConstant.EMAIL))){
            errorMessageMap.put(ParamConstant.EMAIL,parameterMap.get(ParamConstant.EMAIL));
        }
        if(!checkData(PASSWORD_REGEX,parameterMap.get(ParamConstant.PASSWORD_ONE))){
            errorMessageMap.put(ParamConstant.PASSWORD_ONE,parameterMap.get(ParamConstant.PASSWORD_ONE));
        }
        if(checkData(PASSWORD_REGEX,parameterMap.get(ParamConstant.PASSWORD_ONE)) &&!parameterMap.get(ParamConstant.PASSWORD_ONE).equals(parameterMap.get(ParamConstant.PASSWORD_TWO))){
            errorMessageMap.put(ParamConstant.PASSWORD_TWO,parameterMap.get(ParamConstant.PASSWORD_TWO));
        }
        return errorMessageMap;
    }

    /**
     * Validate passwords
     *
     * @param password1 the password one
     * @param password2 the password two
     * @return empty map if successful
     */
    public HashMap<String,String> checkChangedPassword(String password1,String password2){
        HashMap<String,String> errorMap=new HashMap<>();
        if(!checkData(PASSWORD_REGEX,password1)){
            errorMap.put(ParamConstant.PASSWORD_ONE,password1);
        }
        if(checkData(PASSWORD_REGEX,password1) &&!password1.equals(password2)){
            errorMap.put(ParamConstant.PASSWORD_TWO,password2);
        }
        return errorMap;
    }

    /**
     * Validate user marks
     *
     * @param first the first mark
     * @param second the second mark
     * @param third the third mark
     * @param certificate the sertificate mark
     * @return true if validate
     */
    public boolean validateUserMarks(String first, String second, String third,String certificate) {
        return checkData(MARK_REGEX, first) && checkData(MARK_REGEX, second) && checkData(MARK_REGEX, third)&& checkData(MARK_REGEX,certificate) ;

    }

    /**
     * Check faculty or speciality name
     *
     * @param name the name
     * @return return true if name is correct
     */
    public boolean checkFacultyOrSpecialityName(String name){
        return checkData(FACULTY_SPECIALITY_NAME_REGEX,name);
    }


    /**
     *Check id
     *
     * @param id is the id
     * @return true if id is correct
     */
    public boolean checkId(String id){return checkData(ID_REGEX,id);}

    /**
     * Validate date of new speciality
     *
     * @param parameterMap the param map
     * @return empty map if it correct
     * @throws DAOException the DAO exception
     */
    public Map<String,String> checkSpecialtyData(Map<String,String> parameterMap) throws DAOException {
        Map<String,String> errorMessageMap=new HashMap<>();

        if(!checkId(parameterMap.get(ParamConstant.FACULTY_ID))){
            errorMessageMap.put(ParamConstant.FACULTY_ID,parameterMap.get(ParamConstant.FACULTY_ID));
        }
        if( checkId(parameterMap.get(ParamConstant.FACULTY_ID)) && !isContainsFaculty(Integer.parseInt(parameterMap.get(ParamConstant.FACULTY_ID)))){
            errorMessageMap.put(ParamConstant.FACULTY_ID,parameterMap.get(ParamConstant.FACULTY_ID));
        }
        if(!checkData(RECRUITMENT_PLAN,parameterMap.get(ParamConstant.RECRUITMENT_PLAN))){
            errorMessageMap.put(ParamConstant.RECRUITMENT_PLAN,parameterMap.get(ParamConstant.FACULTY_ID));
        }
        if(!checkFacultyOrSpecialityName(parameterMap.get(ParamConstant.SPECIALITY_NAME))){
            errorMessageMap.put(ParamConstant.SPECIALITY_NAME,parameterMap.get(ParamConstant.SPECIALITY_NAME));
        }
        if(!checkData(SUBJECT_ID,parameterMap.get(ParamConstant.FIRST_SUBJECT))){
            errorMessageMap.put(ParamConstant.FIRST_SUBJECT,parameterMap.get(ParamConstant.FIRST_SUBJECT));
        }
        if(!checkData(SUBJECT_ID,parameterMap.get(ParamConstant.SECOND_SUBJECT))){
            errorMessageMap.put(ParamConstant.SECOND_SUBJECT,parameterMap.get(ParamConstant.SECOND_SUBJECT));
        }
        if(!checkData(SUBJECT_ID,parameterMap.get(ParamConstant.THIRD_SUBJECT))){
            errorMessageMap.put(ParamConstant.THIRD_SUBJECT,parameterMap.get(ParamConstant.THIRD_SUBJECT));
        }
        if(parameterMap.get(ParamConstant.SECOND_SUBJECT)!=null && parameterMap.get(ParamConstant.THIRD_SUBJECT)!=null) {
            if (parameterMap.get(ParamConstant.FIRST_SUBJECT).equals(parameterMap.get(ParamConstant.SECOND_SUBJECT)) ||
                    parameterMap.get(ParamConstant.FIRST_SUBJECT).equals(parameterMap.get(ParamConstant.THIRD_SUBJECT)) ||
                    parameterMap.get(ParamConstant.SECOND_SUBJECT).equals(parameterMap.get(ParamConstant.THIRD_SUBJECT))) {
                errorMessageMap.put(ParamConstant.SUBJECTS, ParamConstant.SUBJECTS);
            }
        }
        if(!checkData(DATE_TIME_REGEX,parameterMap.get(ParamConstant.START_REGISTRATION))){
            errorMessageMap.put(ParamConstant.START_REGISTRATION,parameterMap.get(ParamConstant.START_REGISTRATION));
        }
        if(!checkData(DATE_TIME_REGEX,parameterMap.get(ParamConstant.END_REGISTRATION))){
            errorMessageMap.put(ParamConstant.END_REGISTRATION,parameterMap.get(ParamConstant.END_REGISTRATION));
        }
        if(checkData(DATE_TIME_REGEX,parameterMap.get(ParamConstant.START_REGISTRATION)) && checkData(DATE_TIME_REGEX,parameterMap.get(ParamConstant.END_REGISTRATION))){
            LocalDateTime startDateTime=DateConverter.getLocaleDateTime(parameterMap.get(ParamConstant.START_REGISTRATION));
            LocalDateTime endDateTime=DateConverter.getLocaleDateTime(parameterMap.get(ParamConstant.END_REGISTRATION));
            if(endDateTime.isBefore(startDateTime)){
                errorMessageMap.put(ParamConstant.END_REGISTRATION,parameterMap.get(ParamConstant.END_REGISTRATION));
            }
        }
        return errorMessageMap;
    }

    /**
     * Validate date registration on speciality
     *
     * @param start the start registration date
     * @param end the end registration date
     * @param specialityId the speciality id
     * @return empty map if it validate
     */
    public Map<String,String> checkRegisterDate(String start,String end,String specialityId){
        Map<String,String> errorMap=new HashMap<>();
        if(!checkId(specialityId)){
            errorMap.put(ParamConstant.SPECIALITY_ID,specialityId);
        }
        if(!checkData(DATE_TIME_REGEX,start)){
            errorMap.put(ParamConstant.START_REGISTRATION,start);
        }
        if(!checkData(DATE_TIME_REGEX,end)){
            errorMap.put(ParamConstant.END_REGISTRATION,end);
        }
        if(checkData(DATE_TIME_REGEX,start) && checkData(DATE_TIME_REGEX,end)){
            LocalDateTime startDateTime=DateConverter.getLocaleDateTime(start);
            LocalDateTime endDateTime=DateConverter.getLocaleDateTime(start);
            if(endDateTime.isBefore(startDateTime)){
                errorMap.put(ParamConstant.END_REGISTRATION,end);
            }
        }
        return errorMap;
    }

    /**
     * Check page counter
     *
     * @param counter is the counter
     * @return true if it correct
     */
    public boolean checkCounter(String counter){
        return checkData(COUNTER_REGEX, counter);
    }

    private boolean isContainsFaculty(int id) throws DAOException {
        DAOFactory mysqlFactory=DAOFactory.getFactory(DAOFactory.MySQL);

            FacultyDAO facultyDAO=mysqlFactory.getFacultyDAO();
            List<Faculty> faculties=facultyDAO.findAllEntity();
            for(Faculty faculty:faculties){
                if(faculty.getFacultyId()==id){
                    return true;
                }
            }

        return false;
    }

}
