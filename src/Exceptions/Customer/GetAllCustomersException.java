package Exceptions.Customer;

public class GetAllCustomersException extends Exception
{
    private final String message;

    public GetAllCustomersException()
    {
        this.message = "An error occurred while trying to get all customers.";
    }

    public String getMessage()
    {
        return this.message;
    }
}
