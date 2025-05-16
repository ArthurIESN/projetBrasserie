package Exceptions.ProcessType;

public class ProcessTypeException extends Exception{
    private String message;
    public ProcessTypeException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
