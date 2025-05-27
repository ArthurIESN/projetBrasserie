package DataAccess.DeliveryTruck;

import Model.DeliveryTruck.DeliveryTruck;

public interface DeliveryTruckDataAccess
{
    void createDeliveryTruck(DeliveryTruck deliveryTruck);
    void updateDeliveryTruck(DeliveryTruck deliveryTruck);
    void deleteDeliveryTruck(Integer id);
    DeliveryTruck getDeliveryTruck(Integer id);
    java.util.ArrayList<DeliveryTruck> getAllDeliveryTrucks();
}
