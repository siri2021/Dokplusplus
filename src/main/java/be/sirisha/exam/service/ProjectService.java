package be.sirisha.exam.service;

import be.sirisha.exam.data.ProjectDAO;
import be.sirisha.exam.model.Project;

import java.sql.SQLException;
import java.util.List;

public class ProjectService {
    private  ProjectDAO projectDAO = new ProjectDAO();
    public List<Project> getAllProjects() {
        return projectDAO.getAllProjects();
    }

    public void addProject(Project project) throws SQLException {
        projectDAO.addProject(project);
    }

    public List<Project> showProjectsStartingToday() {
        return projectDAO.showProjectsStartingToday();
    }

    public List<Project> showCurrentProjects() {
        return projectDAO.showCurrentProjects();
    }
}
