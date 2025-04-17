package Exceptions.Search;

public class GetMinMaxDatePaymentException extends Exception {
  private final String message;

  public GetMinMaxDatePaymentException()
  {
    this.message = "Error while getting the min and max payment date.";
  }

  public String getMessage()
  {
    return this.message;
  }
}
