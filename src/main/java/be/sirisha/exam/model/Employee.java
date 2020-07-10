package be.sirisha.exam.model;

import java.sql.Date;
import java.time.LocalDate;

public class Employee {
    private int EmployeeId;
    private String FirstName;
    private String LastName;
    private String PhoneNumber;
    private String PhoneNumberIce;
    private LocalDate DateOfBirth;
    private float Salary;

    public String getSingleLine(){
        return "Employee{" +
                "EmployeeId=" + EmployeeId +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmployeeId=" + EmployeeId +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", PhoneNumberIce='" + PhoneNumberIce + '\'' +
                ", DateOfBirth=" + DateOfBirth +
                ", Salary=" + Salary +
                '}';
    }
    public String empSal(){
        return "EmpSal{"+
                "EmployeeId=" + EmployeeId +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Salary=" + Salary +
                '}';
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String  getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }


    public String getPhoneNumberIce() {
        return PhoneNumberIce;
    }

    public void setPhoneNumberIce(String phoneNumberIce) {
        PhoneNumberIce = phoneNumberIce;
    }

    public LocalDate getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public float getSalary() {
        return Salary;
    }

    public void setSalary(float salary) {
        Salary = salary;
    }



    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }


}
