package com.cjme.motorphsystem.util;


import com.cjme.motorphsystem.exception.InvalidEmployeeDataException;

public class ValidationUtils {

    public static void validateEmployeeFields(
        String firstName,
        String lastName,
        String birthday,
        String address,
        String phoneNum,
        String sssNum,
        String pagibigNum,
        String philHealthNum,
        String tinNum
    ) throws InvalidEmployeeDataException {

        if (firstName == null || firstName.isEmpty()) {
            throw new InvalidEmployeeDataException("First name is required.");
        }

        if (lastName == null || lastName.isEmpty()) {
            throw new InvalidEmployeeDataException("Last name is required.");
        }

        if (birthday == null || birthday.isEmpty()) {
            throw new InvalidEmployeeDataException("Birthday is required.");
        }

        if (address == null || address.isEmpty()) {
            throw new InvalidEmployeeDataException("Address is required.");
        }

        if (phoneNum == null || phoneNum.isEmpty()) {
            throw new InvalidEmployeeDataException("Phone number is required.");
        }

        if (!phoneNum.matches("\\d{3}-\\d{3}-\\d{3}") &&
            !phoneNum.matches("\\d{4}-\\d{3}-\\d{4}")) {
            throw new InvalidEmployeeDataException("Phone number format is invalid.");
        }

        if (sssNum == null || !sssNum.matches("\\d{2}-\\d{7}-\\d{1}")) {
            throw new InvalidEmployeeDataException("SSS number format is invalid.");
        }

        if (pagibigNum == null || (
            !pagibigNum.matches("\\d{4}-\\d{4}-\\d{4}") &&
            !pagibigNum.matches("\\d{3}-\\d{3}-\\d{3}-\\d{3}")
        )) {
            throw new InvalidEmployeeDataException("Pagibig number format is invalid.");
        }

        if (philHealthNum == null || (
            !philHealthNum.matches("\\d{12}") &&
            !philHealthNum.matches("\\d{2}-\\d{9}-\\d{1}")
        )) {
            throw new InvalidEmployeeDataException("PhilHealth number format is invalid.");
        }

        if (tinNum == null || (
            !tinNum.matches("\\d{12}") &&
            !tinNum.matches("\\d{3}-\\d{3}-\\d{3}-\\d{3}")
        )) {
            throw new InvalidEmployeeDataException("TIN number format is invalid.");
        }
    }
}
