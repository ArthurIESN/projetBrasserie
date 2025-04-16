package Exceptions.DocumentStatus;

public class GetAllDocumentStatusException extends RuntimeException {
    private String messge;
    public GetAllDocumentStatusException() {
        this.messge = "An error occurred while trying to get all document status.";
    }

    public String getMessage() {
        return this.messge;
    }
}
