package be.sirisha.exam.service;

import be.sirisha.exam.model.Employee;

import java.util.regex.Pattern;

import static java.time.LocalDate.now;

public class utilities {
    public static boolean  validate(){
       return false;
    }
    private boolean validateEmployee(Employee emp) {

        if(validatePhone(emp.getPhoneNumber()) &&
                validatePhone(emp.getPhoneNumberIce()) &&
                emp.getSalary() > 0 &&
                emp.getDateOfBirth().plusYears(18).isBefore(now()))
            System.out.println("valid employee");
        return true;
    }

    private boolean validatePhone(String  phoneNumber) {
        return Pattern.matches("^(0[1-9][0-9]{8})$", phoneNumber);
    }
}
