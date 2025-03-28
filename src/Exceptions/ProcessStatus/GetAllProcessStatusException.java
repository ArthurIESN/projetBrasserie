package Exceptions.ProcessStatus;

public class GetAllProcessStatusException extends Exception {
    private final String message;

    public GetAllProcessStatusException() {
        this.message = "An error occurred while trying to get all process statuses.";
    }

    public String getMessage() {
        return this.message;
    }
}
