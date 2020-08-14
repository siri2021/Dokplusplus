package be.sirisha.exam.model;

import java.time.LocalDate;

public class WorkDone {
    private int EmpId;
    private int ProjectId;
    private LocalDate Date;
    private float HoursWorked;
    private String Description;

    public int getEmpId() {
        return EmpId;
    }

    public void setEmpId(int empId) {
        EmpId = empId;
    }

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public float getHoursWorked() {
        return HoursWorked;
    }

    public void setHoursWorked(float hoursWorked) {
        HoursWorked = hoursWorked;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }



    public void setDate(String next) {
    }

    @Override
    public String toString() {
        return "WorkDone{" +
                "EmpId=" + EmpId +
                ", ProjectId=" + ProjectId +
                ", Date=" + Date +
                ", HoursWorked=" + HoursWorked +
                ", Description='" + Description + '\'' +
                '}';
    }
}
