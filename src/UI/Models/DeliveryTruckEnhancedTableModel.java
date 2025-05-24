package UI.Models;

import Model.DeliveryTruck.DeliveryTruck;
import UI.Components.EnhancedTable.AbstractEnhancedTableModel;

import java.util.ArrayList;

public class DeliveryTruckEnhancedTableModel extends AbstractEnhancedTableModel<DeliveryTruck>
{
    public DeliveryTruckEnhancedTableModel(ArrayList<DeliveryTruck> data) {
        super("Delivery Trucks", new String[]{"ID", "License Plate", "Fuel Quantity", "Mileage"}, data);
    }

    public DeliveryTruckEnhancedTableModel() {
        this(new ArrayList<>());
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DeliveryTruck deliveryTruck = getData().get(rowIndex);

        if (deliveryTruck == null) {
            return " - ";
        }

        return switch (columnIndex) {
            case 0 -> deliveryTruck.getId();
            case 1 -> deliveryTruck.getLicensePlate();
            case 2 -> deliveryTruck.getFuelQuantity();
            case 3 -> deliveryTruck.getMileage();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            case 2, 3 -> Float.class;
            default -> String.class;
        };
    }
}
