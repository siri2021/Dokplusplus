package be.sirisha.exam.validations;

import be.sirisha.exam.model.Employee;
import be.sirisha.exam.model.Project;
import be.sirisha.exam.model.WorkDone;

import javax.swing.*;
import java.util.regex.Pattern;

import static java.time.LocalDate.now;

public class Validator {
    public static boolean  validate(){
        return false;
    }
    public static boolean validateEmployee(Employee emp) {

            if (validatePhone(emp.getPhoneNumber())) {
                System.out.println("valid phonenumber");
                if (validatePhone(emp.getPhoneNumberIce())) {
                    System.out.println("valid ph ice");

                    if (emp.getSalary() > 0) {
                        System.out.println("salary positive");
                        if (emp.getDateOfBirth().plusYears(18).isBefore(now())) {

                            System.out.println("older than 18");return true;

                        }else System.out.println("minor");
                        return false;


                    }else System.out.println("negative salary");
                    return false;

                } else System.out.println("not a valid ice phone number");
              return false;
            } else System.out.println("not a valid phone number");
            return false;
    }

    public static boolean validatePhone(String  phoneNumber) {


            if(phoneNumber.startsWith("0")) {
                if (phoneNumber.matches("\\d{10}"))
                    return true;
                else
                    return false;}
            else
                return false;
    }



    //while adding a project things to keep in mind
    //make sure due dates are not before the start dates
    //do not allow projects which  started in the past
    //all prices must be above zero

    //project validators
    public static boolean validateProject(Project project) {
        if (project.getExpectedEndDate().isAfter(project.getStartDate())) {
            if (project.getStartDate().isAfter(now())) {
                if (project.getPrice() > 0) {//if3
                    System.out.println("valid price");
                    return true;
                }//if3
                else {
                    System.out.println("invalid price");
                    return false;
                }//else3
            }//if2
            else {
                System.out.println("projects should start in future ");
                return false;
            }//else2

        }//if1
        else {
            System.out.println("end date cant be before start date..u entered wrong date");
            return false;
        }//else1
    }//validate project

    public static boolean validateWorkDone(WorkDone workDone){

        if(workDone.getHoursWorked() > 0) {
            if (now().isAfter(workDone.getDate())) {
                System.out.println("valid entry");
                return true;
            } else {
                System.out.println("should be an ongoing project");
                return false;
            }
        }else {
            System.out.println("should have worked on the project for a few hours");
            return false;
        }
        }
    }//validator


