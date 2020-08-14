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




    public List<Employee> employeeByFirstName(String firstName) {
        return employeeDAO.employeeByFirstName(firstName);
            }

    public List<Employee> employeeByLastName(String lastName) {
        return employeeDAO.employeeByLastName(lastName);
    }

    public void updateSalary(int empId, float salary) {
        employeeDAO.updateSalary(empId,salary);
    }

    public List<Employee> getBirthday() {
       return employeeDAO.getBirthday();
    }
    public void greet(List<Employee> employees){
        employeeDAO.greet(employees);
    }

    public List<Employee> getBirthdayBuddiesupcoming7Days() {
        return employeeDAO.getBirthdayBuddiesupcoming7Days();
    }

    public void deleteEmployee(int empIdToDelete) throws SQLException {
        employeeDAO.deleteEmp(empIdToDelete);
    }
}
