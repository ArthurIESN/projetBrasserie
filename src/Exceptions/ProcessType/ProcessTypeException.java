package Exceptions.ProcessType;

public class ProcessTypeException extends Exception{
    private final String message;
    public ProcessTypeException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
