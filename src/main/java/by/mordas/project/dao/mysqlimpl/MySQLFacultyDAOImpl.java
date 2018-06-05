package by.mordas.project.dao.mysqlimpl;

import by.mordas.project.dao.DAOException;
import by.mordas.project.dao.FacultyDAO;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.pool.ConnectionPool;
import by.mordas.project.pool.PooledConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLFacultyDAOImpl implements FacultyDAO {
    private static final String FIND_ALL_FACULTY="SELECT ID,FACULTY_NAME FROM FACULTY";
    private static final String FIND_FACULTY_BY_ID="SELECT ID,FACULTY_NAME FROM FACULTY WHERE ID=?";
    private static final String CREATE_FACULTY="INSERT INTO FACULTY(FACULTY_NAME) VALUES(?)";
    private static final String UPDATE_FACULTY="UPDATE FACULTY SET FACULTY_NAME=? WHERE ID=?";
    private static final String DELETE_FACULTY="DELETE FROM FACULTY WHERE ID=?";
    private static final String FIND_ALL_SPECIALITY_BY_FACULTY_ID="SELECT SPECIALITY.ID,SPECIALITY_NAME,RECRUITMENT_PLAN, FACULTY_ID,START_REGISTRATION,END_REGISTRATION FROM SPECIALITY" +
            " INNER JOIN FACULTY ON SPECIALITY.FACULTY_ID=FACULTY.ID AND FACULTY.ID=?";


    @Override
    public List<Faculty> findAllEntity() throws DAOException {
        List<Faculty> faculties=null;
        try(PooledConnection connection=ConnectionPool.getInstance().getConnection();
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(FIND_ALL_FACULTY)) {
            if (rs != null) {
                faculties=new ArrayList<>();
                while (rs.next()) {
                    Faculty faculty=new Faculty();
                    faculty.setFacultyId(rs.getInt("ID"));
                    faculty.setFacultyName(rs.getString("FACULTY_NAME"));
                    faculties.add(faculty);
                }
            }
        } catch (SQLException e) {
           throw new DAOException("Exception in findAllEntity method:",e);
        }
        return faculties;
    }

    @Override
    public Faculty findEntityById(long id) throws DAOException {
        Faculty faculty=null;
        try(PooledConnection conn= ConnectionPool.getInstance().getConnection();
            PreparedStatement pStatement=conn.prepareStatement(FIND_FACULTY_BY_ID)) {
            pStatement.setLong(1,id);
            ResultSet rs=pStatement.executeQuery();
            if(rs.next()){
                faculty=new Faculty();
                faculty.setFacultyId(rs.getInt("ID"));
                faculty.setFacultyName(rs.getString("FACULTY_NAME"));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in findEntityById method",e);
        }
        return faculty;
    }

    @Override
    public boolean delete(long id) throws DAOException {

        try (PooledConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement pStatement = connection.prepareStatement(DELETE_FACULTY)) {
            pStatement.setLong(1,id);
            return pStatement.executeUpdate()==1;
        } catch (SQLException e) {
            throw new DAOException("Exception in delete method",e);
        }
    }

    @Override
    public void create(Faculty faculty) throws DAOException {
        try(PooledConnection connection=ConnectionPool.getInstance().getConnection(); PreparedStatement pStatement=connection.prepareStatement(CREATE_FACULTY,Statement.RETURN_GENERATED_KEYS)){
            pStatement.setString(1,faculty.getFacultyName());
            pStatement.executeUpdate();
            ResultSet resultSet=pStatement.getGeneratedKeys();
            if(resultSet.next()){
                faculty.setFacultyId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
           throw new DAOException("Exception in create method",e);
        }
    }

    @Override
    public Faculty update(Faculty faculty) throws DAOException {
        try(PooledConnection connection=ConnectionPool.getInstance().getConnection(); PreparedStatement pStatement=connection.prepareStatement(UPDATE_FACULTY)){
            pStatement.setString(1,faculty.getFacultyName());
            pStatement.setLong(2,faculty.getFacultyId());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in update method",e);
        }
        return faculty;
    }

    public List<Speciality> findSpecialityFromFaculty(long facultyId) throws DAOException {
        List<Speciality> specialties=null;
        try(PooledConnection connection=ConnectionPool.getInstance().getConnection();
            PreparedStatement pStatement=connection.prepareStatement(FIND_ALL_SPECIALITY_BY_FACULTY_ID)) {
            pStatement.setLong(1,facultyId);
            ResultSet rs=pStatement.executeQuery();
            if(rs!=null){
                specialties=new ArrayList<>();
                while (rs.next()){
                    Speciality speciality=new Speciality();
                    speciality.setSpecialityId(rs.getLong("ID"));
                    speciality.setSpecialityName(rs.getString("SPECIALITY_NAME"));
                    speciality.setRecruitmentPlan(rs.getInt("RECRUITMENT_PLAN"));
                    speciality.setFacultyId(rs.getInt("FACULTY_ID"));
                    speciality.setStartRegistration(rs.getTimestamp("START_REGISTRATION").toLocalDateTime());
                    speciality.setEndRegistration(rs.getTimestamp("END_REGISTRATION").toLocalDateTime());
                    specialties.add(speciality);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in findSpecialityFromFaculty",e);
        }
        return specialties;
    }
}