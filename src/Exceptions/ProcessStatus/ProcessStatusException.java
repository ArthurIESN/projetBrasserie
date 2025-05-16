package Exceptions.ProcessStatus;

public class ProcessStatusException extends Exception{
    private String message;
    public ProcessStatusException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
