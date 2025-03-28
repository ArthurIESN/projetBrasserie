package Exceptions.Employee;

public class GetAllEmployeesException extends Exception
{
    private final String message;

    public GetAllEmployeesException()
    {
        this.message = "An error occurred while trying to get all employees.";
    }

    public String getMessage()
    {
        return this.message;
    }
}
