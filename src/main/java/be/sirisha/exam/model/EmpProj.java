package be.sirisha.exam.model;

import java.time.LocalDate;

public class EmpProj {

    int empId;
    int projId;
    String empName;
    String projectDes;
    int hoursWorked;
    LocalDate date;
    float projCost;
    float salary;
    float profit;

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }


    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getProjId() {
        return projId;
    }

    public void setProjId(int projId) {
        this.projId = projId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public float getProjCost() {
        return projCost;
    }

    public void setProjCost(float projCost) {
        this.projCost = projCost;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getProjectDes() {
        return projectDes;
    }

    public void setProjectDes(String projectDes) {
        this.projectDes = projectDes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "EmpProj{" +
                "empId=" + empId +
                ", projId=" + projId +
                ", empName='" + empName + '\'' +
                ", projectDes='" + projectDes + '\'' +
                ", hoursWorked=" + hoursWorked +
                ", date=" + date +
                ", projCost=" + projCost +
                ", salary=" + salary +
                '}';
    }

    public String empDatePro() {
        return "EmpDateProj{" +
                "empId=" + empId +
                ", projId=" + projId +
                ", empName='" + empName + '\'' +
                ", projectDes='" + projectDes + '\'' +
                ", date=" + date + '}';
    }


    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public String projProfit() {
        return " PROJECT & PROFIT" +
                "                    " +
                 '\'' + projectDes + "                    " + profit ;
    }
}
