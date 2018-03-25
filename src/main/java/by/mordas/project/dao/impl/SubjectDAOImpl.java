package by.mordas.project.dao.impl;

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
    private static final String

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
    public Subject findEntityById(int id) {

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

        }

        return subject;
    }

    @Override
    public boolean delete(int id) {

        try (DBConnection connection = ConnectionPool.getInstance().getConnection();PreparedStatement pStatement = connection.prepareStatement(DELETE_SUBJECT)) {
            pStatement.setInt(1,id);
            return pStatement.executeUpdate()==2;
        } catch (SQLException e) {

        }
        return false;
    }

    @Override
    public void create(Subject subject) {

        try( DBConnection connection=ConnectionPool.getInstance().getConnection();PreparedStatement pStatement=connection.prepareStatement(CREATE_SUBJECT)){
            subject.setSubjectId(subject.getSubjectId());
            subject.setSubjectName(subject.getSubjectName());
            int result=pStatement.executeUpdate();
        } catch (SQLException e) {

        }

    }

    @Override
    public Subject update(Subject subject) {

        try(DBConnection connection=ConnectionPool.getInstance().getConnection();PreparedStatement pStatement=connection.prepareStatement(UPDATE_SUBJECT)){
            pStatement.setInt(1,subject.getSubjectId());
            pStatement.setString(2,subject.getSubjectName());
            pStatement.executeUpdate();
        } catch (SQLException e) {

        }

        return subject;
    }

    @Override
    public List<Subject> findSubjectByUserId(int id) {

        return null;
    }
}
