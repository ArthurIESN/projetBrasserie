package Controller.DeliveryTruck;

import BusinessLogic.DeliveryTruck.DeliveryTruckManager;
import Model.DeliveryTruck.DeliveryTruck;

import java.util.ArrayList;

public class DeliveryTruckController
{
    private static final DeliveryTruckManager deliveryTruckManager = new DeliveryTruckManager();

    public static void createDeliveryTruck(DeliveryTruck deliveryTruck)
    {
        deliveryTruckManager.createDeliveryTruck(deliveryTruck);
    }

    public static void updateDeliveryTruck(DeliveryTruck deliveryTruck)
    {
        deliveryTruckManager.updateDeliveryTruck(deliveryTruck);
    }

    public static void deleteDeliveryTruck(Integer id)
    {
        deliveryTruckManager.deleteDeliveryTruck(id);
    }

    public static DeliveryTruck getDeliveryTruck(Integer id)
    {
        return deliveryTruckManager.getDeliveryTruck(id);
    }

    public static ArrayList<DeliveryTruck> getAllDeliveryTrucks()
    {
        return deliveryTruckManager.getAllDeliveryTrucks();
    }
}
