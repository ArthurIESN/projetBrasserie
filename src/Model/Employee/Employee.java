package Model.Employee;

import Model.EmployeeStatus.EmployeeStatus;

import java.util.Date;
import java.util.Objects;

public class Employee {
    private Integer id;
    private String lastName;
    private String firstName;
    private Date birthDate;
    private String password;
    private EmployeeStatus employeeStatus;

    public Employee (Integer id, String lastName, String firstName, Date birthDate, String password,
                     EmployeeStatus employeeStatus)
    {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Employee employee = (Employee) obj;

        return Objects.equals(id, employee.id) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(birthDate, employee.birthDate) &&
                Objects.equals(employeeStatus, employee.employeeStatus);
    }

    public static int hashCode(Integer id, String lastName, String firstName, Date birthDate,
                                EmployeeStatus employeeStatus)
    {
        return Objects.hash(id, lastName, firstName, birthDate, employeeStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, birthDate, employeeStatus);
    }

    @Override
    public String toString() {
        return id + " - " + lastName + ", " + firstName;
    }

}
