package by.mordas.project.dao.mysqlimpl;

import by.mordas.project.dao.DAOException;
import by.mordas.project.dao.UserDAO;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;
import by.mordas.project.pool.ConnectionPool;
import by.mordas.project.pool.PooledConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MySQLUserDAOImpl implements UserDAO {
    private static Logger logger= LogManager.getRootLogger();

    private static final String FIND_USER_BY_ID ="SELECT ID,FIRST_NAME,LAST_NAME,BIRTHDAY,CERTIFICATE_MARK," +
            "SPECIALITY_ID,LOGIN,EMAIL,ROLE_ID FROM USER WHERE ID=?";
    private static final String FIND_ALL_USER="SELECT ID,FIRST_NAME,LAST_NAME,BIRTHDAY," +
            "SPECIALITY_ID,EMAIL,LOGIN,ROLE_ID FROM USER ORDER BY ID";
    private static final String INSERT_USER ="INSERT INTO USER(FIRST_NAME,LAST_NAME,BIRTHDAY," +
            "LOGIN,PASSWORD,EMAIL) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_USER ="UPDATE USER SET ID=?,FIRST_NAME=?,LAST_NAME=?," +
            "BIRTHDAY=?,CERTIFICATE_AVG=?,SPECIALITY_ID=?,PASSWORD=?,EMAIL=?";
    private static final String UPDATE_SUBJECT_USER="UPDATE USER_SUBJECT_MARK SET ID_USER=?, ID_SUBJECT=?,USER_MARK=?";
    private static final String DELETE_USER_BY_ID="DELETE FROM USER WHERE ID=?";
    private static final String INSERT_STUDENTS_SUBJECTS="INSERT INTO USER_SUBJECT_MARK(ID_USER,ID_SUBJECT," +
            "USER_MARK) VALUES(?,?,?)";
    private static final String FIND_USER_BY_PASSWORD_AND_LOGIN="SELECT * FROM USER WHERE LOGIN=? and PASSWORD=?";
    private static final String FIND_SUBJECT_USER="SELECT * FROM USER_SUBJECT_MARK WHERE ID_USER=?";
    private static final String FIND_USER_BY_LOGIN="SELECT * FROM USER WHERE LOGIN=?";
    private static final String FIND_USER_SUBJECTS_AND_SCORE="SELECT SUBJECT.ID,SUBJECT.SUBJECT_NAME,USER_MARK FROM SUBJECT INNER JOIN USER_SUBJECT_MARK AS S ON SUBJECT.ID = S.ID_SUBJECT AND ID_USER=?";
    private static final String CHANGE_USER_PASSWORD="UPDATE USER SET PASSWORD=? WHERE ID=?";
    private static final String UPDATE_USER_SPECIALITY="UPDATE USER SET SPECIALITY_ID=?,CERTIFICATE_MARK=? WHERE ID=?";
    private static final String INSERT_USER_SCORES="INSERT INTO USER_SUBJECT_MARK (ID_USER, ID_SUBJECT,USER_MARK) VALUES(?,?,?)";
    private static final String DELETE_USER_SCORES="DELETE FROM USER_SUBJECT_MARK WHERE ID_USER=?";
    private static final String FIND_USERS_WITH_LIMIT="SELECT * FROM user LIMIT ?,10";
/*SELECT  q,r,recruitment_plan,speciality_name from speciality INNER JOIN
(SELECT count(user.id) q,avg(m) r,speciality_id FROM user INNER JOIN
(SELECT id u,avg(mark) m FROM (SELECT id, certificate_mark as mark from user
UNION
SELECT id_user, user_mark FROM user_subject_mark) as marks GROUP BY id) as s on id=u WHERE speciality_id=6) as w
on speciality.id=w.speciality_id - просмотр среднего бала и кол-ва подонных заявлений*/
    @Override
    public List<User> findAllEntity() throws DAOException {
        List<User> users =null;
        try(PooledConnection conn= ConnectionPool.getInstance().getConnection(); Statement statement=conn.createStatement();
            ResultSet rs=statement.executeQuery(FIND_ALL_USER)) {
            if(rs!=null){
                users=new ArrayList<>();
                while (rs.next()) {
                    User user =getUser(rs);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in findAllEntity method",e);
        }
        return users;
    }

    @Override
    public User findEntityById(long id) throws DAOException {
        User user = null;
        try(PooledConnection connection=ConnectionPool.getInstance().getConnection()) {
            PreparedStatement pStatement=connection.prepareStatement(FIND_USER_BY_ID);
            pStatement.setLong(1,id);
            ResultSet rs=pStatement.executeQuery();

            if(rs.next()){
               user=getUser(rs);
            }
            pStatement=connection.prepareStatement(FIND_SUBJECT_USER);

                pStatement.setLong(1,id);
            rs=pStatement.executeQuery();
                if(rs!=null) {
                    while (rs.next()){
                        Subject subject=new Subject();
                        Long subjectId=rs.getLong("ID_SUBJECT");
                        subject.setSubjectId(subjectId);
                        Integer mark=rs.getInt("USER_MARK");
                        user.put(subject,mark);
                    }
                }
        } catch (SQLException e) {
            throw new DAOException("Exception in findEntityById method",e);
        }
        return user;
    }

    @Override
    public boolean delete(long id) throws DAOException {
        PooledConnection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement pStatement = connection.prepareStatement(DELETE_USER_BY_ID)) {
            pStatement.setLong(1,id);
            return pStatement.executeUpdate()==7;
        } catch (SQLException e) {
           throw new DAOException("Exception in delete method",e);
        }
    }

        @Override
    public void create(User user) throws DAOException {
        try(PooledConnection connection=ConnectionPool.getInstance().getConnection(); PreparedStatement pStatement=connection.prepareStatement(INSERT_USER)){
            pStatement.setString(1, user.getFirstName());
            pStatement.setString(2, user.getLastName());
            pStatement.setDate(3, user.getBirthday());
            pStatement.setString(4,user.getLogin());
            pStatement.setString(5,user.getPassword());
            pStatement.setString(6,user.getEmail());
            pStatement.executeUpdate();
//            for(Map.Entry<Integer,Integer> entry: user.getSubjectMark().entrySet()){
//                pStatement=connection.prepareStatement(INSERT_STUDENTS_SUBJECTS);
//                pStatement.setLong(1, user.getUserId());
//                pStatement.setInt(2,entry.getKey());
//                pStatement.setInt(3,entry.getValue());
//                pStatement.executeUpdate();
//            }
        } catch (SQLException e) {
            throw new DAOException("Exception in create method",e);

        }
    }


    @Override
    public User update(User user) throws DAOException {
        PooledConnection connection=null;
        try {
            connection=ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pStatement=connection.prepareStatement(UPDATE_USER);
            pStatement.setLong(1, user.getUserId());
            pStatement.setString(2, user.getFirstName());
            pStatement.setString(3, user.getLastName());
            pStatement.setDate(4, user.getBirthday());
            pStatement.setInt(5, user.getCertificateMark());
            pStatement.setLong(6, user.getSpecialityId());
            pStatement.setString(7,user.getPassword());
            pStatement.setString(8,user.getEmail());
            pStatement.executeUpdate();
            for(Map.Entry<Subject,Integer> entry: user.getSubjectMark().entrySet()){
                pStatement=connection.prepareStatement(UPDATE_SUBJECT_USER);
                pStatement.setLong(1, user.getUserId());
                pStatement.setLong(2,entry.getKey().getSubjectId());
                pStatement.setInt(3,entry.getValue());
                pStatement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException("Exception in update method",e);
        }

        return user;
    }

    @Override
    public boolean findUserByLogin(String login) throws DAOException {

        try(PooledConnection connection=ConnectionPool.getInstance().getConnection();
            PreparedStatement pStetement=connection.prepareStatement(FIND_USER_BY_LOGIN);){
            pStetement.setString(1,login);
            ResultSet rs=pStetement.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
           throw new DAOException("Exception in findUserByLogin method",e);
        }
    }

    @Override
    public User findUserByPasswordAndLogin(String login,String password) throws DAOException {
        User user=null;
        try(PooledConnection connection=ConnectionPool.getInstance().getConnection();
            PreparedStatement pStatement=connection.prepareStatement(FIND_USER_BY_PASSWORD_AND_LOGIN)){
            pStatement.setString(1,login);
            pStatement.setString(2,password);
            ResultSet rs=pStatement.executeQuery();
            if(rs.next()) {
                user=getUser(rs);
            }

        } catch (SQLException e) {
            throw new DAOException("Exception in findUserByPasswordAndLogin",e);
        }
       return user;
    }

    private User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getLong("ID"));
        user.setFirstName(rs.getString("FIRST_NAME"));
        user.setLastName(rs.getString("LAST_NAME"));
        user.setBirthday(rs.getDate("BIRTHDAY"));
        user.setEmail(rs.getString("EMAIL"));
        user.setRoleId(rs.getInt("ROLE_ID"));
        user.setLogin(rs.getString("LOGIN"));
        user.setSpecialityId(rs.getLong("SPECIALITY_ID"));
    return user;
    }

    @Override
    public Map<Subject, Integer> findUserSubjectsAndScores(long id) throws DAOException {
        Map<Subject,Integer> subjects=new HashMap<>();
        try(PooledConnection connection=ConnectionPool.getInstance().getConnection();
            PreparedStatement pStatement=connection.prepareStatement(FIND_USER_SUBJECTS_AND_SCORE)){
            pStatement.setLong(1,id);
            ResultSet rs=pStatement.executeQuery();
            if(rs!=null){
                while (rs.next()){
                    Subject subject=new Subject();
                    subject.setSubjectId(rs.getLong("ID"));
                    subject.setSubjectName("SUBJECT_NAME");
                    int score=rs.getInt("USER_MARK");
                    subjects.put(subject,score);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Exception in findUserSubjectsAndScores method",e);
        }
        return subjects;
    }

    @Override
    public void changeUserPassword(Long userId, String password) throws DAOException {
        try(PooledConnection connection=ConnectionPool.getInstance().getConnection();
            PreparedStatement pStatement=connection.prepareStatement(CHANGE_USER_PASSWORD)){
            pStatement.setString(1,password);
            pStatement.setLong(2,userId);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in changeUserPassword method",e);
        }

    }

    @Override
    public void updateUserSpeciality(User user) throws DAOException {
        PooledConnection connection=ConnectionPool.getInstance().getConnection();
        PreparedStatement pStatement=null;
        try{
            connection.setAutoCommit(false);
            pStatement=connection.prepareStatement(UPDATE_USER_SPECIALITY);
            pStatement.setLong(1,user.getSpecialityId());
            pStatement.setInt(2,user.getCertificateMark());
            pStatement.setLong(3,user.getUserId());
            pStatement.executeUpdate();
            for(Map.Entry<Subject,Integer> entry: user.getSubjectMark().entrySet()){
                pStatement=connection.prepareStatement(INSERT_USER_SCORES);
                pStatement.setLong(1, user.getUserId());
                pStatement.setLong(2,entry.getKey().getSubjectId());
                pStatement.setInt(3,entry.getValue());
                pStatement.executeUpdate();

            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("Exception in updateUserSpeciality method",e);
            }
            throw new DAOException("Exception in updateUserSpeciality method",e);
        }
        finally {
            try {
                if(connection!=null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
                if(pStatement!=null) {
                    pStatement.close();
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, e.getMessage());
            }

        }

    }

    @Override
    public void clearUserScore(User user) throws DAOException {
        PooledConnection connection=ConnectionPool.getInstance().getConnection();
        PreparedStatement pStatement=null;
        try {
            connection.setAutoCommit(false);
            pStatement=connection.prepareStatement(UPDATE_USER_SPECIALITY);
            pStatement.setString(1,null);
            pStatement.setInt(2,user.getCertificateMark());
            pStatement.setLong(3,user.getUserId());
            pStatement.executeUpdate();
            pStatement=connection.prepareStatement(DELETE_USER_SCORES);
            pStatement.setLong(1,user.getUserId());
            pStatement.executeUpdate();

        } catch (SQLException e) {
           try{
            connection.rollback();
        } catch (SQLException e1) {
            throw new DAOException("Exception in clearUserScores method",e);
        }
        throw new DAOException("Exception in clearUserScores method",e);
    }
        finally {
        try {
            if(connection!=null) {
                connection.setAutoCommit(true);
                connection.close();
            }
            if(pStatement!=null) {
                pStatement.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        }
    }

    @Override
    public List<User> findUsersWithLimit(int count) throws DAOException {
        List<User> users =null;
        try(PooledConnection conn= ConnectionPool.getInstance().getConnection(); PreparedStatement pStatement=conn.prepareStatement(FIND_USERS_WITH_LIMIT)){
           pStatement.setInt(1,count);
            ResultSet rs=pStatement.executeQuery();
            if(rs!=null){
                users=new ArrayList<>();
                while (rs.next()) {
                    User user =getUser(rs);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in findUsersWithLimit method",e);
        }
        return users;
    }
}
