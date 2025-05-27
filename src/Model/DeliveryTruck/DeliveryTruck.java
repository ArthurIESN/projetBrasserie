package Model.DeliveryTruck;

import Exceptions.DeliveryTruck.DeliveryTruckException;

import java.util.Objects;

public class DeliveryTruck {
    private Integer id;
    private String licensePlate;
    private float fuelQuantity;
    private float mileage;

    public DeliveryTruck(Integer id,String licensePlate,float fuelQuantity,float mileage) throws DeliveryTruckException
    {
        setId(id);
        setLicensePlate(licensePlate);
        setFuelQuantity(fuelQuantity);
        setMileage(mileage);
    }

    private void setId(Integer id) throws DeliveryTruckException
    {
        if(id == null || id <= 0)
        {
            throw new DeliveryTruckException("ID cannot be null or less than or equal to 0");
        }

        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public String getLicensePlate(){
        return this.licensePlate;
    }

    public void setLicensePlate(String licensePlate) throws DeliveryTruckException {
        if (licensePlate == null || licensePlate.isEmpty()) {
            throw new DeliveryTruckException("License plate cannot be null or empty");
        }
        this.licensePlate = licensePlate;
    }

    public float getFuelQuantity()
    {
        return this.fuelQuantity;
    }

    public void setFuelQuantity(float fuelQuantity) throws DeliveryTruckException
    {
        if (fuelQuantity < 0)
        {
            throw new DeliveryTruckException("Fuel quantity cannot be negative");
        }
        this.fuelQuantity = fuelQuantity;
    }

    public float getMileage()
    {
        return this.mileage;
    }

    public void setMileage(float mileage) throws DeliveryTruckException
    {
        if (mileage < 0)
        {
            throw new DeliveryTruckException("Mileage cannot be negative");
        }
        this.mileage = mileage;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        DeliveryTruck deliveryTruck = (DeliveryTruck) obj;

        return Objects.equals(id, deliveryTruck.id) &&
                Objects.equals(licensePlate, deliveryTruck.licensePlate) &&
                Objects.equals(fuelQuantity, deliveryTruck.fuelQuantity) &&
                Objects.equals(mileage, deliveryTruck.mileage);
    }

    public static int hashCode(Integer id, String licensePlate, float fuelQuantity, float mileage)
    {
        return Objects.hash(id, licensePlate, fuelQuantity, mileage);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, licensePlate, fuelQuantity, mileage);
    }

    @Override
    public String toString(){
        return "DeliveryTruck{ \n" +
                "       id=" + id + ", \n" +
                "       licensePlate='" + licensePlate + '\n' +
                "       fuelQuantity=" + fuelQuantity + "\n" +
                "       mileage=" + mileage + "\n" +
                '}';
    }
}
