package by.mordas.project.dao.impl;

import by.mordas.project.dao.DAOException;
import by.mordas.project.dao.SpecialityDAO;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;
import by.mordas.project.pool.ConnectionPool;
import by.mordas.project.pool.DBConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SpecialityDAOImpl implements SpecialityDAO {
    private static final Logger logger= LogManager.getRootLogger();
    private static final String FIND_ALL_SPECIALITY="SELECT ID,SPECIALITY_NAME,RECRUITMENT_PLAN,FACULTY_ID FROM SPECIALITY";
    private static final String FIND_SPECIALITY_BY_ID="SELECT ID,SPECIALITY_NAME,RECRUITMENT_PLAN,FACULTY_ID FROM SPECIALITY WHERE ID=?";
    private static final String CREATE_SPECIALITY="INSERT INTO SPECIALITY(SPECIALITY_NAME,RECRUITMENT_PLAN,FACULTY_ID) VALUES(?,?,?,?)";
    private static final String UPDATE_SPECIALITY="UPDATE SPECIALITY SET ID=? SPECIALITY_NAME=?,RECRUITMENT_PLAN=?,FACULTY_ID=?";
    private static final String UPDATE_SPECIALITY_SUBJECT="UPDATE SUBJECT_FOR_SPECIALITY SET ID_SPECIALITY=?,ID_SUBJECT=?";
    private static final String DELETE_SPECIALITY="DELETE FROM SPECIALITY WHERE ID=?";
    private static final String FIND_ALL_SPECIALITY_BY_FACULTY_ID="SELECT SPECIALITY.ID,SPECIALITY_NAME,RECRUITMENT_PLAN, FACULTY_ID FROM SPECIALITY WHERE FACULTY_ID=?";
    private static final String FIND_ALL_USERS_ON_SPECIALITY_BY_ID ="SELECT ID,FIRST_NAME,LAST_NAME,BIRTHDAY,CERTIFICATE_AVG," +
            "SPECIALITY_ID FROM USER  WHERE SPECIALITY ID=?";
    private static final String INSERT_SPECIALITY_SUBJECTS="INSERT INTO SUBJECT_FOR_SPECIALITY(ID_SPECIALITY,ID_SUBJECT)";
//    private static final String FIND_ALL_SPECIALITY_BY_FACULTY_ID="SELECT SPECIALITY.ID,SPECIALITY_NAME,RECRUITMENT_PLAN, FACULTY_ID FROM SPECIALITY" +
//
// 30          " INNER JOIN FACULTY ON SPECIALITY.FACULTY_ID=FACULTY.ID AND FACULTY.ID=?";

    @Override
    public List<Speciality> findAllEntity() throws DAOException {

        List<Speciality> specialties=new ArrayList<>();
        try( DBConnection connection= ConnectionPool.getInstance().getConnection();Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(FIND_ALL_SPECIALITY)) {
            if (rs != null) {
                while (rs.next()) {
                    Speciality speciality=new Speciality();
                    setSpeciality(rs,speciality);
                }
            }
        } catch (SQLException e) {
           throw new DAOException();
        }

        return specialties;

    }

    @Override
    public Speciality findEntityById(int id) throws DAOException {

        Speciality speciality =new Speciality();
        try(DBConnection conn= ConnectionPool.getInstance().getConnection();PreparedStatement pStatement=conn.prepareStatement(FIND_SPECIALITY_BY_ID);
            ) {
            pStatement.setInt(1,id);
            ResultSet rs=pStatement.executeQuery();
            if(rs!=null){

                setSpeciality(rs,speciality);
            }
        } catch (SQLException e) {
            throw new DAOException();
        }
        return speciality;
    }

    public List<Speciality> findSpecialitiesByFacultyID(int id) throws DAOException {

        List<Speciality> specialities=new ArrayList<>();
        try(DBConnection connection=ConnectionPool.getInstance().getConnection();PreparedStatement pStatement=connection.prepareStatement(FIND_ALL_SPECIALITY_BY_FACULTY_ID);
        ) {
            pStatement.setInt(1,id);
            ResultSet rs=pStatement.executeQuery();
            if(rs!=null){
                while (rs.next()) {
                    Speciality speciality=new Speciality();
                    setSpeciality(rs,speciality);
                    specialities.add(speciality);
                }
                }
        } catch (SQLException e) {
            throw new DAOException();
        }

        return specialities;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try (DBConnection connection=ConnectionPool.getInstance().getConnection();PreparedStatement pStatement = connection.prepareStatement(DELETE_SPECIALITY)) {
            pStatement.setInt(1,id);
            return pStatement.executeUpdate()==4;
        } catch (SQLException e) {
            throw new DAOException();
        }

    }

    @Override
    public void create(Speciality specialty) throws DAOException {
        PreparedStatement pStatement=null;
        try(DBConnection connection=ConnectionPool.getInstance().getConnection()){
            pStatement=connection.prepareStatement(CREATE_SPECIALITY);
            pStatement.setString(2,specialty.getSpecialityName());
            pStatement.setInt(3,specialty.getRecruitmentPlan());
            pStatement.setInt(4,specialty.getFacultyId());
            ResultSet resultSet=pStatement.getGeneratedKeys();
            if (resultSet.next()){
                specialty.setFacultyId(resultSet.getInt("ID"));
            }
            for(Subject subject:specialty.getSubjects()){
                pStatement=connection.prepareStatement(INSERT_SPECIALITY_SUBJECTS);
                pStatement.setInt(1,specialty.getSpecialityId());
                pStatement.setInt(2,subject.getSubjectId());
            }
        } catch (SQLException e) {
            throw new DAOException();
        }
        finally {
            try {if(pStatement!=null)
                pStatement.close();
            } catch (SQLException e) {
               logger.log(Level.ERROR,"Problems with close prepared statement");
            }
        }

    }

    @Override
    public Speciality update(Speciality specialty) throws DAOException {
        PreparedStatement pStatement=null;
        try(DBConnection connection=ConnectionPool.getInstance().getConnection()){
            pStatement=connection.prepareStatement(UPDATE_SPECIALITY);
            pStatement.setInt(1,specialty.getSpecialityId());
            pStatement.setString(2,specialty.getSpecialityName());
            pStatement.setInt(3,specialty.getRecruitmentPlan());
            pStatement.setInt(4,specialty.getFacultyId());
            pStatement.executeUpdate();
            for(Subject subject:specialty.getSubjects()){
                pStatement=connection.prepareStatement(UPDATE_SPECIALITY_SUBJECT);
                pStatement.setInt(1,specialty.getSpecialityId());
                pStatement.setInt(2,subject.getSubjectId());
                pStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new DAOException();
        }
        finally {
            if(pStatement!=null){
                try {
                  pStatement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR,"Problems with close prepared statement");
                }
            }

        }
        return specialty;
    }


    public List<User> findUserOnSpeciality(int id) throws DAOException {
        List<User> users =new ArrayList<>();
        try(DBConnection connection=ConnectionPool.getInstance().getConnection();
            PreparedStatement pStatement=connection.prepareStatement(FIND_ALL_USERS_ON_SPECIALITY_BY_ID)){
            pStatement.setInt(1,id);
            ResultSet rs=pStatement.executeQuery();
            if(rs!=null){
                while (rs.next()){
                    User user =new User();
                    user.setFirstName(rs.getString("FIRST_NAME"));
                    user.setLastName(rs.getString("LAST_NAME"));
                    user.setBirthday(rs.getDate("BIRTHDAY"));
                    user.setCertificateMark(rs.getInt("CERTIFICATE_MARK"));
                    user.setSpecialityId(rs.getInt("SPECIALITY_ID"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DAOException();
        }
        return users;
    }

    @Override
    public List<Speciality> findSpecialityForCurrentUserByFacultyId(int userId, int facultyId) {
        return null;
    }

    private Speciality setSpeciality(ResultSet rs,Speciality specialty) throws SQLException {
        specialty.setSpecialityId(rs.getInt("ID"));
        specialty.setSpecialityName(rs.getString("SPECIALITY_NAME"));
        specialty.setRecruitmentPlan(rs.getInt("RECRUITMENT_PLAN"));
        specialty.setFacultyId(rs.getInt("FACULTY_ID"));
        return specialty;
    }


}
