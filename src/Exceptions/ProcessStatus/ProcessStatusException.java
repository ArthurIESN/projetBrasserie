package Exceptions.ProcessStatus;

public class ProcessStatusException extends Exception{
    private final String message;
    public ProcessStatusException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
