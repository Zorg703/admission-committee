package by.mordas.admissioncommittee.util;

import by.mordas.project.command.ParamConstant;
import by.mordas.project.dao.DAOException;
import by.mordas.project.util.DataValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/***
 Author: Sergei Mordas
 Date: 20.05.2018
 ***/

@Test
public class TestDataValidator {
    private DataValidator dataValidator;

    private static final Map<String, String> goodUserParameters;
    static {
        goodUserParameters = new HashMap<String, String>();
        goodUserParameters.put(ParamConstant.USER_ID, "1");
        goodUserParameters.put(ParamConstant.BIRTHDAY, "1990-03-07");
        goodUserParameters.put(ParamConstant.FIRST_NAME, "Sergey");
        goodUserParameters.put(ParamConstant.LAST_NAME,"Mordas");
        goodUserParameters.put(ParamConstant.LOG_IN, "Super_user");
        goodUserParameters.put(ParamConstant.EMAIL, "sergei.mordas@mail.ru");
        goodUserParameters.put(ParamConstant.PASSWORD_ONE, "Astra123");
        goodUserParameters.put(ParamConstant.PASSWORD_TWO, "Astra123");

    }

    private static final Map<String, String> badUserParameters;
    static {
        badUserParameters = new HashMap<String, String>();
        badUserParameters.put(ParamConstant.USER_ID, "-1");
        goodUserParameters.put(ParamConstant.LOG_IN, "***");
        badUserParameters.put(ParamConstant.BIRTHDAY, "3000-09-57");
        badUserParameters.put(ParamConstant.FIRST_NAME, "1234");
        badUserParameters.put(ParamConstant.LAST_NAME,"s");
        badUserParameters.put(ParamConstant.EMAIL, "@mail.ru");
        badUserParameters.put(ParamConstant.PASSWORD_ONE, "мой пароль");
        badUserParameters.put(ParamConstant.PASSWORD_TWO, "чужой пароль");

    }

    private static final Map<String, String> goodSpecialityParameters;
    static {
        goodSpecialityParameters=new HashMap<>();
        goodSpecialityParameters.put(ParamConstant.SPECIALITY_NAME,"Best speciality");
        goodSpecialityParameters.put(ParamConstant.RECRUITMENT_PLAN,"10");
        goodSpecialityParameters.put(ParamConstant.FACULTY_ID,"1");
        goodSpecialityParameters.put(ParamConstant.FIRST_SUBJECT,"1");
        goodSpecialityParameters.put(ParamConstant.SECOND_SUBJECT,"2");
        goodSpecialityParameters.put(ParamConstant.THIRD_SUBJECT,"3");
        goodSpecialityParameters.put(ParamConstant.START_REGISTRATION,"2018-02-03T11:00");
        goodSpecialityParameters.put(ParamConstant.END_REGISTRATION,"2018-07-02T00:00");
    }

    private static final Map<String, String> badSpecialityParameters;
    static {
        badSpecialityParameters=new HashMap<>();
        badSpecialityParameters.put(ParamConstant.SPECIALITY_NAME,"Ц");
        badSpecialityParameters.put(ParamConstant.RECRUITMENT_PLAN,"100000000");
        badSpecialityParameters.put(ParamConstant.FACULTY_ID,"100");
        badSpecialityParameters.put(ParamConstant.FIRST_SUBJECT,"-1");
        badSpecialityParameters.put(ParamConstant.SECOND_SUBJECT,"-1");
        badSpecialityParameters.put(ParamConstant.THIRD_SUBJECT,"-");
        badSpecialityParameters.put(ParamConstant.START_REGISTRATION,"assw-a5-vvT77:/*");
        badSpecialityParameters.put(ParamConstant.END_REGISTRATION,"2000--7-ssT47:;;");
    }

        @BeforeMethod
    public void setDataValidator(){
        dataValidator=new DataValidator();
    }

    @DataProvider
    public Object[][] goodIdData(){
        return new Object[][]{{"10"},{"1"},{"5588897"},{"33"},{"45"}};
    }


    @DataProvider
    public Object[][] badIdData(){
        return new Object[][]{{"-1"},{"001"},{"asd"},{"dfg"},{"=-*"},{"Сва"},{null}};
    }



    @Test(dataProvider = "goodIdData")
    public void testValidateIdGood(String id){
        boolean result=dataValidator.checkId(id);
        Assert.assertTrue(result);
    }

    @Test(dataProvider = "badIdData")
    public void testValidateIdBad(String id){
        boolean result=dataValidator.checkId(id);
        Assert.assertFalse(result);
    }
    @Test
    public void testGoodUserValidate() throws DAOException {

           Map<String,String> errorMap= dataValidator.checkUserData(goodUserParameters);
           int sizeMap=0;
           Assert.assertEquals(sizeMap,errorMap.size());

    }

    @Test
    public void testBadUserValidate() throws DAOException {

        Map<String,String> errorMap= dataValidator.checkUserData(badUserParameters);
        int sizeMap=6;
        Assert.assertEquals(sizeMap,errorMap.size());

    }

    @Test
    public void testBadSpecialityValidate() throws DAOException {

        Map<String,String> errorMap= dataValidator.checkSpecialtyData(badSpecialityParameters);
        int sizeMap=9;
        Assert.assertEquals(sizeMap,errorMap.size());

    }

    @Test
    public void testGoodSpecialityValidate() throws DAOException {

        Map<String,String> errorMap= dataValidator.checkSpecialtyData(goodSpecialityParameters);
        int sizeMap=0;
        Assert.assertEquals(sizeMap,errorMap.size());

    }


}
