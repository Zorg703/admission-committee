package by.mordas.project.util;

import by.mordas.project.command.ParamConstant;
import by.mordas.project.logic.LogicException;
import by.mordas.project.logic.impl.UserLogicImpl;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {

    private static final String FACULTY_SPECIALITY_NAME_REGEX="([А-Я]{1}([а-я]{2,50}(\\s)?)+)|[A-Z]{1}([a-z]{2,50}(\\s)?)+";
    private static final String ID_REGEX="[1-9]\\d*";
    private static final String FIRST_NAME_REGEX="[А-Я]{1}[а-я]{2,50}|[A-Z]{1}[a-z]{2,50}";
    private static final String LAST_NAME_REGEX="[А-Я]{1}[а-я]{2,50}|[A-Z]{1}[a-z]{2,50}";
    private static final String DATE_REGEX="(((19\\d\\d)|(200\\d)|(2010))-((0[1-9]|1[012])-(0[1-9]|[12]\\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31))";
    private static final String MARK_REGEX="[1-9]\\d?|100";
    private static final String LOGIN_REGEX="^[a-zA-Z][a-zA-Z0-9-_]{4,30}";
    private static final String PASSWORD_REGEX="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,50}";
    private static final String EMAIL_REGEX="^[\\w]+[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private static final String SUBJECT_ID="[1-9]";
    private static final String LOGIN_BUSY ="login_busy";
    /*private void xc(String data){
        Pattern loginPattern = Pattern.compile(f);
        Matcher matcher = loginPattern.matcher(data);
    }*/
    private static boolean checkLogin(String login){
        UserLogicImpl userLogicImpl =new UserLogicImpl();
        boolean isLoginFree=false;
        try {
            isLoginFree= userLogicImpl.findUserByLogin(login);//todo
        } catch (LogicException e) {
            e.printStackTrace();
        }
        return isLoginFree;
    }

    public boolean checkLoginPassword(String login,String password){
        return checkData(LOGIN_REGEX,login) && checkData(PASSWORD_REGEX,password);
    }

    private static boolean checkData(final String REGEX, String data){
        if(data!=null) {
            Pattern loginPattern = Pattern.compile(REGEX);
            Matcher matcher = loginPattern.matcher(data);
            return matcher.matches();
        }
        else
        {
            return false;
        }
    }

    public HashMap<String,String> checkUserData(HashMap<String,String> parameterMap){
        HashMap<String,String> errorMessageMap=new HashMap<>();
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
            errorMessageMap.put(LOGIN_BUSY,LOGIN_BUSY);
        }
        if(!checkData(DATE_REGEX,parameterMap.get(ParamConstant.BIRTHDAY))){
            errorMessageMap.put(ParamConstant.BIRTHDAY,parameterMap.get(ParamConstant.BIRTHDAY));
        }
        /*if(!checkData(MARK_REGEX,parameterMap.get(ParamConstant.CERTIFICATE_AVG))){
            errorMessageMap.put(ParamConstant.CERTIFICATE_AVG,parameterMap.get(ParamConstant.CERTIFICATE_AVG));
        }
        if(!checkData(MARK_REGEX,parameterMap.get(ParamConstant.FIRST_SUBJECT_MARK))){
            errorMessageMap.put(ParamConstant.FIRST_SUBJECT_MARK,parameterMap.get(ParamConstant.FIRST_SUBJECT_MARK));
        }
        if(!checkData(MARK_REGEX,parameterMap.get(ParamConstant.SECOND_SUBJECT_MARK))){
            errorMessageMap.put(ParamConstant.SECOND_SUBJECT_MARK,parameterMap.get(ParamConstant.SECOND_SUBJECT_MARK));
        }
        if(!checkData(MARK_REGEX,parameterMap.get(ParamConstant.THIRD_SUBJECT_MARK))){
            errorMessageMap.put(ParamConstant.THIRD_SUBJECT_MARK,parameterMap.get(ParamConstant.THIRD_SUBJECT_MARK));
        }

        */
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
    public boolean validateUserMarks(String first, String second, String third) {
        return checkData(MARK_REGEX, first) && !checkData(MARK_REGEX, second) && checkData(MARK_REGEX, third);

    }

    public boolean checkFacultyOrSpecialityName(String name){
        return checkData(FACULTY_SPECIALITY_NAME_REGEX,name);
    }


    public boolean checkId(String id){return checkData(ID_REGEX,id);}

    public HashMap<String,String> checkSpecialtyData(HashMap<String,String> parameterMap){
        HashMap<String,String> errorMessageMap=new HashMap<>();

        if(!checkId(parameterMap.get(ParamConstant.FACULTY_ID))){
            errorMessageMap.put(ParamConstant.FACULTY_ID,parameterMap.get(ParamConstant.FACULTY_ID));
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
        return errorMessageMap;
    }
}
