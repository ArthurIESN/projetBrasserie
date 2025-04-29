package BusinessLogic.DeliveryTruck;

import DataAccess.DeliveryTruck.DeliveryTruckDBAccess;
import DataAccess.DeliveryTruck.DeliveryTruckDataAccess;
import Model.DeliveryTruck.DeliveryTruck;

import java.util.ArrayList;

public class DeliveryTruckManager
{
    private final DeliveryTruckDataAccess deliveryTruckDataAccess = new DeliveryTruckDBAccess();

    public void createDeliveryTruck(DeliveryTruck deliveryTruck)
    {
        deliveryTruckDataAccess.createDeliveryTruck(deliveryTruck);
    }

    public void updateDeliveryTruck(DeliveryTruck deliveryTruck)
    {
        deliveryTruckDataAccess.updateDeliveryTruck(deliveryTruck);
    }

    public void deleteDeliveryTruck(Integer id)
    {
        deliveryTruckDataAccess.deleteDeliveryTruck(id);
    }

    public DeliveryTruck getDeliveryTruck(Integer id)
    {
        return deliveryTruckDataAccess.getDeliveryTruck(id);
    }

    public ArrayList<DeliveryTruck> getAllDeliveryTrucks()
    {
        return deliveryTruckDataAccess.getAllDeliveryTrucks();
    }
}
