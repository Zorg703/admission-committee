package by.mordas.project.dao.impl;

import by.mordas.project.dao.DAOException;
import by.mordas.project.dao.FacultyDAO;
import by.mordas.project.entity.Faculty;
import by.mordas.project.entity.Speciality;
import by.mordas.project.pool.ConnectionPool;
import by.mordas.project.pool.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FacultyDAOImpl implements FacultyDAO {
    private static final String FIND_ALL_FACULTY="SELECT ID,FACULTY_NAME FROM FACULTY";
    private static final String FIND_FACULTY_BY_ID="SELECT ID,FACULTY_NAME WHERE ID=?";
    private static final String CREATE_FACULTY="INSERT INTO FACULTY(FACULTY_NAME) VALUES(?,?)";
    private static final String UPDATE_FACULTY="UPDATE FACULTY SET ID=? FACULTY_NAME=?";
    private static final String DELETE_FACULTY="DELETE FROM FACULTY WHERE ID=?";
    private static final String FIND_ALL_SPECIALITY_BY_FACULTY_ID="SELECT SPECIALITY.ID,SPECIALITY_NAME,RECRUITMENT_PLAN, FACULTY_ID FROM SPECIALITY" +
            " INNER JOIN FACULTY ON SPECIALITY.FACULTY_ID=FACULTY.ID AND FACULTY.ID=?";


    @Override
    public List<Faculty> findAllEntity() throws DAOException {

        List<Faculty> faculties=new ArrayList<>();
        try(DBConnection connection=ConnectionPool.getInstance().getConnection();Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(FIND_ALL_FACULTY)) {
            if (rs != null) {
                while (rs.next()) {
                    Faculty faculty=new Faculty();
                    faculty.setFacultyId(rs.getInt("ID"));
                    faculty.setFacultyName(rs.getString("FACULTY_NAME"));
                    faculties.add(faculty);
                }
            }
        } catch (SQLException e) {
           throw new DAOException();
        }

        return faculties;
    }

    @Override
    public Faculty findEntityById(int id) throws DAOException {
        Faculty faculty=new Faculty();
        try(DBConnection conn= ConnectionPool.getInstance().getConnection();
            PreparedStatement pStatement=conn.prepareStatement(FIND_FACULTY_BY_ID)) {
            pStatement.setInt(1,id);
            ResultSet rs=pStatement.executeQuery();
            if(rs!=null){
                faculty.setFacultyId(rs.getInt("ID"));
                faculty.setFacultyName(rs.getString("FACULTY_NAME"));

            }
        } catch (SQLException e) {
            throw new DAOException();
        }

        return faculty;
    }

    @Override
    public boolean delete(int id) throws DAOException {

        try (DBConnection connection = ConnectionPool.getInstance().getConnection();PreparedStatement pStatement = connection.prepareStatement(DELETE_FACULTY)) {
            pStatement.setInt(1,id);
            return pStatement.executeUpdate()==2;
        } catch (SQLException e) {
            throw new DAOException();
        }
    }

    @Override
    public void create(Faculty faculty) throws DAOException {
        try(DBConnection connection=ConnectionPool.getInstance().getConnection();PreparedStatement pStatement=connection.prepareStatement(CREATE_FACULTY)){
            pStatement.setString(1,faculty.getFacultyName());
        } catch (SQLException e) {
           throw new DAOException();
        }


    }

    @Override
    public Faculty update(Faculty faculty) throws DAOException {

        try(DBConnection connection=ConnectionPool.getInstance().getConnection();PreparedStatement pStatement=connection.prepareStatement(UPDATE_FACULTY)){
            pStatement.setInt(1,faculty.getFacultyId());
            pStatement.setString(2,faculty.getFacultyName());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException();
        }

        return faculty;
    }

    public List<Speciality> findSpecialityFromFaculty() throws DAOException {
        List<Speciality> specialties=new ArrayList<>();
        try(DBConnection connection=ConnectionPool.getInstance().getConnection()) {
            PreparedStatement pStatement=connection.prepareStatement(FIND_ALL_SPECIALITY_BY_FACULTY_ID);
            ResultSet rs=pStatement.executeQuery();
            if(rs!=null){
                while (rs.next()){

                }
            }
        } catch (SQLException e) {
            throw new DAOException();
        }
        return specialties;

    }

}