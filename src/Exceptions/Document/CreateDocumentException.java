package Exceptions.Document;

public class CreateDocumentException extends Exception {
    private final String message;

    public CreateDocumentException(String message) {
    this.message = message;
    }

    public CreateDocumentException() {
        this("Error while creating the document.");
    }

    public String getMessage() {
        return message;
    }
}
