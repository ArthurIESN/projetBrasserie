package DataAccess.DeliveryTruck;

import DataAccess.DataAccesUtils;
import DataAccess.DatabaseConnexion;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DeliveryTruck.GetAllDeliveryTrucksException;
import Model.DeliveryTruck.DeliveryTruck;
import Model.DeliveryTruck.MakeDeliveryTruck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryTruckDBAccess implements DeliveryTruckDataAccess
{

    @Override
    public void createDeliveryTruck(DeliveryTruck deliveryTruck) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateDeliveryTruck(DeliveryTruck deliveryTruck) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteDeliveryTruck(Integer id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public DeliveryTruck getDeliveryTruck(Integer id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ArrayList<DeliveryTruck> getAllDeliveryTrucks() {
        String query = "SELECT * FROM delivery_truck";
        try{
            Connection databaseConnection = DatabaseConnexion.getInstance();
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<DeliveryTruck> deliveryTrucks = new ArrayList<>();
            while(resultSet.next()){
                DeliveryTruck deliveryTruck = makeDeliveryTruck(resultSet);
                if(deliveryTruck != null) {
                    deliveryTrucks.add(deliveryTruck);
                }
            }

            return deliveryTrucks;
        }catch (SQLException | DatabaseConnectionFailedException e){
            System.err.println(e.getMessage());
            throw new GetAllDeliveryTrucksException();
        }
    }

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
