package Exceptions.DataAccess.Search;

public class SearchItemException
{
    private final String message;
    private final String sqlErrorMessage;

    public SearchItemException(String sqlErrorMessage)
    {
        this.sqlErrorMessage = sqlErrorMessage;
        this.message = "Error while searching for items.";
    }

    public String getMessage()
    {
        return this.message;
    }

    public String getSqlErrorMessage()
    {
        return this.sqlErrorMessage;
    }}
