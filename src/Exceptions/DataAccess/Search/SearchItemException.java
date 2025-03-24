package Exceptions.DataAccess.Search;

public class SearchItemException extends Exception {
    private final String message;

    public SearchItemException()
    {
        this.message = "Error while searching for items.";
    }

    public String getMessage()
    {
        return this.message;
    }
}
