package by.mordas.project.dao.impl;

import by.mordas.project.dao.DAOException;
import by.mordas.project.dao.UserDAO;
import by.mordas.project.entity.Subject;
import by.mordas.project.entity.User;
import by.mordas.project.pool.ConnectionPool;
import by.mordas.project.pool.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserDAOImpl implements UserDAO {
    private static final String FIND_USER_BY_ID ="SELECT ID,FIRST_NAME,LAST_NAME,BIRTHDAY,CERTIFICATE_MARK," +
            "SPECIALITY_ID,LOGIN,PASSWORD,EMAIL FROM USER WHERE ID=?";
    private static final String FIND_ALL_USER="SELECT ID,FIRST_NAME,LAST_NAME,BIRTHDAY,CERTIFICATE_MARK," +
            "SPECIALITY_ID FROM USER ORDER BY LAST_NAME";
    private static final String INSERT_USER ="INSERT INTO USER(FIRST_NAME,LAST_NAME,BIRTHDAY," +
            "CERTIFICATE_MARK,LOGIN,PASSWORD,EMAIL) VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE_USER ="UPDATE USER SET ID=?,FIRST_NAME=?,LAST_NAME=?," +
            "BIRTHDAY=?,CERTIFICATE_AVG=?,SPECIALITY_ID=?,PASSWORD=?,EMAIL=?";
    private static final String UPDATE_SUBJECT_USER="UPDATE USER_SUBJECT_MARK SET ID_USER=?, ID_SUBJECT=?,USER_MARK=?";
    private static final String DELETE_USER_BY_ID="DELETE FROM USER WHERE ID=?";
    private static final String INSERT_STUDENTS_SUBJECTS="INSERT INTO USER_SUBJECT_MARK(ID_USER,ID_SUBJECT," +
            "USER_MARK) VALUES(?,?,?) ";
    private static final String FIND_USER_BY_PASSWORD_AND_LOGIN="SELECT * FROM USER WHERE LOGIN=? and PASSWORD=?";
    private static final String FIND_SUBJECT_USER="SELECT * FROM USER_SUBJECT_MARK WHERE ID=?";
    private static final String FIND_USER_BY_LOGIN="SELECT * FROM USER WHERE LOGIN=?";
    private static final String FIND_USER_SUBJECTS_AND_SCORE="SELECT SUBJECT.ID,SUBJECT.SUBJECT_NAME,USER_MARK FROM SUBJECT INNER JOIN USER_SUBJECT_MARK AS S ON SUBJECT.ID = S.ID_SUBJECT AND ID_USER=?";
    private static final String CHANGE_USER_PASSWORD="UPDATE USER SET PASSWORD=? WHERE ID=?";

    @Override
    public List<User> findAllEntity() throws DAOException {

        List<User> users =new ArrayList<>();
        try(DBConnection conn= ConnectionPool.getInstance().getConnection();Statement statement=conn.createStatement();
            ResultSet rs=statement.executeQuery(FIND_ALL_USER)) {
            if(rs!=null){
                while (rs.next()) {
                    User user =getUser(rs);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DAOException();
        }

        return users;
    }

    @Override
    public User findEntityById(int id) throws DAOException {

        User user = new User();

        try(DBConnection connection=ConnectionPool.getInstance().getConnection()) {
            PreparedStatement pStatement=connection.prepareStatement(FIND_USER_BY_ID);
            ResultSet rs=pStatement.executeQuery();
            pStatement.setInt(1,id);
            if(rs!=null){
               user=getUser(rs);
            }
            pStatement=connection.prepareStatement(FIND_SUBJECT_USER);
            rs=pStatement.executeQuery();
                pStatement.setInt(1,id);
                if(rs!=null) {
                    while (rs.next()){
                        Integer subjectId=rs.getInt("SUBJECT_ID");
                        Integer mark=rs.getInt("USER_SUBJECT_MARK");
                        user.put(subjectId,mark);
                    }
                }
        } catch (SQLException e) {
            throw new DAOException();
        }

        return user;
    }

    @Override
    public boolean delete(int id) throws DAOException {

        DBConnection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement pStatement = connection.prepareStatement(DELETE_USER_BY_ID)) {
            pStatement.setInt(1,id);
            return pStatement.executeUpdate()==7;
        } catch (SQLException e) {
           throw new DAOException();
        }

    }

        @Override
    public void create(User user) throws DAOException {
        DBConnection connection=ConnectionPool.getInstance().getConnection();
        PreparedStatement pStatement=null;

        try{
            connection.setAutoCommit(false);
            pStatement=connection.prepareStatement(INSERT_USER,Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, user.getFirstName());
            pStatement.setString(2, user.getLastName());
            pStatement.setDate(3, user.getBirthday());
            pStatement.setInt(4, user.getCertificateMark());
            pStatement.setString(5,user.getLogin());
            pStatement.setString(6,user.getPassword());
            pStatement.setString(7,user.getEmail());
            pStatement.executeUpdate();
            ResultSet resultSet=pStatement.getGeneratedKeys();
            if (resultSet.next()){
                user.setUserId(resultSet.getInt(1));
            }

            for(Map.Entry<Integer,Integer> entry: user.getSubjectMark().entrySet()){
                pStatement=connection.prepareStatement(INSERT_STUDENTS_SUBJECTS);
                pStatement.setInt(1, user.getUserId());
                pStatement.setInt(2,entry.getKey());
                pStatement.setInt(3,entry.getValue());
                pStatement.executeUpdate();

            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
               throw new DAOException();

            }

        }
        finally {
            try {
                connection.close();
                pStatement.close();
            } catch (SQLException e) {
               throw new DAOException();
            }

        }


    }


    @Override
    public User update(User user) throws DAOException {
        DBConnection connection=ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement pStatement=connection.prepareStatement(UPDATE_USER);
            pStatement.setInt(1, user.getUserId());
            pStatement.setString(2, user.getFirstName());
            pStatement.setString(3, user.getLastName());
            pStatement.setDate(4, user.getBirthday());
            pStatement.setInt(5, user.getCertificateMark());
            pStatement.setInt(6, user.getSpecialityId());
            pStatement.setString(7,user.getPassword());
            pStatement.setString(8,user.getEmail());
            pStatement.executeUpdate();
            for(Map.Entry<Integer,Integer> entry: user.getSubjectMark().entrySet()){
                pStatement=connection.prepareStatement(UPDATE_SUBJECT_USER);
                pStatement.setInt(1, user.getUserId());
                pStatement.setInt(2,entry.getKey());
                pStatement.setInt(3,entry.getValue());
                pStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new DAOException();
        }

        return user;
    }

    public boolean findUserByLogin(String login) throws DAOException {
        DBConnection connection;
        connection=ConnectionPool.getInstance().getConnection();
        try{
            PreparedStatement pStetement=connection.prepareStatement(FIND_USER_BY_LOGIN);
            pStetement.setString(1,login);
            ResultSet rs=pStetement.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
           throw new DAOException();
        }
    }


    public User findUserByPasswordAndLogin(String login,String password) throws DAOException {
        DBConnection connection;
        User user=null;
        try{
            connection=ConnectionPool.getInstance().getConnection();
            PreparedStatement pStatement=connection.prepareStatement(FIND_USER_BY_PASSWORD_AND_LOGIN);

            pStatement.setString(1,login);
            pStatement.setString(2,password);
            ResultSet rs=pStatement.executeQuery();
            if(rs.next()) {
                user=getUser(rs);
            }

        } catch (SQLException e) {
            throw new DAOException();
        }
       return user;
    }

    private User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("ID"));
        user.setFirstName(rs.getString("FIRST_NAME"));
        user.setLastName(rs.getString("LAST_NAME"));
        user.setBirthday(rs.getDate("BIRTHDAY"));
        user.setCertificateMark(rs.getInt("CERTIFICATE_AVG"));
        user.setSpecialityId(rs.getInt("SPECIALITY_ID"));
        user.setLogin(rs.getString("LOGIN"));
        user.setPassword(rs.getString("PASSWORD"));
        user.setEmail(rs.getString("EMAIL"));

    return user;
    }

    @Override
    public Map<Subject, Integer> findUserSubjectsAndScores(int id) throws DAOException {
        Map<Subject,Integer> subjects=new HashMap<>();
        try(DBConnection connection=ConnectionPool.getInstance().getConnection();
        PreparedStatement pStatement=connection.prepareStatement(FIND_USER_SUBJECTS_AND_SCORE)){
            pStatement.setInt(1,id);
            ResultSet rs=pStatement.executeQuery();
            if(rs!=null){
                while (rs.next()){
                    Subject subject=new Subject();
                    subject.setSubjectId(rs.getInt("ID"));
                    subject.setSubjectName("SUBJECT_NAME");
                    int score=rs.getInt("USER_MARK");
                    subjects.put(subject,score);
                }
            }

        } catch (SQLException e) {
            throw new DAOException();
        }
        return subjects;
    }

    @Override
    public void changeUserPassword(Integer userId, String password) throws DAOException {
        try(DBConnection connection=ConnectionPool.getInstance().getConnection();
            PreparedStatement pStatement=connection.prepareStatement(CHANGE_USER_PASSWORD)){
            pStatement.setString(1,password);
            pStatement.setInt(1,userId);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException();
        }
    }
}
