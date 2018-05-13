package by.mordas.project.dao.mysqlimpl;

import by.mordas.project.dao.DAOException;
import by.mordas.project.dao.SpecialityDAO;
import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;
import by.mordas.project.pool.ConnectionPool;
import by.mordas.project.pool.DBConnection;
import by.mordas.project.util.DateConverter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLSpecialityDAOImpl implements SpecialityDAO {
    private static final Logger logger= LogManager.getRootLogger();
    private static final String FIND_ALL_SPECIALITY="SELECT ID,SPECIALITY_NAME,RECRUITMENT_PLAN,FACULTY_ID,START_REGISTRATION,END_REGISTRATION FROM SPECIALITY";
    private static final String FIND_SPECIALITY_BY_ID="SELECT ID,SPECIALITY_NAME,RECRUITMENT_PLAN,FACULTY_ID,START_REGISTRATION,END_REGISTRATION FROM SPECIALITY WHERE ID=?";
    private static final String CREATE_SPECIALITY="INSERT INTO SPECIALITY(SPECIALITY_NAME,RECRUITMENT_PLAN,FACULTY_ID,START_REGISTRATION,END_REGISTRATION) VALUES(?,?,?,?,?)";
    private static final String UPDATE_SPECIALITY="UPDATE SPECIALITY SET SPECIALITY_NAME=?,RECRUITMENT_PLAN=?,FACULTY_ID=?,START_REGISTRATION=?,END_REGISTRATION=? WHERE ID=?";
    private static final String UPDATE_SPECIALITY_SUBJECT="UPDATE SUBJECT_FOR_SPECIALITY SET ID_SUBJECT=? WHERE ID_SPECIALITY=?";
    private static final String DELETE_SPECIALITY="DELETE FROM SPECIALITY WHERE ID=?";
    private static final String FIND_ALL_SPECIALITY_BY_FACULTY_ID="SELECT SPECIALITY.ID,SPECIALITY_NAME,RECRUITMENT_PLAN, FACULTY_ID,,START_REGISTRATION,END_REGISTRATION FROM SPECIALITY WHERE FACULTY_ID=?";
    private static final String FIND_ALL_USERS_ON_SPECIALITY_BY_ID ="SELECT ID,FIRST_NAME,LAST_NAME,BIRTHDAY,CERTIFICATE_MARK," +
            "SPECIALITY_ID FROM USER  WHERE SPECIALITY_ID=?";
    private static final String INSERT_SPECIALITY_SUBJECTS="INSERT INTO SUBJECT_FOR_SPECIALITY(ID_SPECIALITY,ID_SUBJECT) VALUES (?,?)";
    private static final String FIND_SUBJECT_USER="SELECT * FROM USER_SUBJECT_MARK WHERE ID_USER=?";
    private static final String DEFINE_SUMMARY_USER_SCORE="SELECT sum(user_mark) as sum FROM user INNER JOIN (SELECT id_user, user_mark FROM user_subject_mark UNION SELECT id,certificate_mark FROM user)as marks ON user.id=marks.id_user WHERE speciality_id= ? GROUP BY id";
    private static final String DELETE_SPECIALITY_SUBJECTS="DELETE FROM SUBJECT_FOR_SPECIALITY WHERE ID_SPECIALITY=?";
    /// /    private static final String FIND_ALL_SPECIALITY_BY_FACULTY_ID="SELECT SPECIALITY.ID,SPECIALITY_NAME,RECRUITMENT_PLAN, FACULTY_ID FROM SPECIALITY" +
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
                    specialties.add(speciality);
                }
            }
        } catch (SQLException e) {
           throw new DAOException("Exception in delete method",e);
        }

        return specialties;

    }

    @Override
    public Speciality findEntityById(long id) throws DAOException {
        Speciality speciality=null;
        try(DBConnection conn= ConnectionPool.getInstance().getConnection();PreparedStatement pStatement=conn.prepareStatement(FIND_SPECIALITY_BY_ID);
            ) {
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

    public List<Speciality> findSpecialitiesByFacultyID(long id) throws DAOException {
        List<Speciality> specialities=new ArrayList<>();
        try(DBConnection connection=ConnectionPool.getInstance().getConnection();PreparedStatement pStatement=connection.prepareStatement(FIND_ALL_SPECIALITY_BY_FACULTY_ID);
        ) {
            pStatement.setLong(1,id);
            ResultSet rs=pStatement.executeQuery();
            if(rs!=null){
                while (rs.next()) {
                    Speciality speciality=new Speciality();
                    setSpeciality(rs,speciality);
                    specialities.add(speciality);
                }
                }
        } catch (SQLException e) {
            throw new DAOException("Exception in findSpecialitesByFacultyId method",e);
        }

        return specialities;
    }

    @Override
    public boolean delete(long id) throws DAOException {
        DBConnection connection=null;
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
    public void create(Speciality specialty) throws DAOException {
        PreparedStatement pStatement=null;
        DBConnection connection=null;
        try{
            connection=ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            pStatement=connection.prepareStatement(CREATE_SPECIALITY,Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1,specialty.getSpecialityName());
            pStatement.setInt(2,specialty.getRecruitmentPlan());
            pStatement.setLong(3,specialty.getFacultyId());
            pStatement.setTimestamp(4, DateConverter.getTimestamp(specialty.getStart_registration()));
            pStatement.setTimestamp(5,DateConverter.getTimestamp(specialty.getEnd_registration()));
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
        DBConnection connection=null;
        try{
            connection=ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            pStatement=connection.prepareStatement(UPDATE_SPECIALITY);
            pStatement.setString(1,specialty.getSpecialityName());
            pStatement.setLong(2,specialty.getRecruitmentPlan());
            pStatement.setLong(3,specialty.getFacultyId());
            pStatement.setLong(4,specialty.getSpecialityId());
            pStatement.setTimestamp(5, DateConverter.getTimestamp(specialty.getStart_registration()));
            pStatement.setTimestamp(6,DateConverter.getTimestamp(specialty.getEnd_registration()));

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
                    e.printStackTrace();
                }

            }

        }
        return specialty;
    }

    @Override
    public List<User> findUsersOnSpeciality(long id) throws DAOException {
        List<User> users =new ArrayList<>();
        PreparedStatement pStatement=null;
        PreparedStatement pStatement2=null;
        DBConnection connection=null;
        try{
            connection=ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            pStatement=connection.prepareStatement(FIND_ALL_USERS_ON_SPECIALITY_BY_ID);
            pStatement.setLong(1,id);
            ResultSet rs=pStatement.executeQuery();
            if(rs!=null){
                while (rs.next()){
                    User user =new User();
                    user.setUserId(rs.getLong("ID"));
                    user.setFirstName(rs.getString("FIRST_NAME"));
                    user.setLastName(rs.getString("LAST_NAME"));
                    user.setCertificateMark(rs.getInt("CERTIFICATE_MARK"));
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
        try(DBConnection connection=ConnectionPool.getInstance().getConnection();
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

    private void setSpeciality(ResultSet rs,Speciality specialty) throws SQLException {
        specialty.setSpecialityId(rs.getLong("ID"));
        specialty.setSpecialityName(rs.getString("SPECIALITY_NAME"));
        specialty.setRecruitmentPlan(rs.getInt("RECRUITMENT_PLAN"));
        specialty.setFacultyId(rs.getInt("FACULTY_ID"));
        specialty.setStart_registration(rs.getTimestamp("START_REGISTRATION").toLocalDateTime());
        specialty.setEnd_registration(rs.getTimestamp("END_REGISTRATION").toLocalDateTime());
    }


}
