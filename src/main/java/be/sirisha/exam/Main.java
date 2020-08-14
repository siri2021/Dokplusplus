package be.sirisha.exam;

import be.sirisha.exam.model.EmpProj;
import be.sirisha.exam.model.Employee;
import be.sirisha.exam.model.Project;
import be.sirisha.exam.model.WorkDone;
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
public class Main {
    public static void main(String[] args) throws SQLException {
        int mainChoice;
        int subChoice = -1;

            do {
                showMenu();
                mainChoice = requestIntInput(0, 3);
                if (mainChoice != 0) {
                    showSubMenu(mainChoice);
                    subChoice = requestIntInput(0,10);
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
                    // accept values to create an employee object
                    Employee employee = new Employee();
                    Scanner keyboard = new Scanner(System.in);

                    System.out.println("Enter employee details \n");
                    System.out.println("EmpId:");
                    employee.setEmployeeId(keyboard.nextInt());

                    System.out.println("FirstName: ");
                    employee.setFirstName(keyboard.next());
                    System.out.println("LastName: ");
                    employee.setLastName(keyboard.next());
                    System.out.println("PhoneNumber(10-digit,starts with 0:)");
                    String phoneNumber = keyboard.next();
                    employee.setPhoneNumber(phoneNumber);

                    System.out.println("phoneNumberICE(10-digit,starts with 0:)");
                    String phoneNumberICE = keyboard.next();
                    employee.setPhoneNumberIce(phoneNumberICE);
                    System.out.println("DateOfBirth(yyyy-MM-dd):");
                    employee.setDateOfBirth(LocalDate.parse(keyboard.next()));
                    System.out.println("Salary:");
                    employee.setSalary(keyboard.nextFloat());

                    employeeService.addEmployee(employee);


                }
                if (subChoice == 3) {
                    System.out.println("Enter employees  first_name:");
                    Scanner keyboard = new Scanner(System.in);
                    String firstName = keyboard.next();

                    List<Employee> employeeByName = employeeService.employeeByFirstName(firstName);

                    employeeByName.forEach(System.out::println);

                }
                if(subChoice==4){
                    System.out.println("Enter employees  last_name:");
                    Scanner keyboard = new Scanner(System.in);
                    String lastName = keyboard.next();
                    List<Employee> employeeByName = employeeService.employeeByLastName(lastName);

                    employeeByName.forEach(System.out::println);
                }

                if (subChoice == 5) {
                    System.out.print("enter the details of emp whose salary you want to update:");
                    System.out.println("employee id");
                    Scanner keyboard = new Scanner(System.in);
                    int empId = keyboard.nextInt();
                    System.out.println("Salary:");
                    float salary = keyboard.nextFloat();
                    employeeService.updateSalary(empId, salary);

                }
                if (subChoice == 6) {
                    System.out.println("employees celebtating their birthday today:");
                    List<Employee> birthdaybuddies = employeeService.getBirthday();
                    //birthdaybuddies.forEach(System.out::println);
                    employeeService.greet(birthdaybuddies);
                }
                if (subChoice == 7) {
                    System.out.println("employees celebtating their birthday in coming 7 days");
                    List<Employee> birthdaybuddies = employeeService.getBirthdayBuddiesupcoming7Days();
                   birthdaybuddies.forEach(System.out::println);
                }
                if(subChoice == 8){
                    System.out.println("Enter the employeeID to be deleted:");
                    Scanner scanner=new Scanner(System.in);
                    int empIdToDelete=scanner.nextInt();
                    employeeService.deleteEmployee(empIdToDelete);
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

                else if (mainChoice == 3) {//workdone table
                WorkDoneService workDoneService = new WorkDoneService();
                if (subChoice == 1) {
                    List<WorkDone> workDone= workDoneService.workDoneList();
                    workDone.forEach(System.out::println);
                }
                if (subChoice == 2) {
                    //add in work done table
                    WorkDone workDone=new WorkDone();
                    Scanner scanner =new Scanner(System.in);
                    System.out.println("Enter employee project details ");
                    System.out.println("EmpId:");
                    workDone.setEmpId(scanner.nextInt());
                    System.out.println("ProjectId:");
                    workDone.setProjectId(scanner.nextInt());
                    System.out.println("Date:");
                    workDone.setDate(LocalDate.parse(scanner.next()));
                    System.out.println("Hours WORKED:");
                    workDone.setHoursWorked(scanner.nextFloat());
                    System.out.println("Description:");
                    workDone.setDescription(scanner.next());

                    workDoneService.addEmpProj(workDone);
                }
                if (subChoice == 3) {
                    //edit
                    WorkDone workDone=new WorkDone();
                    Scanner scanner=new Scanner(System.in);
                    System.out.println("give details to  update:");
                    System.out.println("EMPID:");
                    workDone.setEmpId(scanner.nextInt());
                    System.out.println("no of hours worked:");
                    workDone.setHoursWorked(scanner.nextInt());
                    workDoneService.updateEmpProj(workDone);

                }
                if (subChoice == 4) {
                    //delete
                    WorkDone workDone=new WorkDone();
                    Scanner scanner=new Scanner(System.in);
                    System.out.println("give details to  update:");
                    System.out.println("EMPID:");
                    workDone.setEmpId(scanner.nextInt());

                    workDoneService.delete(workDone);

                }
                if(subChoice==5){
                   //list of all projects with profitability
                   List<EmpProj> projectsWithProfits=workDoneService.projectsWithProfits();

                    projectsWithProfits.forEach(p-> System.out.println(p.projProfit()));
                }
                if (subChoice == 6) {
                    //list of top 3 employees per project

                    Scanner scanner=new Scanner(System.in);
                    System.out.println("enter the projectId(1-9):");
                    int projectId=scanner.nextInt();


                    List<EmpProj> employeesINAProject=workDoneService.showEmployeesInAProject(projectId);
                    employeesINAProject.forEach(System.out::println);
                }
                //need to work tomorrow
                if(subChoice== 7){//which employee worked  on which project and when
                    Scanner scanner=new Scanner(System.in);
                    System.out.println("Enter ProjectID(1-digit)");
                    int projectId=scanner.nextInt();
                    System.out.println("Enter EmployeeID(2digit)");
                     int empId=scanner.nextInt();
                    List<EmpProj> empProjList=workDoneService.showEmployeesInAProject(projectId,empId);
                    empProjList.forEach(System.out::println);
                }

            }//end of third main(if)

            }//end of method




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
                    System.out.println("3. Look for Employee by FirstName");
                    System.out.println("4. Look for Employee by LastName");
                    System.out.println("5. Edit Employee salary");
                    System.out.println("6. CHECK whose birthday is today and wish them");
                    System.out.println("7. CHECK WHOSE BIRTHDAY IS IN upcoming 7 days");
                    System.out.println("8.Delete");
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
                    System.out.println("1.show workdone table");
                    System.out.println("2.Add ");
                    System.out.println("3.update ");
                    System.out.println("4.DELETE ");
                    //check it must be existing employee and existing project(just enter empID and PROJECTid NO RESEARCH WHETHER THEY R THERE)
                    System.out.println("5.LIST OF all projects WITH PROFITABILITY");
                    System.out.println("6.LIST OF TOP 3 EMPLOYEES FOR A SPECIFIC PROJECT");
                    System.out.println("7.which employee worked at which project and when");
                    System.out.println("8.Delete");
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
                if (input < lower || input > upper) System.out.println("That's not a valid number");
            } while (input < lower || input > upper);

            return input;
        }


}




