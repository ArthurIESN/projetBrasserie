package Exceptions.DeliveryTruck;

public class GetAllDeliveryTrucksException extends RuntimeException {
    private final String message;
    public GetAllDeliveryTrucksException() {
        this.message = "Error while getting all delivery trucks.";
    }
    public GetAllDeliveryTrucksException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return this.message;
    }
}
