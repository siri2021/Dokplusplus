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
            String query = "select  e.empId,CONCAT(e.FirstName, e.LastName) AS empName,p.ProjectId ,p.Description,w.HoursWorked,w.Date,e.Salary,p.Price" +
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
            empProj.setHoursWorked(rs.getInt(5));
            empProj.setDate(rs.getDate(6).toLocalDate());
            empProj.setSalary(rs.getFloat(7));
            empProj.setProjCost(rs.getFloat(8));
            empProjs.add(empProj);
        }
        return empProjs;
    }

    private float calculateProjectProfitability(EmpProj empProj) {
//subtract expenditureperproject() from price of the project

       float profit= (float) (empProj.getProjCost()-expenditurePerProj(empProj));

       return profit;
    }

    private float calculateHourlywages(EmpProj empProj) {
        /* Each employee works for 22days/month days and 8hours/day
        SALARY_PER _DAY= Salari/22
        SALARY_PER_HOUR=SALARY_PER _DAY/8
        */

         float salary=empProj.getSalary();

        float empSalaryPerDay = salary/22;

        float empSalaryPerHour = empSalaryPerDay/8;

        return empSalaryPerHour;
    }

    public List<EmpProj> projectsWithProfits() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        String query="Select distinct w.ProjectId , p.Price , e.Salary  from ProjectData p INNER JOIN WorkDone w on  w.ProjectId=p.ProjectId INNER JOIN  Employee e on e.EmpId=w.EmpId;";

        Statement st = con.createStatement();
        ResultSet rs = null;
        rs = st.executeQuery(query);
        List<EmpProj> empProjects=new ArrayList<>();
        while(rs.next()) {
            EmpProj  empProject =new EmpProj();

            empProject.setProjId(rs.getInt(1));
            empProject.setProjCost(rs.getFloat(2));
          //  empProject.setHoursWorked(rs.getInt(3));
           empProject.setProfit(calculateProjectProfitability(empProject));

            empProjects.add(empProject);
        }
    return empProjects;

        }



        public  float expenditurePerProj(EmpProj empProj) {
            float projExpenditure=0;
        try{
            int pid=empProj.getProjId();

            Connection con = ConnectionFactory.getConnection();
            String query="Select w.EmpId , e.Salary ,w.HoursWorked,p.Description from Employee e INNER JOIN WorkDone w on e.EmpId=w.EmpId INNER JOIN ProjectData p on p.ProjectId=w.ProjectId where w.ProjectId=?";
            PreparedStatement ps= con.prepareStatement(query);
            ps.setInt(1,pid);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {

                empProj.setEmpId(rs.getInt(1));
                empProj.setSalary(rs.getFloat(2));
                empProj.setHoursWorked(rs.getInt(3));
                empProj.setProjectDes(rs.getString(4));
                 int hoursWorked=empProj.getHoursWorked();
                projExpenditure += (hoursWorked * calculateHourlywages(empProj));

            }
            } catch (SQLException e) {
                e.printStackTrace();
            }
           // System.out.println(projExpenditure);
            return  projExpenditure;
        }


}

///follow the order methods are called and then solve