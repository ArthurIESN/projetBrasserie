package Exceptions.Search;

public class GetAllPaymentYearsException extends Exception {
  private final String message;

  public GetAllPaymentYearsException()
  {
    this.message = "Error while getting the min and max payment date.";
  }

  public String getMessage()
  {
    return this.message;
  }
}
