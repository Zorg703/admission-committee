package by.mordas.project.dao.impl;

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
            "BIRTHDAY=?,CERTIFICATE_AVG=?,SPECIALITY_ID=?,LOGIN=?,PASSWORD=?,EMAIL=?";
    private static final String UPDATE_SUBJECT_USER="UPDATE USER_SUBJECT_MARK SET ID_USER=?, ID_SUBJECT=?,USER_MARK=?";
    private static final String DELETE_USER_BY_ID="DELETE FROM USER WHERE ID=?";
    private static final String INSERT_STUDENTS_SUBJECTS="INSERT INTO USER_SUBJECT_MARK(ID_USER,ID_SUBJECT," +
            "USER_MARK) VALUES(?,?,?) ";
    private static final String FIND_USER_BY_PASSWORD_AND_LOGIN="SELECT * FROM USER WHERE LOGIN=? and PASSWORD=?";
    private static final String FIND_SUBJECT_USER="SELECT * FROM USER_SUBJECT_MARK WHERE ID=?";
    private static final String FIND_USER_BY_LOGIN="SELECT * FROM USER WHERE LOGIN=?";



    @Override
    public List<User> findAllEntity() {
        DBConnection conn= ConnectionPool. getConnection();
        List<User> users =new ArrayList<>();
        try(Statement statement=conn.createStatement();
            ResultSet rs=statement.executeQuery(FIND_ALL_USER)) {
            if(rs!=null){
                while (rs.next()) {
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
        finally {
           ConnectionPool.closeConnection(conn);
        }
        return users;
    }

    @Override
    public User findEntityById(int id) {
        DBConnection conn= ConnectionPool.getConnection();
        User user = new User();

        try {
            PreparedStatement pStatement=conn.prepareStatement(FIND_USER_BY_ID);
            ResultSet rs=pStatement.executeQuery();
            pStatement.setInt(1,id);
            if(rs!=null){
                user.setUserId(rs.getInt("ID"));
                user.setFirstName(rs.getString("FIRST_NAME"));
                user.setLastName(rs.getString("LAST_NAME"));
                user.setBirthday(rs.getDate("BIRTHDAY"));
                user.setCertificateMark(rs.getInt("CERTIFICATE_AVG"));
                user.setSpecialityId(rs.getInt("SPECIALITY_ID"));
                user.setLogin(rs.getString("LOGIN"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setEmail(rs.getString("EMAIL"));
            }
            pStatement=conn.prepareStatement(FIND_SUBJECT_USER);
            rs=pStatement.executeQuery() ;
                pStatement.setInt(1,id);
                if(rs!=null) {
                    while (rs.next()){
                        Subject subject=new Subject();
                        subject.setSubjectId(rs.getInt("SUBJECT_ID"));
                        Integer mark=rs.getInt("USER_SUBJECT_MARK");
                        user.put(subject,mark);
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionPool.closeConnection(conn);
        }
        return user;
    }

    @Override
    public boolean delete(int id) {

        DBConnection connection = ConnectionPool.getConnection();
        try (PreparedStatement pStatement = connection.prepareStatement(DELETE_USER_BY_ID)) {
            pStatement.setInt(1,id);
            return pStatement.executeUpdate()==7;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionPool.closeConnection(connection);
        }
        return false;
    }

        @Override
    public void create(User user)
    {
        DBConnection connection=ConnectionPool.getConnection();
        PreparedStatement pStatement=null;
        PreparedStatement pStatement2=null;
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

            for(Map.Entry<Subject,Integer> entry: user.getSubjectMark().entrySet()){
                pStatement2=connection.prepareStatement(INSERT_STUDENTS_SUBJECTS);
                pStatement2.setInt(1, user.getUserId());
                pStatement2.setInt(2,entry.getKey().getSubjectId());
                pStatement2.setInt(3,entry.getValue());
                pStatement2.executeUpdate();

            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
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


    }


    @Override
    public User update(User user) {
        DBConnection connection=ConnectionPool.getConnection();
        try {
            PreparedStatement pStatement=connection.prepareStatement(UPDATE_USER);
            pStatement.setInt(1, user.getUserId());
            pStatement.setString(2, user.getFirstName());
            pStatement.setString(3, user.getLastName());
            pStatement.setDate(4, user.getBirthday());
            pStatement.setInt(5, user.getCertificateMark());
            pStatement.setInt(6, user.getSpecialityId());
            pStatement.setString(7,user.getLogin());
            pStatement.setString(8,user.getPassword());
            pStatement.setString(9,user.getEmail());
            pStatement.executeUpdate();
            for(Map.Entry<Subject,Integer> entry: user.getSubjectMark().entrySet()){
                pStatement=connection.prepareStatement(UPDATE_SUBJECT_USER);
                pStatement.setInt(1, user.getUserId());
                pStatement.setInt(2,entry.getKey().getSubjectId());
                pStatement.setInt(3,entry.getValue());
                pStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
          ConnectionPool.closeConnection(connection);
        }
        return user;
    }

    public boolean findUserByLogin(String login){
        DBConnection connection;
        connection=ConnectionPool.getConnection();
        try{
            PreparedStatement pStetement=connection.prepareStatement(FIND_USER_BY_LOGIN);
            pStetement.setString(1,login);
            ResultSet rs=pStetement.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new DAOException
        }
        return false;//TODO
    }


    public User findUserByPasswordAndLogin(String login,String password){
        DBConnection connection;
        User user=null;
        try{
            connection=ConnectionPool.getConnection();
            PreparedStatement pStetement=connection.prepareStatement(FIND_USER_BY_PASSWORD_AND_LOGIN);

            pStetement.setString(1,login);
            pStetement.setString(2,password);
            ResultSet rs=pStetement.executeQuery();
            if(rs.next()){
                user=new User();
                user.setFirstName(rs.getString("FIRST_NAME"));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


}
