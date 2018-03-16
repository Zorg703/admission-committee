package by.mordas.project.util;

import by.mordas.project.logic.UserLogic;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String FIRST_NAME_REGEX="[А-Я]{1}[а-я]{2,50}|[A-Z]{1}[a-z]{2,50}";
    private static final String LAST_NAME_REGEX="[А-Я]{1}[а-я]{2,50}|[A-Z]{1}[a-z]{2,50}";
    private static final String DATE_REGEX="(((19\\d\\d)|(200\\d)|(2010))-((0[1-9]|1[012])-(0[1-9]|[12]\\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31))";
    private static final String MARK_REGEX="[1-9]\\d|100";
    private static final String LOGIN_REGEX="^[a-zA-Z][a-zA-Z0-9-_]{4,30}";
    private static final String PASSWORD_REGEX="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,50}";
    private static final String EMAIL_REGEX="^[\\w]+[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";


    public boolean checkLogin(String login){
        boolean isLoginFree=false;
        Pattern loginPattern=Pattern.compile(LOGIN_REGEX);
        Matcher matcher=loginPattern.matcher(login);
        if(matcher.find()){
           isLoginFree= new UserLogic().findUserByLogin(login);
        }
        return false;
    }

    public boolean checkFirstName(String firstName){
        Pattern loginPattern=Pattern.compile(FIRST_NAME_REGEX);
        Matcher matcher=loginPattern.matcher(firstName);
        return matcher.find();
    }
    public boolean checkLastName(String lastName){
        Pattern loginPattern=Pattern.compile(LAST_NAME_REGEX);
        Matcher matcher=loginPattern.matcher(lastName);
        return matcher.find();
    }

    public boolean checkDate(Date date){
        Pattern loginPattern=Pattern.compile(DATE_REGEX);
        Matcher matcher=loginPattern.matcher(date.toString());
        return matcher.find();
    }

    public boolean checkPassword(String password){
        Pattern loginPattern=Pattern.compile(PASSWORD_REGEX);
        Matcher matcher=loginPattern.matcher(password);
        return matcher.find();
    }
    public boolean checkEmail(String email){
        Pattern loginPattern=Pattern.compile(EMAIL_REGEX);
        Matcher matcher=loginPattern.matcher(email);
        return matcher.find();
    }

    public boolean checkMark(Integer mark){
        Pattern loginPattern=Pattern.compile(MARK_REGEX);
        Matcher matcher=loginPattern.matcher(mark.toString());
        return matcher.find();
    }
}
