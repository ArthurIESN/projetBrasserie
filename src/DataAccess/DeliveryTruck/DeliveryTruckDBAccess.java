package DataAccess.DeliveryTruck;

import DataAccess.DataAccesUtils;
import Model.DeliveryTruck.DeliveryTruck;
import Model.DeliveryTruck.MakeDeliveryTruck;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryTruckDBAccess implements DeliveryTruckDataAccess
{

    public static DeliveryTruck makeDeliveryTruck(ResultSet resultSet) throws SQLException
    {
        if(!DataAccesUtils.hasColumn(resultSet, "delivery_truck.id")) return null;

        return MakeDeliveryTruck.getDeliveryTruck(
                resultSet.getInt("delivery_truck.id"),
                resultSet.getString("delivery_truck.license_plate"),
                resultSet.getFloat("delivery_truck.fuel_quantity"),
                resultSet.getFloat("delivery_truck.mileage")
        );
    }
}
