package be.sirisha.exam;

import be.sirisha.exam.model.EmpProj;
import be.sirisha.exam.model.Employee;
import be.sirisha.exam.model.Project;
import be.sirisha.exam.service.EmployeeService;
import be.sirisha.exam.service.ProjectService;
import be.sirisha.exam.service.WorkDoneService;

import java.sql.*;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
//VERSION1.2 with project-profit list
public class
        Main {

    public static void main(String[] args) throws SQLException {
        int mainChoice;
        int subChoice = -1;

            do {
                showMenu();
                 mainChoice = requestIntInput(0, 3);

                 if (mainChoice != 0) {
                    showSubMenu(mainChoice);
                    subChoice = requestIntInput(0, 6);

                    handleUserChoice(mainChoice, subChoice);
                }
            } while (mainChoice != 0 && subChoice != 0);
        }


        private static void handleUserChoice(int mainChoice, int subChoice) throws SQLException {
            if (mainChoice == 1) {
                EmployeeService employeeService = new EmployeeService();
                if (subChoice == 1) {
                    // show all employees
                    List<Employee> employees = null;

                    try {
                        employees = employeeService.getAllEmployees();
                        employees.forEach(System.out::println);
                    } catch (SQLException ignored) {
                        System.out.println("Problems with db...");
                    }

                }
                if (subChoice == 2) {
                    // EmployeeService employeeService=new EmployeeService();
                    Employee employee = new Employee();
                    Scanner keyboard = new Scanner(System.in);

                    System.out.println("Enter employee details \n");
                    System.out.println("EmpId:");
                    employee.setEmployeeId(keyboard.nextInt());

                    System.out.println("FirstName: ");
                    employee.setFirstName(keyboard.next());
                    System.out.println("LastName: ");
                    employee.setLastName(keyboard.next());
                    System.out.println("PhoneNumber:");
                    String phoneNumber = keyboard.next();
                    employee.setPhoneNumber(phoneNumber);

                    System.out.println("phoneNumberICE:");
                    String phoneNumberICE = keyboard.next();
                    employee.setPhoneNumberIce(phoneNumberICE);
                    System.out.println("DateOfBirth:");
                    employee.setDateOfBirth(LocalDate.parse(keyboard.next()));
                    System.out.println("Salary:");
                    employee.setSalary(keyboard.nextFloat());

                    employeeService.addEmployee(employee);


                }
                if (subChoice == 3) {
                    System.out.println("Enter employees  first_name:");
                    Scanner keyboard = new Scanner(System.in);
                    String firstName = keyboard.next();
                    System.out.println("Enter employees  last_name:");
                    String lastName = keyboard.next();
                    System.out.println("checking the table");
                    List<Employee> employeeByName = employeeService.employeeByName(firstName, lastName);

                    employeeByName.forEach(System.out::println);

                }

                if (subChoice == 4) {
                    System.out.print("enter the details of emp whose salary you want to update:");
                    System.out.println("employee id");
                    Scanner keyboard = new Scanner(System.in);
                    int empId = keyboard.nextInt();
                    System.out.println("Salary:");
                    float salary = keyboard.nextFloat();
                    employeeService.updateSalary(empId, salary);

                }
                if (subChoice == 5) {
                    System.out.println("employees celebtating their birthday today:");
                    List<Employee> birthdaybuddies = employeeService.getBirthday();
                    birthdaybuddies.forEach(System.out::println);

                }
                if (subChoice == 6) {
                    System.out.println("employees celebtating their birthday in coming 7 days");
                    List<Employee> birthdaybuddies = employeeService.getBirthdayBuddiesupcoming7Days();
                    birthdaybuddies.forEach(System.out::println);

                }
            }


                else if (mainChoice == 2) {
                        ProjectService projectService = new ProjectService();
                        if (subChoice == 1) {
                            // show all projects
                            List<Project> projects =null ;
                            projects=projectService.getAllProjects();
                            projects.forEach(System.out::println);
                        }
                        if(subChoice==2) {
                            try{
                            Project project = new Project();
                            //ADD PROJECT
                            Scanner scan = new Scanner(System.in);
                            System.out.println("ProjectID:");
                            project.setProjectId(scan.nextInt());
                                System.out.println("description:");
                                project.setDescription(scan.next());
                            System.out.println("startdate:");
                            project.setStartDate(LocalDate.parse(scan.next()));

                            System.out.println("Price:");
                            project.setPrice(scan.nextFloat());
                            System.out.println("Expected end date:");
                            project.setExpectedEndDate(LocalDate.parse(scan.next()));

                            projectService.addProject(project);
                        } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        }
                         if (subChoice == 3) {
                            List<Project> projects=projectService.showProjectsStartingToday();
                            projects.forEach(System.out::println);
                         }
                         if(subChoice==4){
                             List<Project> projects=projectService.showCurrentProjects();
                             projects.forEach(System.out::println);
                         }
                 }//end of second main(if)
            else if (mainChoice == 3) {
                WorkDoneService workDoneService = new WorkDoneService();
                if (subChoice == 1) {
                    List<EmpProj> empProj = workDoneService.emp_proj();
                    empProj.forEach(System.out::println);
                }
                if (subChoice == 2) {
                    //add in work done table

                }
                if (subChoice == 3) {
                    //edit
                }
                if (subChoice == 4) {
                    //delete
                }
                if(subChoice==5){
                   //list of all projects with profitability
                   List<EmpProj> projectsWithProfits=workDoneService.projetsWithProfits();
                    //beers.forEach(b -> System.out.println(b.getSingleLine()));
                    projectsWithProfits.forEach(p-> System.out.println(p.projProfit()));
                }
                if (subChoice == 6
                ) {
                    //list of top 3 employees per project

                }



            }//end of third main(if)

            }//enf of method




    //MAIN MENU
        private static void showMenu() {
                System.out.println("0. Exit");
                System.out.println("1. Employees");
                System.out.println("2. ProjectData");
                System.out.println("3. WorkDone");
        }

        private static void showSubMenu( int choice) {
                if (choice == 1) {
                    System.out.println("0. Exit");
                    System.out.println("1. Show all Employees");
                    System.out.println("2. Add Employee");
                    //while adding employees distinguish between right and wrong phone numbers
                    //salaries should always be positive
                    //hire employees who are at least 18 or going to be 18
                    System.out.println("3. Look for Employee by Name");
                    System.out.println("4. Edit Employee salary");
                    System.out.println("5. CHECK whosebirthday is today and wish them");
                    System.out.println("6. CHECK WHOSE BIRTHDAY IS IN upcoming 7 days");
                }

                if (choice == 2) {
                    System.out.println("0.EXIT");
                    System.out.println("1. Show all Projects");
                    System.out.println("2.Add Project");
                    //while adding a project things to keep in mind
                    //make sure due dates are not before the start dates
                    //do not allow projects which  started in the past
                    //all prices must be above zero
                    System.out.println("3.show all projects that are starting today");
                    System.out.println("4.see what projects are going on");
                }


                if (choice == 3) {
                    System.out.println("0.EXIT");
                    System.out.println("1. which employee worked at which project and when");
                    System.out.println("2.Add ");
                    System.out.println("3.update ");
                    System.out.println("4.DELETE ");
                    //check it must be existing employee and existing project(just enter empID and PROJECTid NO RESEARCH WHETHER THEY R THERE)
                    System.out.println("5.LIST OF all projects WITH PROFITABILITY");
                    System.out.println("6.LIST OF TOP 3 EMPLOYEES FOR A SPECIFIC PROJECT");
                }
        }

        private static int requestIntInput ( int lower, int upper){
            Scanner scanner = new Scanner(System.in);
            int input = -1;
            do {
                try {
                    System.out.print("Make a choice: ");
                    input = scanner.nextInt();
                } catch (InputMismatchException e) {
                    input = -1;
                }
                scanner.nextLine();
                if (input < lower || input > upper) System.out.println("That's not a valid number, bruh");
            } while (input < lower || input > upper);

            return input;
        }

        private static void printResult (List < String > result) {
            result.forEach(System.out::println);
        }
}




