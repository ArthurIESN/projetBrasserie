package Exceptions.Search;

public class GetAllPaymentYearsException extends Exception {
  private final String message;

  public GetAllPaymentYearsException()
  {
    this.message = "Error while getting all payments dates.";
  }

  public String getMessage()
  {
    return this.message;
  }
}
