package be.sirisha.exam.model;

import java.time.LocalDate;

public class Project {
   private int  ProjectId ;
   private LocalDate StartDate  ;
    private String Description ;
    private float Price;
    private LocalDate ExpectedEndDate;

    @Override
    public String toString() {
        return "Project{" +
                "ProjectId=" + ProjectId +
                ", StartDate=" + StartDate +
                ", Description='" + Description + '\'' +
                ", Price=" + Price +
                ", ExpectedEndDate=" + ExpectedEndDate +
                '}';
    }

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }

    public LocalDate getStartDate() {
        return StartDate;
    }

    public void setStartDate(LocalDate startDate) {
        StartDate = startDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public LocalDate getExpectedEndDate() {
        return ExpectedEndDate;
    }

    public void setExpectedEndDate(LocalDate expectedEndDate) {
        ExpectedEndDate = expectedEndDate;
    }


}
