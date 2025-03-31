package Exceptions.Event;

public class GetEventsWithItemException extends Exception {
    private final String message;

    public GetEventsWithItemException(){
        this.message = "An error occurred while retrieving the events.";
    }

    public String getMessage(){
        return this.message;
    }
}
