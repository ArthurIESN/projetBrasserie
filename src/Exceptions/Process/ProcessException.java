package Exceptions.Process;

public class ProcessException extends Exception {
   private final String message;
    public ProcessException(String message) {
         this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
