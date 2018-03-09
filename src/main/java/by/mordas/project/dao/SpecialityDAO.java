package by.mordas.project.dao;

import by.mordas.project.entity.User;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;
import by.mordas.project.pool.ConnectionPool;
import by.mordas.project.pool.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SpecialityDAO extends AbstractDAO<Integer,Speciality> {
    private static final String FIND_ALL_SPECIALITY="SELECT ID,SPECIALITY_NAME,RECRUITMENT_PLAN,FACULTY_ID FROM SPECIALITY";
    private static final String FIND_SPECIALITY_BY_ID="SELECT ID,SPECIALITY_NAME,RECRUITMENT_PLAN,FACULTY_ID FROM SPECIALITY WHERE ID=?";
    private static final String CREATE_SPECIALITY="INSERT INTO SPECIALITY(SPECIALITY_NAME,RECRUITMENT_PLAN,FACULTY_ID) VALUES(?,?,?,?)";
    private static final String UPDATE_SPECIALITY="UPDATE SPECIALITY SET ID=? SPECIALITY_NAME=?,RECRUITMENT_PLAN=?,FACULTY_ID=?";
    private static final String UPDATE_SPECIALITY_SUBJECT="UPDATE SUBJECT_FOR_SPECIALITY SET ID_SPECIALITY=?,ID_SUBJECT=?";
    private static final String DELETE_SPECIALITY="DELETE FROM SPECIALITY WHERE ID=?";
    private static final String FIND_ALL_SPECIALITY_BY_FACULTY_ID="SELECT SPECIALITY.ID,SPECIALITY.SPECIALITY_NAME,SPECIALITY.RECRUITMENT_PLAN, SPECIALITY.FACULTY_ID FROM SPECIALITY WHERE FACULTY_ID=?";
    private static final String FIND_ALL_USERS_ON_SPECIALITY_BY_ID ="SELECT ID,FIRST_NAME,LAST_NAME,BIRTHDAY,CERTIFICATE_AVG," +
            "SPECIALITY_ID FROM USER  WHERE SPECIALITY ID=?";
    private static final String INSERT_SPECIALITY_SUBJECTS="INSERT INTO SUBJECT_FOR_SPECIALITY(ID_SPECIALITY,ID_SUBJECT)";


    @Override
    public List<Speciality> findAllEntity() {
        DBConnection connection= ConnectionPool.getConnection();
        List<Speciality> specialties=new ArrayList<>();
        try(Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(FIND_ALL_SPECIALITY)) {
            if (rs != null) {
                while (rs.next()) {
                   setSpeciality(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionPool.closeConnection(connection);

        }
        return specialties;

    }

    @Override
    public Speciality findEntityById(int id) {
        DBConnection conn= ConnectionPool.getConnection();
        Speciality speciality =new Speciality();
        try(PreparedStatement pStatement=conn.prepareStatement(FIND_SPECIALITY_BY_ID);
            ) {
            pStatement.setInt(1,id);
            ResultSet rs=pStatement.executeQuery();
            if(rs!=null){
                    speciality.setSpecialityId(rs.getInt("ID"));
                    speciality.setSpecialityName(rs.getString("SPECIALITY_NAME"));
                    speciality.setRecruitmentPlan(rs.getInt("RECRUITMENT_PLAN"));
                    speciality.setFacultyId(rs.getInt("FACULTY_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionPool.closeConnection(conn);
        }
        return speciality;
    }
    public List<Speciality> findSpecialitiesByFacultyID(int id){
        DBConnection connection=ConnectionPool.getConnection();
        List<Speciality> specialities=new ArrayList<>();
        try(PreparedStatement pStatement=connection.prepareStatement(FIND_ALL_SPECIALITY_BY_FACULTY_ID);
        ) {
            pStatement.setInt(1,id);
            ResultSet rs=pStatement.executeQuery();
            if(rs!=null){
                while (rs.next()) {
                    Speciality speciality=new Speciality();
                    speciality.setSpecialityId(rs.getInt("ID"));
                    speciality.setSpecialityName(rs.getString("SPECIALITY_NAME"));
                    speciality.setRecruitmentPlan(rs.getInt("RECRUITMENT_PLAN"));
                    speciality.setFacultyId(rs.getInt("FACULTY_ID"));
                    specialities.add(speciality);
                }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionPool.closeConnection(connection);
        }
        return specialities;
    }

    @Override
    public boolean delete(int id) {
        DBConnection connection = ConnectionPool.getConnection();
        try (PreparedStatement pStatement = connection.prepareStatement(DELETE_SPECIALITY)) {
            pStatement.setInt(1,id);
            return pStatement.executeUpdate()==4;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionPool.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean create(Speciality specialty) {
        DBConnection connection=ConnectionPool.getConnection();
        PreparedStatement pStatement=null;
        try{
            pStatement=connection.prepareStatement(CREATE_SPECIALITY);
            pStatement.setString(2,specialty.getSpecialityName());
            pStatement.setInt(3,specialty.getRecruitmentPlan());
            pStatement.setInt(4,specialty.getFacultyId());
            int result=pStatement.executeUpdate();
            ResultSet resultSet=pStatement.getGeneratedKeys();
            while (resultSet.next()){
                specialty.setFacultyId(resultSet.getInt("ID"));

            }

            for(Subject subject:specialty.getSubjects()){
                pStatement=connection.prepareStatement(INSERT_SPECIALITY_SUBJECTS);
                pStatement.setInt(1,specialty.getSpecialityId());
                pStatement.setInt(2,subject.getSubjectId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                pStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionPool.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Speciality update(Speciality specialty) {
        DBConnection connection=ConnectionPool.getConnection();
        try{
            PreparedStatement pStatement=connection.prepareStatement(UPDATE_SPECIALITY);
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
            e.printStackTrace();
        }
        finally {

            ConnectionPool.closeConnection(connection);
        }
        return specialty;
    }


    public List<User> findUserOnSpeciality(int id){
        DBConnection connection=ConnectionPool.getConnection();
        List<User> users =new ArrayList<>();
        try(PreparedStatement pStatement=connection.prepareStatement(FIND_ALL_USERS_ON_SPECIALITY_BY_ID)
        ; ResultSet rs=pStatement.executeQuery()){
            pStatement.setInt(1,id);
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
            e.printStackTrace();
        }
        return users;


    }

    private Speciality setSpeciality(ResultSet rs) throws SQLException {
        Speciality specialty=new Speciality();
        specialty.setSpecialityId(rs.getInt("ID"));
        specialty.setSpecialityName(rs.getString("SPECIALITY_NAME"));
        specialty.setRecruitmentPlan(rs.getInt("RECRUITMENT_PLAN"));
        specialty.setFacultyId(rs.getInt("FACULTY_ID"));
        return specialty;
    }


}
