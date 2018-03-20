package by.mordas.project.util;

import by.mordas.project.command.ParamConstant;
import by.mordas.project.logic.UserLogic;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {


    private static final String FIRST_NAME_REGEX="[А-Я]{1}[а-я]{2,50}|[A-Z]{1}[a-z]{2,50}";
    private static final String LAST_NAME_REGEX="[А-Я]{1}[а-я]{2,50}|[A-Z]{1}[a-z]{2,50}";
    private static final String DATE_REGEX="(((19\\d\\d)|(200\\d)|(2010))-((0[1-9]|1[012])-(0[1-9]|[12]\\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31))";
    private static final String MARK_REGEX="[1-9]\\d?|100";
    private static final String LOGIN_REGEX="^[a-zA-Z][a-zA-Z0-9-_]{4,30}";
    private static final String PASSWORD_REGEX="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,50}";
    private static final String EMAIL_REGEX="^[\\w]+[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private static final String SUBJECT_ID="[1-9]";
    private static final String LOGIN_BUSY ="login_busy";

    public boolean checkLogin(String login){
        UserLogic userLogic=new UserLogic();
        boolean isLoginFree=false;
        Pattern loginPattern=Pattern.compile(LOGIN_REGEX);
        Matcher matcher=loginPattern.matcher(login);
        if(matcher.matches()){
           isLoginFree=userLogic.findUserByLogin(login);
        }
        return isLoginFree;
    }

    public boolean checkPassword(String password){
        Pattern loginPattern=Pattern.compile(PASSWORD_REGEX);
        Matcher matcher=loginPattern.matcher(password);
        return matcher.matches();
    }
    public boolean checkывPassword(String password){
        Pattern loginPattern=Pattern.compile(FIRST_NAME_REGEX);
        Matcher matcher=loginPattern.matcher(password);
        return matcher.matches();
    }

    private boolean checkData(final String REGEX, String date){
        Pattern loginPattern=Pattern.compile(REGEX);
        Matcher matcher=loginPattern.matcher(date);
        return matcher.matches();
    }


    public HashMap<String,String> checkUserDate(HashMap<String,String> parameterMap){
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
        if(!checkData(MARK_REGEX,parameterMap.get(ParamConstant.CERTIFICATE_AVG))){
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
        if(!checkData(SUBJECT_ID,parameterMap.get(ParamConstant.FIRST_SUBJECT))){
            errorMessageMap.put(ParamConstant.FIRST_SUBJECT,parameterMap.get(ParamConstant.FIRST_SUBJECT));
        }
        if(!checkData(SUBJECT_ID,parameterMap.get(ParamConstant.SECOND_SUBJECT))){
            errorMessageMap.put(ParamConstant.SECOND_SUBJECT,parameterMap.get(ParamConstant.SECOND_SUBJECT));
        }
        if(!checkData(SUBJECT_ID,parameterMap.get(ParamConstant.THIRD_SUBJECT))){
            errorMessageMap.put(ParamConstant.THIRD_SUBJECT,parameterMap.get(ParamConstant.THIRD_SUBJECT));
        }
        if(!checkData(EMAIL_REGEX,parameterMap.get(ParamConstant.EMAIL))){
            errorMessageMap.put(ParamConstant.EMAIL,parameterMap.get(ParamConstant.EMAIL));
        }
        if(!checkPassword(parameterMap.get(ParamConstant.PASSWORD_ONE))){
            errorMessageMap.put(ParamConstant.PASSWORD_ONE,parameterMap.get(ParamConstant.PASSWORD_ONE));
        }
        if(checkPassword(parameterMap.get(ParamConstant.PASSWORD_ONE)) &&!parameterMap.get(ParamConstant.PASSWORD_ONE).equals(parameterMap.get(ParamConstant.PASSWORD_TWO))){
            errorMessageMap.put(ParamConstant.PASSWORD_TWO,parameterMap.get(ParamConstant.PASSWORD_TWO));
        }
        if(parameterMap.get(ParamConstant.FIRST_SUBJECT).equals(parameterMap.get(ParamConstant.SECOND_SUBJECT)) ||
                parameterMap.get(ParamConstant.FIRST_SUBJECT).equals(parameterMap.get(ParamConstant.THIRD_SUBJECT)) ||
                parameterMap.get(ParamConstant.SECOND_SUBJECT).equals(parameterMap.get(ParamConstant.THIRD_SUBJECT))){
            errorMessageMap.put(ParamConstant.SUBJECTS,ParamConstant.SUBJECTS);
        }


            return errorMessageMap;
    }
}
