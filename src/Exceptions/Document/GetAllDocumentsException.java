package Exceptions.Document;

public class GetAllDocumentsException extends RuntimeException {
    private final String message;
    public GetAllDocumentsException() {
        this.message = "Error while getting all documents.";
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
