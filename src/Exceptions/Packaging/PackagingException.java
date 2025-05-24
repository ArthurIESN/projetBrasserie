package Exceptions.Packaging;

public class PackagingException extends RuntimeException {
  private final String message;
    public PackagingException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
