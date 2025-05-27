package Exceptions.DocumentStatus;

public class GetAllDocumentStatusException extends Exception {
    private final String message;
    public GetAllDocumentStatusException()
    {
        this.message = "An error occurred while trying to get all document status.";
    }

    public String getMessage()
    {
        return this.message;
    }
}
