package be.sirisha.exam.service;

import be.sirisha.exam.data.WorkDoneDAO;
import be.sirisha.exam.model.EmpProj;
import be.sirisha.exam.model.WorkDone;

import java.sql.SQLException;
import java.util.List;

public class WorkDoneService {
    WorkDoneDAO workDoneDao=new WorkDoneDAO();
    public List<WorkDone> workDoneList() throws SQLException {
        return  workDoneDao.workDoneList();
    }

    public List<EmpProj> emp_proj() throws SQLException {
        return  workDoneDao.emp_proj();
    }


    public List<EmpProj> projectsWithProfits() throws SQLException {

        return  workDoneDao.projectsWithProfits();
    }

    public void addEmpProj(WorkDone workdone) throws SQLException{
        workDoneDao.addEmpProject(workdone);
    }

    public void updateEmpProj(WorkDone workdone) throws SQLException{
        workDoneDao.updateEmpProject(workdone);
    }

    public void delete(WorkDone workdone) throws SQLException{
        workDoneDao.delete(workdone);
    }
    public List<EmpProj> showEmployeesInAProject(int projectId) throws SQLException {

        return  workDoneDao.showEmployeesInAProject(projectId);
    }

    public List<EmpProj> showEmployeesInAProject(int projectId, int empId) throws SQLException {

        return  workDoneDao.showEmployeesInAProject(projectId);
    }

}
