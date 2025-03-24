package Exceptions.DataAccess.Search;

public class GetMinMaxItemQuantityAndPriceException extends Exception {
    private final String message;
    private final String sqlErrorMessage;

    public GetMinMaxItemQuantityAndPriceException(String sqlErrorMessage)
    {
        this.sqlErrorMessage = sqlErrorMessage;
        this.message = "Error while getting the min and max item quantity and price.";
    }

    public String getMessage()
    {
        return this.message;
    }

    public String getSqlErrorMessage()
    {
        return this.sqlErrorMessage;
    }
}
