package be.sirisha.exam.data;

import be.sirisha.exam.model.Employee;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import static java.time.LocalDate.now;


public class EmployeeDAO {
    public List<Employee> getAllEmployees() throws SQLException {
        //1.create connection
        Connection con = ConnectionFactory.getConnection();
        //2.send query
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM Employee");
        //3.parse query results
        return parsedResultSet(rs);
    }

        private List<Employee> parsedResultSet(ResultSet rs) throws SQLException {
            List<Employee> Employees = new ArrayList< >();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("EmpId"));
                employee.setFirstName(rs.getString("FirstName"));
                employee.setLastName(rs.getString("LastName"));
                employee.setPhoneNumber(rs.getString("PhoneNumber"));
                employee.setPhoneNumberIce(rs.getString("PhoneNumberICE"));
                employee.setDateOfBirth(rs.getDate("DateOfBirth").toLocalDate());
                employee.setSalary(rs.getFloat("Salary"));
                Employees.add(employee);
            }
            return Employees;
            //4.result
        }

    public void addEmployee(Employee emp)  {

                try {

                    Connection conn = ConnectionFactory.getConnection();

                    String sql="INSERT INTO Employee VALUES(?,?,?,?,?,?,?)";
                    System.out.println(sql);
                    try {
                        PreparedStatement ps = conn.prepareStatement(sql);
                        System.out.println("im in try " + sql);
                        //if(validateEmployee(emp)) {
                        ps.setInt(1, emp.getEmployeeId());

                        ps.setString(2, emp.getFirstName());

                        ps.setString(3, emp.getLastName());

                        ps.setString(4, emp.getPhoneNumber());

                        ps.setString(5, emp.getPhoneNumberIce());

                        ps.setDate(6, Date.valueOf(emp.getDateOfBirth()));

                        ps.setFloat(7, emp.getSalary());

                        ps.executeUpdate();

                        //}
                    }
                    catch (SQLException e) {
                    e.printStackTrace();
                     }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
    }




public List<Employee> employeeByName(String firstName, String lastName) {
        try {
        Connection conn=ConnectionFactory.getConnection();
        String sql="SELECT * FROM Employee WHERE FirstName LIKE ? OR LastName LIKE ? ;" ;

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1,firstName);
        ps.setString(2,lastName);
        ResultSet rs=ps.executeQuery();
        return parsedResultSet(rs);

        } catch (SQLException e) {
        e.printStackTrace();
        return Collections.emptyList();
        }
        }


    public void updateSalary(int empId, float salary) {
        System.out.println("im in update");
        try {
            Connection con = ConnectionFactory.getConnection();
            String sql="UPDATE Employee SET Salary = ? where EmpId = ? ;" ;
            System.out.println(sql);
        PreparedStatement ps=con.prepareStatement(sql);
            System.out.println(sql);
        ps.setFloat(1, salary);
            System.out.println(sql);
        ps.setInt(2,empId);
            System.out.println(sql);
        ps.executeUpdate();
            System.out.println("update done");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Employee> getBirthday() {

        try {
            Connection conn = ConnectionFactory.getConnection();

        String sql="SELECT * FROM Employee WHERE MONTH(DateOfBirth)= ? AND DAY(DateOfBirth)=?" ;
        PreparedStatement ps= conn.prepareStatement(sql);
        ps.setInt(1, now().getMonthValue());
            ps.setInt(2,now().getDayOfMonth());
        ResultSet rs=ps.executeQuery();
        return parsedResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Employee> getBirthdayBuddiesupcoming7Days() {
        try {
            Connection conn = ConnectionFactory.getConnection();

            String sql= "SELECT * FROM Employee where DATE_FORMAT(DateOfBirth, '%m-%d') >= DATE_FORMAT(CURDATE(), '%m-%d') and DATE_FORMAT(DateOfBirth, '%m-%d') <= DATE_FORMAT((CURDATE() + INTERVAL +7 DAY), '%m-%d') " ;
            Statement statement= conn.createStatement();

            ResultSet rs=statement.executeQuery(sql);
            return parsedResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }
}





