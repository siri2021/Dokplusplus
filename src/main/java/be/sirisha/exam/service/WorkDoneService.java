package be.sirisha.exam.service;

import be.sirisha.exam.data.WorkDoneDAO;
import be.sirisha.exam.model.EmpProj;
import be.sirisha.exam.model.Project;

import java.sql.SQLException;
import java.util.List;

public class WorkDoneService {
    WorkDoneDAO workDoneDao=new WorkDoneDAO();

    public List<EmpProj> emp_proj() throws SQLException {
        return  workDoneDao.emp_proj();
    }


    public List<EmpProj> projetsWithProfits() throws SQLException {

        return  workDoneDao.projectsWithProfits();
    }
}
