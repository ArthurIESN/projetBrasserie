package Exceptions.Document;

public class DeleteDocumentException extends Exception{
    private final String message;
    public DeleteDocumentException(String message) {
        this.message = message;
    }
    public DeleteDocumentException(){
        this.message = "An error occurred while deleting the document.";
    }
    @Override
    public String getMessage() {
        return message;
    }

}
