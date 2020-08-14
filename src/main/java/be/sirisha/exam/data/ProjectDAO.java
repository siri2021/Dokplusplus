package be.sirisha.exam.data;

import be.sirisha.exam.model.Employee;
import be.sirisha.exam.model.Project;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static be.sirisha.exam.validations.Validator.validateProject;

public class ProjectDAO {
    public List<Project> getAllProjects() {

        try {

            Connection con = ConnectionFactory.getConnection();

        String sql= "SELECT * FROM ProjectData ;";

        Statement st=con.createStatement();

        ResultSet rs=st.executeQuery(sql);

        return parsedProjecctData(rs);

        } catch (SQLException e) {
            e.printStackTrace();

            return Collections.emptyList();
        }

    }

    static List<Project> parsedProjecctData(ResultSet rs) throws SQLException {
        List<Project> Projects=new ArrayList<>();
        while (rs.next()) {
            Project project = new Project();
            project.setProjectId(rs.getInt("ProjectId"));
            project.setStartDate(rs.getDate("StartDate").toLocalDate());
            project.setDescription(rs.getString("Description"));
            project.setPrice(rs.getFloat("Price"));
            project.setExpectedEndDate(rs.getDate("ExpectedEndDate").toLocalDate());

            Projects.add(project);
        }
        return Projects;
    }

    public void addProject(Project project) throws SQLException {
        if(validateProject(project)) {
            Connection conn = ConnectionFactory.getConnection();

            String sql = "INSERT INTO ProjectData VALUES(?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, project.getProjectId());
            ps.setDate(2, Date.valueOf(project.getStartDate()));
            ps.setString(3, project.getDescription());
            ps.setFloat(4, (float) project.getPrice());
            ps.setDate(5, Date.valueOf(project.getExpectedEndDate()));

            System.out.println("inserted");
            ps.executeUpdate();
        }else
            System.out.println("invalid data");
    }

    public List<Project> showProjectsStartingToday() {
        try{
        Connection con=ConnectionFactory.getConnection();
        String query = "SELECT * FROM ProjectData where StartDate=CURDATE()";
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        return parsedProjecctData(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Project> showCurrentProjects() {
        try{
        Connection con=ConnectionFactory.getConnection();
        String query = "SELECT * FROM ProjectData where CURDATE() BETWEEN StartDate  AND ExpectedEndDate";
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);

            return parsedProjecctData(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
