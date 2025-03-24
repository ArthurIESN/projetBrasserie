package Exceptions.DataAccess.Search;

public class GetMinMaxItemQuantityAndPriceException extends Exception {
    private final String message;

    public GetMinMaxItemQuantityAndPriceException()
    {
        this.message = "Error while getting the min and max item quantity and price.";
    }

    public String getMessage()
    {
        return this.message;
    }
}
