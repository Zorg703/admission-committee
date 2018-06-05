package by.mordas.project.dao.mysqlimpl;

import by.mordas.project.dao.DAOException;
import by.mordas.project.dao.SpecialityDAO;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;
import by.mordas.project.pool.ConnectionPool;
import by.mordas.project.pool.PooledConnection;
import by.mordas.project.util.DateConverter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/***
 Author: Sergei Mordas
 Date: 06.04.2018
 ***/

public class MySQLSpecialityDAOImpl implements SpecialityDAO {
    private static final Logger logger= LogManager.getRootLogger();
    private static final String FIND_ALL_SPECIALITY="SELECT ID,SPECIALITY_NAME,RECRUITMENT_PLAN,FACULTY_ID,START_REGISTRATION,END_REGISTRATION FROM SPECIALITY";
    private static final String FIND_SPECIALITY_BY_ID="SELECT ID,SPECIALITY_NAME,RECRUITMENT_PLAN,FACULTY_ID,START_REGISTRATION,END_REGISTRATION FROM SPECIALITY WHERE ID=?";
    private static final String CREATE_SPECIALITY="INSERT INTO SPECIALITY(SPECIALITY_NAME,RECRUITMENT_PLAN,FACULTY_ID,START_REGISTRATION,END_REGISTRATION) VALUES(?,?,?,?,?)";
    private static final String UPDATE_SPECIALITY="UPDATE SPECIALITY SET SPECIALITY_NAME=?,RECRUITMENT_PLAN=?,FACULTY_ID=?,START_REGISTRATION=?,END_REGISTRATION=? WHERE ID=?";
    private static final String DELETE_SPECIALITY="DELETE FROM SPECIALITY WHERE ID=?";
    private static final String FIND_ALL_SPECIALITY_BY_FACULTY_ID="SELECT SPECIALITY.ID,SPECIALITY_NAME,RECRUITMENT_PLAN, FACULTY_ID,START_REGISTRATION,END_REGISTRATION FROM SPECIALITY WHERE FACULTY_ID=?";
    private static final String FIND_ALL_USERS_ON_SPECIALITY_BY_ID ="SELECT ID,FIRST_NAME,LAST_NAME,BIRTHDAY,CERTIFICATE_MARK," +
            "SPECIALITY_ID,EMAIL FROM USER  WHERE SPECIALITY_ID=?";
    private static final String INSERT_SPECIALITY_SUBJECTS="INSERT INTO SUBJECT_FOR_SPECIALITY(ID_SPECIALITY,ID_SUBJECT) VALUES (?,?)";
    private static final String FIND_SUBJECT_USER="SELECT * FROM USER_SUBJECT_MARK WHERE ID_USER=?";
    private static final String DEFINE_SUMMARY_USER_SCORE="SELECT sum(user_mark) as sum FROM user INNER JOIN (SELECT id_user, user_mark FROM user_subject_mark UNION SELECT id,certificate_mark FROM user)as marks ON user.id=marks.id_user WHERE speciality_id= ? GROUP BY id";
    private static final String DELETE_SPECIALITY_SUBJECTS="DELETE FROM SUBJECT_FOR_SPECIALITY WHERE ID_SPECIALITY=?";
    private static final String UPDATE_REGISTER_DATE="UPDATE SPECIALITY SET START_REGISTRATION=?,END_REGISTRATION=? WHERE ID=?";
    private static final String FIND_ALL_USERS_ON_SPECIALITY_BY_ID_WITH_LIMIT ="SELECT ID,FIRST_NAME,LAST_NAME,BIRTHDAY,CERTIFICATE_MARK," +
            "SPECIALITY_ID,EMAIL FROM USER  WHERE SPECIALITY_ID=? LIMIT ?,10";
    private static final String FIND_ALL_SPECIALITY_WITH_LIMIT="SELECT ID,SPECIALITY_NAME,RECRUITMENT_PLAN,FACULTY_ID,START_REGISTRATION,END_REGISTRATION FROM SPECIALITY LIMIT ?,10";
    /// /    private static final String FIND_ALL_SPECIALITY_BY_FACULTY_ID="SELECT SPECIALITY.ID,SPECIALITY_NAME,RECRUITMENT_PLAN, FACULTY_ID FROM SPECIALITY" +
//
// 30          " INNER JOIN FACULTY ON SPECIALITY.FACULTY_ID=FACULTY.ID AND FACULTY.ID=?";

    @Override
    public List<Speciality> findAllEntity() throws DAOException {
        List<Speciality> specialties=null;
        try(PooledConnection connection= ConnectionPool.getInstance().getConnection();
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(FIND_ALL_SPECIALITY)) {
            if (rs != null) {
                specialties=new ArrayList<>();
                while (rs.next()) {
                    Speciality speciality=new Speciality();
                    setSpeciality(rs,speciality);
                    specialties.add(speciality);
                }
            }
        } catch (SQLException e) {
           throw new DAOException("Exception in findAllEntity method",e);
        }

        return specialties;

    }

    @Override
    public Speciality findEntityById(long id) throws DAOException {
        Speciality speciality=null;
        try(PooledConnection conn= ConnectionPool.getInstance().getConnection();
            PreparedStatement pStatement=conn.prepareStatement(FIND_SPECIALITY_BY_ID)            ) {
            pStatement.setLong(1,id);
            ResultSet rs=pStatement.executeQuery();
            if(rs.next()){
                speciality=new Speciality();
               setSpeciality(rs,speciality);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in findAllEntity method",e);
        }
        return speciality;
    }

    @Override
    public boolean delete(long id) throws DAOException {
        PooledConnection connection=null;
        PreparedStatement pStatement =null;
        int result=0;
        try {
            connection=ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            pStatement=connection.prepareStatement(DELETE_SPECIALITY_SUBJECTS);
            pStatement.setLong(1,id);
            result+=pStatement.executeUpdate();
            pStatement = connection.prepareStatement(DELETE_SPECIALITY);
            pStatement.setLong(1,id);
            result+=pStatement.executeUpdate();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("Exception in delete method",e);
            }
            throw new DAOException("Exception in delete method",e);
        }
        finally {
            try {if(pStatement!=null ) {
                pStatement.close();
            }
                if(connection!=null){
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR,"Problems with close prepared statement");
            }
        }
        return result==4;
    }

    @Override
    public List<Speciality> findSpecialitiesWithLimit(int count) throws DAOException {
        List<Speciality> specialties=null;
        try(PooledConnection connection= ConnectionPool.getInstance().getConnection();
            PreparedStatement ptatement=connection.prepareStatement(FIND_ALL_SPECIALITY_WITH_LIMIT)) {
            ptatement.setInt(1,count);
            ResultSet rs=ptatement.executeQuery();
            if (rs != null) {
                specialties=new ArrayList<>();
                while (rs.next()) {
                    Speciality speciality=new Speciality();
                    setSpeciality(rs,speciality);
                    specialties.add(speciality);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in find specialities with limit method",e);
        }

        return specialties;

    }

    @Override
    public List<User> findUsersOnSpecialityWithLimit(Long specialityId, int count) throws DAOException {
        List<User> users= null;
        PreparedStatement pStatement=null;
        PreparedStatement pStatement2=null;
        PooledConnection connection=null;
        try{
            connection=ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            pStatement=connection.prepareStatement(FIND_ALL_USERS_ON_SPECIALITY_BY_ID_WITH_LIMIT);
            pStatement.setLong(1,specialityId);
            pStatement.setInt(2,count);
            ResultSet rs=pStatement.executeQuery();
            if(rs!=null){
                users=new ArrayList<>();
                while (rs.next()){
                    User user =new User();
                    user.setUserId(rs.getLong("ID"));
                    user.setFirstName(rs.getString("FIRST_NAME"));
                    user.setLastName(rs.getString("LAST_NAME"));
                    user.setCertificateMark(rs.getInt("CERTIFICATE_MARK"));
                    user.setEmail(rs.getString("EMAIL"));
                    pStatement2=connection.prepareStatement(FIND_SUBJECT_USER);
                    pStatement2.setLong(1,user.getUserId());
                    ResultSet rs2=pStatement2.executeQuery();
                    if(rs2!=null) {
                        while (rs2.next()) {
                            Subject subject=new Subject();
                            Long subjectId = rs2.getLong("ID_SUBJECT");
                            subject.setSubjectId(subjectId);
                            Integer mark = rs2.getInt("USER_MARK");
                            user.put(subject, mark);
                        }
                    }
                    users.add(user);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("Exception in findUserOnSpeciality method",e);
            }
            throw new DAOException("Exception in findUserOnSpeciality method",e);
        }
        finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
                if (pStatement != null && pStatement2!=null) {
                    pStatement.close();
                    pStatement2.close();
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, e.getMessage());
            }
        }

        return users;
    }

    @Override
    public void create(Speciality specialty) throws DAOException {
        PreparedStatement pStatement=null;
        PooledConnection connection=null;
        try{
            connection=ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            pStatement=connection.prepareStatement(CREATE_SPECIALITY,Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1,specialty.getSpecialityName());
            pStatement.setInt(2,specialty.getRecruitmentPlan());
            pStatement.setLong(3,specialty.getFacultyId());
            pStatement.setTimestamp(4, DateConverter.getTimestamp(specialty.getStartRegistration()));
            pStatement.setTimestamp(5,DateConverter.getTimestamp(specialty.getEndRegistration()));
            pStatement.executeUpdate();
            ResultSet resultSet=pStatement.getGeneratedKeys();
            if (resultSet.next()){
                specialty.setSpecialityId(resultSet.getLong(1));
            }
            for(Subject subject:specialty.getSubjects()){
                pStatement=connection.prepareStatement(INSERT_SPECIALITY_SUBJECTS);
                pStatement.setLong(1,specialty.getSpecialityId());
                pStatement.setLong(2,subject.getSubjectId());
                pStatement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("Exception in create method",e);
            }
            throw new DAOException("Exception in create method",e);
        }
        finally {
            try {if(pStatement!=null ) {
                pStatement.close();
            }
            if(connection!=null){
                connection.setAutoCommit(true);
                connection.close();
            }
            } catch (SQLException e) {
               logger.log(Level.ERROR,"Problems with close prepared statement");
            }
        }

    }

    @Override
    public Speciality update(Speciality specialty) throws DAOException {
        PreparedStatement pStatement=null;
        PooledConnection connection=null;
        try{
            connection=ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            pStatement=connection.prepareStatement(UPDATE_SPECIALITY);
            pStatement.setString(1,specialty.getSpecialityName());
            pStatement.setLong(2,specialty.getRecruitmentPlan());
            pStatement.setLong(3,specialty.getFacultyId());
            pStatement.setLong(4,specialty.getSpecialityId());
            pStatement.setTimestamp(5, DateConverter.getTimestamp(specialty.getStartRegistration()));
            pStatement.setTimestamp(6,DateConverter.getTimestamp(specialty.getEndRegistration()));

            pStatement.executeUpdate();
            pStatement=connection.prepareStatement(DELETE_SPECIALITY_SUBJECTS);
            pStatement.setLong(1,specialty.getSpecialityId());
            pStatement.executeUpdate();
            for(Subject subject:specialty.getSubjects()){
                pStatement=connection.prepareStatement(INSERT_SPECIALITY_SUBJECTS);
                pStatement.setLong(1,specialty.getSpecialityId());
                pStatement.setLong(2,subject.getSubjectId());

                pStatement.executeUpdate();
            }
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DAOException("Exception in update method:",e);

        }
        finally {
            if(pStatement!=null){
                try {
                  pStatement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR,"Problems with close prepared statement");
                }
            }
            if(connection!=null){
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, e.getMessage());
                }

            }

        }
        return specialty;
    }

    @Override
    public List<User> findUsersOnSpeciality(long id) throws DAOException {
        List<User> users= null;
        PreparedStatement pStatement=null;
        PreparedStatement pStatement2=null;
        PooledConnection connection=null;
        try{
            connection=ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            pStatement=connection.prepareStatement(FIND_ALL_USERS_ON_SPECIALITY_BY_ID);
            pStatement.setLong(1,id);
            ResultSet rs=pStatement.executeQuery();
            if(rs!=null){
                users=new ArrayList<>();
                while (rs.next()){
                    User user =new User();
                    user.setUserId(rs.getLong("ID"));
                    user.setFirstName(rs.getString("FIRST_NAME"));
                    user.setLastName(rs.getString("LAST_NAME"));
                    user.setCertificateMark(rs.getInt("CERTIFICATE_MARK"));
                    user.setEmail(rs.getString("EMAIL"));
                    pStatement2=connection.prepareStatement(FIND_SUBJECT_USER);
                    pStatement2.setLong(1,user.getUserId());
                    ResultSet rs2=pStatement2.executeQuery();
                    if(rs2!=null) {
                        while (rs2.next()) {
                            Subject subject=new Subject();
                            Long subjectId = rs2.getLong("ID_SUBJECT");
                            subject.setSubjectId(subjectId);
                            Integer mark = rs2.getInt("USER_MARK");
                            user.put(subject, mark);
                        }
                    }
                    users.add(user);
                }
            }
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("Exception in findUserOnSpeciality method",e);
            }
            throw new DAOException("Exception in findUserOnSpeciality method",e);
        }
        finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
                if (pStatement != null & pStatement2!=null) {
                    pStatement.close();
                    pStatement2.close();
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, e.getMessage());
            }
        }

            return users;
    }

    @Override
    public List<Integer> defineUsersSumScoreRegisterOnSpeciality(long specialityId) throws DAOException {
        List<Integer> sumList=new ArrayList<>();
        try(PooledConnection connection=ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(DEFINE_SUMMARY_USER_SCORE)) {
            preparedStatement.setLong(1,specialityId);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet!=null){
                while (resultSet.next()) {
                    sumList.add(resultSet.getInt(1));
                }


            }
        } catch (SQLException e) {
            throw new DAOException("Problems in defineUsersSumScoreRegisterOnSpeciality method");
        }
        return sumList;
    }

    @Override
    public void updateSpecialityRegisterDate(Speciality speciality) throws DAOException {
        try(PooledConnection connection=ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_REGISTER_DATE)) {
            preparedStatement.setTimestamp(1,DateConverter.getTimestamp(speciality.getStartRegistration()));
            preparedStatement.setTimestamp(2,DateConverter.getTimestamp(speciality.getEndRegistration()));
            preparedStatement.setLong(3,speciality.getSpecialityId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Problems in updateSpecialityDate method");
        }
    }

    private void setSpeciality(ResultSet rs,Speciality specialty) throws SQLException {
        specialty.setSpecialityId(rs.getLong("ID"));
        specialty.setSpecialityName(rs.getString("SPECIALITY_NAME"));
        specialty.setRecruitmentPlan(rs.getInt("RECRUITMENT_PLAN"));
        specialty.setFacultyId(rs.getInt("FACULTY_ID"));
        specialty.setStartRegistration(rs.getTimestamp("START_REGISTRATION").toLocalDateTime());
        specialty.setEndRegistration(rs.getTimestamp("END_REGISTRATION").toLocalDateTime());
    }


}
