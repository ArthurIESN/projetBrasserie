package Exceptions.Process;

import jdk.jshell.spi.ExecutionControl;

public class GetProcessWithSpecificType extends Exception {
    private static final String message = "Error while getting process with specific type";

    public GetProcessWithSpecificType() {
        super(message);
    }

    public String getMessage() {
        return message;
    }
}
