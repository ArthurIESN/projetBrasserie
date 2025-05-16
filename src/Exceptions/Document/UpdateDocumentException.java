package Exceptions.Document;

public class UpdateDocumentException extends RuntimeException {
    private final String message;
    public UpdateDocumentException(String message) {
        this.message = message;
    }
    public UpdateDocumentException() {
        this.message = "Error while updating document.";
    }
    @Override
    public String getMessage() {
        return this.message;
    }
}
