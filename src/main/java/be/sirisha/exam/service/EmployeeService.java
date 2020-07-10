package be.sirisha.exam.service;

import be.sirisha.exam.data.EmployeeDAO;
import be.sirisha.exam.model.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private EmployeeDAO employeeDAO=new EmployeeDAO();
    public List<Employee> getAllEmployees() throws SQLException {
        return employeeDAO.getAllEmployees();
    }
    public void addEmployee(Employee emp){
        employeeDAO.addEmployee(emp);
    }




    public List<Employee> employeeByName(String firstName,String lastName) {
        return employeeDAO.employeeByName(firstName,lastName);
    }

    public void updateSalary(int empId, float salary) {
        employeeDAO.updateSalary(empId,salary);
    }

    public List<Employee> getBirthday() {
       return employeeDAO.getBirthday();
    }

    public List<Employee> getBirthdayBuddiesupcoming7Days() {
        return employeeDAO.getBirthdayBuddiesupcoming7Days();
    }
}
