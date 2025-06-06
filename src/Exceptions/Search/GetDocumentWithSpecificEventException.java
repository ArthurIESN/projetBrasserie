package Exceptions.Search;

public class GetDocumentWithSpecificEventException extends Exception{
    private final String message;
    public GetDocumentWithSpecificEventException(){
        this.message = "Order supplier with specific event not found";
    }

    public String getMessage(){
        return message;
    }
}
