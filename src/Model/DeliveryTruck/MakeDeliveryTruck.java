package Model.DeliveryTruck;

import java.util.HashMap;

public class MakeDeliveryTruck
{
    private static final HashMap<Integer, DeliveryTruck> deliveryTruckMap = new HashMap<>();

    public static DeliveryTruck getDeliveryTruck(Integer id, String plateNumber, float fuelQuantity, float mileage)
    {
        if(deliveryTruckMap.containsKey(id))
        {
            return deliveryTruckMap.get(id);
        }
        else
        {
            DeliveryTruck deliveryTruck = new DeliveryTruck(id, plateNumber, fuelQuantity, mileage);
            deliveryTruckMap.put(id, deliveryTruck);
            return deliveryTruck;
        }
    }
}
