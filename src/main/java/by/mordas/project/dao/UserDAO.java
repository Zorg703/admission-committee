package by.mordas.project.dao;
import by.mordas.project.entity.User;
import by.mordas.project.entity.Subject;
import by.mordas.project.pool.ConnectionPool;
import by.mordas.project.pool.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UserDAO extends AbstractDAO<Integer,User> {
    private static final String FIND_USER_BY_ID ="SELECT ID,FIRST_NAME,LAST_NAME,BIRTHDAY,CERTIFICATE_MARK," +
            "SPECIALITY_ID,LOGIN,PASSWORD,EMAIL FROM USER WHERE ID=?";
    private static final String FIND_ALL_USER="SELECT ID,FIRST_NAME,LAST_NAME,BIRTHDAY,CERTIFICATE_MARK," +
            "SPECIALITY_ID FROM USER ";
    private static final String INSERT_USER ="INSERT INTO USER(FIRST_NAME,LAST_NAME,BIRTHDAY," +
            "CERTIFICATE_MARK,SPECIALITY_ID,LOGIN,PASSWORD,EMAIL) VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE_USER ="UPDATE USER SET ID=?,FIRST_NAME=?,LAST_NAME=?," +
            "BIRTHDAY=?,CERTIFICATE_AVG=?,SPECIALITY_ID=?,LOGIN=?,PASSWORD=?,EMAIL=?";
    private static final String UPDATE_SUBJECT_USER="UPDATE USER_SUBJECT_MARK SET USER_ID=?, SUBJECT_ID=?,USER_MARK=?";
    private static final String DELETE_USER_BY_ID="DELETE FROM USER WHERE ID=?";
    private static final String INSERT_STUDENTS_SUBJECTS="INSERT INTO USER_SUBJECT_MARK(SUBJECT_ID,USER_ID," +
            "USER_MARK) VALUES(?,?,?) ";
    private static final String FIND_USER_BY_PASSWORD_AND_LOGIN="SELECT FIRST_NAME FROM USER WHERE LOGIN=? and PASSWORD=?";




    @Override
    public List<User> findAll() {
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
        try(PreparedStatement pStatement=conn.prepareStatement(FIND_USER_BY_ID);
            ResultSet rs=pStatement.executeQuery()) {
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
    public boolean create(User user)
    {
        DBConnection connection=ConnectionPool.getConnection();

        try{
            PreparedStatement pStatement=connection.prepareStatement(INSERT_USER);
            pStatement.setString(1, user.getFirstName());
            pStatement.setString(2, user.getLastName());
            pStatement.setDate(3, user.getBirthday());
            pStatement.setInt(4, user.getCertificateMark());
            pStatement.setInt(5, user.getSpecialityId());
            pStatement.setString(6,user.getLogin());
            pStatement.setString(7,user.getPassword());
            pStatement.setString(8,user.getEmail());
            int result=pStatement.executeUpdate();
            ResultSet resultSet=pStatement.getGeneratedKeys();
            while (resultSet.next()){
                user.setUserId(resultSet.getInt("ID"));
            }

            for(Map.Entry<Subject,Integer> entry: user.getSubjectMark().entrySet()){
                pStatement=connection.prepareStatement(INSERT_STUDENTS_SUBJECTS);
                pStatement.setInt(1, user.getUserId());
                pStatement.setInt(2,entry.getKey().getSubjectId());
                pStatement.setInt(3,entry.getValue());
            }
            return result==7;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            ConnectionPool.closeConnection(connection);
        }


        return false;
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

    public User findUserByPasswordAndLogin(String login,String password){
        DBConnection connection;
        return null;
    }


}
