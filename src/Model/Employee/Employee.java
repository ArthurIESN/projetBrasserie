package Model.Employee;

import Exceptions.Employee.EmployeeException;
import Model.EmployeeStatus.EmployeeStatus;

import java.util.Date;
import java.util.Objects;

public class Employee {
    private Integer id;
    private String lastName;
    private String firstName;
    private Date birthDate;
    private boolean isMarried;
    private String password;
    private EmployeeStatus employeeStatus;

    public Employee (Integer id, String lastName, String firstName, Date birthDate, boolean isMarried, String password,
                     EmployeeStatus employeeStatus) throws EmployeeException
    {
        setId(id);
        setLastName(lastName);
        setFirstName(firstName);
        setBirthDate(birthDate);
        this.isMarried = isMarried;
        setPassword(password);
        setEmployeeStatus(employeeStatus);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) throws EmployeeException
    {
        if(id == null || id <= 0)
        {
            throw new EmployeeException("ID cannot be null or less than 1");
        }
        else
        {
            this.id = id;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws EmployeeException
    {
        if(lastName == null || lastName.isEmpty())
        {
            throw new EmployeeException("Last name cannot be null or empty");
        }
        else
        {
            this.lastName = lastName;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws EmployeeException
    {
        if(firstName == null || firstName.isEmpty())
        {
            throw new EmployeeException("First name cannot be null or empty");
        }
        else
        {
            this.firstName = firstName;
        }
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) throws EmployeeException
    {
        if(birthDate == null)
        {
            throw new EmployeeException("Birth date cannot be null");
        }
        else
        {
            this.birthDate = birthDate;
        }
    }

    public boolean isMarried() {
        return isMarried;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) throws EmployeeException
    {
        if(employeeStatus == null)
        {
            throw new EmployeeException("Employee status cannot be null");
        }
        else
        {
            this.employeeStatus = employeeStatus;
        }
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
                Objects.equals(isMarried, employee.isMarried) &&
                Objects.equals(employeeStatus, employee.employeeStatus);
    }

    public static int hashCode(Integer id, String lastName, String firstName, Date birthDate, boolean isMarried,
                                EmployeeStatus employeeStatus)
    {
        return Objects.hash(id, lastName, firstName, birthDate, isMarried, employeeStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, birthDate, isMarried, employeeStatus);
    }

    @Override
    public String toString() {
        return id + " - " + lastName + ", " + firstName;
    }

}
