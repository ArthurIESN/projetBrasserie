package Model.DeliveryTruck;

import java.util.HashMap;

public class MakeDeliveryTruck
{
    private static final HashMap<Integer, DeliveryTruck> deliveryTruckMap = new HashMap<>();

    public static DeliveryTruck getDeliveryTruck(Integer id, String plateNumber, float fuelQuantity, float mileage)
    {
        DeliveryTruck deliveryTruck = new DeliveryTruck(id, plateNumber, fuelQuantity, mileage);
        int deliveryTruckHash = deliveryTruck.hashCode();

        if(deliveryTruckMap.containsKey(deliveryTruckHash))
        {
            return deliveryTruckMap.get(deliveryTruckHash);
        }
        else
        {
            deliveryTruckMap.put(deliveryTruckHash, deliveryTruck);
            return deliveryTruck;
        }
    }
}
