package by.mordas.project.dao.mysqlimpl;

import by.mordas.project.dao.DAOException;
import by.mordas.project.dao.SubjectDAO;
import by.mordas.project.entity.Subject;
import by.mordas.project.pool.ConnectionPool;
import by.mordas.project.pool.PooledConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLSubjectDAOImpl implements SubjectDAO {
    private static final String FIND_ALL_SUBJECT="SELECT ID,SUBJECT_NAME FROM SUBJECT";
    private static final String FIND_SUBJECT_BY_ID="SELECT ID,SUBJECT_NAME FROM SUBJECT WHERE ID=?";
    private static final String CREATE_SUBJECT="INSERT INTO SUBJECT(ID,SUBJECT_NAME) VALUES(?,?)";
    private static final String UPDATE_SUBJECT="UPDATE SUBJECT SET ID=? SUBJECT_NAME=?";
    private static final String DELETE_SUBJECT="DELETE FROM SUBJECT WHERE ID=?";
    private static final String FIND_USER_SUBJECTS="SELECT SUBJECT.SUBJECT_NAME,USER_MARK FROM SUBJECT INNER JOIN " +
            "USER_SUBJECT_MARK as USER ON SUBJECT.ID = USER.id_subject AND ID_USER=?";
    private static final String FIND_SUBJECTS_FOR_SPECIALITY="SELECT SUBJECT_NAME,SUBJECT.ID from SUBJECT INNER JOIN SUBJECT_FOR_SPECIALITY S ON SUBJECT.ID = S.ID_SUBJECT WHERE ID_SPECIALITY=?";

    @Override
    public List<Subject> findAllEntity() throws DAOException {
        List<Subject> subjects=new ArrayList<>();
        try(PooledConnection connection= ConnectionPool.getInstance().getConnection(); Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(FIND_ALL_SUBJECT)){
            if (rs != null) {
                while (rs.next()) {
                    Subject subject=new Subject();
                    subject.setSubjectId(rs.getLong("ID"));
                    subject.setSubjectName(rs.getString("FACULTY_NAME"));
                    subjects.add(subject);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in findAllEntity method",e);
        }

        return subjects;
    }

    @Override
    public Subject findEntityById(long id) throws DAOException {

        Subject subject=new Subject();
        try(PooledConnection conn= ConnectionPool.getInstance().getConnection(); PreparedStatement pStatement=conn.prepareStatement(FIND_SUBJECT_BY_ID)
           ) {
            pStatement.setLong(1,id);
            ResultSet rs=pStatement.executeQuery();
            if(rs.next()){
                subject.setSubjectId(rs.getLong("ID"));
                subject.setSubjectName(rs.getString("SUBJECT_NAME"));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in findEntityById method",e);
        }

        return subject;
    }

    @Override
    public boolean delete(long id) throws DAOException {

        try (PooledConnection connection = ConnectionPool.getInstance().getConnection(); PreparedStatement pStatement = connection.prepareStatement(DELETE_SUBJECT)) {
            pStatement.setLong(1,id);
            return pStatement.executeUpdate()==2;
        } catch (SQLException e) {
            throw new DAOException("Exception in delete method",e);
        }
    }

    @Override
    public void create(Subject subject) throws DAOException {

        try(PooledConnection connection=ConnectionPool.getInstance().getConnection(); PreparedStatement pStatement=connection.prepareStatement(CREATE_SUBJECT)){
            subject.setSubjectId(subject.getSubjectId());
            subject.setSubjectName(subject.getSubjectName());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in create method",e);
        }

    }

    @Override
    public Subject update(Subject subject) throws DAOException {

        try(PooledConnection connection=ConnectionPool.getInstance().getConnection(); PreparedStatement pStatement=connection.prepareStatement(UPDATE_SUBJECT)){
            pStatement.setLong(1,subject.getSubjectId());
            pStatement.setString(2,subject.getSubjectName());
            pStatement.executeUpdate();
            return subject;
        } catch (SQLException e) {
            throw new DAOException("Exception in update method",e);
        }

    }


    @Override
    public List<Subject> findSubjectsBySpecialityId(long id) throws DAOException {
        List<Subject> subjects=new ArrayList<>();
        try(PooledConnection connection=ConnectionPool.getInstance().getConnection();
            PreparedStatement pStatement=connection.prepareStatement(FIND_SUBJECTS_FOR_SPECIALITY)) {
          pStatement.setLong(1,id);
            ResultSet resultSet=pStatement.executeQuery();
            if(resultSet!=null){
                while (resultSet.next()){
                    Subject subject=new Subject();
                    subject.setSubjectName(resultSet.getString("SUBJECT_NAME"));
                    subject.setSubjectId(resultSet.getLong("ID"));
                    subjects.add(subject);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in findSubjectsBySpecialityId method",e);
        }
        return subjects;
    }
}
