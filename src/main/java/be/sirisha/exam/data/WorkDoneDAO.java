package be.sirisha.exam.data;

import be.sirisha.exam.model.EmpProj;
import be.sirisha.exam.model.Employee;
import be.sirisha.exam.model.Project;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkDoneDAO {

    public List<EmpProj> emp_proj() throws SQLException {
        try {
            Connection con = ConnectionFactory.getConnection();
            String query = "select  e.empId,CONCAT(e.FirstName, e.LastName) AS empName,p.ProjectId ,p.Description,w.Date,e.Salary,p.Price" +
                    " from  Employee e inner join WorkDone w on e.EmpId=w.EmpId inner join ProjectData p on p.ProjectId=w.ProjectId;";
            Statement st = con.createStatement();
            ResultSet rs = null;
            rs = st.executeQuery(query);
            return parsedEmpProj(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    private List<EmpProj> parsedEmpProj(ResultSet rs) throws SQLException {
        List<EmpProj> empProjs = new ArrayList<>();
        while (rs.next()) {
            EmpProj empProj = new EmpProj();
            empProj.setEmpId(rs.getInt(1));
            empProj.setEmpName(rs.getString(2));
            empProj.setProjId(rs.getInt(3));
            empProj.setProjectDes(rs.getString(4));
            empProj.setDate(rs.getDate(5).toLocalDate());
            empProj.setSalary(rs.getFloat(6));
            empProj.setProjCost(rs.getFloat(7));
            empProjs.add(empProj);
        }
        return empProjs;
    }

    private void calculateProjectProfitability(Project proj) {
//subtract expenditureperproject() from price of the project

    }

    private float calculateHourlywages(Employee emp) {
        /* Each employee works for 22days/month days and 8hours/day
        SALARY_PER _DAY= Salari/22
        SALARY_PER_HOUR=SALARY_PER _DAY/8
        */
         float salary=emp.getSalary();
        float empSalaryPerDay = salary/22;
        float empSalaryPerHour = empSalaryPerDay/8;
        return empSalaryPerHour;
    }

    public List<Project> projectsWithProfits() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        String query="Select distinct ProjectId  from WorkDone";
        Statement st = con.createStatement();
        ResultSet rs = null;
        rs = st.executeQuery(query);

        List<Project> projects=ProjectDAO.parsedProjecctData(rs);
        while(projects.listIterator().hasNext()) {

            calculateProjectProfitability(projects.get(0));
        }
        return projects;
        }

        public void expenditurePerProj(Project project) {
        try{Employee emp=new Employee();
            Connection con = ConnectionFactory.getConnection();
            String query="Select EmpId  from WorkDone where ProjectId=?";
            PreparedStatement ps= con.prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);
            while(rs.next()) {
                emp.setEmployeeId(rs.getInt(1));
                calculateHourlywages(emp);
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


}

