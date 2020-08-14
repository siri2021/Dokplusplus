package be.sirisha.exam.data;

import be.sirisha.exam.model.EmpProj;
import be.sirisha.exam.model.Employee;
import be.sirisha.exam.model.Project;
import be.sirisha.exam.model.WorkDone;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static be.sirisha.exam.validations.Validator.validateWorkDone;

public class WorkDoneDAO {

    public List<WorkDone> workDoneList() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql="SELECT * from WorkDone ";
        PreparedStatement ps=conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        List<WorkDone> workDoneList=new ArrayList<>();
        while(rs.next()) {
            WorkDone  workDone =new WorkDone();
            workDone.setEmpId(rs.getInt(1));
            workDone.setProjectId(rs.getInt(2));
            workDone.setDate(rs.getDate(3).toLocalDate());
            workDone.setHoursWorked(rs.getInt(4));
            workDone.setDescription(rs.getString(5));
            workDoneList.add(workDone);
        }
        return workDoneList;
    }

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

    public void addEmpProject(WorkDone workDone) throws SQLException {

        if(validateWorkDone(workDone)) {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO WorkDone VALUES(?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, workDone.getEmpId());
            ps.setInt(2, workDone.getProjectId());
            ps.setDate(3, Date.valueOf(workDone.getDate()));
            ps.setFloat(4, workDone.getHoursWorked());
            ps.setString(5, workDone.getDescription());
            System.out.println("added");
            ps.executeUpdate();
        }else{
            System.out.println("not valid");
        }
    }

    public void updateEmpProject(WorkDone workDone) throws SQLException{
        Connection conn = ConnectionFactory.getConnection();
        String sql="UPDATE WorkDone SET HoursWorked=? where EmpId =?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setFloat(1,workDone.getHoursWorked());
        ps.setInt(2,workDone.getEmpId());
        int rowsUpdated=ps.executeUpdate();
        System.out.println("rowsUpdated");
    }

    public void delete(WorkDone workDone) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql="DELETE from WorkDone where EmpId =?  ;";
        System.out.println(sql);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,workDone.getEmpId());
        int rowCount= ps.executeUpdate();
        System.out.println(rowCount+"rows deleted");


    }

    public List<EmpProj> showEmployeesInAProject(int projectId) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql="SELECT  EmpId,ProjectId , HoursWorked from WorkDone where ProjectId=? ORDER BY  HoursWorked desc";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1,projectId);
        ResultSet rs=ps.executeQuery();
        List<EmpProj> empProjects=new ArrayList<>();
        while(rs.next()) {
            EmpProj  empProject =new EmpProj();
            empProject.setEmpId(rs.getInt(1));
            empProject.setProjId(rs.getInt(2));

            empProject.setHoursWorked(rs.getInt(3));

            empProjects.add(empProject);
        }
        return empProjects;
    }
    public List<EmpProj> showEmployeesInAProject(int projectId,int empId) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql="SELECT  EmpId,ProjectId , HoursWorked from WorkDone where ProjectId=? , EmpId=?  ORDER BY  HoursWorked desc";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1,projectId);
        ps.setInt(2,empId);
        ResultSet rs=ps.executeQuery();
        List<EmpProj> empProjects=new ArrayList<>();
        while(rs.next()) {
            EmpProj  empProject =new EmpProj();
            empProject.setEmpId(rs.getInt(1));
            empProject.setProjId(rs.getInt(2));

            empProject.setHoursWorked(rs.getInt(3));

            empProjects.add(empProject);
        }
        return empProjects;
    }





}
