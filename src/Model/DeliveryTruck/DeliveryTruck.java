package Model.DeliveryTruck;

import java.util.Objects;

public class DeliveryTruck {
    private Integer id;
    private String licensePlate;
    private float fuelQuantity;
    private float mileage;

    public DeliveryTruck(Integer id,String licensePlate,float fuelQuantity,float mileage){
        setId(id);
        this.licensePlate = licensePlate;
        this.fuelQuantity = fuelQuantity;
        this.mileage = mileage;
    }

    private void setId(Integer id)
    {
        if(id == null || id <= 0)
        {
            id = null;
        }
        else
        {
            this.id = id;
        }
    }

    public Integer getId(){
        return this.id;
    }

    public String getLicensePlate(){
        return this.licensePlate;
    }

    public float getFuelQuantity(){
        return this.fuelQuantity;
    }

    public float getMileage(){
        return this.mileage;
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
