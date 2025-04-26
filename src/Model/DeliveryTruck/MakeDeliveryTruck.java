package Model.DeliveryTruck;

import java.util.HashMap;

public class MakeDeliveryTruck
{
    private static final HashMap<Integer, DeliveryTruck> deliveryTruckMap = new HashMap<>();

    public static DeliveryTruck getDeliveryTruck(Integer id, String plateNumber, float fuelQuantity, float mileage)
    {

        int deliveryTruckHash = DeliveryTruck.hashCode(id, plateNumber, fuelQuantity, mileage);

        if(deliveryTruckMap.containsKey(deliveryTruckHash))
        {
            return deliveryTruckMap.get(deliveryTruckHash);
        }
        else
        {
            DeliveryTruck deliveryTruck = new DeliveryTruck(id, plateNumber, fuelQuantity, mileage);
            deliveryTruckMap.put(deliveryTruckHash, deliveryTruck);
            return deliveryTruck;
        }
    }
}
