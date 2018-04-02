package by.mordas.project.dao.impl;

import by.mordas.project.dao.DAOException;
import by.mordas.project.dao.SubjectDAO;
import by.mordas.project.entity.Subject;
import by.mordas.project.pool.ConnectionPool;
import by.mordas.project.pool.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {
    private static final String FIND_ALL_SUBJECT="SELECT ID,SUBJECT_NAME FROM SUBJECT";
    private static final String FIND_SUBJECT_BY_ID="SELECT ID,SUBJECT_NAME FROM SUBJECT WHERE ID=?";
    private static final String CREATE_SUBJECT="INSERT INTO SUBJECT(ID,SUBJECT_NAME) VALUES(?,?)";
    private static final String UPDATE_SUBJECT="UPDATE SUBJECT SET ID=? SUBJECT_NAME=?";
    private static final String DELETE_SUBJECT="DELETE FROM SUBJECT WHERE ID=?";
    private static final String FIND_USER_SUBJECTS="SELECT SUBJECT.SUBJECT_NAME,USER_MARK FROM SUBJECT INNER JOIN " +
            "USER_SUBJECT_MARK as USER ON SUBJECT.ID = USER.id_subject AND ID_USER=?";
    private static final String FIND_SUBJECTS_FOR_SPECIALITY="SELECT SUBJECT_NAME,SUBJECT.ID from SUBJECT INNER JOIN SUBJECT_FOR_SPECIALITY S ON SUBJECT.ID = S.ID_SUBJECT WHERE ID_SPECIALITY=?";

    @Override
    public List<Subject> findAllEntity() {
        List<Subject> subjects=new ArrayList<>();
        try(DBConnection connection= ConnectionPool.getInstance().getConnection();Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(FIND_ALL_SUBJECT)){
            if (rs != null) {
                while (rs.next()) {
                    Subject subject=new Subject();
                    subject.setSubjectId(rs.getInt("ID"));
                    subject.setSubjectName(rs.getString("FACULTY_NAME"));
                    subjects.add(subject);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
    }

    @Override
    public Subject findEntityById(int id) throws DAOException {

        Subject subject=new Subject();
        try( DBConnection conn= ConnectionPool.getInstance().getConnection();PreparedStatement pStatement=conn.prepareStatement(FIND_SUBJECT_BY_ID)
           ) {
            pStatement.setInt(1,id);
            ResultSet rs=pStatement.executeQuery();
            if(rs.next()){
                subject.setSubjectId(rs.getInt("ID"));
                subject.setSubjectName(rs.getString("SUBJECT_NAME"));
            }
        } catch (SQLException e) {
            throw new DAOException();
        }

        return subject;
    }

    @Override
    public boolean delete(int id) throws DAOException {

        try (DBConnection connection = ConnectionPool.getInstance().getConnection();PreparedStatement pStatement = connection.prepareStatement(DELETE_SUBJECT)) {
            pStatement.setInt(1,id);
            return pStatement.executeUpdate()==2;
        } catch (SQLException e) {
            throw new DAOException();
        }
    }

    @Override
    public void create(Subject subject) throws DAOException {

        try( DBConnection connection=ConnectionPool.getInstance().getConnection();PreparedStatement pStatement=connection.prepareStatement(CREATE_SUBJECT)){
            subject.setSubjectId(subject.getSubjectId());
            subject.setSubjectName(subject.getSubjectName());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException();
        }

    }

    @Override
    public Subject update(Subject subject) throws DAOException {

        try(DBConnection connection=ConnectionPool.getInstance().getConnection();PreparedStatement pStatement=connection.prepareStatement(UPDATE_SUBJECT)){
            pStatement.setInt(1,subject.getSubjectId());
            pStatement.setString(2,subject.getSubjectName());
            pStatement.executeUpdate();
            return subject;
        } catch (SQLException e) {
            throw new DAOException();
        }


    }

    @Override
    public List<Subject> findSubjectByUserId(int id) throws DAOException {
        List<Subject> subjects=null;
        try(DBConnection connection=ConnectionPool.getInstance().getConnection();
        PreparedStatement pStatement=connection.prepareStatement(FIND_USER_SUBJECTS)){
            pStatement.setInt(1,id);
            ResultSet rs=pStatement.executeQuery();
            if(rs!=null){
                while (rs.next()){
                    Subject subject=new Subject();

                }
            }
        } catch (SQLException e) {
            throw new DAOException();
        }
        return subjects;
    }

    @Override
    public List<Subject> findSubjectsBySpecialityId(int id) throws DAOException {
        List<Subject> subjects=new ArrayList<>();
        try(DBConnection connection=ConnectionPool.getInstance().getConnection();
            PreparedStatement pStatement=connection.prepareStatement(FIND_SUBJECTS_FOR_SPECIALITY)) {
            pStatement.setInt(1,id);
            ResultSet resultSet=pStatement.executeQuery();
            if(resultSet!=null){
                while (resultSet.next()){
                    Subject subject=new Subject();
                    subject.setSubjectName(resultSet.getString("SUBJECT_NAME"));
                    subject.setSubjectId(resultSet.getInt("ID"));
                    subjects.add(subject);
                }
            }
        } catch (SQLException e) {
            throw new DAOException();
        }
        return subjects;
    }
}
