package Model.Employee;

import Model.EmployeeStatus.EmployeeStatus;

import java.util.Date;

public class Employee {
    private Integer id;
    private String lastName;
    private String firstName;
    private Date birthDate;
    private EmployeeStatus employeeStatus;

    public Employee (Integer id, String lastName, String firstName, Date birthDate,
                     EmployeeStatus employeeStatus)
    {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.employeeStatus = employeeStatus;
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    @Override
    public String toString() {
        return id + " - " + lastName + ", " + firstName;
    }

}
