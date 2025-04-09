package com.cjme.motorphsystem.util;

import com.cjme.motorphsystem.exception.InvalidEmployeeDataException;
import com.cjme.motorphsystem.model.Employee;

/**
 *
 * @author JustAMob
 */
public class ValidationUtils {
    public static void validateEmployeeData(Employee employee) throws InvalidEmployeeDataException {
        if (employee.getFirstName() == null || employee.getFirstName().isEmpty()) {
            throw new InvalidEmployeeDataException("First name is required.");
        }
        if (employee.getLastName() == null || employee.getLastName().isEmpty()) {
            throw new InvalidEmployeeDataException("Last name is required.");
        }
        if (employee.getBirthday() == null ) {
            throw new InvalidEmployeeDataException("Birthday is required.");
        }
        if (employee.getAddress() == null || employee.getAddress().isEmpty()) {
            throw new InvalidEmployeeDataException("Address is required.");
        }
        if (employee.getPhoneNum() == null || employee.getPhoneNum().isEmpty()) {
            throw new InvalidEmployeeDataException("Phone Number is required.");
        }
        if (employee.getSssNum() == null || employee.getSssNum().isEmpty()) {
            throw new InvalidEmployeeDataException("SSS Number is required.");
        }
        if (employee.getPagibigNum() == null || employee.getPagibigNum().isEmpty()) {
            throw new InvalidEmployeeDataException("Pagibig Number is required.");
        }
        if (employee.getPhilHealthNum() == null || employee.getPhilHealthNum().isEmpty()) {
            throw new InvalidEmployeeDataException("PhilHealth Number is required.");
        }        
        if (employee.getTinNum() == null || employee.getTinNum().isEmpty()) {
            throw new InvalidEmployeeDataException("Tin Number is required.");
        }         
        
        // Phone num given by motorph xxx-xxx-xxx phone num in reality xxxx-xxx-xxx
        String phone = employee.getPhoneNum();
        if (!phone.matches("\\d{3}-\\d{3}-\\d{3}") || !phone.matches("\\d{4}-\\d{3}-\\d{4}")) {
            throw new InvalidEmployeeDataException("Phone number must be in the format xxxx-xxx-xxxx.");
        }
  
        //SSS Num format, Incorporated the old and actual format
        String sssNum = employee.getSssNum();
        if (!sssNum.matches("\\d{2}-\\d{7}-\\d{1}")) {
            throw new InvalidEmployeeDataException("SSS number must be in the format xx-xxxxxxx-x.");
        }
        
        //Pagibig Num format, Incorporated the old and actual format
        String pagibigNum = employee.getPagibigNum();
        if (!pagibigNum.matches("\\d{3}-\\d{3}-\\d{3}-\\d{3}") || !pagibigNum.matches("\\d{4}-\\d{4}-\\d{4}")) {
            throw new InvalidEmployeeDataException("Pagibig number must be in the format xxxx-xxxx-xxxx.");
        }
        //Pagibig Num format, Incorporated the old and actual format
        String philHealthNum = employee.getPhilHealthNum();
        if (!philHealthNum.matches("\\d{12}") || !philHealthNum.matches("\\d{2}-\\d{9}-\\d{1}")) {
            throw new InvalidEmployeeDataException("PhilHealth number must be in the format xx-xxxxxxxxx-x.");
        }
        
        //Tin Num format, Incorporated the old and actual format
        String tinNum = employee.getTinNum();
        if (!tinNum.matches("\\d{12}") || !tinNum.matches("\\d{3}-\\d{3}-\\d{3}-\\d(3)")) {
            throw new InvalidEmployeeDataException("Tin number must be in the format xxx-xxx-xxx-xxx.");
        }
    }  
    
}
